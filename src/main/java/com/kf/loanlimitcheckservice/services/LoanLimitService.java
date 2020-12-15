package com.kf.loanlimitcheckservice.services;

import com.kf.loanlimitcheckservice.domains.LoanLimitDomain;
import com.kf.loanlimitcheckservice.request.LoanLimitCheckRequest;
import com.kf.loanlimitcheckservice.response.CreditScoreResponse;
import com.kf.loanlimitcheckservice.response.LoanLimitCheckResponse;
import com.kf.loanlimitcheckservice.response.LoanLimitCheckResultEnum;
import com.kf.loanlimitcheckservice.util.Interpolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RefreshScope
public class LoanLimitService {

    @Autowired
    CreditScoreService creditScoreService;

    @Autowired
    LoanLimitDomain loanLimitDomain;

    @Autowired
    Environment env;

    @Value("${loanlimit.multiplier: 4.0}")
    private Double multiplier;


    public LoanLimitCheckResponse calculateLoanLimit(LoanLimitCheckRequest loanLimitCheckRequest) {
        CreditScoreResponse creditScoreResponse = creditScoreService.getCreditScore(loanLimitCheckRequest.getIdentificationNumber());
        LoanLimitCheckResponse loanLimitCheckResponse;
        if (creditScoreResponse.getExist().equalsIgnoreCase("BULUNAMADI")) {
            loanLimitCheckResponse =
                    LoanLimitCheckResponse
                            .builder()
                            .identificationNumber(loanLimitCheckRequest.getIdentificationNumber())
                            .loanLimitCheckResultEnum(LoanLimitCheckResultEnum.getResult(creditScoreResponse.getExist()))
                            .build();
        } else {
            Double limit = loanLimitDomain.getLimit(loanLimitCheckRequest.getIncome(), creditScoreResponse.getCreditScore(), multiplier);
            LoanLimitCheckResultEnum rsEnum;
            if (limit <= 0) {
                rsEnum = LoanLimitCheckResultEnum.RED;
            } else {
                rsEnum = LoanLimitCheckResultEnum.ONAY;
            }
            loanLimitCheckResponse =
                    LoanLimitCheckResponse
                            .builder()
                            .identificationNumber(loanLimitCheckRequest.getIdentificationNumber())
                            .loanLimitCheckResultEnum(rsEnum)
                            .limit(limit)
                            .creditScore(creditScoreResponse.getCreditScore())
                            .build();
        }
        return loanLimitCheckResponse;
    }


}

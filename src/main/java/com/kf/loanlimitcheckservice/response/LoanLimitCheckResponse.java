package com.kf.loanlimitcheckservice.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanLimitCheckResponse {

    private Long identificationNumber;
    private LoanLimitCheckResultEnum loanLimitCheckResultEnum;
    private Double limit;
    private Integer creditScore;

}

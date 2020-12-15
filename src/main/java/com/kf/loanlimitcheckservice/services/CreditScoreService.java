package com.kf.loanlimitcheckservice.services;

import com.kf.loanlimitcheckservice.response.CreditScoreResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CreditScoreService {

    @Autowired
    private RestTemplate restTemplate;

    public CreditScoreResponse getCreditScore(Long identityNumber){
        CreditScoreResponse creditScoreResponse =
                restTemplate.getForObject("http://credit-score-service/credit-score/" + identityNumber,CreditScoreResponse.class);
        return creditScoreResponse;
    }

}

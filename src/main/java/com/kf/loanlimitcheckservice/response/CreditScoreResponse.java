package com.kf.loanlimitcheckservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditScoreResponse {

    private String exist;
    private Integer creditScore;

}

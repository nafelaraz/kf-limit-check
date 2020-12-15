package com.kf.loanlimitcheckservice.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanLimitCheckRequest {

    private Long identificationNumber;
    private Double income;

}

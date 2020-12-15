package com.kf.loanlimitcheckservice.controller;

import com.kf.loanlimitcheckservice.request.LoanLimitCheckRequest;
import com.kf.loanlimitcheckservice.response.LoanLimitCheckResponse;
import com.kf.loanlimitcheckservice.services.LoanLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loanlimit")
public class LoanLimitCheckController {

    @Autowired
    LoanLimitService loanLimitService;

    @RequestMapping(method = RequestMethod.POST, value = "/check")
    public LoanLimitCheckResponse calculateLoanLimit(@RequestBody LoanLimitCheckRequest loanLimitCalculatorRequest){

        return loanLimitService.calculateLoanLimit(loanLimitCalculatorRequest);
    }


}

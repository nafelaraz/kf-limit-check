package com.kf.loanlimitcheckservice.services;

import com.kf.loanlimitcheckservice.domains.LoanLimitDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoanLimitServiceTests {
    private LoanLimitDomain loanLimitDomain;

    @BeforeEach()
    void initTest() {
        this.loanLimitDomain = new LoanLimitDomain();
    }

    @Test()
    void limitIsZeroWhenUnsifficientScore(){
        Double limit = this.loanLimitDomain.getLimit(999999.0, 460 ,4.0);
        assertEquals(limit, 0, 0);
    }

    @Test()
    void limitIsTenThousandWhenSufficientScore(){
        Double limit = this.loanLimitDomain.getLimit(2500.0, 750, 4.0);
        assertEquals(limit, 10000, 0);
    }

    @Test()
    void limitIsInterpolatedWhenSufficientScoreAndIncome(){
        Double limit = this.loanLimitDomain.getLimit(7500.0, 750, 4.0);
        assertEquals(limit, 22500, 0);
    }

    @Test()
    void limitIsMultipliedWhenSufficientScore(){
        Double limit = this.loanLimitDomain.getLimit(7500.0, 1500, 4.0);
        assertEquals(limit, 30000, 0);
    }

    @Test()
    void limitIsMultipliedByTwoWhenMultiplierIsNull(){
        Double limit = this.loanLimitDomain.getLimit(7500.0, 750, null);
        assertEquals(limit, 15000, 0);
    }
}

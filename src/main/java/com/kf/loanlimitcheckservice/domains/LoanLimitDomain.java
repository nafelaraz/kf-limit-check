package com.kf.loanlimitcheckservice.domains;

import com.kf.loanlimitcheckservice.util.Interpolation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class LoanLimitDomain {


    public Double getLimit(Double income, Integer creditScore, Double multiplier) {
        Double limit = 0.0;
        if (creditScore < 500) {
            limit = 0.0;
        } else if (creditScore >= 500 && creditScore < 1000 && income < 5000) {
            limit = 10000.0;
        } else if (creditScore >= 500 && creditScore < 1000 && income >= 5000) {

            // kredi skoru bu aralıkta iken kişinin kişinin geliri 5k altında ise 5k*2=10k limit atanıyor
            // kişinin geliri 5k üzerinde iken 10k limit atamak yerine kişinin gelirini
            // lineer enterpolasyon ile belirlediğim bir çarpan ile çarparak kredi limitini hesapladım
            // lineer enterpolasyona konu olan değişkenler bu aralıktaki min-max kredi skorları ve
            // bu aralıkta alınan sabit 2 çarpanı ile kredi limit çarpanı olarak sistemde tutulan 4 değeri
            // (4 değeri gitte application.yml dosyasında default olarak verilmiştir. bu dosyadan değer değiştirilip
            // actuator-refresh yapılması ile sisteme ve dolayısı ile tüm hesaplamalara da yeni çarpan yansıyacaktır)
            if (multiplier == null) {
                BigDecimal inc = new BigDecimal(income.toString());
                BigDecimal mlt = new BigDecimal("2.0");
                BigDecimal lmt = inc.multiply(mlt);

                limit = lmt.doubleValue();
            } else {
                Double multipl = Interpolation.linearInterpolation(500.0, 2.0, 1000.0, multiplier, creditScore);
                BigDecimal inc = new BigDecimal(income.toString());
                BigDecimal mlt = new BigDecimal(multipl.toString());
                BigDecimal lmt = inc.multiply(mlt);

                limit = lmt.doubleValue();
            }
        } else if (creditScore >= 1000) {
            BigDecimal inc = new BigDecimal(income.toString());
            BigDecimal mlt = new BigDecimal(multiplier.toString());
            BigDecimal lmt = inc.multiply(mlt);

            limit = lmt.doubleValue();
        }


        return limit;
    }
}

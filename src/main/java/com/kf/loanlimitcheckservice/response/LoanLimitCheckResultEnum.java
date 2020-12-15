package com.kf.loanlimitcheckservice.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Arrays;


@NoArgsConstructor
@AllArgsConstructor
public enum LoanLimitCheckResultEnum {
    RED("RED", 0),
    ONAY("ONAY", 1 ),
    BULUNAMADI("BULUNAMADI" , 2),
    ERROR("ERROR",3);
    private String text;
    private Integer value;

    public static LoanLimitCheckResultEnum getResult(String rs){
        if(rs!=null){
            for(LoanLimitCheckResultEnum loanLimitCheckResultEnum : LoanLimitCheckResultEnum.values()){
                if(loanLimitCheckResultEnum.getText().equalsIgnoreCase(rs)){
                    return loanLimitCheckResultEnum;
                }
            }
        }
        return null;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

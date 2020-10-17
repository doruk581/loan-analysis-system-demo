package com.doruk.creditapproval.interfaces.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreditApproveRequest {

    private String identityNumber;
    private String name;
    private String surname;
    private Integer bornYear;
    private BigDecimal monthlyIncome;
    private String phoneNumber;
}

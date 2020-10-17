package com.doruk.creditapproval.interfaces.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class CreditApprovalResponse {
    private ApprovalStatus approvalStatus;
    private BigDecimal approvedLimit;
}

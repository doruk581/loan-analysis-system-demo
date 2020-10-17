package com.doruk.creditapproval.domain;

import com.doruk.creditapproval.interfaces.request.CreditApproveRequest;

public interface CreditApplicationFactory {

    CreditApplication createFrom(final CreditApproveRequest request);
}

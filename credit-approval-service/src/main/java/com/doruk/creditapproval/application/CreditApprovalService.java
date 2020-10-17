package com.doruk.creditapproval.application;

import com.doruk.creditapproval.domain.CreditApplication;
import com.doruk.creditapproval.interfaces.request.CreditApproveRequest;
import com.doruk.creditapproval.interfaces.response.CreditApprovalResponse;

public interface CreditApprovalService {

    CreditApplication getCreditApplication(final Integer id);

    CreditApprovalResponse checkApproval(final CreditApproveRequest request);
}

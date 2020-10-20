package com.doruk.creditapproval.domain;

import com.doruk.creditapproval.application.CreditApprovalService;
import com.doruk.creditapproval.domain.flow.IdentityControlHandler;
import com.doruk.creditapproval.interfaces.request.CreditApproveRequest;
import com.doruk.creditapproval.interfaces.response.CreditApprovalResponse;

public class DefaultCreditApprovalService implements CreditApprovalService {

    private final CreditRepository creditRepository;
    private final CreditApplicationFactory factory;
    private final IdentityControlHandler identityControlHandler;

    public DefaultCreditApprovalService(CreditRepository creditRepository, CreditApplicationFactory factory, IdentityControlHandler identityControlHandler) {
        this.creditRepository = creditRepository;
        this.factory = factory;
        this.identityControlHandler = identityControlHandler;
    }

    @Override
    public CreditApplication getCreditApplication(Integer id) {
        return creditRepository.findById(id).orElseThrow(() -> CreditApplicationNotFoundException.create(id));
    }

    @Override
    public CreditApprovalResponse checkApproval(CreditApproveRequest request) {
        //TODO: BLACKLIST SERVICE VS...
        final CreditApplication creditApplication = factory.createFrom(request);

        identityControlHandler.handle(creditApplication);

        creditRepository.save(creditApplication);

        return CreditApprovalResponse.builder().build();
    }
}

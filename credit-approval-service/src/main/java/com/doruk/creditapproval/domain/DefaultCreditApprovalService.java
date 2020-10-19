package com.doruk.creditapproval.domain;

import com.doruk.creditapproval.application.CreditApprovalService;
import com.doruk.creditapproval.interfaces.request.CreditApproveRequest;
import com.doruk.creditapproval.interfaces.response.CreditApprovalResponse;

import java.util.Optional;

public class DefaultCreditApprovalService implements CreditApprovalService {

    private final CreditRepository creditRepository;
    private final CreditApplicationFactory factory;

    public DefaultCreditApprovalService(CreditRepository creditRepository, CreditApplicationFactory factory) {
        this.creditRepository = creditRepository;
        this.factory = factory;
    }

    @Override
    public CreditApplication getCreditApplication(Integer id) {
       return creditRepository.findById(id).orElseThrow(() -> CreditApplicationNotFoundException.create(id));
    }

    @Override
    public CreditApprovalResponse checkApproval(CreditApproveRequest request) {
        //TODO: BLACKLIST SERVICE VS...
        final CreditApplication creditApplication = factory.createFrom(request);


        creditRepository.save(creditApplication);

        return CreditApprovalResponse.builder().build();
    }
}

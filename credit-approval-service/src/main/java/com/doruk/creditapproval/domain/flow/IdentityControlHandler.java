package com.doruk.creditapproval.domain.flow;

import com.doruk.creditapproval.domain.CreditApplication;
import com.doruk.creditapproval.domain.IdentityInformationNotMatchException;
import com.doruk.creditapproval.domain.IdentityService;
import com.doruk.creditapproval.infrastructure.exception.GatewayException;
import com.doruk.creditapproval.infrastructure.identityservice.IdentityResponse;
import io.vavr.control.Either;

public class IdentityControlHandler implements Handler<CreditApplication> {

    private final IdentityService identityService;
    private Handler<CreditApplication> successor;

    public IdentityControlHandler(IdentityService identityService) {
        this.identityService = identityService;
    }

    @Override
    public void setSuccessor(Handler<CreditApplication> handler) {
        this.successor = handler;
    }

    @Override
    public void handle(final CreditApplication creditApplication) {
        final Either<GatewayException, IdentityResponse> maybeIdentityResponse =
                identityService.getIdentityInformation(creditApplication.getApplicant().getApplicantIdentityNumber());

        if (maybeIdentityResponse.isLeft())
            throw maybeIdentityResponse.getLeft();

        final IdentityResponse identityResponse = maybeIdentityResponse.get();

        if (!controlIdentityInformation(identityResponse, creditApplication))
            throw IdentityInformationNotMatchException.create(creditApplication.getApplicant().getApplicantIdentityNumber());

        if (successor != null) successor.handle(creditApplication);
    }

    private Boolean controlIdentityInformation(final IdentityResponse identityResponse, final CreditApplication creditApplication) {
        //TODO:SOME CONTROLS ...
        return Boolean.TRUE;
    }
}

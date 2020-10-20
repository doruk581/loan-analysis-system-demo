package com.doruk.creditapproval.domain;

import com.doruk.creditapproval.infrastructure.exception.GatewayException;
import com.doruk.creditapproval.infrastructure.identityservice.IdentityResponse;
import io.vavr.control.Either;


public interface IdentityService {
    Either<GatewayException, IdentityResponse> getIdentityInformation(final String identityNumber);
}

package com.doruk.identity.domain;

import com.doruk.identity.infrastructure.externalidentityservice.ExternalIdentityResponse;

public interface IdentityInformationFactory {
    IdentityInformation createFrom(final ExternalIdentityResponse response);
}

package com.doruk.identity.infrastructure.externalidentityservice;

import java.util.Optional;

public interface ExternalIdentityService {
    Optional<ExternalIdentityResponse> getIdentityInformation(final String identityNo);
}

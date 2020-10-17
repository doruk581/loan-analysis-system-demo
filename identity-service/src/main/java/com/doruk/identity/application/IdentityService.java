package com.doruk.identity.application;

import com.doruk.identity.interfaces.response.IdentityResponse;

public interface IdentityService {

    IdentityResponse getIdentityInformation(final String identityNo);
}

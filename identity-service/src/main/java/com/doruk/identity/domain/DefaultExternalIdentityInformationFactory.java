package com.doruk.identity.domain;

import com.doruk.identity.infrastructure.externalidentityservice.ExternalIdentityResponse;

public class DefaultExternalIdentityInformationFactory implements IdentityInformationFactory {

    @Override
    public IdentityInformation createFrom(ExternalIdentityResponse response) {

        return IdentityInformation.builder()
                .bornCountry(response.getBornCountry())
                .bornDate(response.getBornDate())
                .fatherName(response.getFatherName())
                .identityNumber(response.getIdentityNumber())
                .identitySerialNumber(response.getIdentitySerialNumber())
                .motherName(response.getMotherName())
                .name(response.getName())
                .surname(response.getSurname())
                .build();
    }
}

package com.doruk.identity.domain;

import com.doruk.identity.application.IdentityService;
import com.doruk.identity.infrastructure.IdentityRepository;
import com.doruk.identity.infrastructure.externalidentityservice.ExternalIdentityResponse;
import com.doruk.identity.infrastructure.externalidentityservice.ExternalIdentityService;
import com.doruk.identity.interfaces.response.IdentityResponse;

import java.util.Optional;

public class DefaultIdentityService implements IdentityService {

    private final IdentityRepository identityRepository;
    private final ExternalIdentityService externalIdentityService;
    private final IdentityInformationFactory identityInformationFactory;

    public DefaultIdentityService(IdentityRepository identityRepository, ExternalIdentityService externalIdentityService, IdentityInformationFactory identityInformationFactory) {
        this.identityRepository = identityRepository;
        this.externalIdentityService = externalIdentityService;
        this.identityInformationFactory = identityInformationFactory;
    }

    @Override
    public IdentityResponse getIdentityInformation(final String identityNo) {

        Optional<IdentityInformation> identityInformation = Optional.ofNullable(identityRepository.findOne(identityNo));

        if (identityInformation.isEmpty()){
            final ExternalIdentityResponse externalIdentityResponse =
                    externalIdentityService.getIdentityInformation(identityNo)
                    .orElseThrow(() -> IdentityInformationNotFoundException.create(identityNo));

            identityInformation = Optional.of(identityInformationFactory.createFrom(externalIdentityResponse));

            identityRepository.save(identityInformation.get());
        }

        return IdentityResponse.builder()
                .bornCountry(identityInformation.get().getBornCountry())
                .identityNumber(identityInformation.get().getIdentityNumber())
                .fatherName(identityInformation.get().getFatherName())
                .identitySerialNumber(identityInformation.get().getIdentitySerialNumber())
                .name(identityInformation.get().getName())
                .motherName(identityInformation.get().getMotherName())
                .surname(identityInformation.get().getSurname())
                .bornDate(identityInformation.get().getBornDate())
                .build();
    }
}

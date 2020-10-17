package com.doruk.identity.infrastructure.externalidentityservice;


import com.doruk.identity.domain.IdentityInformationNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class DefaultExternalIdentityService implements ExternalIdentityService {

    private static final Map<String, ExternalIdentityResponse> externalIdentityMap = prepareExternalIdentityMap();


    @Override
    public Optional<ExternalIdentityResponse> getIdentityInformation(final String identityNo) {
        return Optional.ofNullable(externalIdentityMap.get(identityNo));
    }

    private static Map<String,ExternalIdentityResponse> prepareExternalIdentityMap(){

        final Map<String,ExternalIdentityResponse> map = new HashMap<>();

        final ExternalIdentityResponse externalIdentityResponse = new ExternalIdentityResponse();
        externalIdentityResponse.setIdentitySerialNumber(14141);
        externalIdentityResponse.setBornCountry("TURKEY");
        externalIdentityResponse.setBornDate(LocalDate.of(1993,10,13));
        externalIdentityResponse.setFatherName("SEMİRAMİS");
        externalIdentityResponse.setMotherName("AJDA");
        externalIdentityResponse.setIdentityNumber("22945367992");
        externalIdentityResponse.setName("FENASI");
        externalIdentityResponse.setSurname("KERAMETTIN");

        map.put(externalIdentityResponse.getIdentityNumber(),externalIdentityResponse);

        return map;
    }
}

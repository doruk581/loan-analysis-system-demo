package com.doruk.identity.infrastructure.externalidentityservice;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ExternalIdentityResponse {
    private String identityNumber;
    private String name;
    private String surname;
    private String motherName;
    private String fatherName;
    private LocalDate bornDate;
    private String bornCountry;
    private Integer identitySerialNumber;
}

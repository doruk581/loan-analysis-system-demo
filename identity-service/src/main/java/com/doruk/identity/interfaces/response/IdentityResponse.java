package com.doruk.identity.interfaces.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class IdentityResponse {
    private String identityNumber;
    private String name;
    private String surname;
    private String motherName;
    private String fatherName;
    private LocalDate bornDate;
    private String bornCountry;
    private Integer identitySerialNumber;
}

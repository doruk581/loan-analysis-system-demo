package com.doruk.identity.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "IDENTITY_INFORMATION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdentityInformation {

    @Id
    @Column(name = "IDENTITY_NUMBER")
    private String identityNumber;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "MOTHER_NAME")
    private String motherName;

    @Column(name = "FATHER_NAME")
    private String fatherName;

    @Column(name = "BORN_DATE")
    private LocalDate bornDate;

    @Column(name = "BORN_COUNTRY")
    private String bornCountry;

    @Column(name = "IDENTITY_SERIAL")
    private Integer identitySerialNumber;
}

package com.doruk.creditapproval.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactInformation {

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "CITY")
    private String city;

    @Column(name = "ZIP_CODE")
    private Integer zipCode;
}

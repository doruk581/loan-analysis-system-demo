package com.doruk.creditapproval.domain;

import lombok.*;

import javax.persistence.*;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Applicant {
    @Column(name = "APPLICANT_NAME",nullable = false)
    private String applicantName;

    @Column(name = "APPLICANT_SURNAME",nullable = false)
    private String applicantSurname;

    @Column(name = "APPLICANT_IDENTITY",nullable = false)
    private String applicantIdentityNumber;
}

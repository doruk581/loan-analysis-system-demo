package com.doruk.creditapproval.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "CREDIT_APPLICATION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private ApplicationStatus applicationStatus;

    @Embedded
    private Applicant applicant;
}

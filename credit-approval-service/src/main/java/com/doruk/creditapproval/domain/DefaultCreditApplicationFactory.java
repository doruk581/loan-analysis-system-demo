package com.doruk.creditapproval.domain;

import com.doruk.creditapproval.interfaces.request.CreditApproveRequest;

public class DefaultCreditApplicationFactory implements CreditApplicationFactory {

    @Override
    public CreditApplication createFrom(CreditApproveRequest request) {
        final CreditApplication creditApplication = new CreditApplication();
        //creditApplication.setApplicant(createApplicant(request));
        creditApplication.setApplicationStatus(ApplicationStatus.IN_PROCESS);
       // creditApplication.setContactInformation(createContactInformation(request));

        return creditApplication;
    }

    private ContactInformation createContactInformation(final CreditApproveRequest request){
        return ContactInformation.builder().phoneNumber(request.getPhoneNumber()).build();
    }

    private Applicant createApplicant(final CreditApproveRequest request){
        return Applicant.builder()
                .applicantIdentityNumber(request.getIdentityNumber())
                .applicantName(request.getName())
                .applicantSurname(request.getSurname())
                .build();
    }
}

package com.doruk.creditapproval.application.validation;

import com.doruk.creditapproval.interfaces.ErrorCode;
import com.doruk.creditapproval.interfaces.request.CreditApproveRequest;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreditApproveRequestValidationService implements ValidationService<CreditApproveRequest> {

    @Override
    public ValidationResult validate(CreditApproveRequest request) {
        if (request.getBornYear() < 1900 ||request.getBornYear() >= LocalDate.now().getYear())
            return ValidationResult.error("Born year is invalid!", ErrorCode.BORNYEARINVALID);

        if (request.getIdentityNumber() == null ||request.getIdentityNumber().isBlank())
            return ValidationResult.error("Identity number is invalid!", ErrorCode.IDENTITYNUMBERINVALID);

        if (request.getName() == null ||request.getName().isBlank())
            return ValidationResult.error("Name is invalid!", ErrorCode.NAMEINVALID);

        if (request.getMonthlyIncome().compareTo(BigDecimal.ZERO) < 1)
            return ValidationResult.error("Monthly income invalid!", ErrorCode.MONTHLYINCOMEINVALID);

        if (request.getSurname() == null ||request.getSurname().isBlank())
            return ValidationResult.error("Surname is invalid!", ErrorCode.SURNAMEINVALID);

        if (request.getPhoneNumber() == null || request.getPhoneNumber().isBlank())
            return ValidationResult.error("Phone number is invalid!", ErrorCode.PHONENUMBERINVALID);

        return ValidationResult.success();
    }
}

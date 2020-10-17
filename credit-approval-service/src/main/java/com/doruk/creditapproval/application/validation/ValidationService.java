package com.doruk.creditapproval.application.validation;

public interface ValidationService<T> {
    ValidationResult validate(T request);
}

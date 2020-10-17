package com.doruk.creditapproval.application;

import com.doruk.creditapproval.application.validation.ValidationResult;
import com.doruk.creditapproval.application.validation.ValidationService;
import com.doruk.creditapproval.interfaces.ApiError;
import com.doruk.creditapproval.interfaces.request.CreditApproveRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@Slf4j
public class CreditApprovalController {

    private final CreditApprovalService creditApprovalService;
    private final ValidationService<CreditApproveRequest> creditApproveRequestValidationService;

    public CreditApprovalController(CreditApprovalService creditApprovalService, ValidationService<CreditApproveRequest> creditApproveRequestValidationService) {
        this.creditApprovalService = creditApprovalService;
        this.creditApproveRequestValidationService = creditApproveRequestValidationService;
    }

    @GetMapping(path = "/v1/{id}")
    public ResponseEntity<Object> getApplicationById(@PathVariable Integer id){
        return ResponseEntity.ok(creditApprovalService.getCreditApplication(id));
    }

    @PostMapping(path = "/v1/credit/approve")
    public ResponseEntity<Object> creditApproval(@RequestBody CreditApproveRequest creditApproveRequest){

        final ValidationResult validationResult = creditApproveRequestValidationService.validate(creditApproveRequest);
        if (!validationResult.getIsValid()) {
            log.warn(validationResult.getMessage());
            return new ResponseEntity<>(ApiError.create(HttpStatus.BAD_REQUEST.value(), validationResult.getMessage(), validationResult.getErrorCode().code()), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(creditApprovalService.checkApproval(creditApproveRequest));
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
        log.warn("Unhandled exception occurred!", ex);
        return new ResponseEntity<>(ApiError.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), "CTS1000"), HttpStatus.BAD_REQUEST);
    }
}

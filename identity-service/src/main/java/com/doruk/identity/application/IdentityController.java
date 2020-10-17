package com.doruk.identity.application;

import com.doruk.identity.interfaces.ApiError;
import com.doruk.identity.interfaces.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@Slf4j
public class IdentityController {

    private final IdentityService identityService;

    public IdentityController(IdentityService identityService) {
        this.identityService = identityService;
    }

    @GetMapping(path = "/v1/identity/{id}")
    private ResponseEntity<Object> getIdentityInformation(@PathVariable String id){
        if (id == null ||id.isBlank()){
            return new ResponseEntity<>(ApiError.create(HttpStatus.BAD_REQUEST.value(), "Id cannot be empty", ErrorCode.IDINVALID.code()), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(identityService.getIdentityInformation(id));
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
        log.warn("Unhandled exception occurred!", ex);
        return new ResponseEntity<>(ApiError.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), "CTS1000"), HttpStatus.BAD_REQUEST);
    }
}

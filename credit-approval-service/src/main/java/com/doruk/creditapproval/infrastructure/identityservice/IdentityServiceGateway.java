package com.doruk.creditapproval.infrastructure.identityservice;

import com.doruk.creditapproval.domain.IdentityService;
import com.doruk.creditapproval.infrastructure.exception.GatewayException;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class IdentityServiceGateway implements IdentityService {

    private final RestTemplate restTemplate;

    public IdentityServiceGateway(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Either<GatewayException, IdentityResponse> getIdentityInformation(final String identityNumber) {
        return CompletableFuture
                .supplyAsync(() -> restTemplate.exchange(getIdentityUrl(identityNumber), HttpMethod.GET, null, IdentityResponse.class, identityNumber))
                .<Either<GatewayException, IdentityResponse>>thenApply(resp -> {
                    log.info("identity service called");
                    return Either.right(resp.getBody());
                })
                .exceptionally(ex -> Either.left(new GatewayException(IdentityServiceGateway.class.getName(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value())))
                .join();
    }

    private String getIdentityUrl(final String identityNumber) {
        return "http://identityservice/v1/identity/{id}";
    }
}

package com.doruk.creditapproval;

import com.doruk.creditapproval.application.CreditApprovalService;
import com.doruk.creditapproval.application.validation.CreditApproveRequestValidationService;
import com.doruk.creditapproval.application.validation.ValidationService;
import com.doruk.creditapproval.domain.*;
import com.doruk.creditapproval.domain.flow.IdentityControlHandler;
import com.doruk.creditapproval.infrastructure.identityservice.IdentityServiceGateway;
import com.doruk.creditapproval.interfaces.request.CreditApproveRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableDiscoveryClient
@Configuration
@EnableSwagger2
@EnableCircuitBreaker
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CreditApprovalService creditApprovalService(CreditRepository creditRepository, CreditApplicationFactory creditApplicationFactory, IdentityControlHandler identityControlHandler) {
        return new DefaultCreditApprovalService(creditRepository, creditApplicationFactory, identityControlHandler);
    }

    @Bean
    ValidationService<CreditApproveRequest> creditApproveRequestValidationService() {
        return new CreditApproveRequestValidationService();
    }

    @Bean
    CreditApplicationFactory creditApplicationFactory() {
        return new DefaultCreditApplicationFactory();
    }


    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    IdentityService identityService(RestTemplate restTemplate) {
        return new IdentityServiceGateway(restTemplate);
    }

    @Bean
    IdentityControlHandler identityControlHandler(IdentityService identityService) {
        return new IdentityControlHandler(identityService);
    }

}

package com.doruk.creditapproval;

import com.doruk.creditapproval.application.CreditApprovalService;
import com.doruk.creditapproval.application.validation.CreditApproveRequestValidationService;
import com.doruk.creditapproval.application.validation.ValidationService;
import com.doruk.creditapproval.domain.CreditApplicationFactory;
import com.doruk.creditapproval.domain.CreditRepository;
import com.doruk.creditapproval.domain.DefaultCreditApplicationFactory;
import com.doruk.creditapproval.domain.DefaultCreditApprovalService;
import com.doruk.creditapproval.interfaces.request.CreditApproveRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Bean
    CreditApprovalService creditApprovalService(CreditRepository creditRepository, CreditApplicationFactory creditApplicationFactory){
        return new DefaultCreditApprovalService(creditRepository,creditApplicationFactory);
    }

    @Bean
    ValidationService<CreditApproveRequest> creditApproveRequestValidationService(){
        return new CreditApproveRequestValidationService();
    }

    @Bean
    CreditApplicationFactory creditApplicationFactory(){
        return new DefaultCreditApplicationFactory();
    }
}

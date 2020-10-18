package com.doruk.identity;

import com.doruk.identity.application.IdentityService;
import com.doruk.identity.domain.DefaultExternalIdentityInformationFactory;
import com.doruk.identity.domain.DefaultIdentityService;
import com.doruk.identity.domain.IdentityInformationFactory;
import com.doruk.identity.infrastructure.IdentityRepository;
import com.doruk.identity.infrastructure.externalidentityservice.DefaultExternalIdentityService;
import com.doruk.identity.infrastructure.externalidentityservice.ExternalIdentityService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Configuration
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }


    @Bean
    IdentityService getIdentityService(IdentityInformationFactory identityInformationFactory, ExternalIdentityService externalIdentityService,
                                       IdentityRepository identityRepository){
        return new DefaultIdentityService(identityRepository,externalIdentityService,identityInformationFactory);
    }

    @Bean
    ExternalIdentityService getExternalIdentityService(){
        return new DefaultExternalIdentityService();
    }

    @Bean
    IdentityInformationFactory getIdentityInformationFactory(){
        return new DefaultExternalIdentityInformationFactory();
    }
}

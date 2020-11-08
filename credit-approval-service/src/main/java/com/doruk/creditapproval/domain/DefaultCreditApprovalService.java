package com.doruk.creditapproval.domain;

import com.doruk.creditapproval.application.CreditApprovalService;
import com.doruk.creditapproval.domain.flow.IdentityControlHandler;
import com.doruk.creditapproval.interfaces.request.CreditApproveRequest;
import com.doruk.creditapproval.interfaces.response.CreditApprovalResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import java.util.Random;

public class DefaultCreditApprovalService implements CreditApprovalService {

    private final CreditRepository creditRepository;
    private final CreditApplicationFactory factory;
    private final IdentityControlHandler identityControlHandler;

    public DefaultCreditApprovalService(CreditRepository creditRepository, CreditApplicationFactory factory, IdentityControlHandler identityControlHandler) {
        this.creditRepository = creditRepository;
        this.factory = factory;
        this.identityControlHandler = identityControlHandler;
    }

    @HystrixCommand(fallbackMethod = "buildFallbackGetCreditApplication",
            threadPoolKey = "licenseByOrgThreadPool",
            threadPoolProperties =
                    {@HystrixProperty(name = "coreSize",value="30"),
                            @HystrixProperty(name="maxQueueSize", value="10")},
            commandProperties={
                    @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="10"),
                    @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="75"),
                    @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="7000"),
                    @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds", value="15000"),
                    @HystrixProperty(name="metrics.rollingStats.numBuckets", value="5")}
    )
    @Override
    public CreditApplication getCreditApplication(Integer id) {
        randomlyRunLong();
        return creditRepository.findById(id).orElseThrow(() -> CreditApplicationNotFoundException.create(id));
    }

    private CreditApplication buildFallbackGetCreditApplication(Integer id){
        throw CreditApplicationNotFoundException.create(id);
    }

    @Override
    public CreditApprovalResponse checkApproval(CreditApproveRequest request) {
        //TODO: BLACKLIST SERVICE VS...
        final CreditApplication creditApplication = factory.createFrom(request);

        identityControlHandler.handle(creditApplication);

        creditRepository.save(creditApplication);

        return CreditApprovalResponse.builder().build();
    }

    private void randomlyRunLong(){
        Random rand = new Random();

        int randomNum = rand.nextInt((3 - 1) + 1) + 1;

        if (randomNum==3) sleep();
    }

    private void sleep(){
        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package com.doruk.creditapproval.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends CrudRepository<CreditApplication,Integer> {
}

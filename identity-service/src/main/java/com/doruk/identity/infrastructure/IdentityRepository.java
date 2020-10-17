package com.doruk.identity.infrastructure;

import com.doruk.identity.domain.IdentityInformation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentityRepository extends CrudRepository<IdentityInformation,String> {
}

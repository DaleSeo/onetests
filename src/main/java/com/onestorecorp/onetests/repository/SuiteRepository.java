package com.onestorecorp.onetests.repository;

import com.onestorecorp.onetests.domain.Suite;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SuiteRepository extends MongoRepository<Suite, String> {

}

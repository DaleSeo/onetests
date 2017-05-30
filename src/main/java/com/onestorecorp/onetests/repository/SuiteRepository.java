package com.onestorecorp.onetests.repository;

import com.onestorecorp.onetests.domain.Suite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface SuiteRepository extends MongoRepository<Suite, String> {

}

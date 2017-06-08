package com.onestorecorp.onetests.repository;

import com.onestorecorp.onetests.domain.Test;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestRepository extends MongoRepository<Test, Long> {
}

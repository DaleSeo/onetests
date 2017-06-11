package com.onestorecorp.onetests.repository;

import com.onestorecorp.onetests.domain.SuiteResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

public interface SuiteResultRepository extends MongoRepository<SuiteResult, String> {

    @RestResource(path="bySuite", rel="bySuite")
    Page<SuiteResult> findBySuiteId(@Param("id") String suiteId, Pageable pageable);

}

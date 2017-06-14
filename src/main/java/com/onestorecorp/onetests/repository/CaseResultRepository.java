package com.onestorecorp.onetests.repository;

import com.onestorecorp.onetests.domain.CaseResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

public interface CaseResultRepository extends MongoRepository<CaseResult, String> {

    @RestResource(path="byCase", rel="byCase")
    Page<CaseResult> findByCaseId(@Param("id") String caseId, Pageable pageable);

}

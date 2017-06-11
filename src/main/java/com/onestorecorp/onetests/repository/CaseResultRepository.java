package com.onestorecorp.onetests.repository;

import com.onestorecorp.onetests.domain.CaseResult;
import com.onestorecorp.onetests.domain.SuiteResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

public interface CaseResultRepository extends MongoRepository<CaseResult, String> {

    @RestResource(path="byCaseId", rel="byCaseId")
    Page<CaseResult> findByCaseId(@Param("caseId") String caseId, Pageable pageable);

}

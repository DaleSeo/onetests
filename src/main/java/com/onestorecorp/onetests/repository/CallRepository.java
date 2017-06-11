package com.onestorecorp.onetests.repository;

import com.onestorecorp.onetests.domain.Call;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

public interface CallRepository extends MongoRepository<Call, String> {

	@RestResource(path = "byCaseId", rel= "byCaseId")
	List<Call> findByCaseIdOrderByIdDesc(@Param("caseId") String caseId);

	@RestResource(path = "bySuiteId", rel= "bySuiteId")
	List<Call> findBySuiteIdOrderByIdDesc(@Param("suiteId") String suiteId);

}

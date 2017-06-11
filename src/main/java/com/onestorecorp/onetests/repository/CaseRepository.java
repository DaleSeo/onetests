package com.onestorecorp.onetests.repository;

import com.onestorecorp.onetests.domain.Case;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

public interface CaseRepository extends MongoRepository<Case, String> {


	@RestResource(path="findByServiceId", rel="findByServiceId")
//	@Query("{ 'service': { '$ref': 'service', '$id' : ?0 }}")
	List<Case> findByServiceId(@Param("serviceId") String serviceId);

	List<Case> findByServiceIdAndRequestPathContaining(String serviceId, String path);

}

package com.onestorecorp.onetests.repository;

import com.onestorecorp.onetests.domain.Api;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

public interface ApiRepository extends MongoRepository<Api, String> {

	@RestResource(path="findByServiceIdAndSuiteId", rel="findByServiceIdAndSuiteId")
	List<Api> findByServiceId(@Param("serviceId") String serviceId);

}

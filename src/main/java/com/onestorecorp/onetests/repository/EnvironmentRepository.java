package com.onestorecorp.onetests.repository;

import com.onestorecorp.onetests.domain.Environment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

public interface EnvironmentRepository extends MongoRepository<Environment, String> {

	@RestResource(path="byService", rel="byService")
	List<Environment> findByServiceId(@Param("id") String id);

}

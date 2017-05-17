package com.onestorecorp.onetests.repository;

import com.onestorecorp.onetests.model.Api;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
public interface ApiRepository extends MongoRepository<Api, String> {

	@RestResource(path="findByServiceId", rel="findByServiceId")
	List<Api> findByServiceId(@Param("serviceId") String serviceId);

}

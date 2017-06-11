package com.onestorecorp.onetests.repository;

import com.onestorecorp.onetests.domain.Host;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

public interface HostRepository extends MongoRepository<Host, String> {

	@RestResource(path="findByServiceId", rel="findByServiceId")
	List<Host> findByServiceId(@Param("serviceId") String serviceId);

}

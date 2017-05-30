package com.onestorecorp.onetests.repository;

import com.onestorecorp.onetests.domain.Case;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
public interface CaseRepository extends MongoRepository<Case, String> {

	@RestResource(path="findByServiceId", rel="findByServiceId")
	List<Case> findByServiceId(@Param("serviceId") String serviceId);

}

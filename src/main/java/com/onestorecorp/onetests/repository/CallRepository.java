package com.onestorecorp.onetests.repository;

import com.onestorecorp.onetests.model.Call;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
public interface CallRepository extends MongoRepository<Call, String> {
	
	List<Call> findBySuiteId(@Param("suiteId") String suiteId);

}

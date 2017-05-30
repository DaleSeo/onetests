package com.onestorecorp.onetests.repository;

import com.onestorecorp.onetests.domain.Service;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
public interface ServiceRepository extends MongoRepository<Service, String> {

	List<Service> findById(String id);

}

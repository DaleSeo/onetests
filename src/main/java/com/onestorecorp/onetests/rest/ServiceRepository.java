package com.onestorecorp.onetests.rest;

import com.onestorecorp.onetests.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
public interface ServiceRepository extends MongoRepository<Service, String> {

	List<Person> findById(String id);

}

package com.onestorecorp.onetests.rest;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
public interface HostRepository extends MongoRepository<Host, String> {

	List<Host> findById(String id);

}

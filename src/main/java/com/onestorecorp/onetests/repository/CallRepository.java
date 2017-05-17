package com.onestorecorp.onetests.repository;

import com.onestorecorp.onetests.model.Call;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface CallRepository extends MongoRepository<Call, String> {

}

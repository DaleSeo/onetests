package com.onestorecorp.onetests.repository;

import com.onestorecorp.onetests.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

public interface UserRepository extends MongoRepository<User, String> {

	@RestResource(path="findOneByEmail", rel="findOneByEmail")
	User findOneByEmail(@Param("email") String email);

}

package com.onestorecorp.onetests.repository;

import com.onestorecorp.onetests.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {

}

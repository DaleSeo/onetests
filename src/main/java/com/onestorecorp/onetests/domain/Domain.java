package com.onestorecorp.onetests.domain;

import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document
@Data
public abstract class Domain implements Serializable {

	@Id
	String id;

	@CreatedDate
	Date createdDate;

	@CreatedBy
	@Indexed
	@DBRef(lazy = true)
	User createdBy;

	@LastModifiedDate
	Date lastModifiedDate;

	@LastModifiedBy
	@Indexed
	@DBRef(lazy = true)
	User lastModifiedBy;

}

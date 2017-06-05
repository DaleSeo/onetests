package com.onestorecorp.onetests.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class Call {

	@Id
	private String id;

	private Request request;
	private Response response;

	private boolean starred;

	@Indexed
	private String caseId;

	@Indexed
	private String suiteId;

	@CreatedDate
	private Date createdDate;

	@CreatedBy
	@Indexed
	@DBRef
	private User createdBy;

}

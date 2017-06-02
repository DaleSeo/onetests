package com.onestorecorp.onetests.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

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

}

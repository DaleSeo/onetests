package com.onestorecorp.onetests.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Data
public class Suite {

	@Id
	private String id;
	private String title;
	private String description;

	private List<Request> requests;
	private SuiteResult result;

	@CreatedDate
	private Date createdDate;

}

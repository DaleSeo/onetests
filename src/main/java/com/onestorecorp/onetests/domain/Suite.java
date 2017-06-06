package com.onestorecorp.onetests.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Data
@EqualsAndHashCode(callSuper=false)
public class Suite extends Domain {

	private String title, description;

	private List<Request> requests;
	private SuiteResult result;

}

package com.onestorecorp.onetests.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@EqualsAndHashCode(callSuper=false)
public class Call extends Domain {

	Request request;
	Response response;
	Result result;

	@Indexed
	String caseId;

	@Indexed
	String suiteId;

}

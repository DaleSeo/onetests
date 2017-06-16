package com.onestorecorp.onetests.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@EqualsAndHashCode(callSuper=false)
public class Suite extends Domain {

	String title, description;

	@Indexed
	String serviceId;

	@DBRef(lazy = true)
	Service service;

	@Indexed
	String hostId;

	@DBRef(lazy = true)
	Host host;

	List<String> caseIds;

	@DBRef(lazy = true)
    List<Case> cases;

	SuiteResult result;

}

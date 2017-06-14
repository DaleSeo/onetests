package com.onestorecorp.onetests.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Case extends Domain {

	String title, description;

	Request request;
	Response response;

	@Indexed
	String serviceId;

	@DBRef(lazy = true)
	Service service;

	@Indexed
	String hostId;

	@DBRef(lazy = true)
	Host host;

	List<String> exclusions;

	public Case() {
	}

	public Case(String id) {
		this.id = id;
	}

}
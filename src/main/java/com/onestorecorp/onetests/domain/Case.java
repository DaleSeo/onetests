package com.onestorecorp.onetests.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
@EqualsAndHashCode(callSuper=false)
public class Case extends Domain {

	private String title, description;

	private Request request;
	private Response response;

	@Indexed
	private String serviceId;

	@DBRef(lazy = true)
	private Service service;

	@Indexed
	private String hostId;

	@DBRef(lazy = true)
	private Host host;

	private List<String> exclusions;

	public Case() {
	}

	public Case(String id) {
		this.id = id;
	}

}
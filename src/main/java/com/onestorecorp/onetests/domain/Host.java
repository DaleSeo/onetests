package com.onestorecorp.onetests.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@EqualsAndHashCode(callSuper=false)
public class Host extends Domain {

	String title, description, baseUrl, environment;

	@Indexed
	String serviceId;

	@DBRef(lazy = true)
	Service service;

	public Host() {
	}

	public Host(String id) {
		this.id = id;
	}

}

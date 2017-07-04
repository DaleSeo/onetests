package com.onestorecorp.onetests.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
@EqualsAndHashCode(callSuper=false)
public class Service extends Domain {

	String code;
	String name;
	String description;
	List<String> hosts;

	@DBRef(lazy = true)
	Environment environment;

	public Service() {
	}

	public Service(String id) {
		this.id = id;
	}

}

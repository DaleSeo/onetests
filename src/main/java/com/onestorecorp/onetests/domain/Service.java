package com.onestorecorp.onetests.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@Document
@EqualsAndHashCode(callSuper=false)
public class Service extends Domain {

	String code;
	String name;
	String description;
	List<String> hosts;
	Map<String, String> variables;

	public Service() {
	}

	public Service(String id) {
		this.id = id;
	}

}

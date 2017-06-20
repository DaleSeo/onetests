package com.onestorecorp.onetests.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Service extends Domain {

	private String code;
	private String name;
	private String description;
	private List<String> hosts;

	public Service() {
	}

	public Service(String id) {
		this.id = id;
	}

}

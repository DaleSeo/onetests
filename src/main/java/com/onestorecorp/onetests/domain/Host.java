package com.onestorecorp.onetests.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Host {

	@Id
	private String id;
	private String title;
	private String baseUrl;
	@Indexed
	private String serviceId;

	public Host() {
	}

	public Host(String id) {
		this.id = id;
	}

}

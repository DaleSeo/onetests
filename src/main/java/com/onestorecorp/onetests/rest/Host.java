package com.onestorecorp.onetests.rest;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public class Host {

	@Id
	private String id;
	private String title;
	private String baseUrl;
	@Indexed
	private String serviceId;

}

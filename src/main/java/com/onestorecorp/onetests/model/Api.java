package com.onestorecorp.onetests.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;
import java.util.Map;

@Data
public class Api {

	@Id
	private String id;
	private String title, method, path, body, description;
	private Map<String, List<String>> headers;
	@Indexed
	private String serviceId;
	private boolean skip;
	private List<String> exclusions;

}

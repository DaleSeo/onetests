package com.onestorecorp.onetests.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

@Data
public class Case extends BaseDomain {

	private String title, description;

	private Request request;
	private Response response;

	@Indexed
	private String serviceId;

	private List<String> exclusions;

}
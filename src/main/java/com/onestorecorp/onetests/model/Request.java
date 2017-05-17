package com.onestorecorp.onetests.model;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Data;

import java.util.Map;

@Data
public class Request {

	private String method;

	private String url;

	private Map<String, String> queries;

	private Map<String, String> headers;

	@JsonRawValue
	private Object body;

}

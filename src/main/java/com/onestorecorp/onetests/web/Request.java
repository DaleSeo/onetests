package com.onestorecorp.onetests.web;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Request {

	private String method;

	private String url;

	private Map<String, List<String>> headers;

	@JsonRawValue
	private Object body;

}

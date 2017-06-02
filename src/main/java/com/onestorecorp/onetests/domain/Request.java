package com.onestorecorp.onetests.domain;

import lombok.Data;

import java.util.Map;

@Data
public class Request {

	private String method;

	private String host;

	private String path;

	private Map<String, String> queries;

	private Map<String, String> headers;

	private String body;

}

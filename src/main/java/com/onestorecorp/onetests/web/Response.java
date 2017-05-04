package com.onestorecorp.onetests.web;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Response {

	private int statusCode;

	private String statusMessage;

	private Map<String, List<String>> headers;

	private String body;

	private String text;

}

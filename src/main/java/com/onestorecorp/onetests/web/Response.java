package com.onestorecorp.onetests.web;

import lombok.Data;

import java.util.Map;

@Data
public class Response {

	private int statusCode;

	private String statusMessage;

	private Map<String, String> headers;

	private String body;

	private String text;

}

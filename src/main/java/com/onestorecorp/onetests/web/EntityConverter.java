package com.onestorecorp.onetests.web;

import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class EntityConverter {

	public RequestEntity<Object> convertRequest(Request request) {
		Object body = request.getBody();
		MultiValueMap<String, String> headers  = null;
		HttpMethod method = HttpMethod.valueOf(request.getMethod());
		URI uri = UriComponentsBuilder.fromHttpUrl(request.getUrl()).build().toUri();
		return new RequestEntity<>(body, headers, method, uri);
	}

	public Response convertResponse(ResponseEntity<String> entity) {
		Response response = new Response();
		response.setStatusCode(entity.getStatusCodeValue());
		response.setStatusMessage(entity.getStatusCode().getReasonPhrase());
		response.setHeaders(entity.getHeaders());
		response.setBody(entity.getBody());
		response.setText(entity.getBody());
		return response;
	}

}

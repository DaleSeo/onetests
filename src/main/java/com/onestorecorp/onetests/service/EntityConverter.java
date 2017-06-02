package com.onestorecorp.onetests.service;

import com.onestorecorp.onetests.domain.Request;
import com.onestorecorp.onetests.domain.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class EntityConverter {

	public RequestEntity<Object> convertRequest(Request request) {
		Object body = request.getBody();

		HttpMethod method = HttpMethod.valueOf(request.getMethod());

		MultiValueMap<String, String> headers = new HttpHeaders();
		if (request.getHeaders() != null) {
			headers.setAll(request.getHeaders());
		}

		MultiValueMap<String, String> params = new LinkedMultiValueMap();
		if (request.getQueries() != null) {
			params.setAll(request.getQueries());
		}

		String httpUrl = "";
		if (request.getHost() != null) {
			httpUrl += request.getHost();
		}
		if (request.getPath() != null) {
			httpUrl += request.getPath();
		}

		URI uri = UriComponentsBuilder.fromHttpUrl(httpUrl).queryParams(params).build().encode().toUri();
		return new RequestEntity<>(body, headers, method, uri);
	}

	public Response convertResponse(ResponseEntity<String> entity) {
		Response response = new Response();
		response.setStatusCode(entity.getStatusCodeValue());
		response.setStatusMessage(entity.getStatusCode().getReasonPhrase());
		response.setHeaders(entity.getHeaders().toSingleValueMap());
		response.setBody(entity.getBody());
		return response;
	}

}

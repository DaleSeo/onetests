package com.onestorecorp.onetests.web;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CallApiService {

	public EntityConverter converter = new EntityConverter();

	public Response callApi(Request req) {
		RestTemplate restTemplate = new RestTemplate(); // TODO: Inject
		RequestEntity<Object> requestEntity = converter.convertRequest(req);
		ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
		Response response = converter.convertResponse(responseEntity);
		return response;
	}

}

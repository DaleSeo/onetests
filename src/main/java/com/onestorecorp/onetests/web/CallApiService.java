package com.onestorecorp.onetests.web;

import com.onestorecorp.onetests.model.Call;
import com.onestorecorp.onetests.model.Request;
import com.onestorecorp.onetests.model.Response;
import com.onestorecorp.onetests.repository.CallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CallApiService {

	@Autowired
	private CallRepository callRepo;

	private EntityConverter converter = new EntityConverter();

	public Response callApi(Request req, String apiId) {
		Response res = callApi(req);
		addHistory(req, res, apiId);
		return res;
	}

	public Response callApi(Request req) {
		RestTemplate restTemplate = new RestTemplate(); // TODO: Inject
		RequestEntity<Object> requestEntity = converter.convertRequest(req);
		ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
		Response res = converter.convertResponse(responseEntity);
		return res;
	}

	private void addHistory(Request req, Response res, String apiId) {
		Call call = new Call();
		call.setRequest(req);
		call.setResponse(res);
		call.setApiId(apiId);
		call.setStarred(false);
		callRepo.insert(call);
	}

}

package com.onestorecorp.onetests.service;

import com.onestorecorp.onetests.domain.Call;
import com.onestorecorp.onetests.domain.Request;
import com.onestorecorp.onetests.domain.Response;
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

	public Response callApiWithSuiteId(Request req, String suiteId) {
		Response res = callApi(req);
		addHistory(req, res, null, suiteId);
		return res;
	}

	public Call callApi(Request req, String caseId) {
		Response res = callApi(req);
		Call call = addHistory(req, res, caseId, null);
		return call;
	}

	public Response callApi(Request req) {
		RestTemplate restTemplate = new RestTemplate(); // TODO: Inject
		RequestEntity<Object> requestEntity = converter.convertRequest(req);
		ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
		Response res = converter.convertResponse(responseEntity);
		return res;
	}

	private Call addHistory(Request req, Response res, String caseId, String suiteId) {
		Call call = new Call();
		call.setRequest(req);
		call.setResponse(res);
		call.setCaseId(caseId);
		call.setSuiteId(suiteId);
		return callRepo.save(call);
	}

}

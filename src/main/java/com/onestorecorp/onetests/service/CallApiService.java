package com.onestorecorp.onetests.service;

import com.onestorecorp.onetests.domain.Call;
import com.onestorecorp.onetests.domain.Request;
import com.onestorecorp.onetests.domain.Response;
import com.onestorecorp.onetests.domain.Result;
import com.onestorecorp.onetests.repository.CallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

@Service
public class CallApiService {

	@Autowired
	private CallRepository callRepo;

	@Autowired
	private RestOperations restOperations;

	private EntityConverter converter = new EntityConverter();

	public Response callApiWithSuiteId(Request req, String suiteId) {
		Response res = callApi(req);
		addHistory(req, res, null, suiteId, null);
		return res;
	}

	public Call callApi(Request req, String caseId) {
		Response res = callApi(req);
		Call call = addHistory(req, res, caseId, null, null);
		return call;
	}

	public Response callApi(Request req) {
		RequestEntity<Object> requestEntity = converter.convertRequest(req);
		ResponseEntity<String> responseEntity = restOperations.exchange(requestEntity, String.class);
		Response res = converter.convertResponse(responseEntity);
		return res;
	}

	public Call addHistory(Request req, Response res, String caseId, String suiteId, Result result) {
		Call call = new Call();
		call.setRequest(req);
		call.setResponse(res);
		call.setCaseId(caseId);
		call.setSuiteId(suiteId);
		call.setResult(result);
		return callRepo.save(call);
	}

}

package com.onestorecorp.onetests.service;

import com.onestorecorp.onetests.domain.Case;
import com.onestorecorp.onetests.domain.Request;
import com.onestorecorp.onetests.domain.Response;
import com.onestorecorp.onetests.domain.Result;
import com.onestorecorp.onetests.repository.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

@Service
public class RunTestService {

	@Autowired
	private CaseRepository caseRepo;

	@Autowired
	private CallApiService callApiSvc;

	@Autowired
	private RestOperations restOperations;

	private EntityConverter converter = new EntityConverter();

	public void runTest(String caseId) {
		Case cas = caseRepo.findOne(caseId);
		Request request = cas.getRequest();

		Response expectedResponse = cas.getResponse();
		Response acutalResponse = callApiSvc.callApi(request);
	}

	private Result diffResponses() {
		return null;
	}

}

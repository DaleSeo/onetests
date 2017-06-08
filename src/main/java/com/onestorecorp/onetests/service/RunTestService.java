package com.onestorecorp.onetests.service;

import com.onestorecorp.onetests.component.ResponseEqualer;
import com.onestorecorp.onetests.domain.*;
import com.onestorecorp.onetests.repository.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RunTestService {

	@Autowired
	private CaseRepository caseRepo;

	@Autowired
	private CallApiService callApiSvc;

	@Autowired
	private ResponseEqualer responseEqualer;

	public Call runTest(String caseId) {
		Case cas = caseRepo.findOne(caseId);
		Request req = cas.getRequest();

		Response expectedRes = cas.getResponse();
		Response res = callApiSvc.callApi(req);

		Result result = responseEqualer.equals(expectedRes, res);
		Call call = callApiSvc.addHistory(req, res, caseId, null, result);

		return call;
	}
}

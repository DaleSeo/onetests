package com.onestorecorp.onetests.service;

import com.onestorecorp.onetests.domain.Case;
import com.onestorecorp.onetests.domain.Request;
import com.onestorecorp.onetests.domain.Response;
import com.onestorecorp.onetests.repository.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 서대영(DAEYOUNG SEO)/Onestore/SKP
 */
@Service
public class CaseService {

	@Autowired
	private CaseRepository caseRepo;

	@Autowired
	private CallApiService callApiSvc;

	public Response recordResponse(String id) {
		Case aCase = caseRepo.findOne(id);
		Request req = aCase.getRequest();
		Response res = callApiSvc.callApiThenAddHistory(req);
		aCase.setResponse(res); // replace the current response
		caseRepo.save(aCase);
		return res;
	}

	public Case clone(String id) {
		Case aCase = caseRepo.findOne(id);
		Case newCase = aCase.clone();
		caseRepo.insert(newCase);
		return newCase;
	}

}

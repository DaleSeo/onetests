package com.onestorecorp.onetests.service;

import com.onestorecorp.onetests.component.ResponseEqualer;
import com.onestorecorp.onetests.domain.*;
import com.onestorecorp.onetests.repository.CaseRepository;
import com.onestorecorp.onetests.repository.CaseResultRepository;
import com.onestorecorp.onetests.repository.SuiteRepository;
import com.onestorecorp.onetests.repository.SuiteResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RunTestService {

	@Autowired
	private CaseRepository caseRepo;

	@Autowired
	private SuiteRepository suiteRepo;

    @Autowired
    private CaseResultRepository caseResultRepo;

    @Autowired
    private SuiteResultRepository suiteResultRepo;

	@Autowired
	private CallApiService callApiSvc;

	@Autowired
	private ResponseEqualer responseEqualer;

	public CaseResult runTestCase(String caseId) {
		Case cas = caseRepo.findOne(caseId);
		Request req = cas.getRequest();

		Response expectedRes = cas.getResponse();
		Response res = callApiSvc.invoke(req);

		Result result = responseEqualer.equals(expectedRes, res);
		Call call = callApiSvc.addHistory(req, res, caseId, null, null);

		CaseResult caseResult = convert(result);
		caseResult.setCaseId(cas.getId());
		caseResult.setCallId(call.getId());
		caseResultRepo.save(caseResult);

		return caseResult;
	}

    public SuiteResult runTestSuite(String id) {
	    long start = System.currentTimeMillis();
	    SuiteResult suiteResult = new SuiteResult(id);

	    Suite suite = suiteRepo.findOne(id);
		List<CaseResult> caseResults = suite.getCases()
				.parallelStream()
				.map(aCase -> {
					Request req = aCase.getRequest();

					Response expectedRes = aCase.getResponse();
					Response res = callApiSvc.invoke(req);

					Result result = responseEqualer.equals(expectedRes, res);
                    Call call = callApiSvc.addHistory(req, res, aCase.getId(), suite.getId(), null);

                    CaseResult caseResult = convert(result);
                    caseResult.setCaseId(aCase.getId());
                    caseResult.setCallId(call.getId());
                    caseResultRepo.save(caseResult);

                    if (caseResult.isPassed()) {
                        suiteResult.incrementSuccess();
                    }

					return caseResult;
				})
				.collect(Collectors.toList());

		suiteResult.setCaseResults(caseResults);
        suiteResult.setTotal(caseResults.size());

	    long end = System.currentTimeMillis();
	    suiteResult.setElapsedTime(end - start);

        suiteResultRepo.save(suiteResult);

		return suiteResult;
    }

    private static CaseResult convert(Result result) {
		CaseResult caseResult = new CaseResult();
		caseResult.setStatusPassed(result.isStatusEqual());
		caseResult.setHeadersPassed(result.isHeadersEqual());
		caseResult.setBodyPassed(result.isBodyEqual());
		return caseResult;
	}

}

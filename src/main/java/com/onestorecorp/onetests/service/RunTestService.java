package com.onestorecorp.onetests.service;

import com.onestorecorp.onetests.component.ResponseEqualer;
import com.onestorecorp.onetests.domain.*;
import com.onestorecorp.onetests.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RunTestService {

	private static Logger logger = LoggerFactory.getLogger(RunTestService.class);

	@Autowired
	private EnvironmentService environmentService;

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
	private EnvironmentRepository environmentRepo;

	@Autowired
	private ResponseEqualer responseEqualer;

    public SuiteResult runTestSuite(String id, String environmentId) {
	    SuiteResult suiteResult = new SuiteResult(id);

	    Suite suite = suiteRepo.findOne(id);
		List<CaseResult> caseResults = suite.getCases()
				.parallelStream()
				.map(aCase -> {
                    CaseResult caseResult = run(aCase, suite, environmentId);

                    if (caseResult.isPassed()) {
                        suiteResult.incrementSuccess();
                    }

					return caseResult;
				})
				.collect(Collectors.toList());

		suiteResult.setCaseResults(caseResults);
        suiteResult.setTotal(caseResults.size());

        long elapsedTime = caseResults.stream()
		        .map(CaseResult::getElapsedTime)
		        .collect(Collectors.summingLong(Long::longValue));

	    suiteResult.setElapsedTime(elapsedTime);

        suiteResultRepo.save(suiteResult);

		return suiteResult;
    }

	public CaseResult runTestCase(String caseId, String environmentId) {
		Case cas = caseRepo.findOne(caseId);
		return run(cas, new Suite(), environmentId);
	}

	private CaseResult run(Case cas, Suite suite, String environmentId) {
		long start = System.currentTimeMillis();
		Request req = cas.getRequest();

		Environment environment = environmentRepo.findOne(environmentId);
		environmentService.applyEnvironmentToRequest(req, environment, cas.getService());

		Response expected = cas.getResponse();
		Response actual;
		CaseResult caseResult;

		try {
			actual = callApiSvc.invoke(req);
			Result result = responseEqualer.equals(expected, actual);
			Call call = callApiSvc.addHistory(req, actual, cas.getId(), suite.getId(), null);
			caseResult = convert(result);
			caseResult.setCaseId(cas.getId());
			caseResult.setEnvironment(environment);
			caseResult.setCallId(call.getId());
			caseResult.setRequest(req);
			caseResult.setExpected(expected);
			caseResult.setActual(actual);
		} catch (Exception e) {
			caseResult = new CaseResult();
			caseResult.setCaseId(cas.getId());
			caseResult.setEnvironment(environment);
			caseResult.setRequest(req);
			caseResult.setExpected(expected);
			caseResult.setError(e.toString());
		}

		long end = System.currentTimeMillis();
		caseResult.setElapsedTime(end - start);

		caseResultRepo.save(caseResult);

		return caseResult;
	}

    private static CaseResult convert(Result result) {
		CaseResult caseResult = new CaseResult();
		caseResult.setStatusPassed(result.isStatusEqual());
		caseResult.setHeadersPassed(result.isHeadersEqual());
		caseResult.setBodyPassed(result.isBodyEqual());
		return caseResult;
	}

}

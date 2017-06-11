package com.onestorecorp.onetests.web;

import com.onestorecorp.onetests.domain.Call;
import com.onestorecorp.onetests.domain.SuiteResult;
import com.onestorecorp.onetests.service.RunTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class RunTestController {

	private static final Logger logger = LoggerFactory.getLogger(RunTestController.class);

	@Autowired
	private RunTestService svc;

	@PostMapping("/api/cases/{id}/run")
	public Call runTestCase(@PathVariable String id) {
		logger.info("# Start Test: {}", id);
		Call call = svc.runTestCase(id);
		logger.info("# Finish Test: {}", call);
		return call;
	}

	@PostMapping("/api/suites/{id}/run")
	public SuiteResult runTestSuite(@PathVariable String id) {
		logger.info("# Start TestSuite: {}", id);
		SuiteResult result = svc.runTestSuite(id);
		logger.info("# Finish TestSuite: {}", result);
		return result;
	}

}
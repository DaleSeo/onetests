package com.onestorecorp.onetests.web;

import com.onestorecorp.onetests.domain.CaseResult;
import com.onestorecorp.onetests.domain.SuiteResult;
import com.onestorecorp.onetests.service.RunTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class RunTestController {

	private static final Logger logger = LoggerFactory.getLogger(RunTestController.class);

	@Autowired
	private RunTestService svc;

	@PostMapping("/api/cases/{id}/run")
	public CaseResult runTestCase(@PathVariable String id, @RequestParam String environmentId) {
		logger.info("# Start TestCase: {}", id);
		CaseResult result = svc.runTestCase(id, environmentId);
		logger.info("# Finish TestCase: {}", result);
		return result;
	}

	@PostMapping("/api/suites/{id}/run")
	public SuiteResult runTestSuite(@PathVariable String id, @RequestParam String environmentId) {
		logger.info("# Start TestSuite: {}", id);
		SuiteResult result = svc.runTestSuite(id, environmentId);
		logger.info("# Finish TestSuite: {}", result);
		return result;
	}

}
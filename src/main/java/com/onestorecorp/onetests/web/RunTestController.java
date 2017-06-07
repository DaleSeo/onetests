package com.onestorecorp.onetests.web;

import com.onestorecorp.onetests.service.RunTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class RunTestController {

	private static final Logger logger = LoggerFactory.getLogger(RunTestController.class);

	@Autowired
	private RunTestService svc;

	@PostMapping("/api/cases/{id}/run")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void runTest(@PathVariable String id) {
		logger.info("##### {}", id);
		svc.runTest(id);
	}

}
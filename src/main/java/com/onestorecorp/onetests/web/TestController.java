package com.onestorecorp.onetests.web;

import com.onestorecorp.onetests.domain.Case;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//@RestController
public class TestController {

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	@PostMapping("/api/cases/put/{caseId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Case callApi(@RequestBody Case cas, @PathVariable String caseId) {
		logger.info("##### caseId: {}", caseId);
		logger.info("##### case: {}", cas);
		return cas;
	}

}

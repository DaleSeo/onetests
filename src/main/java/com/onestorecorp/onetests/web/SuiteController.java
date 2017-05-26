package com.onestorecorp.onetests.web;

import com.onestorecorp.onetests.model.SuiteResult;
import com.onestorecorp.onetests.service.SuiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
public class SuiteController {

	private static final Logger logger = LoggerFactory.getLogger(SuiteController.class);

	@Autowired
	private SuiteService svc;

	@RequestMapping(value = "/suites/run", method = RequestMethod.POST)
	public @ResponseBody SuiteResult run(@RequestParam String id) {
		logger.info("##### id: {}", id);
		SuiteResult result = svc.run(id);
		logger.info("##### SuiteResult: {}", result);
		return result;
	}

}

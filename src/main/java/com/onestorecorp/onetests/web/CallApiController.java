package com.onestorecorp.onetests.web;

import com.onestorecorp.onetests.domain.Call;
import com.onestorecorp.onetests.domain.Request;
import com.onestorecorp.onetests.service.CallApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/callApi")
public class CallApiController {

	private static final Logger logger = LoggerFactory.getLogger(CallApiController.class);

	@Autowired
	private CallApiService svc;

	@PostMapping
	public Call callApi(@RequestBody Request req, @RequestParam String caseId) {
		logger.info("##### {}", req);
		Call call = svc.callApi(req, caseId);
		logger.info("##### {}", call);
		return call;
	}

}
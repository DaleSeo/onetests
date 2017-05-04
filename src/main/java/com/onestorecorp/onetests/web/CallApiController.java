package com.onestorecorp.onetests.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/callApi")
public class CallApiController {

	private static final Logger logger = LoggerFactory.getLogger(CallApiController.class);

	@Autowired
	private CallApiService svc;

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Response callApi(@RequestBody Request req) {
		logger.info("##### {}", req);
		Response res = svc.callApi(req);
		logger.info("##### {}", res);
		return res;
	}

}

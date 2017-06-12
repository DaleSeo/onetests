package com.onestorecorp.onetests.web;

import com.onestorecorp.onetests.domain.Response;
import com.onestorecorp.onetests.service.CaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 서대영(DAEYOUNG SEO)/Onestore/SKP
 */
@RestController
public class CaseController {

	private static final Logger logger = LoggerFactory.getLogger(CaseController.class);

	@Autowired
	private CaseService svc;

	@PatchMapping("/api/cases/{id}/recordResponse")
	public Response recordResponse(@PathVariable String id) {
		Response response = svc.recordResponse(id);
		logger.debug("response: {}", response);
		return response;
	}

}

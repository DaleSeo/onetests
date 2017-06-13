package com.onestorecorp.onetests.web;

import com.onestorecorp.onetests.domain.Case;
import com.onestorecorp.onetests.domain.Response;
import com.onestorecorp.onetests.repository.CaseRepository;
import com.onestorecorp.onetests.service.CaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author 서대영(DAEYOUNG SEO)/Onestore/SKP
 */
@RestController
public class CaseController {

	private static final Logger logger = LoggerFactory.getLogger(CaseController.class);

	@Autowired
	private CaseService svc;

	@Autowired
	private CaseRepository repo;

	@PatchMapping("/api/cases/{id}/recordResponse")
	public Response recordResponse(@PathVariable String id) {
		Response response = svc.recordResponse(id);
		logger.debug("response: {}", response);
		return response;
	}

	@PutMapping("/api/cases/{id}/response")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void replaceResponse(@PathVariable String id, @RequestBody Response response) {
		Case found = repo.findOne(id);
		found.setResponse(response);
		repo.save(found);
	}

}

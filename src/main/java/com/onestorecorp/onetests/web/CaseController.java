package com.onestorecorp.onetests.web;

import com.onestorecorp.onetests.domain.*;
import com.onestorecorp.onetests.repository.CaseRepository;
import com.onestorecorp.onetests.service.CaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author 서대영(DAEYOUNG SEO)/Onestore/SKP
 */
@RestController
@Slf4j
public class CaseController {

	@Autowired
	private CaseService svc;

	@Autowired
	private CaseRepository repo;

	@GetMapping("/api/cases")
	public Page<Case> getCases(
			@RequestParam(required = false) String serviceId,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "100") int size) {
		return svc.find(serviceId, page, size);
	}

	@PostMapping("/api/cases")
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody Case aCase) {
		svc.save(aCase);
	}

	@PatchMapping("/api/cases/{id}/recordResponse")
	public Response recordResponse(@PathVariable String id) {
		Response response = svc.recordResponse(id);
		log.debug("response: {}", response);
		return response;
	}

	@PutMapping("/api/cases/{id}/basics")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void replaceBasics(@PathVariable String id, @RequestBody Case cas) {
		Case found = repo.findOne(id);
		found.setTitle(cas.getTitle());
		found.setDescription(cas.getDescription());
		if (cas.getEnvironmentId() != null) {
			found.setEnvironmentId(cas.getEnvironmentId());
			found.setEnvironment(new Environment(cas.getEnvironmentId()));
		}
		svc.save(found);
	}


	@PutMapping("/api/cases/{id}/request")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void replaceRequest(@PathVariable String id, @RequestBody Request request) {
		Case found = repo.findOne(id);
		found.setRequest(request);
		svc.save(found);
	}

	@PutMapping("/api/cases/{id}/response")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void replaceResponse(@PathVariable String id, @RequestBody Response response) {
		Case found = repo.findOne(id);
		found.setResponse(response);
		svc.save(found);
	}

	@PostMapping("/api/cases/{id}/clone")
	public @ResponseBody Case clone(@PathVariable String id) {
		return svc.clone(id);
	}

	@PutMapping("/api/cases/{id}/like")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void like(@PathVariable String id) {
		Case found = repo.findOne(id);
		found.setNumLikes(found.getNumLikes() + 1); // TODO: use MongoDB inc operator to be thread safe
		svc.save(found);
	}

}

package com.onestorecorp.onetests.service;

import com.onestorecorp.onetests.domain.Call;
import com.onestorecorp.onetests.domain.Case;
import com.onestorecorp.onetests.domain.Response;
import com.onestorecorp.onetests.domain.Service;
import com.onestorecorp.onetests.repository.CaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author 서대영(DAEYOUNG SEO)/Onestore/SKP
 */
@org.springframework.stereotype.Service
@CacheConfig(cacheNames = "cases")
@Slf4j
public class CaseService {

	@Autowired
	private CaseRepository caseRepo;

	@Autowired
	private CallApiService callApiSvc;

	@Autowired
	private ServiceService serviceSvc;

	public Response recordResponse(String id) {
		Case aCase = caseRepo.findOne(id);
		Call call = callApiSvc.callApi(aCase);
		aCase.setResponse(call.getResponse()); // replace the current response
		save(aCase);
		return call.getResponse();
	}

	public Case clone(String id) {
		Case aCase = caseRepo.findOne(id);
		Case newCase = aCase.clone();
		insert(newCase);
		return newCase;
	}

	@Cacheable
	public Page<Case> find(String serviceId, int page, int size) {
		log.debug("serviceId: {}, page: {}, size: {}", serviceId, page, size);
		Page<Case> casePage;
		if (serviceId != null)
			casePage = caseRepo.findByServiceId(serviceId, new PageRequest(page, size));
		else
			casePage = caseRepo.findAll(new PageRequest(page, size));
		casePage.getContent().stream()
				.forEach(aCase -> {
					String svcId = aCase.getServiceId();
					Service svc = serviceSvc.findOne(svcId);
					aCase.setService(svc);
				});
		return casePage;
	}

	@CacheEvict(allEntries = true)
	public Case save(Case aCase) {
		return caseRepo.save(aCase);
	}

	@CacheEvict(allEntries = true)
	public Case insert(Case aCase) {
		return caseRepo.insert(aCase);
	}

}

package com.onestorecorp.onetests.service;

import com.onestorecorp.onetests.domain.Request;
import com.onestorecorp.onetests.domain.Suite;
import com.onestorecorp.onetests.domain.SuiteResult;
import com.onestorecorp.onetests.repository.CallRepository;
import com.onestorecorp.onetests.repository.SuiteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuiteService {

	private static Logger logger = LoggerFactory.getLogger(SuiteService.class);

	@Autowired
	private SuiteRepository suiteRepo;

	@Autowired
	private CallApiService callApiService;

	@Autowired
	private CallRepository callRepo;

	public SuiteResult run(String id) {
		Suite suite = suiteRepo.findOne(id);

		int success = 0, failure = 0;
		for (Request request : suite.getRequests()) {
			try {
				callApiService.callApiWithSuiteId(request, id);
				success++;
			} catch (Exception e) {
				logger.warn("Failed to call API.");
				failure++;
			}
		}

		SuiteResult result = new SuiteResult();
		result.setSuccess(success);
		result.setTotal(success + failure);
		suite.setResult(result);

		suiteRepo.save(suite);
		return result;
	}

}

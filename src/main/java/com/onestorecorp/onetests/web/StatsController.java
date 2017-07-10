package com.onestorecorp.onetests.web;

import com.onestorecorp.onetests.domain.Stats;
import com.onestorecorp.onetests.repository.CallRepository;
import com.onestorecorp.onetests.repository.CaseRepository;
import com.onestorecorp.onetests.repository.SuiteRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 서대영(DAEYOUNG SEO)/Onestore/SKP
 */
@RestController
public class StatsController {

	private final CallRepository callRepo;

	private final CaseRepository caseRepo;

	private final SuiteRepository suiteRepo;

	public StatsController(CallRepository callRepo, CaseRepository caseRepo, SuiteRepository suiteRepo) {
		this.callRepo = callRepo;
		this.caseRepo = caseRepo;
		this.suiteRepo = suiteRepo;
	}

	@GetMapping("/api/stats")
	public Stats get() {
		long numCalls = callRepo.count();
		long numCases = caseRepo.count();
		long numSuites = suiteRepo.count();
		return new Stats(numCalls, numCases, numSuites);
	}

}

package com.onestorecorp.onetests.data;

import com.onestorecorp.onetests.domain.Api;
import com.onestorecorp.onetests.domain.Case;
import com.onestorecorp.onetests.domain.Request;
import com.onestorecorp.onetests.domain.Service;
import com.onestorecorp.onetests.repository.ApiRepository;
import com.onestorecorp.onetests.repository.CaseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@DataMongoTest
@ActiveProfiles("development")
public class ApiToCaseMig {

	@Autowired
	private ApiRepository apiRepo;

	@Autowired
	private CaseRepository caseRepo;

	@Test
	public void test() {
		List<Api> apis = apiRepo.findAll();

		for (Api api : apis) {
			Case cas = new Case();
			cas.setCreatedDate(new Date());
			cas.setTitle(api.getTitle());
			cas.setDescription(api.getDescription());
			cas.setExclusions(api.getExclusions());

			Request req = new Request();
			Map<String, String> headers = new HashMap<>();
			Optional.ofNullable(api.getHeaders()).ifPresent(map -> {
				map.entrySet().forEach(entry -> {
					if (!entry.getValue().isEmpty()) {
						headers.put(entry.getKey(), entry.getValue().get(0));
					}
				});
			});
			req.setHeaders(headers);
			req.setBody(api.getBody());
			req.setHost(api.getPath());
			req.setPath(api.getPath());
			req.setMethod(api.getMethod());
			cas.setRequest(req);

			Service service = new Service();
			service.setId(api.getServiceId() == null ? "5912a282ec46ff6c417a9481": api.getServiceId());
			cas.setService(service);
			cas.setServiceId(api.getServiceId() == null ? "5912a282ec46ff6c417a9481": api.getServiceId());

			caseRepo.save(cas);
			System.out.println("##### " + cas);
		}
	}

}

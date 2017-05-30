package com.onestorecorp.onetests;

import com.onestorecorp.onetests.domain.Api;
import com.onestorecorp.onetests.domain.Case;
import com.onestorecorp.onetests.domain.Request;
import com.onestorecorp.onetests.repository.ApiRepository;
import com.onestorecorp.onetests.repository.CaseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataMig2 {

	@Autowired
	private ApiRepository apiRepo;

	@Autowired
	private CaseRepository caseRepo;

	@Test
	public void test() {
		List<Api> apis = apiRepo.findAll();

		for (Api api : apis) {
			Case cas = new Case();
			cas.setId(api.getId());
			cas.setCreatedDate(new Date());
			cas.setTitle(api.getTitle());
			cas.setDescription(api.getDescription());
			cas.setServiceId(api.getServiceId());
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
			req.setUrl(api.getPath());
			api.setMethod(api.getMethod());

			caseRepo.save(cas);
			System.out.println("##### " + cas);
		}
	}

}

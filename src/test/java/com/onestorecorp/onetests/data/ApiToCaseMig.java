package com.onestorecorp.onetests.data;

import com.onestorecorp.onetests.domain.*;
import com.onestorecorp.onetests.repository.ApiRepository;
import com.onestorecorp.onetests.repository.CaseRepository;
import com.onestorecorp.onetests.repository.HostRepository;
import com.onestorecorp.onetests.repository.UserRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RunWith(SpringRunner.class)
@DataMongoTest
@ActiveProfiles("production")
public class ApiToCaseMig {

	private static final AtomicInteger counter = new AtomicInteger();

	private static Map<String, String> hostMap;

	@Autowired
	private ApiRepository apiRepo;

	@Autowired
	private CaseRepository caseRepo;

	@Autowired
	private HostRepository hostRepo;

	@Autowired
	private UserRepository repository;

	@BeforeClass
	public static void beforeClass() {
		hostMap = new HashMap<>();
		hostMap.put("5912a282ec46ff6c417a9481", "5912cd44ec46ffe66178f626");
		hostMap.put("5912a299ec46ff6c417a9482", "5913badd10295a2cd72b2001");
		hostMap.put("5912a2a7ec46ff6c417a9483", "591be2bcbd8f8d0ac060737c");
	}

	@Test
	public void test() {
		List<Api> apis = apiRepo.findAll();

		for (Api api : apis) {
			if (api.getServiceId() == null) continue;

			Case cas = new Case();

			Date date = new Date();
			User user = repository.findOneByEmail("dale.seo@onestorecorp.com");

			cas.setCreatedDate(date);
			cas.setCreatedBy(user);
			cas.setLastModifiedDate(date);
			cas.setLastModifiedBy(user);

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
			req.setPath(api.getPath());
			req.setMethod(api.getMethod());
			cas.setRequest(req);

			Service service = new Service();
			service.setId(api.getServiceId());
			cas.setService(service);
			cas.setServiceId(api.getServiceId());

			Host host = hostRepo.findOne(hostMap.get(api.getServiceId()));
			cas.setHostId(host.getId());
			cas.setHost(host);
			req.setHost(host.getBaseUrl());

			caseRepo.save(cas);
			counter.incrementAndGet();
		}

		System.out.println("# Total : " + counter.intValue());
	}

}

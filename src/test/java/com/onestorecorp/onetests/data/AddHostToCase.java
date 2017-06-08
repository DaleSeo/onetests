package com.onestorecorp.onetests.data;

import com.onestorecorp.onetests.domain.Case;
import com.onestorecorp.onetests.domain.Host;
import com.onestorecorp.onetests.domain.Request;
import com.onestorecorp.onetests.repository.CaseRepository;
import com.onestorecorp.onetests.repository.HostRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Ignore
@RunWith(SpringRunner.class)
@DataMongoTest
@ActiveProfiles("production")
public class AddHostToCase {

	@Autowired
	private CaseRepository caseRepo;

	@Autowired
	private HostRepository hostRepo;

	@Test
	public void test() {
		List<Case> cases = caseRepo.findAll();

		int count = 0;
		for (Case cas : cases) {
			if (!cas.getServiceId().equals("5912a2a7ec46ff6c417a9483")) {
				continue;
			}
			cas.setHostId("591be2bcbd8f8d0ac060737c");

			Host host = new Host();
			host.setId("591be2bcbd8f8d0ac060737c");

			Request req = cas.getRequest();
			req.setHost("http://qa-ec-store.sungsu.onestore.co.kr");
			cas.setRequest(req);

			caseRepo.save(cas);
			count ++;
		}
		System.out.println("##### total: " + count);
	}

}

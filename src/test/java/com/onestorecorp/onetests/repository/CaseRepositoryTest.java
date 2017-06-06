package com.onestorecorp.onetests.repository;

import com.onestorecorp.onetests.domain.Call;
import com.onestorecorp.onetests.domain.Case;
import com.onestorecorp.onetests.domain.Request;
import com.onestorecorp.onetests.domain.Service;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;


@Ignore
@RunWith(SpringRunner.class)
@DataMongoTest
@ActiveProfiles("production")
public class CaseRepositoryTest {

    @Autowired
    private CaseRepository repository;

    @Test
    public void test() {
        Request request = new Request();
        request.setMethod("GET");
        request.setPath("/posts/1");
        request.setBody("{}");
        request.setQueries(Collections.singletonMap("a", "1"));
        request.setHeaders(Collections.singletonMap("Accept", "application/json"));

        Service service = new Service();
        service.setId("5912a299ec46ff6c417a9482");

        Case toSave = new Case();
        toSave.setTitle("test title");
        toSave.setDescription("test description");

        toSave.setRequest(request);
        toSave.setService(service);

        Case saved = repository.save(toSave);
        System.out.println("# saved: " + saved);

        Case found = repository.findOne(saved.getId());
        System.out.println("# found: " + found);
    }

    @Test
	public void test2() {
	    for (Case aCase : repository.findAll()) {
		    System.out.println("# " + aCase);
	    }
    }

    @Test
	public void testFindByServiceId() {
	    List<Case> cases = repository.findByServiceId("5912a299ec46ff6c417a9482");
	    System.out.println("# cases: " + cases);
    }

}
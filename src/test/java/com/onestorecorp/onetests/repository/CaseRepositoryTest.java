package com.onestorecorp.onetests.repository;

import com.onestorecorp.onetests.domain.Case;
import com.onestorecorp.onetests.domain.Request;
import com.onestorecorp.onetests.domain.Service;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataMongoTest
@ActiveProfiles("production")
public class CaseRepositoryTest {

    @Autowired
    private CaseRepository repository;

	@Ignore
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
	    System.out.println("# saved.getCreatedDate(): " + saved.getCreatedDate());

        Case found = repository.findOne(saved.getId());
        System.out.println("# found: " + found);
	    System.out.println("# found.getCreatedDate(): " + saved.getCreatedDate());
	    System.out.println("# found.getLastModifiedDate(): " + saved.getLastModifiedDate());

	    request.setQueries(Collections.singletonMap("b", "2"));
        found.setRequest(request);

	    Case updated = repository.save(toSave);
	    System.out.println("# updated: " + saved);
	    System.out.println("# updated.getCreatedDate(): " + saved.getCreatedDate());
	    System.out.println("# updated.getLastModifiedDate(): " + saved.getLastModifiedDate());

		repository.delete(updated);
    }

    @Test
	public void test2() {
	    for (Case aCase : repository.findAll()) {
		    System.out.println("# " + aCase);
	    }
    }

    @Test
	public void testFindByServiceId() {
	    Page<Case> cases = repository.findByServiceId("5912a299ec46ff6c417a9482", new PageRequest(0, 10));
	    System.out.println("# cases: " + cases.getContent());
	    assertThat(cases.getSize()).isEqualTo(10);
    }

	@Configuration
	@EnableMongoAuditing
	static class Config {}

}
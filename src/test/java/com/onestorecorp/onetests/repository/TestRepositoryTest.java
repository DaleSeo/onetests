package com.onestorecorp.onetests.repository;

import com.onestorecorp.onetests.domain.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TestRepositoryTest {

	@Autowired
	private TestRepository repository;


	@org.junit.Test
	public void test() {
		Test toSave = new Test();
		toSave.setTitle("ABCDE");
		repository.save(toSave);
	}

}
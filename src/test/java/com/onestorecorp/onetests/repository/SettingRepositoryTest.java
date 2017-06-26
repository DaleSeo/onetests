package com.onestorecorp.onetests.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
@ActiveProfiles("production")
public class SettingRepositoryTest {

    @Autowired
    private SettingRepository repository;

    @Test
    public void test() {
	    System.out.println("###" + repository.findAll());
    }

	@Test
	public void test2() {
		System.out.println("###" + repository.findOne("594c9ddc0cb6faf0257e2fe0"));
	}

}
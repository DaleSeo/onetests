package com.onestorecorp.onetests;

import com.onestorecorp.onetests.domain.Api;
import com.onestorecorp.onetests.repository.ApiRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataMig {

	@Autowired
	private ApiRepository repo;

	@Ignore
	@Test
	public void test() {
		RestOperations restOps = new RestTemplate();
		Map<String, Map> result = restOps.getForObject("https://api-diff.firebaseio.com/services/-KhQ6MTyGDA-W1CaALev/apis.json", Map.class);
		for (Map map : result.values()) {
			Api api = new Api();
			api.setTitle((String) map.get("title"));
			api.setMethod((String) map.get("method"));
			api.setPath((String) map.get("path"));
			api.setBody((String) map.get("text"));
			api.setExclusions((List) map.get("exclusions"));
			api.setSkip((boolean) map.get("skip"));
			System.out.println("#####" + api);
			repo.insert(api);
		}
	}

}

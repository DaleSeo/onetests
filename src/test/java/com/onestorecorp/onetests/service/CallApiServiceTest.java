package com.onestorecorp.onetests.service;

import com.onestorecorp.onetests.domain.Request;
import com.onestorecorp.onetests.domain.Response;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CallApiServiceTest {

	private CallApiService svc;

	@Before
	public void setUp() {
		svc = new CallApiService();
	}

	@Test
	public void testCallApiForGet() {
		Request req = new Request();
		req.setMethod("GET");
		req.setHost("http://jsonplaceholder.typicode.com");
		req.setPath("/posts/1");
		req.setHeaders(null);
		req.setBody(null);

		Response res = svc.invoke(req);
		System.out.println(res);

		assertThat(res.getStatusCode()).isEqualTo(200);
		assertThat(res.getStatusMessage()).isEqualTo("OK");
		assertThat(res.getHeaders()).containsEntry("Content-Type", "application/json; charset=utf-8");
		assertThat(res.getHeaders()).containsEntry("Content-Length", "292");
		assertThat(res.getBody()).isEqualTo("{\n" +
				"  \"userId\": 1,\n" +
				"  \"id\": 1,\n" +
				"  \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
				"  \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
				"}");
	}

	@Test
	public void testCallApiForPost() {
		Request req = new Request();
		req.setMethod("POST");
		req.setHost("http://jsonplaceholder.typicode.com");
		req.setPath("/posts");
		req.setHeaders(null);
		req.setBody("{\n" +
				"  \"body\": \"bar\",\n" +
				"  \"title\": \"foo\",\n" +
				"  \"userId\": 1\n" +
				"}");

		Response res = svc.invoke(req);
		System.out.println(res);

		assertThat(res.getStatusCode()).isEqualTo(201);
		assertThat(res.getStatusMessage()).isEqualTo("Created");
		assertThat(res.getHeaders()).containsEntry("Content-Type", "application/json; charset=utf-8");
		assertThat(res.getHeaders()).containsEntry("Content-Length", "15");
		assertThat(res.getBody()).isEqualTo("{\n" +
				"  \"id\": 101\n" +
				"}");
	}

	@Test
	public void test() {
		Request req = new Request();
		req.setMethod("GET");
		req.setPath("http://www.naver.com");
		req.setHeaders(null);
		req.setBody(null);

		Response res = svc.invoke(req);
		System.out.println(res);
	}

}
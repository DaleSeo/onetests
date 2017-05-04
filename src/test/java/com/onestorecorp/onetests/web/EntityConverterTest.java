package com.onestorecorp.onetests.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

public class EntityConverterTest {

	private EntityConverter converter;

	@Before
	public void setUp() {
		converter = new EntityConverter();
	}

	@Test
	public void testConvertRequestForGet() {
		Request request = new Request();
		request.setMethod("GET");
		request.setUrl("http://jsonplaceholder.typicode.com/posts/1");
		request.setHeaders(null);
		request.setBody("");

		RequestEntity<Object> entity = converter.convertRequest(request);
		System.out.println(entity);

		assertThat(entity.getMethod()).isEqualTo(HttpMethod.GET);
		assertThat(entity.getUrl()).isEqualTo(URI.create("http://jsonplaceholder.typicode.com/posts/1"));
		assertThat(entity.getBody()).isEqualTo("");
	}

	@Test
	public void testConvertRequestForPost() {
		Request request = new Request();
		request.setMethod("POST");
		request.setUrl("http://jsonplaceholder.typicode.com/posts");
		request.setHeaders(null);
		request.setBody("{\n" +
				"  \"body\": \"bar\",\n" +
				"  \"title\": \"foo\",\n" +
				"  \"userId\": 1\n" +
				"}");

		RequestEntity<Object> entity = converter.convertRequest(request);
		System.out.println(entity);

		assertThat(entity.getMethod()).isEqualTo(HttpMethod.POST);
		assertThat(entity.getUrl()).isEqualTo(URI.create("http://jsonplaceholder.typicode.com/posts"));
		assertThat(entity.getBody()).isEqualTo("{\n" +
				"  \"body\": \"bar\",\n" +
				"  \"title\": \"foo\",\n" +
				"  \"userId\": 1\n" +
				"}");
	}

	@Test
	public void testConvertResponse() {
		String body = "{\n" +
				"  \"body\": \"bar\",\n" +
				"  \"id\": 101,\n" +
				"  \"title\": \"foo\",\n" +
				"  \"userId\": 1\n" +
				"}";
		HttpHeaders headers = new HttpHeaders();
		headers.set("expires", "-1");
		headers.set("pragma", "no-cache");
		ResponseEntity<String> entity = new ResponseEntity<>(body, headers, HttpStatus.OK);

		Response response = converter.convertResponse(entity);
		System.out.println(response);

		assertThat(response.getStatusCode()).isEqualTo(200);
		assertThat(response.getStatusMessage()).isEqualTo("OK");
		assertThat(response.getBody()).isEqualTo(body);
		assertThat(response.getText()).isEqualTo(body);
		assertThat(response.getHeaders()).isEqualTo(headers);
	}

}
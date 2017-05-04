package com.onestorecorp.onetests.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

public class JacksonTest {

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void test2() throws IOException {
		String text = "{\"body\":{\"body\":\"bar\",\"title\":\"foo\",\"userId\":\"1\"},\"method\":\"POST\",\"url\":\"http://jsonplaceholder.typicode.com/posts\"}";
		Request value = mapper.readValue(text, Request.class);
		System.out.println(value);
	}

}

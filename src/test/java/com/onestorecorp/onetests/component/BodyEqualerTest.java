package com.onestorecorp.onetests.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BodyEqualerTest {

    private BodyEqualer equaler;

    @Before
    public void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> exclusions = Arrays.asList("date", "time");
        equaler = new BodyEqualer(objectMapper, exclusions);
    }

    @Test
    public void givenBlank() {
        assertThat(equaler.isEqual(null, null)).isTrue();
        assertThat(equaler.isEqual("", "")).isTrue();
        assertThat(equaler.isEqual("", "\"TEST\"")).isFalse();
        assertThat(equaler.isEqual("\"TEST\"", null)).isFalse();
    }

    @Test
    public void givenNumbers() {
        assertThat(equaler.isEqual("1", "1")).isTrue();
        assertThat(equaler.isEqual("1", "2")).isFalse();
    }

    @Test
    public void givenStrings() {
        assertThat(equaler.isEqual("\"TEXT\"", "\"TEXT\"")).isTrue();
        assertThat(equaler.isEqual("\"TEXT\"", "\"TEST\"")).isFalse();
    }

    @Test
    public void givenArrays() {
        assertThat(equaler.isEqual("[\"a\", \"b\"]", "[\"a\", \"b\"]")).isTrue();
        assertThat(equaler.isEqual("[\"a\", \"b\"]", "[\"a\", \"b\", \"c\"]")).isFalse();
        assertThat(equaler.isEqual("[1, \"TEXT\", {\"b\": 2}]", "[1, \"TEXT\", {\"b\": 2}]")).isTrue();
        assertThat(equaler.isEqual("[1, \"TEXT\", {\"b\": 2}]", "[1, \"TEXT\", {\"b\": 3}]")).isFalse();
    }

    @Test
    public void givenObjects() {
        assertThat(equaler.isEqual("{}", "{}")).isTrue();
        assertThat(equaler.isEqual("{}", "{\"a\": 2}")).isTrue();
        assertThat(equaler.isEqual("{\"a\": 1}", "{\"a\": 2}")).isFalse();
        assertThat(equaler.isEqual("{\"a\": 1}", "{\"a\": 1, \"b\": 2}")).isTrue();
        assertThat(equaler.isEqual("{\"a\": 1, \"date\": \"Tue, 18 Apr 2017 02:16:21 GMT\"}", "{\"a\": 1, \"date\": \"Tue, 17 Apr 2017 02:16:21 GMT\"}")).isTrue();
        assertThat(equaler.isEqual("{\"a\": 1, \"date1\": \"Tue, 18 Apr 2017 02:16:21 GMT\"}", "{\"a\": 1, \"date1\": \"Tue, 17 Apr 2017 02:16:21 GMT\"}")).isFalse();
        assertThat(equaler.isEqual("{\"a\": 1, \"b\": {\"c\": \"text\"}, \"d\": [2, \"ttt\"]}", "{\"a\": 1, \"b\": {\"c\": \"text\"}, \"d\": [2, \"ttt\"]}")).isTrue();
        assertThat(equaler.isEqual("{\"a\": 1, \"b\": {\"c\": \"text\"}, \"d\": [2, \"ttt\"]}", "{\"a\": 1, \"b\": {\"c\": \"text\"}, \"d\": [2, \"ttT\"]}")).isFalse();
    }

}
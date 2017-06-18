package com.onestorecorp.onetests.component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class BodyEqualer {

    private static final Logger logger = LoggerFactory.getLogger(BodyEqualer.class);

    private ObjectMapper objectMapper;
    private JsonNodeEqualer jsonNodeEqualer;

    public BodyEqualer(ObjectMapper objectMapper, List<String> exclusions) {
        this.objectMapper = objectMapper;
        jsonNodeEqualer = new JsonNodeEqualer(exclusions);
    }

    boolean isEqual(String expected, String actual) {
        if (areBothBlank(expected, actual)) return true;
        if (isEitherBlank(expected, actual)) return false;

        try {
            JsonNode expectedNode = objectMapper.readTree(expected);
            JsonNode actualNode = objectMapper.readTree(actual);

            return jsonNodeEqualer.isNodeEqual(expectedNode, actualNode);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse the JSON.", e);
        }
    }

    private boolean areBothBlank(String expected, String actual) {
        return StringUtils.isBlank(expected) && StringUtils.isBlank(actual);
    }

    private boolean isEitherBlank(String expected, String actual) {
        return (StringUtils.isBlank(expected) && StringUtils.isNotBlank(actual))
            || (StringUtils.isNotBlank(expected) && StringUtils.isBlank(actual));
    }

}

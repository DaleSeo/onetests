package com.onestorecorp.onetests.component;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class JsonNodeEqualer {

    private static final Logger logger = LoggerFactory.getLogger(JsonNodeEqualer.class);

    private Set<String> exclusions;

    public JsonNodeEqualer(Collection<String> exclusions) {
        this.exclusions = new HashSet<>(exclusions);
    }

    public boolean isNodeEqual(JsonNode nodeA, JsonNode nodeB) {
        if (nodeA.isObject() && nodeB.isObject()) {
            return isObjectEqual(nodeA, nodeB);
        }

        if (nodeA.isArray() && nodeB.isArray()) {
            return isArrayEqual(nodeA, nodeB);
        }

        return nodeA.equals(nodeB);
    }

    public boolean isArrayEqual(JsonNode arrA, JsonNode arrB) {
        logger.debug("#isArrayEqual: {} VS. {}", arrA, arrB);
        if (arrA.size() != arrB.size()) return false;
        for (int i = 0; i < arrA.size(); i++) {
            if (!isNodeEqual(arrA.get(i), arrB.get(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean isObjectEqual(JsonNode objA, JsonNode objB) {
        logger.debug("#isObjectEqual: {} VS. {}", objA, objB);
        for (Iterator<String> iter = objA.fieldNames(); iter.hasNext(); ) {
            String key = iter.next();
            if (exclusions.contains(key)) {
                logger.debug("{} is ignored", key);
                continue;
            }
            if (!isNodeEqual(objA.get(key), objB.get(key))) {
                return false;
            }
        }
        return true;
    }

}

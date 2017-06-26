package com.onestorecorp.onetests.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HeaderEqualer {

    private static final Logger logger = LoggerFactory.getLogger(HeaderEqualer.class);

	Set<String> exclusions;

    public HeaderEqualer(Collection<String> exclusions) {
    	this.exclusions = new HashSet<>(exclusions);
    }

    boolean isEqual(Map<String, String> expected, Map<String, String> actual) {
	    Set<String> keySet = expected.keySet();
	    keySet.removeAll(exclusions);
	    for (String key : keySet) {
		    if (!actual.containsKey(key)) {
			    return false;
		    }

		    String expectedValue = expected.get(key);
		    String actualValue = actual.get(key);

		    if (!expectedValue.equals(actualValue)) {
			    return false;
		    }
	    }
	    return true;
    }

}

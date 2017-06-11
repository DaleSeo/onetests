package com.onestorecorp.onetests.component;

import com.onestorecorp.onetests.domain.Response;
import com.onestorecorp.onetests.domain.Result;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SimpleResponseEqualer implements ResponseEqualer {

	@Override
	public Result equals(Response expected, Response actual) {
		Result result = new Result();
		if (expected == null) {
			result.setStatusEqual(true);
			result.setHeadersEqual(true);
			result.setBodyEqual(true);
			result.setPassed(true);
		} else {
			result.setStatusEqual(expected.getStatusCode() == expected.getStatusCode());
			result.setHeadersEqual(equalsHeaders(expected.getHeaders(), actual.getHeaders()));
			result.setBodyEqual(equalsBody(expected.getBody(), actual.getBody()));
			result.setPassed(result.isStatusEqual() && result.isHeadersEqual() && result.isBodyEqual());
		}
		return result;
	}

	private boolean equalsHeaders(Map<String, String> expected, Map<String, String> actual) {
		for (String key : expected.keySet()) {
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

	private boolean equalsBody(String expected, String actual) {
	    if (expected == null) return true;
        return expected.equals(actual);
    }

}

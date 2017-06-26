package com.onestorecorp.onetests.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onestorecorp.onetests.domain.Response;
import com.onestorecorp.onetests.domain.Result;
import com.onestorecorp.onetests.domain.Setting;
import com.onestorecorp.onetests.repository.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SimpleResponseEqualer implements ResponseEqualer {

	private HeaderEqualer headerEqualer;
	private BodyEqualer bodyEqualer;

	@Autowired
	public SimpleResponseEqualer(SettingRepository settingRepository, ObjectMapper objectMapper) {
		System.out.println("#settingRepository.findAll(): " + settingRepository.findAll());
		Setting setting = settingRepository.findAll()
				.stream()
				.findFirst()
				.orElseGet(Setting::new);
		headerEqualer = new HeaderEqualer(setting.getHeaderExclusions());
		bodyEqualer = new BodyEqualer(objectMapper, setting.getHeaderExclusions());
	}

	@Override
	public Result equals(Response expected, Response actual) {
		Result result = new Result();
		if (expected == null) {
			result.setStatusEqual(true);
			result.setHeadersEqual(true);
			result.setBodyEqual(true);
			result.setPassed(true);
		} else {
			result.setStatusEqual(expected.getStatusCode() == actual.getStatusCode());
			result.setHeadersEqual(equalsHeaders(expected.getHeaders(), actual.getHeaders()));
			result.setBodyEqual(equalsBody(expected.getBody(), actual.getBody()));
			result.setPassed(result.isStatusEqual() && result.isHeadersEqual() && result.isBodyEqual());
		}
		return result;
	}

	private boolean equalsHeaders(Map<String, String> expected, Map<String, String> actual) {
		return headerEqualer.isEqual(expected, actual);
	}

	private boolean equalsBody(String expected, String actual) {
	    if (expected == null) return true;
        return bodyEqualer.isEqual(expected, actual);
    }

}

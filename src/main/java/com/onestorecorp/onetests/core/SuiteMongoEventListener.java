package com.onestorecorp.onetests.core;

import com.onestorecorp.onetests.domain.Case;
import com.onestorecorp.onetests.domain.Host;
import com.onestorecorp.onetests.domain.Service;
import com.onestorecorp.onetests.domain.Suite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SuiteMongoEventListener extends AbstractMongoEventListener<Suite> {

	private static final Logger logger = LoggerFactory.getLogger(SuiteMongoEventListener.class);

	@Override
	public void onBeforeConvert(BeforeConvertEvent<Suite> event) {
		Suite suite = event.getSource();
		List<Case> cases = suite.getCaseIds()
				.parallelStream()
				.map(Case::new)
				.collect(Collectors.toList());
		suite.setCases(cases);
		logger.debug("# before convert a suite: {}", suite);
	}

}
package com.onestorecorp.onetests.component.mongo;

import com.onestorecorp.onetests.domain.Case;
import com.onestorecorp.onetests.domain.Host;
import com.onestorecorp.onetests.domain.Service;
import com.onestorecorp.onetests.domain.Suite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 서대영(DAEYOUNG SEO)/Onestore/SKP
 */
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

		if (suite.getServiceId() != null) {
			suite.setService(new Service(suite.getServiceId()));
		}

		if (suite.getHostId() != null) {
			suite.setHost(new Host(suite.getHostId()));
		}
		logger.debug("# before convert a suite: {}", suite);
	}

}
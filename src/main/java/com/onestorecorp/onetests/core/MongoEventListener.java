package com.onestorecorp.onetests.core;

import com.onestorecorp.onetests.domain.Case;
import com.onestorecorp.onetests.domain.Host;
import com.onestorecorp.onetests.domain.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

public class MongoEventListener extends AbstractMongoEventListener<Object> {

	private static final Logger logger = LoggerFactory.getLogger(MongoEventListener.class);

	@Override
	public void onBeforeConvert(BeforeConvertEvent<Object> event) {
		Object source = event.getSource();
		if (event.getSource() instanceof Case) {
			Case aCase = (Case) source;
			if (aCase.getServiceId() != null) {
				aCase.setService(new Service(aCase.getServiceId()));
			}
			if (aCase.getHostId() != null) {
				aCase.setHost(new Host(aCase.getHostId()));
			}
			logger.debug("# before save a case: {}", aCase);
		}
	}
}
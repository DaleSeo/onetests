package com.onestorecorp.onetests.component.mongo;

import com.onestorecorp.onetests.domain.Host;
import com.onestorecorp.onetests.domain.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class HostMongoEventListener extends AbstractMongoEventListener<Host> {

	private static final Logger logger = LoggerFactory.getLogger(HostMongoEventListener.class);

	@Override
	public void onBeforeConvert(BeforeConvertEvent<Host> event) {
		Host host = event.getSource();

		if (host.getServiceId() != null) {
			host.setService(new Service(host.getServiceId()));
		}
		
		logger.debug("# before save a host: {}", host);
	}
}
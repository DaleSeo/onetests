package com.onestorecorp.onetests.component.mongo;

import com.onestorecorp.onetests.domain.Case;
import com.onestorecorp.onetests.domain.Host;
import com.onestorecorp.onetests.domain.Service;
import com.onestorecorp.onetests.repository.HostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class CaseMongoEventListener extends AbstractMongoEventListener<Case> {

	private static final Logger logger = LoggerFactory.getLogger(CaseMongoEventListener.class);

	@Autowired
	private HostRepository hostRepo;

	@Override
	public void onBeforeConvert(BeforeConvertEvent<Case> event) {
		Case aCase = event.getSource();

		if (aCase.getServiceId() != null) {
			aCase.setService(new Service(aCase.getServiceId()));
		}
		if (aCase.getHostId() != null) {
			aCase.setHost(new Host(aCase.getHostId()));
			Host host = hostRepo.findOne(aCase.getHostId());
			aCase.getRequest().setHost(host.getBaseUrl());
		}

		logger.debug("# before save a case: {}", aCase);
	}
}
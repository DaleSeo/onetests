package com.onestorecorp.onetests.component.mongo;

import com.onestorecorp.onetests.domain.Case;
import com.onestorecorp.onetests.domain.Environment;
import com.onestorecorp.onetests.domain.Service;
import com.onestorecorp.onetests.repository.EnvironmentRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

/**
 * @author 서대영(DAEYOUNG SEO)/Onestore/SKP
 */
@Component
public class CaseMongoEventListener extends AbstractMongoEventListener<Case> {

	private static final Logger logger = LoggerFactory.getLogger(CaseMongoEventListener.class);

	@Autowired
	private EnvironmentRepository environmentRepo;

//	@Autowired
//	private HostRepository hostRepo;

	@Override
	public void onBeforeConvert(BeforeConvertEvent<Case> event) {
		Case aCase = event.getSource();

		if (StringUtils.isNotEmpty(aCase.getServiceId())) {
			aCase.setService(new Service(aCase.getServiceId()));
		}
		if (StringUtils.isNotEmpty(aCase.getEnvironmentId())) {
			aCase.setEnvironment(new Environment(aCase.getEnvironmentId()));
//			Environment environment = environmentRepo.findOne(aCase.getEnvironmentId());
//			aCase.getRequest().setHost(environment.getHost());
		}
//		if (aCase.getHostId() != null) {
//			aCase.setHost(new Host(aCase.getHostId()));
//			Host host = hostRepo.findOne(aCase.getHostId());
//			aCase.getRequest().setHost(host.getBaseUrl());
//		}

		logger.debug("# before save a case: {}", aCase);
	}
}
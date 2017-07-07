package com.onestorecorp.onetests.service;

import com.onestorecorp.onetests.domain.Service;
import com.onestorecorp.onetests.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author 서대영(DAEYOUNG SEO)/Onestore/SKP
 */
@org.springframework.stereotype.Service
@CacheConfig(cacheNames = "service")
public class ServiceService {

	@Autowired
	private ServiceRepository repository;

	@Cacheable
	public Service findOne(String id) {
		return repository.findOne(id);
	}

}

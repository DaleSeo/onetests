package com.onestorecorp.onetests.repository;

import com.onestorecorp.onetests.domain.Setting;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SettingRepository extends MongoRepository<Setting, String> {

}

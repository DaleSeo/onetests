package com.onestorecorp.onetests.core;

import com.onestorecorp.onetests.common.SecurityUtils;
import com.onestorecorp.onetests.domain.User;
import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<User> {

    @Override
    public User getCurrentAuditor() {
    	return SecurityUtils.getCurrentUser().orElse(null);
    }

}

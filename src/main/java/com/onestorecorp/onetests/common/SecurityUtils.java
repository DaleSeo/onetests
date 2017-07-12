package com.onestorecorp.onetests.common;

import com.onestorecorp.onetests.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @author 서대영(DAEYOUNG SEO)/Onestore/SKP
 */
public class SecurityUtils {

	public static Optional<User> getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()) {
			return Optional.empty();
		}

		if (!(authentication.getPrincipal() instanceof User)) {
			return Optional.empty();
		}

		return Optional.of((User) authentication.getPrincipal());
	}

	public static boolean isCurrentUser(User user) {
		if (user == null) return false;

		User currentUser = getCurrentUser().orElse(null);
		if (currentUser == null) return false;

		return user.getId().equals(currentUser.getId());
	}

}

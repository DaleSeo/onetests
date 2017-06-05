package com.onestorecorp.onetests.core;

import com.onestorecorp.onetests.domain.User;
import com.onestorecorp.onetests.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = repository.findOneByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("No email found");
		}

		return user;
	}

}
package com.onestorecorp.onetests.core;

import com.onestorecorp.onetests.domain.User;
import com.onestorecorp.onetests.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

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

		return new UserDetails() {
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				String[] roles = user.getRoles().toArray(new String[user.getRoles().size()]);
				return AuthorityUtils.createAuthorityList(roles);
			}

			@Override
			public String getPassword() {
				return user.getPassword();
			}

			@Override
			public String getUsername() {
				return user.getEmail();
			}

			@Override
			public boolean isAccountNonExpired() {
				return true;
			}

			@Override
			public boolean isAccountNonLocked() {
				return true;
			}

			@Override
			public boolean isCredentialsNonExpired() {
				return true;
			}

			@Override
			public boolean isEnabled() {
				return true;
			}
		};
	}

}
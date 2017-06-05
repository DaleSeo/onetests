package com.onestorecorp.onetests.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Document
@Data
public class User implements UserDetails {

    @Id
    private String id;

    @CreatedDate
    private Date createdDate;

    @Indexed(unique = true)
    private String email;
    private String password;

    private String name;
    private String employeeNo;

    private List<String> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String[] authorities = roles.toArray(new String[roles.size()]);
		return AuthorityUtils.createAuthorityList(authorities);
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
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

}

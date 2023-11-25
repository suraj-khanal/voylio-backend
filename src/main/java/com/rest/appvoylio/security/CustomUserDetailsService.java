package com.rest.appvoylio.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rest.appvoylio.entity.RoleEntity;
import com.rest.appvoylio.entity.SignupEntity;
import com.rest.appvoylio.repository.SignupRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private SignupRepository signupRepository;
	
	@Autowired
	public CustomUserDetailsService(SignupRepository signupRepository) {
		this.signupRepository = signupRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SignupEntity user = signupRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username Not Found "+ username));
		
		return new User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}
	
	private Collection<GrantedAuthority> mapRolesToAuthorities(Set<RoleEntity> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toSet());
	}

}










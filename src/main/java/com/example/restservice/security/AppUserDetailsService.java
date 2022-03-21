package com.example.restservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private SecurityRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel user = repo.findByName(username);
		if (user == null)
			throw new UsernameNotFoundException("user not found");
		else
		{
			UserPrincipal principal = new UserPrincipal(user);
			return principal;
		}
			
			
	}

}

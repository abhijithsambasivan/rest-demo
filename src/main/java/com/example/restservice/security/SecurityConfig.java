package com.example.restservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	
	//old
	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { auth.inMemoryAuthentication().
	 * withUser("abhi").password("pass1").roles("USER").and()
	 * .withUser("ADMIN").password("root").roles("ADMIN"); }
	 */

	
	/******************************************************
	//in memory 
	@Bean
	public UserDetailsService userDetails() {
		List<UserDetails> userdetails = new ArrayList<UserDetails>();
		userdetails.add(User.builder().username("abhi").password("pass1").roles("USER").build());
		userdetails.add(User.builder().username("admin").password("root").roles("ADMIN").build());

		return new InMemoryUserDetailsManager(userdetails);
	}

	**************************************************************/
	
	
//	@Bean
//	public AuthenticationProvider provider() {
//		
//	DaoAuthenticationProvider daoprovider = new DaoAuthenticationProvider();
//	daoprovider.setUserDetailsService(userDetailsService);
//	daoprovider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//	return daoprovider;
//	
//	}
	
	
	
	  @Override
	    public void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	    }
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);// very important
		// without this we will have issues with authorisation
	
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http
		.authorizeRequests().
		antMatchers("/login").permitAll()
		.antMatchers("/employees").authenticated()
			.antMatchers("/employees/*").hasRole("ADMIN")
			.and().addFilter(new JwtAuthenticationFilter(authenticationManager()))
			.addFilter(new JwtAuthorisationFilter(authenticationManager()))
			;

	}

	
    private PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder(12);  
        return encoder;
    }
	
	
}

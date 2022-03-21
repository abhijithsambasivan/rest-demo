package com.example.restservice.security;

import java.io.IOException;
import java.security.Key;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private static final long EXPIRATION = 1000 * 60 * 60*24*5;
	private static Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	AuthenticationManager authenticationManager;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			ClientAuthJwtModel model = new ObjectMapper().readValue(request.getInputStream(), ClientAuthJwtModel.class);
			Authentication authenticate = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken
							(model.getName(), model.getPassword()));
			return authenticate;
			
		} catch (IOException e) {
			throw new RuntimeException();
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		Key key = Keys.hmacShaKeyFor("thisisapasswordsecretthatshouldbeused".getBytes());

		Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION);

		String jws = Jwts.builder().setSubject(((UserPrincipal)
				authResult.getPrincipal()).getUsername())
				.signWith(key, SignatureAlgorithm.HS256)
				.setExpiration(expirationDate).compact();
		
		
		log.info("token will expire at " + expirationDate);
		log.info("token is:" + jws);
		response.setHeader("jwt_token", jws);
	}

}
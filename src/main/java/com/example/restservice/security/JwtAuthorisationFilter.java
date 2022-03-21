package com.example.restservice.security;



import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtAuthorisationFilter extends BasicAuthenticationFilter {
	private final static Logger logger = LoggerFactory.getLogger(JwtAuthorisationFilter.class);
	public JwtAuthorisationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader("JWT");

		if (header == null) {
			// no token in request

logger.info("token is not present in request");
			chain.doFilter(request, response);
			return;
		}
logger.info("request recieved for jwt verification");
		UsernamePasswordAuthenticationToken authentication = authenticate(request);

		//this line is also important
		//without this we will get forbidden
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);//to allow control to go to the actual rest controller
	}

	private UsernamePasswordAuthenticationToken authenticate(HttpServletRequest request) {

		String token = request.getHeader("JWT");
	try {
		
		if (token != null) {
			logger.info("token extracted from request header....");
;

			Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey((Keys.hmacShaKeyFor("thisisapasswordsecretthatshouldbeused".getBytes()))).build()
					.parseClaimsJws(token);
			Claims user = claimsJws.getBody();

			if (user != null) {
				logger.info("token is valid");
				// third parameter is authority
				// for simplicity we are not considering roles or authorities of user

				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			} else {
				return null;
			}

		}
		
	}
	catch(Exception e)
	{
		logger.info("token is invalid");
		logger.info(e.getMessage());
	
	}
	
		
		return null;
	}
	
}

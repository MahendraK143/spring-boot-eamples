package com.zuul.employeezuulapigatewayservice.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


import io.jsonwebtoken.Jwts;

public class AuthenticationFilter extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// We don't need CSRF for this example
		httpSecurity.csrf().disable()
				// dont authenticate this particular request
				.authorizeRequests().antMatchers("/authenticate", "/register", "swagger-ui.html").
				permitAll().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().
				// all other requests need to be authenticated
				anyRequest().authenticated().and().
				// make sure we use stateless session; session won't be used to
				// store user's state.
				exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
//extends BasicAuthenticationFilter{
//
//	private Environment environment;
//	public AuthenticationFilter(AuthenticationManager authenticationManager,
//			Environment environment) {
//		super(authenticationManager);
//		this.environment = environment;
//	}
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		String authorizationHeader = request.getHeader(environment.getProperty("authorization.token.header.name"));
//		
//		if(authorizationHeader !=null || authorizationHeader.startsWith(environment.getProperty("authorization.token.header.prefix"))) {
//			chain.doFilter(request, response);
//			return;
//		}
//		
//		UsernamePasswordAuthenticationToken authentication = authorizationHeader(request,authorizationHeader);
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		chain.doFilter(request, response);
//	}
//	
//	public UsernamePasswordAuthenticationToken authorizationHeader(HttpServletRequest request, String authorizationHeader) {
//		String token = authorizationHeader.substring(7);
//		
//		String userId = Jwts.parser()
//						.setSigningKey(environment.getProperty("token.secret"))
//						.parseClaimsJws(token)
//						.getBody()
//						.getSubject();
//		if(userId == null) return null;
//		
//		return new UsernamePasswordAuthenticationToken(userId, null, new ArrayList());
//	}
}

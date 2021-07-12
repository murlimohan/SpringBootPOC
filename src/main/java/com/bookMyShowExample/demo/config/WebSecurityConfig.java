package com.bookMyShowExample.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bookMyShowExample.demo.filter.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { auth .inMemoryAuthentication() .withUser("user")
	 * .password(encoder().encode("password")) .roles("USER") .and()
	 * .withUser("admin") .password(encoder().encode("admin")) .roles("USER",
	 * "ADMIN"); }
	 * 
	 * 
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.csrf().disable() .authorizeRequests()
	 * .antMatchers("/user/**").access("hasRole('USER')")
	 * .antMatchers("/admin/**").access("hasRole('ADMIN')") .anyRequest()
	 * .authenticated() .and() .httpBasic(); }
	 */

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	private UserDetailsService jwtUserDetailsService;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.csrf().disable() 
		 .authorizeRequests().antMatchers("/authenticate").permitAll()
		 .anyRequest().authenticated().and()
		 .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
		 .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 
		 http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	/*
	 * @Bean public PasswordEncoder encoder() { return new BCryptPasswordEncoder();
	 * }
	 */
	
	@Bean
	public PasswordEncoder encoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}

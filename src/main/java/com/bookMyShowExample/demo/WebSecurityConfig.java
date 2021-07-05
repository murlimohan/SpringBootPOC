package com.bookMyShowExample.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {	

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth
          .inMemoryAuthentication()
          .withUser("user")
          .password(encoder().encode("password"))
          .roles("USER")
          .and()
          .withUser("admin")
          .password(encoder().encode("admin"))
          .roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
          .authorizeRequests()
          .antMatchers("/user/**").access("hasRole('USER')")
          .antMatchers("/admin/**").access("hasRole('ADMIN')")
          .anyRequest()
          .authenticated()
          .and()
          .httpBasic();
    }
    
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}

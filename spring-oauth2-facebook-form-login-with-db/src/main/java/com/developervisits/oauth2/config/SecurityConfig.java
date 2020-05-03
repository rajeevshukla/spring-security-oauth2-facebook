package com.developervisits.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
    
	  http.authorizeRequests().antMatchers("/login").permitAll()
     .anyRequest().authenticated()
     .and()
     .formLogin().loginPage("/login").defaultSuccessUrl("/formLoginSuccess")
     .and().logout()
     .and().oauth2Login().loginPage("/login").defaultSuccessUrl("/oauth2LoginSuccess")
     .and().csrf().disable();

	}
	
}

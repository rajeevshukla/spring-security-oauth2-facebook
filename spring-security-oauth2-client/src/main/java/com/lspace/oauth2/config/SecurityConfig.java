package com.lspace.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
       
		//If you don't disable csrf then make sure that you call logout URL with post request.
		http.csrf().disable().authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").defaultSuccessUrl("/formLoginSuccess").permitAll()
				.and().logout().permitAll()
				.and().oauth2Login()
				.loginPage("/login").defaultSuccessUrl("/oauth2LoginSuccess");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	// Adding in memory User for form based login
		auth.inMemoryAuthentication()
		.passwordEncoder(NoOpPasswordEncoder.getInstance())
		.withUser("admin")
		.password("admin")
		.roles("USER");	
	}
}

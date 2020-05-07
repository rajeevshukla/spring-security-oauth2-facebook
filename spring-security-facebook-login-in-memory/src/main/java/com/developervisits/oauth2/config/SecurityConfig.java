package com.developervisits.oauth2.config;

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

		http.authorizeRequests().antMatchers("/login").permitAll()
		        .anyRequest().authenticated() 
		        .and().formLogin() // enable form based login
				.loginPage("/login").defaultSuccessUrl("/formLoginSuccess")
				.and().logout() // enable logout 
				.and().oauth2Login() // enable OAuth2 
				.loginPage("/login").defaultSuccessUrl("/oauth2LoginSuccess")
				.and().csrf().disable(); // disable CSRF
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

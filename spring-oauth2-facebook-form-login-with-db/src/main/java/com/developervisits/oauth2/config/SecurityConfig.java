package com.developervisits.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.developervisits.oauth2.service.OAuth2ServiceImpl;
import com.developervisits.oauth2.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	OAuth2ServiceImpl oauth2UserServiceImpl;
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
		auth.userDetailsService(userDetailsService);
	}
	// @formatter:off
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/login","/register").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").usernameParameter("email").passwordParameter("password")
		.defaultSuccessUrl("/formLoginSuccess").failureUrl("/login?error=true")
		.and().logout() // POST 
		.and().oauth2Login().loginPage("/login").userInfoEndpoint().userService(oauth2UserServiceImpl)
		.and().defaultSuccessUrl("/oauth2LoginSuccess",true)
		.and().rememberMe().key("uniqueKey").rememberMeParameter("rm") 
		// this will create cookie which will have  the previous 2 values – username and expirationTime, plus the password and the predefined key
		// this mechanism is potentially vulnerable because if cookie is captured then anyone can login until it is expired. 
		.and()
		.csrf().disable();
	}
	//@formatter:on

	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

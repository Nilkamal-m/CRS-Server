package com.neel.ComplaintRedressalSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.neel.ComplaintRedressalSystem.service.CustomUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	


	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/login", "/signup");
//	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/test/**","/login", "/signup").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN")
			.antMatchers("/engg/**").hasAnyRole("ENGG","MANAGER","ADMIN")
			.antMatchers("/user/**").hasAnyRole("USER","ENGG","MANAGER","ADMIN")

			.anyRequest()
			.authenticated()
			.and()
			.httpBasic()
			.and().cors()
			.and().csrf().disable();
	}
	
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
	
}

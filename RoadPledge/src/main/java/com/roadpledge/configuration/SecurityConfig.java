package com.roadpledge.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.roadpledge.security.CitizenUserDetailService;

import ch.qos.logback.classic.selector.servlet.LoggerContextFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	protected UserDetailsService userDetailService(){
		return  new CitizenUserDetailService();
	}
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authenticate = new DaoAuthenticationProvider();
		authenticate.setUserDetailsService(userDetailService());
		authenticate.setPasswordEncoder(passwordEncoder());
		return authenticate;
	}
	

//	@Bean
//	  WebSecurityCustomizer ignoringCustomizer() {
//	        return (web) -> web.ignoring().antMatchers("/up_govt/citizen/**");
//	 }
//	 
//	
	
	 @Bean
	    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http.csrf().disable()
	                .cors().disable()
	                .antMatcher("/up_govt/citizen/**")
	                .authorizeRequests()
	                .antMatchers("/up_govt/admin/**").hasAnyRole("USER", "ADMIN")
	                .anyRequest()
	                .authenticated()
	                .and()
	                .formLogin().loginPage("/HomePage1.html")
	                .defaultSuccessUrl("/up_govt/admin/registered_citizen", true)
	                .and()
	                .httpBasic();
	         
	        return http.build();
	 }

	
	 @Bean
	 FilterRegistrationBean<LoggerContextFilter> logFilter() {
	     FilterRegistrationBean<LoggerContextFilter> registrationBean = new FilterRegistrationBean<>();
	     registrationBean.setFilter(new LoggerContextFilter());
	     registrationBean.addUrlPatterns("/up_govt/citizen/**");
	     return registrationBean;
	 }

}

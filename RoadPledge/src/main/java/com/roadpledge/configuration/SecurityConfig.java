package com.roadpledge.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.roadpledge.security.CitizenUserDetailService;

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
	
	 @Bean
	    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http.csrf().disable()
	                .cors().disable()
	                .authorizeRequests()
	                .antMatchers(HttpMethod.GET, "/**").hasAnyRole("USER", "ADMIN")
	                .antMatchers(HttpMethod.POST, "/**").hasAnyRole("USER","ADMIN")
	                .anyRequest()
	                .authenticated().and().formLogin().defaultSuccessUrl("/road-safety", true)
	                .and()
	                .httpBasic();

	        return http.build();
	    }
	
	
	
	
}

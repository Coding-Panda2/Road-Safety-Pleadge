package com.roadpledge.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.roadpledge.entity.User;
import com.roadpledge.repository.UserRepository;

@Configuration
public class DataOnStartUp {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@EventListener(ApplicationReadyEvent.class)
	public void addDefaultData(ApplicationReadyEvent event){
		User user1 = User.builder().userName("OWNER").password(passwordEncoder.encode("0123456789")).role("ADMIN").build();
		User user2 = User.builder().userName("WORKER").password(passwordEncoder.encode("9876543210")).role("USER").build();
		userRepository.save(user1);
		userRepository.save(user2);
	}

}

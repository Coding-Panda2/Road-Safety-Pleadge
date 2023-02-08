package com.roadpledge.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roadpledge.entity.Citizen;
import com.roadpledge.repository.CitizenRepository;

@Service
@Transactional
public class CitizenService {
	
	@Autowired
	private CitizenRepository citizenRepository;
	
	public Citizen addCitizen(Citizen parameter) {
		
		Citizen citizen = Citizen.builder()
				          .fullName(parameter.getFullName())
				          .gender(parameter.getGender())
				          .dateOfBirth(parameter.getDateOfBirth())
				          .pincode(parameter.getPincode())
				          .state(parameter.getState())
				          .district(parameter.getDistrict())
				          .email(parameter.getEmail())
				          .mobileNo(parameter.getMobileNo())
				          .location(parameter.getLocation())
				          .ipAddress(parameter.getIpAddress())
				          .image(parameter.getImage()).build();
		
		return citizenRepository.save(citizen);
	}
	
	public List<Citizen> showAllCitizen(){
		
		return citizenRepository.findAll();
		
	}
	
	public Citizen searchById(int id) {
		
		Citizen citizen = citizenRepository.findById(id).orElseThrow();
		return citizen;
	}

}

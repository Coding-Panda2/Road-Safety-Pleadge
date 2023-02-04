package com.roadpledge.service;


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
				          .salutation(parameter.getSalutation())
				          .fullName(parameter.getFullName())
				          .gender(parameter.getGender())
				          .dateOfBirth(parameter.getDateOfBirth())
				          .pincode(parameter.getPincode())
				          .state(parameter.getPincode())
				          .district(parameter.getDistrict())
				          .email(parameter.getEmail())
				          .mobileNo(parameter.getMobileNo()).build();
		
		return citizenRepository.save(citizen);
	}

}

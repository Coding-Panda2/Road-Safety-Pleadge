package com.roadpledge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.roadpledge.entity.Citizen;
import com.roadpledge.service.CitizenService;

@Controller
@RequestMapping(path = "/citizen")
public class CitizenController {

	@Autowired
	private CitizenService citizenService;

	@GetMapping(path = "/road-safety")
	public String showHome() {
		return "HomePage1";
	}

	@GetMapping(path = "/registration")
	public String registrationForm() {
		return "citizen_registrationform";
	}
	

	
	@PostMapping(path = "/pledge")
	public String addCitizen(Citizen citizen) {
		citizenService.addCitizen(citizen);
		return "readpledge";
	}

	
	
	@GetMapping(path="/location")
	public void findLocation(@RequestParam("id") int id, Model theModel) {
		Citizen citizen = citizenService.searchById(id);
		theModel.addAttribute("findcitizen", citizen);
	}
	

}

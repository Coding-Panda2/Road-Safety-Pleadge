package com.roadpledge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.roadpledge.entity.Citizen;
import com.roadpledge.service.CitizenService;

@Controller
@RequestMapping(path="/admin")
public class AdminController {
	
	@Autowired
	private CitizenService citizenService;
	
	@PostMapping(path = "/registered_citizen")
	public String showAllCitizen(Model theModel) {
		List<Citizen> citizens = citizenService.showAllCitizen();
		theModel.addAttribute("citizens", citizens);
		return "registered_citizen";

	}

}

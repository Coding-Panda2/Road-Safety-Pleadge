package com.roadpledge.controller;

import java.io.File;
import java.io.IOException;

//Java Program to Create Rest Controller that
//Defines various API for Sending Mail

//Importing required classes

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roadpledge.entity.EmailDetails;
import com.roadpledge.service.EmailService;

//Annotation
@Controller
@RequestMapping(path="/mail")
public class EmailController {

	@Autowired
	private EmailService emailService;

	   // Sending a simple Email
    @PostMapping(path="/sendMail", consumes = "application/x-www-form-urlencoded")
    public String sendMail(@ModelAttribute EmailDetails details)
    {
    	FileSystemResource file = new FileSystemResource(new File(details.getAttachment().getPath()));
   	    
    	
    	System.out.println(file.getFilename());
    	System.out.println(file.getPath());

    	System.out.println(file.getDescription());
    
    	
    	
    	
    	if(file.getFilename().isEmpty()) {
        emailService.sendSimpleMail(details);
    	}
    	else {
        emailService.sendMailWithAttachment(details);
    	}
    	
        return "mailSent";
    }
}


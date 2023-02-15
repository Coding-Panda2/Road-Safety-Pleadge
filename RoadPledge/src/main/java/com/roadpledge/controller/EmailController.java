package com.roadpledge.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

//Java Program to Create Rest Controller that
//Defines various API for Sending Mail

//Importing required classes

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.roadpledge.entity.EmailDetails;
import com.roadpledge.service.EmailService;

//Annotation
@Controller
@RequestMapping(path="/mail")
public class EmailController {

	@Autowired
	private EmailService emailService;

	   // Sending a simple Email
    @PostMapping(path="/sendMail", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String sendMail(@ModelAttribute EmailDetails details) throws IOException
    {
    	MultipartFile upload = details.getAttachment(); 
    	String name = upload.getOriginalFilename();
//    	upload.transferTo();
//    	File document = new File(upload.getInputStream());
//    	FileSystemResource file2 = new FileSystemResource(null, null);
//    	File document2 = new File(upload.getOriginalFilename());
//    	Files document - new Files();
//    	FileSystemResource file = new FileSystemResource(new File(details.getAttachment().getOriginalFilename()));
//   	    
//    	System.out.println(upload.getName());
//    	System.out.println(upload.getOriginalFilename());
//    	System.out.println(upload.getContentType());
//    	System.out.println(upload.getBytes());
//    	System.out.println(upload.getResource());
//    	System.out.println(upload.getInputStream());
//    	System.out.println(upload.toString());
//    	System.out.println(System.getProperty("java.io.tmpdir"));
//    	
//    	System.out.println("---------------------------------------------------------------------");
//    	System.out.println(file.getFilename());
//    	System.out.println(file.getPath());
//    	System.out.println(file.getDescription());
//    	System.out.println(file.getURI());
//    	System.out.println(file.getURL());
//    	System.out.println(document2.getAbsolutePath());
//    	
//    	System.out.println(System.getProperty(upload.getOriginalFilename()));
//    	
    
    	if(name.isEmpty()) {
        emailService.sendSimpleMail(details);
    	}
    	else {
        emailService.sendMailWithAttachment(details);
    	}
    	
        return "mailSent";
    }
}


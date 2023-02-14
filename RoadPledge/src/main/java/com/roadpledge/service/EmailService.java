package com.roadpledge.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.roadpledge.entity.EmailDetails;

@Service
public class EmailService {

	 @Autowired private JavaMailSender javaMailSender;

	 
	    @Value("${spring.mail.username}") private String sender;

	    // Method 1
	    // To send a simple email
	    public String sendSimpleMail(EmailDetails details){
	    
	        // Try block to check for exceptions
	        try {
	 
	            // Creating a simple mail message
	            SimpleMailMessage mailMessage = new SimpleMailMessage();
	 
	            // Setting up necessary details
	            mailMessage.setFrom(sender);
	            mailMessage.setTo(details.getEmailTo());
	            mailMessage.setText(details.getEmailBody());
	            mailMessage.setSubject(details.getSubject());
	 
	            // Sending the mail
	            javaMailSender.send(mailMessage);
	            return "Mail Sent Successfully...";
	        }
	 
	        // Catch block to handle the exceptions
	        catch (Exception e) {
	            return "Error while Sending Mail";
	        }
	    }
	    // Method 2
	    // To send an email with attachment
	    public String sendMailWithAttachment(EmailDetails details){
	       
	    	// Creating a mime message
	        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	        MimeMessageHelper mimeMessageHelper;
	 
	        try {
	 
	            // Setting multipart as true for attachments to
	            // be send
	            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
	            mimeMessageHelper.setFrom(sender);
	            mimeMessageHelper.setTo(details.getEmailTo());
	            mimeMessageHelper.setText(details.getEmailBody());
	            mimeMessageHelper.setSubject(details.getSubject());
	 
	 
	            // Adding the attachment
	           
	            FileSystemResource file = new FileSystemResource(new File("D:/RoadPledge/src/main/resources/static/"+details.getAttachment().getName()));
	 
	          mimeMessageHelper.addAttachment(file.getFilename(), file);
	          //  mimeMessageHelper.addAttachment(file.getDescription(), file);
	 
	            // Sending the mail
	            javaMailSender.send(mimeMessage);
	            return "Mail sent Successfully";
	        }
	 
	        // Catch block to handle MessagingException
	        catch (MessagingException e) {
	 
	            // Display message when exception occurred
	            return "Error while sending mail!!!";
	        }
	    }
	}



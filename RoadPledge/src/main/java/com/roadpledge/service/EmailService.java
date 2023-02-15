package com.roadpledge.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.fileupload.FileItem;
import org.apache.el.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;



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
	    public String sendMailWithAttachment(EmailDetails details) throws IOException{
	       
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
	 
	            MultipartFile upload = details.getAttachment();
	            String name = upload.getOriginalFilename();
	            String content = upload.getContentType();
	            
	            // Adding the attachment
//	            FileSystemResource file = new FileSystemResource(new File(details.getAttachment().getOriginalFilename()));
//	            mimeMessageHelper.addAttachment(file.getFilename(), file);
	            mimeMessageHelper.addAttachment(name, upload, content);
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



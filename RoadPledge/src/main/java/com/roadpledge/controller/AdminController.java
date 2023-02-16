package com.roadpledge.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;


import org.apache.tomcat.util.http.fileupload.IOUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.roadpledge.entity.Citizen;
import com.roadpledge.entity.EmailDetails;
import com.roadpledge.service.CitizenService;

@Controller
@RequestMapping(path="/admin")
public class AdminController {
	
	@Autowired
	private CitizenService citizenService;
	
	
	@GetMapping(path = "/registered_citizen")
	public String showAllCitizen(Model theModel) {
		List<Citizen> citizens = citizenService.showAllCitizen();
		theModel.addAttribute("citizens", citizens);
		return "registered_citizen";

	}
	
	@GetMapping(path="/{email}")
	public String sendMail(@PathVariable(value="email") String email,Model theModel) {
		EmailDetails emailDetails = new EmailDetails();
		emailDetails.setEmailTo(email);
		theModel.addAttribute("emailDetails", emailDetails);
		return "emailPage";
	}
	
	@GetMapping(path="/downloadImage/{iName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public String downloadImage(@PathVariable(value="iName") String iName ) throws IOException {
	    
		Citizen citizen = citizenService.downloadImage(iName);
		byte[] downloadImage = citizen.getIData();
		ByteArrayInputStream imageStream = new ByteArrayInputStream(downloadImage);
		System.out.println(downloadImage.toString());
		BufferedImage bufferImage = ImageIO.read(imageStream);
		String imageDestination = "D:/Rahul-Git2/Road-Safety-Pleadge/RoadPledge/src/main/resources/static/downloads/" + citizen.getMobileNo() + ".jpeg";
		File imageFile = new File(imageDestination);
		ImageIO.write(bufferImage, "jpeg", imageFile);
	
	    return "redirect:/admin/registered_citizen";
		
	}
	
//	@GetMapping(value = "/get-image", produces = MediaType.IMAGE_JPEG_VALUE)
//	public @ResponseBody byte[] getImageWithMediaType() throws IOException {
//		InputStream in = getClass().getResourceAsStream("D:/Rahul-Git2/Road-Safety-Pleadge/RoadPledge/src/main/resources/static/downloads/image.jpeg");
//		return IOUtils.toByteArray(in);
//	}

}

package com.roadpledge.service;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.roadpledge.entity.Citizen;
import com.roadpledge.repository.CitizenRepository;

import net.glxn.qrgen.javase.QRCode;

@Service
@Transactional
public class CitizenService {
	
	@Autowired
	private CitizenRepository citizenRepository;

	
	public Citizen addCitizen(Citizen parameter) throws Exception {
			
		MultipartFile image = parameter.getImage();
		MultipartFile document = parameter.getDocument();
		
		String imageName = image.getOriginalFilename();
		byte[] imageData = image.getBytes();
		
		String documentName = document.getOriginalFilename();
		byte[] documentData = document.getBytes();
		
		String imageDestination = "D:/Rahul-Git2/Road-Safety-Pleadge/RoadPledge/src/main/resources/static/uploadImage/" + image.getOriginalFilename();
		File imageFile = new File(imageDestination);
		image.transferTo(imageFile);
		
		String documentDestination = "D:/Rahul-Git2/Road-Safety-Pleadge/RoadPledge/src/main/resources/static/uploadDocument/" + document.getOriginalFilename();
		File documentFile = new File(documentDestination);
		document.transferTo(documentFile);
		
		String citizenDetails = parameter.toString();
		BufferedImage qrCode1 = generateQRCodeDetails(citizenDetails);
		String qrCodeDestination1 = "D:/Rahul-Git2/Road-Safety-Pleadge/RoadPledge/src/main/resources/static/QR-Codes-Form-Details/" + parameter.getMobileNo() + ".png";
		File qrCodeFile1 = new File(qrCodeDestination1);
		ImageIO.write(qrCode1, "png", qrCodeFile1);
		
		BufferedImage qrCode2 = generateQRCodeDocument(document);
		String qrCodeDestination2 = "D:/Rahul-Git2/Road-Safety-Pleadge/RoadPledge/src/main/resources/static/QR-Codes-Documents-Pdf/" + parameter.getMobileNo() + ".png";
		File qrCodeFile2 = new File(qrCodeDestination2);
		ImageIO.write(qrCode2, "png", qrCodeFile2);
		
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
				          .iName(imageName).iData(imageData)
				          .dName(documentName).dData(documentData)
				          .build();
		
		return citizenRepository.save(citizen);
	}
	
	//Method to Convert Received form details to QR Code
	
	public BufferedImage generateQRCodeDetails(String barcodeText) throws Exception { 
		
	    ByteArrayOutputStream stream = QRCode.from(barcodeText).withSize(250, 250).stream();
	    ByteArrayInputStream qrCode = new ByteArrayInputStream(stream.toByteArray());
	    
	    return ImageIO.read(qrCode);
	} 

	//Method to Convert pdf details to QR Code
	
		public BufferedImage generateQRCodeDocument(MultipartFile file) throws Exception { 
		    
			String contents = file.toString();
			ByteArrayOutputStream stream = QRCode.from(contents).withSize(250, 250).stream();		 
		    ByteArrayInputStream qrCode = new ByteArrayInputStream(stream.toByteArray());
		
		    return ImageIO.read(qrCode);
		}
	
	public List<Citizen> showAllCitizen(){
		
		return citizenRepository.findAll();
		
	}
	
	public Citizen searchById(int id) {
		
		Citizen citizen = citizenRepository.findById(id).orElseThrow();
		return citizen;
	}
	
	public Citizen searchByMail(String email) {
		
		Citizen citizen = citizenRepository.findByEmail(email);
		return citizen;
		
	}
	
	public Citizen downloadImage(String iName) {
		
		Citizen citizen = citizenRepository.findByiName(iName);
		System.out.println(citizen.getIName());
		
		return citizen;
	}

}

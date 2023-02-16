package com.roadpledge.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.geo.Point;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "citizen")
public class Citizen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String fullName;
	private String gender;
	private String dateOfBirth;
	private String pincode;
	private String state;
	private String district;
	private String email;
	
	@Column(unique = true)
	private String mobileNo;
	
	private Point location;
	private String ipAddress;
	
    @Transient
    private MultipartFile image;
    private String iName;
	@Lob
	private byte[] iData;
	
	@Transient
    private MultipartFile document;
	private String dName;
	@Lob
	private byte[] dData;
	
	
	
}

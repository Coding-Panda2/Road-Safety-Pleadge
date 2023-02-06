package com.roadpledge.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.geo.Point;

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

	private String salutation;
	private String fullName;
	private String gender;
	private String dateOfBirth;
	private String pincode;
	private String state;
	private String district;
	private String email;
	private String mobileNo;
	private Point location;
	private String ipAddress;

}

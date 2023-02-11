package com.roadpledge.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDetails {
	
	private String emailTo;
	private String subject ;
	private String emailBody;
	private String attachement;

}

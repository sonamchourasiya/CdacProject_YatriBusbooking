package com.app.dto;

import java.time.LocalDate;

import com.app.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PaymentDto {

	
	
	private String source;

	private String destination;

	private Long  userid;
	
	private double totalPayment;
	@JsonProperty(access = Access.READ_ONLY) 
	private boolean paymentStatus;
	@JsonProperty(access = Access.READ_ONLY) 
	private LocalDate paymentDate;


}

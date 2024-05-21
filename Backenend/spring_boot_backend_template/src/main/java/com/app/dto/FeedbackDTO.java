package com.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.app.entities.Passenger;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FeedbackDTO {
//	@JsonProperty(access = Access.READ_ONLY)
//	private Long Id;
	@NotBlank
	private String feedBack;
	@NotNull
	private Long passengerid;
	@NotNull
	private Long busid;

}

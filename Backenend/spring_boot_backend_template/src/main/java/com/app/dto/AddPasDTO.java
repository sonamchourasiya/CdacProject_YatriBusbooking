package com.app.dto;
import javax.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddPasDTO {
	@NotNull(message ="Booking Id required!!!!")
	private Long bookId;
	@NotBlank(message = "first name must be supplied!!!")
	private String firstName;	
	private String lastName;	
	@NotBlank(message = "gender required....")
	private String gender;
	@NotNull(message = "Age Required")
	private int age;
	@NotNull(message = "SeatNumber Required")
	private int seatNumber;
}

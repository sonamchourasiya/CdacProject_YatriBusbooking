package com.app.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDto {
	
private Long passengerId;
	
	private String firstName;
	private String lastName;
	
	private String gender;
	
	private int age;
	 
	private int seatNumber;
	
	private Long bookingId;

	public PassengerDto(String firstName, String lastName, String gender, int age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
	}
	
//	public PassengerDto(Long pid,String firstName, String lastName, String gender, int age,int seat,Long bid) {
//		super();
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.gender = gender;
//		this.age = age;
//	}
}

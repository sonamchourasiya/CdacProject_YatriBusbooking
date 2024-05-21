package com.app.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomDto {

	private UserDto user;
	private BookingDto bookingDto;
	
	private BusDto busDto;
	
	private List<PassengerDto> listOfPassengerDto;
	
	private double totalPayment;
	
	public CustomDto(BusDto busDto, List<PassengerDto> listOfPassengerDto, double totalPayment) {
		super();
		this.busDto = busDto;
		this.listOfPassengerDto = listOfPassengerDto;
		this.totalPayment = totalPayment;
	}
	
}

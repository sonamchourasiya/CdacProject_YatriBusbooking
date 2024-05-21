package com.app.dto;
import java.time.LocalDate;
import java.util.List;

import com.app.entities.Bus;
import com.app.entities.BusType;
import com.app.entities.Passenger;
import com.app.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookingDto {

	@JsonProperty(access = Access.READ_ONLY) //used during serialization
	private Long id;
	@JsonProperty(access = Access.READ_ONLY)
	private String bookingStatus;
	
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDate bookingDate;
	
	
	private Long busId;	
	private int noOfSeats;
	
	
	private BusType busType;
	 
//	private Long paymentid;
	
	private Long userId;
	
	private List<Passenger> passengerList;
	
}

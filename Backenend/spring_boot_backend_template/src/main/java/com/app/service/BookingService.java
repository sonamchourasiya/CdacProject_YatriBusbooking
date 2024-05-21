package com.app.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.app.dto.ApiResponse;
import com.app.dto.BookingDto;
import com.app.dto.CustomDto;
import com.app.entities.BusType;
import com.app.entities.Passenger;

public interface BookingService {

	String addNewBooking( Long busid,int noOfSeats , BusType busType, Long userid);

	String addPassenger(List<Passenger> passengerList, Long bookingId);

	CustomDto confirmBooking(Long bookingid);

}

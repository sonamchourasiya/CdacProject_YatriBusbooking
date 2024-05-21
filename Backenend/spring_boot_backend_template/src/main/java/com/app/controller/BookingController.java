package com.app.controller;
import java.util.List;


import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.BookingDto;
import com.app.dto.CustomDto;
import com.app.entities.BusType;
import com.app.entities.Passenger;
import com.app.service.BookingService;
@RestController
@RequestMapping("/booking")
@CrossOrigin(origins="http://localhost:3000")
public class BookingController {
	
	@Autowired
  private BookingService bookingService;
	
	
	//add New Booking
	//http://host:port/bookings
	@GetMapping
	public ResponseEntity<?> addNewBooking(@RequestParam Long busid,@RequestParam int noOfSeats ,@RequestParam BusType busType,@RequestParam Long userid){
		
		System.out.println("in add dept " );
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(bookingService.addNewBooking(busid, noOfSeats , busType, userid));	
		
	}
	
@PostMapping(value="/passenger")
public ResponseEntity<?> addPassenger(@RequestBody List<Passenger> passengerList,@RequestParam("bookingId") Long bookingId) {
	System.out.println("bookingid=" +bookingId);
	String mess = bookingService.addPassenger(passengerList,bookingId);
	return new ResponseEntity<String>(mess, HttpStatus.OK);
}

@GetMapping("/confirm")
public ResponseEntity<?> confirmBooking(@RequestParam Long bookingid){
	System.out.println("inside confirm booking booking id ="+bookingid );
	CustomDto dto =   bookingService.confirmBooking(bookingid);
	return new ResponseEntity<CustomDto>(dto, HttpStatus.OK);
}
}

//package com.app.controller;
//
//import com.app.entities.Passenger;
//import com.app.service.PassengerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/passengers")
//public class PassengerController {
//
//    private final PassengerService passengerService;
//
//    @Autowired
//    public PassengerController(PassengerService passengerService) {
//        this.passengerService = passengerService;
//    }
//
//    @GetMapping
//    public List<Passenger> getAllPassengers() {
//        return passengerService.getAllPassengers();
//    }
//
//    @GetMapping("/{id}")
//    public Passenger getPassengerById(@PathVariable Long id) {
//        return passengerService.getPassengerById(id);
//    }
//
//    @PostMapping
//    public Passenger createPassenger(@RequestBody Passenger passenger) {
//        return passengerService.createPassenger(passenger);
//    }
//
//    @PutMapping("/{id}")
//    public Passenger updatePassenger(@PathVariable Long id, @RequestBody Passenger passenger) {
//        return passengerService.updatePassenger(id, passenger);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deletePassenger(@PathVariable Long id) {
//        passengerService.deletePassenger(id);
//    }
//}
package com.app.controller;



import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AddPasDTO;
import com.app.dto.ApiResponse;
//import com.app.dto.ApiResponse;
import com.app.dto.PasRespDTO;
import com.app.entities.Passenger;
import com.app.entities.Payment;
import com.app.service.PassengerService;
import com.app.service.PaymentService;

@RestController
@RequestMapping("/passenger")
@CrossOrigin(origins = "*")
public class PassengerController {
	// dep :
	@Autowired // (required = true)
	private PassengerService pasService;
	
	@Autowired
    private  PaymentService paymentService;


	public PassengerController() {
		System.out.println("in ctor of " + getClass());
	}

	
	@GetMapping
	public List<Passenger> listAllPassenger() {
		System.out.println("in list all emps");
		return pasService.getAllPassengers();
	}

	
	@PostMapping
	public PasRespDTO addPassengerDetails(@RequestBody @Valid AddPasDTO dto) {
		System.out.println("in add emp " + dto);
		return pasService.addPasDetails(dto);
	}
	
	
	
	// URL : http://localhost:8080/employees/empId
	// Method : GET
	// resp : detached pas or exc
	 @GetMapping("/{bookingId}")
	    public ResponseEntity<Passenger> getPassengerByBookingId(@PathVariable Long bookingId) {
		 System.out.println("Passenger Controller : "+bookingId);
	        return ResponseEntity.ok(pasService.getPassengerByBookingId(bookingId));                      
	    }

	// URL : http://localhost:8080/employees/empId
	// Method : DELETE
	// resp : apr resp dto
	@DeleteMapping("/{pasId}")
	public ApiResponse deletePassengerDetails(@PathVariable Long pasId) {
		System.out.println("in del emp dtls " + pasId);
		return new ApiResponse(pasService.deletePassengerDetails(pasId));
	}
	
//  http://localhost:8080/booking/payment/paymentId
	 //Method : post
	//pathvariable :paymentId
	//payload: booking_id,amount,trans_dateTime,trans_id,status
		// resp : detached pas or exc  
	
	// URL : http://localhost:8080/passenger/bus/bookbus/payment
		// Method : POST
		// req params : in Body
		// paymentId,amount, bookingId, transaction_id,paymentStatus,paymentDateAndTime,
		// paymentType;
		// resp : (booking id with other details)
	@PutMapping("/booking/cancelBooking/{bookingid}")
	public ResponseEntity<?> cancelBooking(@NotNull @PathVariable Long bookingid) 
	{
		return   ResponseEntity.ok(paymentService.cancelBooking(bookingid));	
	}
	
	
	
	
//	 @GetMapping("/booking/payment/{paymentId}")
//	    public ResponseEntity<PaymentRespDTO> getPaymentById(@PathVariable Long paymentId) {
//	    	System.out.println("in getpayment");
//	    	
//	    	PaymentRespDTO paymentRespDTO=	paymentService.getPaymentById(paymentId);
//	    	
//	        return ResponseEntity.ok(paymentRespDTO);
//	    }
}


package com.app.service;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.app.dto.ApiResponse;
import com.app.dto.PaymentDto;

import java.util.List;


import com.app.entities.Payment;

public interface PaymentService {
//	   public PaymentRespDTO getPaymentById(Long paymentId);
	    

//	    public List<Payment> getAllPayments();
//	    
//
//	    public Payment savePayment(Payment payment) ;
//	     
//
//	    public void deletePayment(Long paymentId);

	public boolean isPaymentSucessful(Long paymentid);

//	ApiResponse addNewPayment(@Valid PaymentDto dto);
		String cancelBooking(Long bookingid);

		PaymentDto addPayment(PaymentDto dto);



}

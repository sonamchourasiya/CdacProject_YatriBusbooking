
package com.app.controller;


	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.*;

import com.app.dto.ApiResponse;
import com.app.dto.PaymentDto;
import com.app.entities.Payment;
import com.app.service.PaymentService;

import java.util.List;

	@RestController
	@RequestMapping("/payments")
	@CrossOrigin(origins="*")
	public class PaymentController {
		@Autowired
	    private PaymentService paymentService;

//	    @Autowired
//	    public PaymentController(PaymentService paymentService) {
//	        this.paymentService = paymentService;
//	    }

//	    @GetMapping("/{paymentId}")
//	    public ResponseEntity<Payment> getPaymentById(@PathVariable Long paymentId) {
//	    	System.out.println("in getpayment");
//	        //Payment payment = paymentService.getPaymentById(paymentId);
//	        //return ResponseEntity.ok(payment);
//	    	return null;
//	    }
	    
	    
	    @GetMapping("/initiate")
	    public  PaymentDto addPayment(@RequestParam String source,@RequestParam String destination,@RequestParam Long  userid,@RequestParam  double totalPayment   ){
	    	System.out.println("in add payment"+ source +destination + userid + totalPayment);
	    	PaymentDto dto = new PaymentDto();
	    	dto.setSource(source);
	    	dto.setDestination(destination);
	    	dto.setTotalPayment(totalPayment);
	    	dto.setUserid(userid);
	    	return paymentService.addPayment(dto);
	    }
	}

//	    @GetMapping
//	    public ResponseEntity<List<Payment>> getAllPayments() {
//	        List<Payment> payments = paymentService.getAllPayments();
//	        return ResponseEntity.ok(payments);
//	    }

//	    @PostMapping
//	    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
//	        Payment savedPayment = paymentService.savePayment(payment);
//	        return new ResponseEntity<>(savedPayment, HttpStatus.CREATED);
//	    }

//	    @DeleteMapping("/{paymentId}")
//	    public ResponseEntity<Void> deletePayment(@PathVariable Long paymentId) {
//	        paymentService.deletePayment(paymentId);
//	        return ResponseEntity.noContent().build();
//	    }
	


//package com.app.controller;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.app.dao.PaymentDao;
//import com.app.dto.BusDto;
//import com.app.dto.PaymentDto;
//import com.app.service.PaymentService;
//
//@RestController
//@RequestMapping("/payment")
//@CrossOrigin(origins="http://localhost:3000")
//public class PaymentController {
//
//	@Autowired
//	private PaymentService paymentService;
//	
//	//1.add Payment
//	//http://host:port/payment  , method = post
//	@PostMapping
//	public ResponseEntity<?> addNewPayment(@RequestBody @Valid PaymentDto dto){
//		System.out.println("in add Bus " + dto);
//		return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.addNewPayment(dto));
//	}
//}

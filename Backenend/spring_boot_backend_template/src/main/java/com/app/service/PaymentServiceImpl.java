package com.app.service;


import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.PostMapping;

import com.app.dao.BookingDao;
import com.app.dao.PaymentDao;
import com.app.dao.UserDao;
import com.app.dto.ApiResponse;
import com.app.dto.PaymentDto;
import com.app.entities.Booking;
import com.app.entities.Payment;

import custom_exceptions.ResourceNotFoundException;
import java.time.LocalDate;

import javax.validation.Valid;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import com.app.dao.BookingDao;
import com.app.dao.PaymentDao;

import com.app.entities.Booking;
import com.app.entities.Payment;
import com.app.entities.User;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService{
	  @Autowired
    private final PaymentDao paymentRepository;
    @Autowired
    private BookingDao bookingDao;
    @Autowired
	private ModelMapper mapper;
	@Autowired
	private PaymentDao paymentDao;
	 @Autowired
	    private UserDao userDao;
  
    public PaymentServiceImpl(PaymentDao paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

//    public PaymentRespDTO getPaymentById(Long paymentId) {
//    	
//    	Payment payment=paymentRepository.findById(paymentId).orElseThrow(null);
//    	
//    	System.out.println(payment);
//    	
//    	Booking booking=payment.getBooking();
//    	//User user=payment.getUser();
//    	PaymentRespDTO paymentRespDTO=mapper.map(payment,PaymentRespDTO.class);
//    	//paymentRespDTO.setUserId(user.getId());
//    	paymentRespDTO.setBookingId(booking.getId());
//
//        return paymentRespDTO;
//    }

//    public List<Payment> getAllPayments() {
//        return paymentRepository.findAll();
//    }
//
//    public Payment savePayment(Payment payment) {
//        return paymentRepository.save(payment);
//    }
//
//    public void deletePayment (Long paymentId) {
//        paymentRepository.deleteById(paymentId);
//    }
    @Override
	public String cancelBooking(@NotNull Long bookingid) 
	{
		//System.out.println(bookingDao.findById(bookingid));
		Booking booking=bookingDao.findById(bookingid).orElseThrow(() -> new ResourceNotFoundException("Booking not found"));		
      System.out.println(booking);
				booking.setBookingStatus(false);
		Booking cancelledBooking = bookingDao.save(booking);
		if (cancelledBooking != null)
			return "Booking Cancelled Successfully " + cancelledBooking;
		return "Booking Cant be Cancelled";
	}







	
	
	@Override
	public boolean isPaymentSucessful(Long paymentid) {
		Payment p = paymentDao.findById(paymentid).orElseThrow(()->new ResourceNotFoundException("Invalid id"));
	if(p.isPaymentStatus()) {
		return true;
	}
		return false;
	}

	@Override
	public PaymentDto addPayment(PaymentDto dto) {
		System.out.println("in add payment");
		Payment p = paymentDao.save(mapper.map(dto, Payment.class));
		p.setPaymentDate(LocalDate.now());
		p.setPaymentStatus(true);
		
		p.setUser(userDao.findById(dto.getUserid()).orElseThrow(()->new ResourceNotFoundException("Invalid id")));
		return mapper.map(p, PaymentDto.class);
	}

	

// 	@Override 
// 	public ApiResponse addNewPayment(@Valid PaymentDto dto) {
//	
// 		Payment p = paymentDao.save(mapper.map(dto, Payment.class));
// 		p.setPaymentDate(LocalDate.now());
// 		p.setPaymentStatus(true);
// 		Booking booking = bookingDao.findById(dto.getBookingid()).orElseThrow();
// 		p.setBooking(booking);
// 		return new ApiResponse("Payment Done ");
// 	}
//	
	
	

}

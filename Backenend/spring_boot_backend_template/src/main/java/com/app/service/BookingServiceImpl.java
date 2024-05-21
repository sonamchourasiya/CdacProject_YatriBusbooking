package com.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.BookingDao;
import com.app.dao.BusDao;
import com.app.dao.PassengerDao;
import com.app.dao.PaymentDao;
import com.app.dao.UserDao;
import com.app.dto.ApiResponse;
import com.app.dto.BookingDto;
import com.app.dto.BusDto;
import com.app.dto.CustomDto;
import com.app.dto.PassengerDto;
import com.app.entities.Booking;
import com.app.entities.Bus;
import com.app.entities.BusType;
import com.app.entities.Passenger;
import com.app.entities.Payment;
import com.app.entities.User;

import custom_exceptions.ResourceNotFoundException;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingDao bookingDao;

	@Autowired
	private BusDao busDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private PaymentDao paymentdao;
	@Autowired
	private ModelMapper mapper;
	@Autowired 
	private BusService busService;
	@Autowired 
	private PassengerDao passengerdao;

	@Override
	public String addNewBooking( Long busid,int noOfSeats , BusType busType, Long userid) {
System.out.println("inside add booking busid = "+busid+" no of seat = "+noOfSeats+" userid = "+userid);
		
		Booking booking = new Booking();
		
		booking.setNoOfSeats(noOfSeats);
		System.out.println(busid);
		System.out.println(userid);
		Bus bus = busDao.findById(busid)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Bus Id !!!!"));
		System.out.println(bus.getBusNo());
		
		User user = userDao.findById(userid).orElseThrow(() -> new ResourceNotFoundException("Invalid Bus Id !!!!"));
		
		
		System.out.println(user.getFirstName());
		if(bus.getAvailableSeats()<=0) {
//			return new ResponseEntity<String>("Seats are not available.", HttpStatus.BAD_REQUEST);
			return "Seats are not available.";
		}
		else if (booking.getNoOfSeats()>bus.getAvailableSeats()) {
//			return new ResponseEntity<String>("Only "+ bus.getAvailableSeats()+ " seats are available.", HttpStatus.BAD_REQUEST);
	return "Only "+ bus.getAvailableSeats()+ " seats are available.";
		}
		else {
			int remainingSeat = bus.getAvailableSeats()-booking.getNoOfSeats();
			bus.setAvailableSeats(remainingSeat);
			
			BusDto busdto = new BusDto(bus.getId(),bus.getBusNo(),bus.getCapacity(),
					bus.getSource(),bus.getDestination(),bus.getTravelDate(),bus.getDepartureTime(),
					bus.getArrivalTime(),bus.getFare(),bus.getAvailableSeats());
			
			busService.updateBus(busid, busdto);
			
		
			
			booking.setBusType(busType);
			booking.setBookingDate(LocalDate.now());
			booking.setBus(bus);
			booking.setUser(user);
		booking.setBookingStatus(false);
		booking.setPaymentStatus(false);
		Booking persistetbooking =bookingDao.save(booking);
		System.out.println(persistetbooking);
		
//	BookingDto dto =	mapper.map(persistetbooking, BookingDto.class);
//	dto.setBusId(bus.getId());
//	dto.setUserId(user.getId());
//	dto.setNoOfSeats(noOfSeats);
//	dto.setBusType(busType);
	
//		return new ResponseEntity<String>(""+persistetbooking.getId(), HttpStatus.OK);
		return String.valueOf(persistetbooking.getId());	
			
		}
		
//		Bus bus = busDao.findById(dto.getBusid())
//				.orElseThrow(() -> new ResourceNotFoundException("Invalid Bus Id !!!!"));
//		if (dto.getNoOfSeats() <= bus.getAvailableSeats()) {
//			Payment payment = paymentdao.findById(dto.getPaymentid())
//					.orElseThrow(() -> new ResourceNotFoundException("Invalid id"));
//			if (paymentService.isPaymentSucessful(dto.getPaymentid())) {
//				Booking book = bookingDao.save(mapper.map(dto, Booking.class));
//				book.setBookingDate(LocalDate.now());
//				book.setBookingStatus(true);
//				book.setPaymentStatus(true);
//				book.setBus(bus);
//				book.setPayment(payment);
//				book.setUser(userDao.findById(dto.getUserid()).orElseThrow(() -> new ResourceNotFoundException("Invalid Bus Id !!!!")));
//
//			} else {
//
//				return new ApiResponse("Payment Not Sucessfull.... ");
//			}
//
//		} else {
//
//			return new ApiResponse("Seats Not Available ");
//		}
//
//		return new ApiResponse("Booking Done ");
	}

	@Override
	public String addPassenger(List<Passenger> passengerList, Long bookingId) {
		
		Booking booking = bookingDao.findById(bookingId).orElseThrow(()->new ResourceNotFoundException("Invalid Booking Id"));
		Bus bus = booking.getBus();
		booking.setPassengerList(passengerList);
//		booking.setPassengers(passengerList);
		bookingDao.save(booking);
		
		// now add passengers in passengers table
		for(int i=0; i < booking.getNoOfSeats(); i++)
		{
			passengerList.get(i).setBooking(booking);;
//			passengerList.get(i).setBus(bus);
			
			passengerdao.save(passengerList.get(i));
		}
		return "Passengers added successfully";
	}

	@Override
	public CustomDto confirmBooking(Long bookingid) {
		
		
		
		Booking booking = bookingDao.findById(bookingid).orElseThrow(() -> new ResourceNotFoundException("Invalid booking Id"));
		Bus bus =booking.getBus();
		List<Passenger> list = booking.getPassengerList();
		List<PassengerDto> listOfPassengerDto=new ArrayList<PassengerDto>();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getBooking().getId() == booking.getId()) {
				
			
				listOfPassengerDto.add(new PassengerDto(list.get(i).getId(), list.get(i).getFirstName(),list.get(i).getLastName(),
						list.get(i).getGender(), list.get(i).getAge(), list.get(i).getSeatNumber(),
						list.get(i).getBooking().getId()));
			}
			}
		
		double payment =0.0;
		payment = booking.getNoOfSeats()*bus.getFare();
		
		CustomDto dto = new CustomDto(
				new BusDto(bus.getId(),bus.getSource(),bus.getDestination(),bus.getTravelDate(),bus.getArrivalTime(),bus.getDepartureTime()),
				listOfPassengerDto,
				payment);
		
		return dto;
	}
	
	
	
	
	
}

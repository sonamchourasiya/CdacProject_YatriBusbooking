package com.app.entities;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.CascadeType;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "booking")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Booking  extends BaseEntity{
	@Column(name="no_of_seats")
	private int noOfSeats;
	
	@Column(name="booking_status")
	private boolean bookingStatus;
	
	
	@Column(name="payment_status")
	private boolean paymentStatus;
	
	@Column(name="booking_date")
	private LocalDate bookingDate;
	
	@Enumerated(EnumType.STRING) // col : varchar => enum constant name
	@Column(length = 30)
	private BusType busType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bus_id")
	private Bus bus; 
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="payment_id")
	private Payment payment; 

	@JsonIgnore
	@OneToMany(mappedBy = "booking",cascade = CascadeType.ALL)
	private List<Passenger> passengerList= new ArrayList<Passenger>();

	public Booking(int noOfSeats, boolean bookingStatus, boolean paymentStatus, LocalDate bookingDate, BusType type) {
		super();
		this.noOfSeats = noOfSeats;
		this.bookingStatus = bookingStatus;
		this.paymentStatus = paymentStatus;
		this.bookingDate = bookingDate;
		this.busType  = type;
	}

	
	public void addPassenger(Passenger p) {
		passengerList.add(p);
		p.setBooking(this);
		
	}
	
	public void removePassenger(Passenger p) {
		passengerList.remove(p);
		p.setBooking(null);
		
	}
}

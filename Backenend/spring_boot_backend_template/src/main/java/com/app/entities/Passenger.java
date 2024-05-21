package com.app.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="passenger")

@Getter
@Setter
@NoArgsConstructor
public class Passenger extends BaseEntity {
	
	@Column(length=30)
	private String firstName;
	@Column(length=30)
	private String lastName;
	@Column(length=30)
	private String gender;
	
	private int age;
	
	@Column(name="seat_number")
	private int seatNumber;
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="booking_id")
	private Booking booking;
	
	public Passenger(String firstName, String lastName, String gender, int age,int seatNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.seatNumber = seatNumber;
	}
	
	
	

}
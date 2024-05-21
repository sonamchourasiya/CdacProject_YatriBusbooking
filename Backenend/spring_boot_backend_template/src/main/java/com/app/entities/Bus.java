package com.app.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bus")
@Getter
@Setter
@NoArgsConstructor
public class Bus extends BaseEntity {
	
	
	@Column(length = 20, unique = true)
	private String busNo;


	private int  capacity;
	@Column(length=30)
	private String source;
	@Column(length=30)
	private String destination;
	
	@Column(name="travel_date")
	private LocalDate travelDate;
	
	@Column(name="departure_time")
	private LocalDateTime departureTime;
	@Column(name="arrival_time")
	private LocalDateTime arrivalTime;
	
	

	private  double fare;
	
	@Column(name="available_seats")
	private int availableSeats;
	
	
	
    @ElementCollection
    @CollectionTable(name="passengers_list",joinColumns = @JoinColumn(name="bus_id"))
	private List<Passenger> passengerList = new ArrayList<Passenger>();


    
    
	public Bus(String busNo, int capacity, String source, String destination, LocalDate travelDate,
			LocalDateTime departureTime, LocalDateTime arrivalTime, double fare, int availableSeats) {
		super();
		this.busNo = busNo;
		this.capacity = capacity;
		this.source = source;
		this.destination = destination;
		this.travelDate = travelDate;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.fare = fare;
		this.availableSeats = availableSeats;
	}
	
	
//	public void addPassenger(Passenger p) {
//		passengerList.add(p);
//		p.setBus(this);
//		
//	}
//	
//	public void removePassenger(Passenger p) {
//		passengerList.remove(p);
//		p.setBus(null);
//		
//	}
	
}

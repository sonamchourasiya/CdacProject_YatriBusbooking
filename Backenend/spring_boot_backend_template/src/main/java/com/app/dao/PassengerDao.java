package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Passenger;

public interface PassengerDao extends JpaRepository<Passenger, Long>{
	@Query("select p from Passenger p where p.booking=?1")
	Passenger findByBookingId(Long bookingId);
}

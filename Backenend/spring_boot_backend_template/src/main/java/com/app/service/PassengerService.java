package com.app.service;

import com.app.dto.AddPasDTO;
import com.app.dto.PasRespDTO;
import com.app.entities.Passenger;

import java.util.List;

import javax.validation.Valid;

public interface PassengerService {

    List<Passenger> getAllPassengers();

   // Passenger getPassegetPassengerByBookingIdngerById(Long id);
    public Passenger getPassengerByBookingId(Long bookingId);

   // Passenger createPassenger(@Valid AddPasDTO dto);
    public PasRespDTO addPasDetails(AddPasDTO newPas);

    Passenger updatePassenger(Long id, Passenger passenger);

   // void deletePassenger(Long id);

	

	String deletePassengerDetails(Long pasId);


}

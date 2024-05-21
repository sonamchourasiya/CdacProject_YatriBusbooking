package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Bus;

public interface BusDao extends JpaRepository<Bus, Long>{

	List<Bus> findBySourceAndDestination(String source, String destination);

	

	
}

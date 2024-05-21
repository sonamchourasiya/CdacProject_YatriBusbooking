package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Cancellation;


public interface CancellationDao extends JpaRepository<Cancellation, Long>  {

}

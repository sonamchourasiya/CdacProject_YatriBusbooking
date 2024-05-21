package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cancellation")
@Getter
@Setter
public class Cancellation extends BaseEntity{
	@Column(length=30)
	private String refoundAmount;
	@Column(length=100)
	private String reason;
	@Column(name="cancel_date")
	private LocalDate  cancelDate;
	
	@OneToOne
	@JoinColumn(name="booking_id")
	private Booking booking;
	
	public Cancellation(String refoundAmount, String reason, LocalDate cancelDate) {
		super();
		this.refoundAmount = refoundAmount;
		this.reason = reason;
		this.cancelDate = cancelDate;
	}
	

}

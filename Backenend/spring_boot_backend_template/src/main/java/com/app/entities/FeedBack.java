package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "feedback")
@NoArgsConstructor
@Getter
@Setter
public class FeedBack extends BaseEntity {
	@Column(length = 30)
	private String feedBack;

	@ManyToOne
	@JoinColumn(name = "passenger_id")
	private Passenger passenger;

	@ManyToOne
	@JoinColumn(name = "bus_id")
	private Bus bus;

	public FeedBack(String feedBack) {
		super();
		this.feedBack = feedBack;
	}

}

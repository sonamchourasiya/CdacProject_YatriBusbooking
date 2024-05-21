package com.app.entities;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import antlr.collections.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter

public class User extends BaseEntity {
	

	@Column(length=30)
	private String firstName;
	@Column(length=30)
	private String lastName;
	@Column(length = 30, unique = true) // =>unique
	private String email;
	@Column(nullable = false) // =>NOT NULL
	private String password;
	@Column(length = 30, unique = true) // =>unique
	private String phoneNumber;
	@Column(length=30)  
	private String role;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
 private java.util.List<Booking> bookingList = new ArrayList<Booking>();
	
	 @ElementCollection
	    @CollectionTable(name="payments_list",joinColumns = @JoinColumn(name="user_id"))
	private java.util.List<Payment> paymentList = new ArrayList<Payment>();
	
	 @ElementCollection
	    @CollectionTable(name="feedbacks_list",joinColumns = @JoinColumn(name="user_id"))
	private java.util.List<FeedBack> feedbackList = new ArrayList<FeedBack>();
	
	
	public User(String firstName, String lastName, String email, String password , String phoneno,String role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber=phoneno;
		this.role=role;
	
	}
	
	public void addBooking(Booking b) {
		bookingList.add(b);
		b.setUser(this);
		
	}
	
	public void removeBooking(Booking b) {
		bookingList.remove(b);
		b.setUser(null);
	}
		
	public void addPayment(Payment p) {
		paymentList.add(p);
		
	}
	
	public void removePayment(Payment p) {
		paymentList.remove(p);
		
	}
	
	public void addFeedback(FeedBack f) {
		feedbackList.add(f);
		
	}
	public void removeFeedback(FeedBack f) {
		feedbackList.remove(f);
		
	}
	}

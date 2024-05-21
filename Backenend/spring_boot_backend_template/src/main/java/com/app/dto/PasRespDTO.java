package com.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PasRespDTO {
	private Long id;
	private String firstName;
	private String lastName;
    private String gender;
	private int age;
private int seatNumber;
}

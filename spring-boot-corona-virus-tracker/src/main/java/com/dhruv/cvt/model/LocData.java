package com.dhruv.cvt.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LocData {
	
	private String state;
	private String country;
	private int totalCases;
	private int diffFromPrevDay;
	

}

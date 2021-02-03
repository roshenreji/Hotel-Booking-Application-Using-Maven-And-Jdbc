package com.mindtree.HotelBookingApplication.Entity;

import java.util.List;

public class Hotel {

	private int id;
	private String name;
	private String city;
	
	public Hotel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Hotel(int id, String name, String city) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
}

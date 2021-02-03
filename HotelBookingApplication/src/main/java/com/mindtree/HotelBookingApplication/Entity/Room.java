package com.mindtree.HotelBookingApplication.Entity;

public class Room {

	
	private int roomNumber;
	private String roomType;
	private double cost;
	private Hotel hotel;

	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Room(int roomNumber, String roomType, double cost, Hotel hotel) {
		super();
		
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.cost = cost;
		this.hotel = hotel;
	}

	

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

}

package com.mindtree.HotelBookingApplication.Service;

import java.util.List;

import com.mindtree.HotelBookingApplication.Entity.Hotel;
import com.mindtree.HotelBookingApplication.Entity.Room;
import com.mindtree.HotelBookingApplication.Exceptions.ServiceException.HotelBookingServiceException;

public interface HotelBookingService {

	public boolean insertHotelsToDb(Hotel hotels) throws HotelBookingServiceException;
	 public boolean insertRoomsToDb(Room rooms) throws HotelBookingServiceException;
	 
	 public List<Hotel> getAllHotelsInCity(String city)throws HotelBookingServiceException;
}

package com.mindtree.HotelBookingApplication.DAO;

import java.util.List;

import com.mindtree.HotelBookingApplication.Entity.Hotel;
import com.mindtree.HotelBookingApplication.Entity.Room;
import com.mindtree.HotelBookingApplication.Exceptions.DaoException.HotelBookingDaoException;


public interface HotelBookingDao {

	public boolean insertHotelsToDb(Hotel hotels) throws HotelBookingDaoException;
	 public boolean insertRoomsToDb(Room rooms) throws HotelBookingDaoException;
	 
	 public List<Hotel> getAllHotelsInCity(String city)throws HotelBookingDaoException;
	 
}

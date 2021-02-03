package com.mindtree.HotelBookingApplication.Service.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.mindtree.HotelBookingApplication.DAO.HotelBookingDao;
import com.mindtree.HotelBookingApplication.DAO.Impl.HotelBookingDaoImpl;
import com.mindtree.HotelBookingApplication.Entity.Hotel;
import com.mindtree.HotelBookingApplication.Entity.Room;
import com.mindtree.HotelBookingApplication.Exceptions.DaoException.HotelBookingDaoException;
import com.mindtree.HotelBookingApplication.Exceptions.DaoException.IdAbsentException;
import com.mindtree.HotelBookingApplication.Exceptions.DaoException.IdPresentException;
import com.mindtree.HotelBookingApplication.Exceptions.ServiceException.HotelBookingServiceException;
import com.mindtree.HotelBookingApplication.Service.HotelBookingService;
import com.mindtree.HotelBookingApplication.Utility.JDBCConnection;

public class HotelBookingServiceImpl implements HotelBookingService {

	private static HotelBookingDao dao=new HotelBookingDaoImpl();
	
	

	

	public boolean insertHotelsToDb(Hotel hotels) throws HotelBookingServiceException {
		
		try {
			return dao.insertHotelsToDb(hotels);
		} catch (HotelBookingDaoException e) {
			throw new HotelBookingServiceException("Something is wrong in DB",e);
		}
		
	}

	public boolean insertRoomsToDb(Room rooms) throws HotelBookingServiceException {
		try {
			return dao.insertRoomsToDb(rooms);
		} catch (HotelBookingDaoException e) {
			throw new HotelBookingServiceException("Something is wrong in DB",e);
		}
		
	}

	public List<Hotel> getAllHotelsInCity(String city) throws HotelBookingServiceException {
		try {
			return dao.getAllHotelsInCity(city);
		} catch (HotelBookingDaoException e) {
			throw new HotelBookingServiceException("Something is wrong in DB",e);
		}
	}

	public boolean checkIdPresence(int id) throws HotelBookingServiceException {
		try {
			return dao.checkIdPresence(id);
		}
		catch(IdPresentException e) {
			throw new HotelBookingServiceException("Id Already Present in the Database");
		}
		
	}

}

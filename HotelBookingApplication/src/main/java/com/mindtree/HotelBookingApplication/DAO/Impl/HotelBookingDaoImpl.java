package com.mindtree.HotelBookingApplication.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.mindtree.HotelBookingApplication.DAO.HotelBookingDao;
import com.mindtree.HotelBookingApplication.Entity.Hotel;
import com.mindtree.HotelBookingApplication.Entity.Room;
import com.mindtree.HotelBookingApplication.Exceptions.DaoException.HotelBookingDaoException;
import com.mindtree.HotelBookingApplication.Utility.JDBCConnection;

public class HotelBookingDaoImpl implements HotelBookingDao {

	private static JDBCConnection connection=new JDBCConnection();
	public boolean insertHotelsToDb(Hotel hotels) throws HotelBookingDaoException {
		boolean isInserted=false;
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=connection.getConnection();
			String insertQuery="Insert into Hotel values(?,?,?)";
			ps=con.prepareStatement(insertQuery);
			
			ps.setInt(1, hotels.getId());
			ps.setString(2, hotels.getName());
			ps.setString(3, hotels.getCity());

			ps.executeUpdate();
			isInserted=true;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			connection.closeResources(con);
			connection.closeResources(ps);
		}
		
		return isInserted;
	}

	public boolean insertRoomsToDb(Room rooms) throws HotelBookingDaoException {
		boolean isInserted=false;
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=connection.getConnection();
			String insertQuery="Insert into Room values(?,?,?,?)";
			ps=con.prepareStatement(insertQuery);
			
			ps.setInt(1, rooms.getRoomNumber());
			ps.setString(2, rooms.getRoomType());
			ps.setDouble(3, rooms.getCost());
			ps.setInt(4, rooms.getHotel().getId());

			ps.executeUpdate();
			isInserted=true;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			connection.closeResources(con);
			connection.closeResources(ps);
		}
		
		return isInserted;
	}
	
	public void checkCityAbsence(String city) throws HotelBookingDaoException {
		String cityName = "";
		boolean valid = false;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		try {
			con=connection.getConnection();
			String query = "Select city from Hotel Where City='" + city+"'";
			ps = con.prepareStatement(query);

			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				cityName = resultSet.getString(1);
				if (cityName.equals(city)) {
					valid = true;
					break;

				}
			}

			if (valid == false) {
				throw new HotelBookingDaoException("No such city are there");
			}

			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		finally {
			connection.closeResources(con);
			connection.closeResources(ps);
			connection.closeResources(resultSet);
		}

	}
	public void hotelsInCity(String city) throws HotelBookingDaoException {
		String cityName = "";
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet resultSet=null;
		try {
			checkCityAbsence(city);
			try {

				String query = "Select city from Hotel where city='"+city+"'";
				ps = con.prepareStatement(query);

				resultSet = ps.executeQuery();
				while (resultSet.next()) {
					cityName = resultSet.getString(1);
					if (cityName.equals(city)) {
						getAllHotelsInCity(city);

					}
				}

	

			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		} catch (HotelBookingDaoException e1) {
			// TODO Auto-generated catch block
			throw new HotelBookingDaoException();
		}
		finally {
			connection.closeResources(con);
			connection.closeResources(ps);
			connection.closeResources(resultSet);
		}

	}

	public List<Hotel> getAllHotelsInCity(String city) throws HotelBookingDaoException {
		List<Hotel> hotels=new ArrayList<Hotel>();

		Connection con=null;
		ResultSet rs=null;
		Statement st=null;
		String query = "Select * from Hotel where city='"+ city+"'";
		
		try {
			con=connection.getConnection();
			st = con.createStatement();
			 rs = st.executeQuery(query);
			while (rs.next()) {
				Hotel hotel=new Hotel(rs.getInt(1),rs.getString(2),rs.getString(3));
				hotels.add(hotel);

			}
		} catch (SQLException e) {
			throw new HotelBookingDaoException();
		}
		finally {
			connection.closeResources(con);
			connection.closeResources(st);
			connection.closeResources(rs);
		}
		
		return hotels;
	}

}

package com.mindtree.HotelBookingApplication.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mindtree.HotelBookingApplication.Entity.Hotel;
import com.mindtree.HotelBookingApplication.Entity.Room;
import com.mindtree.HotelBookingApplication.Exceptions.ServiceException.HotelBookingServiceException;
import com.mindtree.HotelBookingApplication.Service.HotelBookingService;
import com.mindtree.HotelBookingApplication.Service.Impl.HotelBookingServiceImpl;

public class HotelBookingApplication {

	private static HotelBookingService hotelService = new HotelBookingServiceImpl();
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		boolean isValid = true;
		Hotel hotel = null;
		Room rooms;
		System.out.println("pull test");
		do {
			displayMessages();
			int choice = sc.nextInt();
			switch (choice) {
			case 1:

				hotel = getCreatedHotels();

				if(hotel!=null) {
					boolean isInserted = false;
					try {
						isInserted = hotelService.insertHotelsToDb(hotel);
					} catch (HotelBookingServiceException e) {
						System.out.println(e.getMessage());
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					if (isInserted == true) {
						System.out.println("Inserted Successfully");
					}
				}
				else
					continue;
				
				break;
			case 2:

				rooms = getCreatedRooms();

				boolean isInsertedRooms = false;
				try {
					isInsertedRooms = hotelService.insertRoomsToDb(rooms);
				} catch (HotelBookingServiceException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				if (isInsertedRooms == true) {
					System.out.println("Inserted Successfully");
				}
				break;

			case 3:
				System.out.println("Enter the city whose hotels you want to display ");
				String city = sc.next();
				try {
					List<Hotel> list = hotelService.getAllHotelsInCity(city);
					displayHotelsFromDB(list, city);
				} catch (HotelBookingServiceException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				isValid = false;
				break;
			default:
				System.out.println("Invalid Choice, Please try Again");
			}

		} while (isValid);

	}

	public static void displayHotelsFromDB(List<Hotel> hotels, String city) {
		if (hotels != null) {
			System.out.println("Hotels in " + city + " are: ");
			for (Hotel hotel : hotels) {
				System.out.println(hotel.getId() + "\t" + hotel.getName() + "\t" + hotel.getCity());
			}
		}

	}

	public static void displayMessages() {
		System.out.println();
		System.out.println("Welcome to Online Hotel Booking Site");
		System.out.println("====================");
		System.out.println("These are the choices");
		System.out.println("1. Create Hotel ");
		System.out.println("2. Create Room");
		System.out.println("3. Display hotel info in a given city");
		System.out.println("4. Exit");
		System.out.println("Enter your choice: ");
	}

	public static Hotel getCreatedHotels() {
		HotelBookingServiceImpl obj = new HotelBookingServiceImpl();
		int index = 0;
		Hotel hotels = null;
		System.out.println("Enter Hotel  id: ");
		int id = sc.nextInt();
		try {
			obj.checkIdPresence(id);
			System.out.println("Enter Hotel Name: ");
			String hotelName = sc.next();
			System.out.println("Enter Hotel City");
			String hotelCity = sc.next();

			hotels = new Hotel(id, hotelName, hotelCity);
		} catch (HotelBookingServiceException e) {
			// throw new HotelBookingServiceException();
			System.out.println(e.getMessage());
		}

		return hotels;

	}

	public static Hotel getCreatedHotelsForRooms() {
		HotelBookingServiceImpl obj = new HotelBookingServiceImpl();
		int index = 0;
		System.out.println("Enter Hotel  id: ");
		int id = sc.nextInt();
		System.out.println("Enter Hotel Name: ");
		String hotelName = sc.next();
		System.out.println("Enter Hotel City");
		String hotelCity = sc.next();

		Hotel hotels = new Hotel(id, hotelName, hotelCity);

		return hotels;

	}

	public static String validateRoomType(String type) {
		boolean isValid = true;
		while (isValid) {
			if (type.equalsIgnoreCase("Luxury") || type.equalsIgnoreCase("semiLuxury")
					|| type.equalsIgnoreCase("deluxe")) {
				isValid = false;
			} else {
				System.out.println("Enter the Room Type Propely(Luxury/semiLuxury/deluxe)");
				type = sc.next();
			}
		}

		return type;
	}

	public static Room getCreatedRooms() {

		System.out.println("Enter Room  Number: ");
		int roomNumber = sc.nextInt();
		System.out.println("Enter Room Type(Luxury/semiLuxury/deluxe): ");
		String roomType = sc.next();
		roomType = validateRoomType(roomType);
		System.out.println("Enter Room Cost");
		double roomCost = sc.nextDouble();
		Hotel hotel = getCreatedHotelsForRooms();
		Room room = new Room(roomNumber, roomType, roomCost, hotel);

		return room;
	}
}

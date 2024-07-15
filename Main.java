package RailwayReservationPackage;

import java.util.Scanner;

public class Main {
	
	public static void bookticket(Passenger p) {
		
		Booking b = new Booking();
		
		if((p.berthPreference.equals("L") && Booking.availableLowerBerths > 0) || 
		   (p.berthPreference.equals("M") && Booking.availableMiddleBerths > 0) || 
		   (p.berthPreference.equals("U") && Booking.availableUpperBerths > 0)){
			
			if(p.berthPreference.equals("L")) {
				System.out.println("Lower berth Booked");
				b.bookTicket(p,Booking.lowerBerthPositions.get(0),"L");
				Booking.lowerBerthPositions.remove(0);
				Booking.availableLowerBerths--;
			}
			else if(p.berthPreference.equals("M")) {
				System.out.println("Middle berth Booked");
				b.bookTicket(p, Booking.middleBerthhPositions.get(0), "M");
				Booking.middleBerthhPositions.remove(0);
				Booking.availableMiddleBerths--;
			}
			else if(p.berthPreference.equals("U")) {
				System.out.println("Upper berth Booked");
				b.bookTicket(p, Booking.upperBerthPositions.get(0), "U");
				Booking.upperBerthPositions.remove(0);
				Booking.availableUpperBerths--;
			}
		}
		else if(Booking.availableLowerBerths>0) {
			System.out.println("Lower berth Booked");
			b.bookTicket(p,Booking.lowerBerthPositions.get(0),"L");
			Booking.lowerBerthPositions.remove(0);
			Booking.availableLowerBerths--;
		}
		else if(Booking.availableMiddleBerths>0) {
			System.out.println("Middle berth Booked");
			b.bookTicket(p, Booking.middleBerthhPositions.get(0), "M");
			Booking.middleBerthhPositions.remove(0);
			Booking.availableMiddleBerths--;
		}
		else if(Booking.availableUpperBerths>0) {
			System.out.println("Upper berth Booked");
			b.bookTicket(p, Booking.upperBerthPositions.get(0), "U");
			Booking.upperBerthPositions.remove(0);
			Booking.availableUpperBerths--;
		}
		else if(Booking.availableRacTickets>0) {
			System.out.println("RAC is Booked");
			b.addToRac(p,Booking.racListPositions.get(0), "RAC");
			Booking.racListPositions.remove(0);
			Booking.availableRacTickets--;
		}
		else {
			System.out.println("Waiting List is Booked");
			b.addToWaitinglist(p,Booking.waitingListPositions.get(0), "WL");
			Booking.waitingListPositions.remove(0);
			Booking.availableWaitingList--;
		}
	}
	
	public static void cancelTicket(int passengerId) {
		Booking b = new Booking();
		if(!Booking.passengerMap.containsKey(passengerId)) {
			System.out.println("Unknown passenger ID");
		}
		else {
			b.cancelTicket(passengerId);
		}
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while(true) {
			System.out.println("Enter the choice here \n 1 -> Book tickets \n 2 -> Cancel tickets "
							 + "\n 3 -> Print all bookings \n 4 -> Print all available tickets");
			int choice = s.nextInt();
			s.nextLine();
			switch(choice) {
				case 1 : System.out.println("CHOSEN NUMBER IS 1");
						{
							if(Booking.availableWaitingList == 0) {
								System.out.println("NO tickets available");
								break;
							}
							else {
								System.out.println("Enter your name here : ");
								String passengerName = s.nextLine();
								System.out.println("Enter your preferred berth here : ");
								String preferredBerth = s.nextLine();
								
								Passenger p = new Passenger(passengerName,preferredBerth);
								bookticket(p);
							}
						}
						 break;
				case 2 : System.out.println("CHOSEN NUMBER IS 2");
						{
							System.out.println("Enter your passenger ID here : ");
							int passengerId = s.nextInt();
							s.nextLine();
							cancelTicket(passengerId);
						}
				 		 break;
				case 3 : System.out.println("CHOSEN NUMBER IS 3");
						{
							if(Booking.passengerMap.size() == 0) System.out.println("No Bookings Done");
							else Booking.printAllBookings();
						}
				 		 break;
				case 4 : System.out.println("CHOSEN NUMBER IS 4");
						{
							Booking.printAvailableTickets();
						}
				 		 break;
				default : System.out.println("ENTER A VALID NUMBER");
						 break;
			}
		}
	}
	
}

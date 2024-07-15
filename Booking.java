package RailwayReservationPackage;

import java.util.*;

public class Booking {
	static int availableUpperBerths  = 3;
	static int availableLowerBerths  = 3;
	static int availableMiddleBerths = 3;
	static int availableRacTickets   = 2;
	static int availableWaitingList  = 2;
	
	static Queue<Integer> racListQueue 			= new LinkedList<Integer>();
	static Queue<Integer> waitingListQueue 		= new LinkedList<Integer>();
	
	static List<Integer> lowerBerthPositions 	= new ArrayList<Integer>(Arrays.asList(1,2,3));
	static List<Integer> middleBerthhPositions 	= new ArrayList<Integer>(Arrays.asList(1,2,3));
	static List<Integer> upperBerthPositions 	= new ArrayList<Integer>(Arrays.asList(1,2,3));
	static List<Integer> racListPositions 		= new ArrayList<Integer>(Arrays.asList(1,2));
	static List<Integer> waitingListPositions 	= new ArrayList<Integer>(Arrays.asList(1,2));
	
	static List<Integer> bookedTicketList = new ArrayList<Integer>();
	
	static Map<Integer,Passenger> passengerMap = new HashMap<Integer,Passenger>();
	
	public void bookTicket(Passenger p, int berthInfo, String allotedBerth) {
		p.number = berthInfo;
		p.allotedBerth = allotedBerth;
		
		passengerMap.put(p.passengerId, p);
		bookedTicketList.add(p.passengerId);
	}
	
	public void addToRac(Passenger p, int racInfo, String allotted) {
		
		p.number = racInfo;
		p.allotedBerth = allotted;
		
		passengerMap.put(p.passengerId, p);
		racListQueue.add(p.passengerId);
	}
	public void addToWaitinglist(Passenger p, int waitingListInfo, String allotted) {
		p.number = waitingListInfo;
		p.allotedBerth = allotted;
		
		passengerMap.put(p.passengerId,p);
		waitingListQueue.add(p.passengerId);
	}
	public void cancelTicket(int passengerId) {
		Passenger p = passengerMap.get(passengerId);
		bookedTicketList.remove(Integer.valueOf(passengerId));
		passengerMap.remove(Integer.valueOf(passengerId));
		System.out.println("Ticket cancelled successfully");
		
		if(p.berthPreference.equals("L")){
			Booking.lowerBerthPositions.add(p.number);
			Booking.availableLowerBerths++;
		}
		else if(p.berthPreference.equals("M")) {
			Booking.middleBerthhPositions.add(p.number);
			Booking.availableMiddleBerths++;
		}
		else if(p.berthPreference.equals("U")) {
			Booking.upperBerthPositions.add(p.number);
			Booking.availableUpperBerths++;
		}
		if(racListQueue.size()>0) {
			Passenger passengerFromRac = passengerMap.get(racListQueue.poll());
			racListPositions.add(passengerFromRac.number);
			racListQueue.remove(Integer.valueOf(passengerFromRac.passengerId));
			availableRacTickets++;
			if(waitingListQueue.size()>0) {
				Passenger passengerFromWl = passengerMap.get(waitingListQueue.poll());
				waitingListPositions.add(passengerFromWl.number);
				waitingListQueue.remove(Integer.valueOf(passengerFromWl.passengerId));
				availableWaitingList++;
				
				passengerFromWl.number = racListPositions.get(0);
				passengerFromWl.allotedBerth = "RAC";
				racListPositions.remove(0);
				racListQueue.add(passengerFromWl.passengerId);
				availableRacTickets--;
			}
			Main.bookticket(passengerFromRac);
		}
	}
	public static void printAvailableTickets() {
		System.out.println("Lower Berths 		- " + availableLowerBerths +
						   "\nMiddle Berths 	 	- " + availableMiddleBerths +
						   "\nUpperBerths 		- " + availableUpperBerths + 
						   "\nRAC seats 		- " + availableRacTickets +
						   "\nWaiting List seats 	- " + availableWaitingList);
	}
	public static void printAllBookings() {
		for(Passenger p : passengerMap.values()) {
			System.out.println("Passenger ID   - " + p.passengerId+
							 "\nPassenger Name - "+p.passengerName+
							 "\nSeat Number    - "+p.number+p.allotedBerth);
		}
	}
}

package RailwayReservationPackage;

public class Passenger {
	static int id = 1;
	int passengerId = 0;
	String passengerName = "";
	String berthPreference = "";
	
	int number = 0;
	String allotedBerth = "";
	
	Passenger(String passengerName, String berthPreference){
		this.passengerName = passengerName;
		this.berthPreference = berthPreference;
		this.passengerId = id++;
	}
}

package application;

import java.util.ArrayList;

public class Employee {

	private String fName;
	private char mName;
	private String lName;
	private int id;
	private ArrayList<TimePeriod> availableTimes;
	private ArrayList<String> availableDays;
	
	public Employee(String firstName, String lastName, int id) {
		fName = firstName;
		lName = lastName;
		mName = ' ';
		this.id = id;
		
		availableTimes = new ArrayList<TimePeriod>();
		availableDays = new ArrayList<String>();
	}
	
	public Employee(String firstName, String lastName, char middleInitial, int id, ArrayList<TimePeriod> availableTimes, ArrayList<String> availableDays) {
		fName = firstName;
		lName = lastName;
		mName = middleInitial;
		this.id = id;
		
		availableTimes = new ArrayList<TimePeriod>();
		availableDays = new ArrayList<String>();
	}
	
	public String getFirstName() {
		return fName;
	}

	public char getMiddleName() {
		return mName;
	}

	public String getLastName() {
		return lName;
	}

	public int getId() {
		return id;
	}

	public ArrayList<TimePeriod> getAvailableTimes() {
		return availableTimes;
	}

	public ArrayList<String> getAvailableDays() {
		return availableDays;
	}

	public void addAvailableTime(int e, int s) {
		this.availableTimes.add(new TimePeriod(e,s));
	}

	public void addAvailableDay(String s) {
		this.availableDays.add(s);
	}
	
}

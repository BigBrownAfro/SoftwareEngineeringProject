package application;

import java.util.ArrayList;

public class Employee {

	//things describing an employee
	private String fName;
	private char mName;
	private String lName;
	private int id;
	private ArrayList<TimePeriod> availableTimes;
	private ArrayList<String> availableDays;
	
	//helpers for database
	boolean[] daysWorking;
	int numDaysWorking;
	
	public Employee(String firstName, String lastName, int id) {
		fName = firstName;
		lName = lastName;
		mName = ' ';
		this.id = id;
		
		availableTimes = new ArrayList<TimePeriod>();
		availableDays = new ArrayList<String>();
		
		daysWorking = new boolean[7];
		numDaysWorking = 0;
	}
	
	public Employee(String firstName, String lastName, char middleInitial, int id) {
		fName = firstName;
		lName = lastName;
		mName = middleInitial;
		this.id = id;
		
		availableTimes = new ArrayList<TimePeriod>();
		availableDays = new ArrayList<String>();
		
		daysWorking = new boolean[7];
		numDaysWorking = 0;
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
	
	public void setNotWorking(String day) {
		int dayIndex = 0;
		
		switch (day){
		case "Sunday":
			dayIndex = 0;
			break;
		case "Monday":
			dayIndex = 1;
			break;
		case "Tuesday":
			dayIndex = 2;
			break;
		case "Wednesday":
			dayIndex = 3;
			break;
		case "Thursday":
			dayIndex = 4;
			break;
		case "Friday":
			dayIndex = 5;
			break;
		case "Saturday":
			dayIndex = 6;
			break;
		}
		daysWorking[dayIndex] = false;
		
		numDaysWorking--;
	}
	
	public void setWorking(String day) {
		int dayIndex = 0;
		
		switch (day){
		case "Sunday":
			dayIndex = 0;
			break;
		case "Monday":
			dayIndex = 1;
			break;
		case "Tuesday":
			dayIndex = 2;
			break;
		case "Wednesday":
			dayIndex = 3;
			break;
		case "Thursday":
			dayIndex = 4;
			break;
		case "Friday":
			dayIndex = 5;
			break;
		case "Saturday":
			dayIndex = 6;
			break;
		}
		
		daysWorking[dayIndex] = true;
		
		numDaysWorking++;
	}
	
	public boolean isWorking(String day) {
		int dayIndex = 0;
		
		switch (day){
		case "Sunday":
			dayIndex = 0;
			break;
		case "Monday":
			dayIndex = 1;
			break;
		case "Tuesday":
			dayIndex = 2;
			break;
		case "Wednesday":
			dayIndex = 3;
			break;
		case "Thursday":
			dayIndex = 4;
			break;
		case "Friday":
			dayIndex = 5;
			break;
		case "Saturday":
			dayIndex = 6;
			break;
		}
		
		return daysWorking[dayIndex];
	}
}

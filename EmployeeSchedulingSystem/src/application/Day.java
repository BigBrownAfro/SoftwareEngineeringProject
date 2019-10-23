package application;

import java.util.ArrayList;

public class Day {
	private String name;
	private String month;
	private int day;
	ArrayList<TimeSlot> timeSlots;
	
	public Day(String month, int day) {
		this.month = month;
		this.day = day;
		calculateDayOfWeek();
		timeSlots = new ArrayList<TimeSlot>();
	}
	
	private void calculateDayOfWeek() { //Not Finished
		int temp = day % 7;
		
		switch(temp) {
		case 0:
			name = "Sunday";
			break;
		case 1:
			name = "Monday";
			break;
		case 2:
			name = "Tuesday";
			break;
		case 3:
			name = "Wednesday";
			break;
		case 4:
			name = "Thursday";
			break;
		case 5:
			name = "Friday";
			break;
		case 6:
			name = "Saturday";
			break;
		default:
			name = "Failed to calulate";
			System.out.println("Failed to calculate day name");
		}
	}
}

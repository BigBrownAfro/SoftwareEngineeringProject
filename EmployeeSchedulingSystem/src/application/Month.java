package application;

public class Month {
	String name;
	private int year;
	Day[] days;
	
	public Month(String name, int year) {
		this.name = name;
		this.year = year;
		
		days = new Day[calculateNumOfDays()];
		for (int i = 0; i < days.length; i++) {
			days[i] = new Day(this.name, i + 1, this.year);
		}
	}
	
	public Month(String name, int numDays, int year) {
		this.name = name;
		this.year = year;
		
		days = new Day[numDays];
		for (int i = 0; i < days.length; i++) {
			days[i] = new Day(this.name, i + 1, year);
		}
	}
	
	private int calculateNumOfDays() { //Not Finished
		return 31;
	}
	
	public Day getDay(int day) {
		return days[day];
	}
}

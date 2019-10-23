package application;

public class Month {
	String name;
	Day[] days;
	
	public Month(String name) {
		this.name = name;
		
		days = new Day[calculateNumOfDays()];
		for (int i = 0; i < days.length; i++) {
			days[i] = new Day(this.name, i + 1);
		}
	}
	
	public Month(String name, int numDays) {
		this.name = name;
		days = new Day[numDays];
		for (int i = 0; i < days.length; i++) {
			days[i] = new Day(this.name, i + 1);
		}
	}
	
	private int calculateNumOfDays() { //Not Finished
		return 31;
	}
}

package application;

public class Calendar {
	int year;
	Month[] months;
	
	public Calendar() {
		year = 2019;
		months = new Month[12];
		setupMonths();
	}
	
	private void setupMonths() {
		months[0] = new Month("January", 31);
		if(year % 4 == 0) {
			months[1] = new Month("February", 29);
		}else {
			months[1] = new Month("February", 28);
		}
		months[2] = new Month("March", 31);
		months[3] = new Month("April", 30);
		months[4] = new Month("May", 31);
		months[5] = new Month("June", 30);
		months[6] = new Month("July", 31);
		months[7] = new Month("August", 31);
		months[8] = new Month("September", 30);
		months[9] = new Month("October", 31);
		months[10] = new Month("November", 30);
		months[11] = new Month("Decemeber", 31);
	}
}

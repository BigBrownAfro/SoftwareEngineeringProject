package application;

public class Calendar {
	int year;
	private int dayOffset; //from sunday starting 2019
	private Month[] months;
	private int selectedMonth; //index of selected month (0 to 11);
	
	public Calendar() {
		year = 2019;
		dayOffset = 2;
		months = new Month[12];
		setupMonths();
		selectedMonth = 9;
	}
	
	private void setupMonths() {
		months[0] = new Month("January", 31, year, dayOffset);
		dayOffset += 31;
		if(year % 4 == 0) {
			months[1] = new Month("February", 29, year, dayOffset);
			dayOffset += 29;
		}else {
			months[1] = new Month("February", 28, year, dayOffset);
			dayOffset += 28;
		}
		months[2] = new Month("March", 31, year, dayOffset);
		dayOffset += 31;
		months[3] = new Month("April", 30, year, dayOffset);
		dayOffset += 30;
		months[4] = new Month("May", 31, year, dayOffset);
		dayOffset += 31;
		months[5] = new Month("June", 30, year, dayOffset);
		dayOffset += 30;
		months[6] = new Month("July", 31, year, dayOffset);
		dayOffset += 31;
		months[7] = new Month("August", 31, year, dayOffset);
		dayOffset += 31;
		months[8] = new Month("September", 30, year, dayOffset);
		dayOffset += 30;
		months[9] = new Month("October", 31, year, dayOffset);
		dayOffset += 31;
		months[10] = new Month("November", 30, year, dayOffset);
		dayOffset += 30;
		months[11] = new Month("December", 31, year, dayOffset);
		dayOffset += 31;
	}
	
	public Day getDay(int day) {
		return months[selectedMonth].getDay(day);
	}
	
	/**
	 * Takes the index of the month desired (from 0 to 11)
	 * @param i
	 */
	public void setMonth(int i) {
		if(i < 0 || i > 11) {
			i = 0;
		}
		selectedMonth = i;
	}
	
	public Month getMonth() {
		return months[selectedMonth];
	}
	
	public Month getMonth(String s) {
		for (Month month: months) {
			if (month.name.equals(s)) {
				return month;
			}
		}
		return null;
	}
	
	public Month[] getMonths() {
		return months;
	}
	
	public Month nextMonth() {
		if(selectedMonth == 11) {
			selectedMonth = 0;
		}else {
			selectedMonth += 1;
		}
		return months[selectedMonth];
	}
	
	public Month previousMonth() {
		if(selectedMonth == 0) {
			selectedMonth = 11;
		}else {
			selectedMonth -= 1;
		}
		return months[selectedMonth];
	}
	
	public Month peekNextMonth() {
		if(selectedMonth == 11) {
			return months[0];
		}
		return months[selectedMonth + 1];
	}
	
	public Month peekPreviousMonth() {
		if(selectedMonth == 0) {
			return months[11];
		}
		return months[selectedMonth - 1];
	}
}

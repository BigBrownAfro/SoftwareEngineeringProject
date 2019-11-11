package application;

public class Calendar {
	int year;
	Month[] months;
	Month selectedMonth;
	
	public Calendar() {
		year = 2019;
		months = new Month[12];
		setupMonths();
		selectedMonth = months[9];
	}
	
	private void setupMonths() {
		months[0] = new Month("January", 31, year);
		if(year % 4 == 0) {
			months[1] = new Month("February", 29, year);
		}else {
			months[1] = new Month("February", 28, year);
		}
		months[2] = new Month("March", 31, year);
		months[3] = new Month("April", 30, year);
		months[4] = new Month("May", 31, year);
		months[5] = new Month("June", 30, year);
		months[6] = new Month("July", 31, year);
		months[7] = new Month("August", 31, year);
		months[8] = new Month("September", 30, year);
		months[9] = new Month("October", 31, year);
		months[10] = new Month("November", 30, year);
		months[11] = new Month("Decemeber", 31, year);
	}
	
	public Day getDay(int day) {
		return selectedMonth.getDay(day);
	}
	
	public void setMonth(int i) {
		if(i < 1 || i > 12) {
			i = 1;
		}
		selectedMonth = months[i - 1];
	}
	
	public Month getMonth() {
		return selectedMonth;
	}
	
	public Month getMonth(String s) {
		for (Month month: months) {
			if (month.name.equals(s)) {
				return month;
			}
		}
		return null;
	}
	
	public Month nextMonth() {
		for(int i = 0; i < months.length; i++) {
			System.out.println(i);
			if (months[i].equals(selectedMonth)) {
				if(i == 11) {
					selectedMonth = months[0];
					//year = year++;
					return selectedMonth;
				}
				selectedMonth = months[i + 1];
				return selectedMonth;
			}
		}
		return selectedMonth;
	}
	
	public Month previousMonth() {
		for(int i = 0; i < months.length; i++) {
			System.out.println(i);
			if (months[i].equals(selectedMonth)) {
				if(i == 0) {
					selectedMonth = months[11];
					//year = year--;
					return selectedMonth;
				}
				selectedMonth = months[i - 1];
				return selectedMonth;
			}
		}
		return selectedMonth;
	}
}

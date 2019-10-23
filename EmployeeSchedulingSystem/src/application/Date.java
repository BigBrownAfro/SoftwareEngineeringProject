package application;

public class Date {
	private String month;
	private int day;
	
	public Date(String m, int d) {
		month = "Undefined";
		day = 0;
		setDate(m, d);
	}
	
	public void setDate(String m, int d) {
		month = m;
		if(d > 0 && d < 32) {
			day = d;
		}else {
			System.out.println("Invalid day number");
		}
	}
}

package application;

import java.util.ArrayList;

public class Setup {

	ArrayList<TimePeriod> staticShifts;
	ArrayList<Employee> employees;
	ArrayList<String> workDays;
	ArrayList<Date> dateExemptions;
	
	public Setup() {
		staticShifts = new ArrayList<TimePeriod>();
		employees = new ArrayList<Employee>();
		workDays = new ArrayList<String>();
		dateExemptions = new ArrayList<Date>();
		
		testDefaultSetup();
	}
	
	private void testDefaultSetup() {
		staticShifts.add(new TimePeriod(000,800)); //12AM to 8AM
		staticShifts.add(new TimePeriod(800,1600)); //8AM to 4PM
		staticShifts.add(new TimePeriod(1600,2400)); //4PM to 12AM
		
		employees.add(new Employee("Elijah", "Williams", 1005));
		employees.add(new Employee("Janet", "Jackson", 'J', 1006));
		employees.add(new Employee("George", "Lopez", 1009));
		
		workDays.add("Monday");
		workDays.add("Tuedday");
		workDays.add("Wednesday");
		workDays.add("Thursday");
		workDays.add("Friday");
		
		dateExemptions.add(new Date("July", 4));
		dateExemptions.add(new Date("December", 25));
		
		System.out.println("test default setup complete");
	}
}

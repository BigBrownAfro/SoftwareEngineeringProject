package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Setup implements Comparator<Employee>{

	ArrayList<TimePeriod> staticShifts;
	ArrayList<Employee> employees;
	ArrayList<String> workDays;
	ArrayList<Date> dateExemptions;
	
	public Setup() {
		staticShifts = new ArrayList<TimePeriod>();
		employees = new ArrayList<Employee>();
		workDays = new ArrayList<String>();
		dateExemptions = new ArrayList<Date>();
		
	}
	
	public void sortEmployeesByHours(){
		Collections.sort(employees, new SortEmployeesByDaysWorked());
	}

	@Override
	public int compare(Employee o1, Employee o2) {
		// TODO Auto-generated method stub
		return 0;
	}
}
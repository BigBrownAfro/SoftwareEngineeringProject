package application;

import java.util.ArrayList;

public class TimeSlot {
	TimePeriod timePeriod;
	boolean isFilled;
	ArrayList<Employee> employees;
	
	public TimeSlot(TimePeriod tp) {
		timePeriod = tp;
		isFilled = false;
		employees = new ArrayList<Employee>();
	}
	
	public boolean addEmployee(Employee e) {
		if(isFilled) {
			return employees.add(e);
		}else {
			isFilled = employees.add(e);
			return isFilled;
		}
	}
	
	public boolean removeEmployee(Employee e) {
		if(isFilled) {
			boolean didRemove = false;
			didRemove = employees.remove(e);
			isFilled = !employees.isEmpty();
			return didRemove;
		}else {
			System.out.println("Could not remove employee from unfilled time slot");
			return false;
		}
	}
}

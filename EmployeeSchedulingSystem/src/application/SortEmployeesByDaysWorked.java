package application;

import java.util.Comparator;

public class SortEmployeesByDaysWorked implements Comparator<Employee> {

	@Override
	public int compare(Employee e1, Employee e2) {
		return e1.numDaysWorking - e2.numDaysWorking;
	}

}

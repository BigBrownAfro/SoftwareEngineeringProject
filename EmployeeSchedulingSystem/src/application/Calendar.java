package application;

import java.util.ArrayList;
import java.util.Collections;

public class Calendar {
	private int dayOffset; //from sunday starting 2019
	private Month[] months;
	private int selectedMonth; //index of selected month (0 to 11);
	
	Setup settings;
	int year;
	
	public Calendar(Setup settings) {
		year = 2019;
		this.settings = settings;
		
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
	
	/**
	 * Generates a filled calendar based on the settings (Setup)
	 * @return
	 */
	public boolean generateCalendar() {
		boolean isValid = true;
		
		//add the static shifts to each worked day
		for(Month month: months) {
			for(Day day: month.days) {
				boolean isWorked = false;
				for(String workDayName: settings.workDays) {
					if(workDayName.equals(day.getName())) {
						isWorked = true;
						break;
					}
				}
				if(isWorked) {
					for(TimePeriod timePeriod: settings.staticShifts) {
						day.timeSlots.add(new TimeSlot(timePeriod));
					}
				}
			}
		}
		
		//Decide schedule per workday
		for(String workDayName: settings.workDays) {
			
			//check which employees can work the day
			ArrayList<Employee> availableEmployees = new ArrayList<Employee>();
			for(Employee employee: settings.employees) {
				for (String dayName: employee.getAvailableDays()) {
					if(dayName.equals(workDayName)) {
						availableEmployees.add(employee);
						break;
					}
				}
			}
			
			//if nobody can work the day return with error
			if(availableEmployees.isEmpty()) {
				System.out.println("No available employees for: " + workDayName);
				isValid = false;
			}
			
			//For each shift for the day
			for(TimePeriod shift: settings.staticShifts) {
				boolean shiftFilled = false;
				
				//sort available employees by days worked
				Collections.sort(availableEmployees, new SortEmployeesByDaysWorked());
				
				//for each available employee
				for(Employee employee: availableEmployees) {
					
					//if the employee is already working today skip them
					if(employee.isWorking(workDayName)) {
						continue;
					}
					
					//for each time availability per employee
					for(TimePeriod availableTime: employee.getAvailableTimes()) {
						
						//If the shift is within their available time
						if(availableTime.completelyOverlaps(shift)) {
							shiftFilled = true;
							
							//Assign the employee to that shift
							assignEmployeeToShift(employee, workDayName, shift);
							
							//Set the employee as working on that day in the employee object
							employee.setWorking(workDayName);
							
							//the shift was filled so break out of the available time loop
							break;
						}
					}
					if(shiftFilled) {
						//the shift was filled so break out of the employees loop and move on to the next shift
						break; 
					}
				}
				
				//Check to see if the shift wasn't filled before exiting
				if(!shiftFilled) {
					System.out.println("shift from " + shift.getStart() + " to " + shift.getEnd() + " on " + workDayName + " wasn't filled");
					isValid = false;
				}
			}
		}
		
		return isValid;
	}
	
	/**
	 * Assigns an employee to a time shift for every day with that name
	 * @param employee
	 * @param dayName
	 * @param timePeriod
	 */
	private void assignEmployeeToShift(Employee employee, String dayName ,TimePeriod timePeriod) {
		for(Month month: months) {
			for(Day day: month.days) {
				if(day.getName().equals(dayName)) {
					for(TimeSlot timeSlot: day.timeSlots) {
						if(timeSlot.timePeriod.equals(timePeriod)) {
							timeSlot.addEmployee(employee);
						}
					}
				}
			}
		}
	}
}

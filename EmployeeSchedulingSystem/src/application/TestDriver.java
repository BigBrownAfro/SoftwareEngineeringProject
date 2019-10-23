package application;

public class TestDriver {

	public static void main(String[] args) {
		Setup preferences = new Setup();
		System.out.println("Employees:");
		for(Employee e: preferences.employees) {
			System.out.println(e.getLastName() + ", " + e.getFirstName() + " " + e.getMiddleName());
		}
		System.out.println("\n");
		
		Calendar calendar = new Calendar();
		Day day;
		System.out.println("Random Days:");
		day = calendar.months[0].days[5];
		System.out.println(day.getMonth() + " " + day.getDay() + ", " + calendar.year);
		
		day = calendar.months[5].days[16];
		System.out.println(day.getMonth() + " " + day.getDay() + ", " + calendar.year);
		
		System.out.println("Test Driver Finished");
	}

}

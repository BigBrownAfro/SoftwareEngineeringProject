package application;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class TimeSlotNode extends StackPane{
	TimeSlot timeSlot;
	Rectangle rectangle;
	Day day;
	VBox allEmployees;
	Text employeeText;
	Text employee2;
	StackPane textOnRectangle;
	
	public TimeSlotNode(Day day, TimeSlot timeSlot) {
		this.day = day;
		this.timeSlot = timeSlot;

		employeeText = new Text("Unknown Employee");
		employee2 = new Text("Unknown Employee 2");
		
		
		if(timeSlot.isFilled) {
			employeeText = new Text(timeSlot.employees.toString());
		}
		
		VBox allEmployees = new VBox();
		allEmployees.getChildren().add(employeeText);
		

		
		if(timeSlot.timePeriod.getStart()== 0) {
			rectangle = new Rectangle(0,0,100,36);
		}else {
			rectangle = new Rectangle(100,36);
		}
		rectangle.setFill(Color.color(Math.random(), Math.random(), Math.random()));
		
		
	}
	
}

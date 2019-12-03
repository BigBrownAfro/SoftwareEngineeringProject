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

		employeeText = new Text(" ");
		employee2 = new Text("Unknown Employee 2");

		
		
		if(timeSlot.isFilled) {
			employeeText = new Text(timeSlot.employees.toString());
		}
		
		
		
		

		
		if(timeSlot.timePeriod.getStart()== 0) {
			rectangle = new Rectangle(0,0,150,36);
		}else {
			rectangle = new Rectangle(150,36);
		}
		rectangle.setFill(Color.color(Math.random(), Math.random(), Math.random()));
		rectangle.setFill(Color.WHITESMOKE);
		
		
	}
	
}

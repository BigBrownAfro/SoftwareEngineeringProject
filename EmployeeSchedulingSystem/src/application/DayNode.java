package application;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
/*
 Coded by Jacob Jackson
 */

public class DayNode extends BorderPane {
	double width = 900;
	double height = 895;
	boolean isActive = true;
	CalendarNode parent;
	Text dayText;
	Text testText;
	TimeSlotNode[] morningTimeSlotArray = new TimeSlotNode[24];
	TimeSlotNode[] eveningTimeSlotArray= new TimeSlotNode[24];
	StackPane[] morningTimePaneArray = new StackPane[24];
	StackPane[] eveningTimePaneArray = new StackPane[24];

	public DayNode(Day day, CalendarNode parent) {
		BorderPane dayRoot = new BorderPane();
		BorderPane dayContent = new BorderPane();
		BorderPane topDayContent = new BorderPane();
		dayText = new Text(day.printDate());
		dayText.setFont(new Font("times new roman", 32));
		
		testText = new Text("This is a test 1");
		testText.setFont(new Font("times new roman", 32));
		
		
		Button exit = new Button("Close");
		
		exit.setOnMouseClicked(event -> {
			
			//dayRoot.setVisible(false);
			isActive = false;
			parent.removeDayNode();
		});
	
		BackgroundFill mainBackgroundFill = new BackgroundFill(Color.THISTLE, null, null);
		Background mainBackground = new Background(mainBackgroundFill);
	
		
		//This creates the black border around the entire view
		dayContent.setBackground(mainBackground);
		Line leftOutline = new Line(0,0,0,900);
		leftOutline.setStroke(Color.BLACK);
		leftOutline.setStrokeWidth(2);
	
		
		Line rightOutline = new Line(0,0,0,900);
		rightOutline.setStroke(Color.BLACK);
		rightOutline.setStrokeWidth(2);
		
		Line bottomOutline = new Line(0,0,955,0);
		bottomOutline.setStroke(Color.BLACK);
		bottomOutline.setStrokeWidth(2);
		
		Line topOutline = new Line(0,0,955,0);
		topOutline.setStroke(Color.BLACK);
		topOutline.setStrokeWidth(2);
		
		//Creating the base contents of the view
		HBox hBox = new HBox();
		VBox morningTimeBox = new VBox(56);
		VBox morningTimePeriodBox = new VBox();
		Rectangle spacingRectangle = new Rectangle(50,36);
		Rectangle spacingRectangle2 = new Rectangle(450,36);
		VBox eveningTimeBox = new VBox(56);
		VBox eveningTimePeriodBox = new VBox();
		StackPane morningTimePeriodBoxPane = new StackPane();
		StackPane morningTimePeriodBoxPane1 = new StackPane();
		
		//Set background color
		hBox.setBackground(new Background(new BackgroundFill(Color.THISTLE, null, null)));

		//Make time slots for every half hour block
		int startCount = 0;
		int endCount = 50;
		for(int i = 0; i < 24; i++) {
			morningTimeSlotArray[i] = new TimeSlotNode(day, new TimeSlot(new TimePeriod(startCount, endCount)));
			morningTimeSlotArray[i].rectangle.setOpacity(0);
			startCount += 50;
			endCount += 50;
		}
		// Making sure only the time slots that are being worked are visible
		for (TimeSlot timeSlot: day.timeSlots) {
			System.out.println("start " + timeSlot.timePeriod.getStart());
			System.out.println("end " + timeSlot.timePeriod.getEnd());
			
			for (TimeSlotNode timeSlotNode: morningTimeSlotArray) {
				if(timeSlot.timePeriod.completelyOverlaps(timeSlotNode.timeSlot.timePeriod)) {
					timeSlotNode.rectangle.setOpacity(1);
					
					timeSlotNode.employeeText = new Text(timeSlot.employees.get(0).getFirstName() + " " + timeSlot.employees.get(0).getLastName());
				}
			}
		}
		
		spacingRectangle.setOpacity(0);
		hBox.getChildren().add(spacingRectangle);
		
		morningTimeBox.getChildren().add(new Text("12:00 AM"));
		morningTimeBox.getChildren().add(new Text("01:00 AM"));
		morningTimeBox.getChildren().add(new Text("02:00 AM"));
		morningTimeBox.getChildren().add(new Text("03:00 AM"));
		morningTimeBox.getChildren().add(new Text("04:00 AM"));
		morningTimeBox.getChildren().add(new Text("05:00 AM"));
		morningTimeBox.getChildren().add(new Text("06:00 AM"));
		morningTimeBox.getChildren().add(new Text("07:00 AM"));
		morningTimeBox.getChildren().add(new Text("08:00 AM"));
		morningTimeBox.getChildren().add(new Text("09:00 AM"));
		morningTimeBox.getChildren().add(new Text("10:00 AM"));
		morningTimeBox.getChildren().add(new Text("11:00 AM"));
			

		
		for(int i = 0; i < 24; i++) {
			morningTimePaneArray[i] = new StackPane();
			morningTimePaneArray[i].getChildren().add(morningTimeSlotArray[i].rectangle);
			morningTimePaneArray[i].getChildren().add(morningTimeSlotArray[i].employeeText);
		}
		
		
		for(int i = 0; i < 24; i++) {
			morningTimePeriodBox.getChildren().add(morningTimePaneArray[i]);

		}
		

		
		
		hBox.getChildren().add(morningTimeBox);
		hBox.getChildren().add(morningTimePeriodBox);
		
		startCount = 1200;
		endCount = 1250;
		for(int i = 0; i < 24; i++) {
			eveningTimeSlotArray[i] = new TimeSlotNode(day, new TimeSlot(new TimePeriod(startCount, endCount)));
			eveningTimeSlotArray[i].rectangle.setOpacity(0);
			startCount += 50;
			endCount += 50;
		}
		
		for (TimeSlot timeSlot: day.timeSlots) {
			System.out.println("start " + timeSlot.timePeriod.getStart());
			System.out.println("end " + timeSlot.timePeriod.getEnd());
			
			for (TimeSlotNode timeSlotNode: eveningTimeSlotArray) {
				if(timeSlot.timePeriod.completelyOverlaps(timeSlotNode.timeSlot.timePeriod)) {
					timeSlotNode.rectangle.setOpacity(1);
					
					timeSlotNode.employeeText = new Text(timeSlot.employees.get(0).getFirstName() + " " + timeSlot.employees.get(0).getLastName());
				}
			}
		}
		
		for(int i = 0; i < 24; i++) {
			eveningTimePaneArray[i] = new StackPane();
			eveningTimePaneArray[i].getChildren().add(eveningTimeSlotArray[i].rectangle);
			eveningTimePaneArray[i].getChildren().add(eveningTimeSlotArray[i].employeeText);
			
		}
		
		for(int i = 0; i < 24; i++) {
			eveningTimePeriodBox.getChildren().add(eveningTimePaneArray[i]);

		}
		
		spacingRectangle2.setOpacity(0);
		hBox.getChildren().add(spacingRectangle2);
	
		
		eveningTimeBox.getChildren().add(new Text("12:00 PM"));
		eveningTimeBox.getChildren().add(new Text("01:00 PM"));
		eveningTimeBox.getChildren().add(new Text("02:00 PM"));
		eveningTimeBox.getChildren().add(new Text("03:00 PM"));
		eveningTimeBox.getChildren().add(new Text("04:00 PM"));
		eveningTimeBox.getChildren().add(new Text("05:00 PM"));
		eveningTimeBox.getChildren().add(new Text("06:00 PM"));
		eveningTimeBox.getChildren().add(new Text("07:00 PM"));
		eveningTimeBox.getChildren().add(new Text("08:00 PM"));
		eveningTimeBox.getChildren().add(new Text("09:00 PM"));
		eveningTimeBox.getChildren().add(new Text("10:00 PM"));
		eveningTimeBox.getChildren().add(new Text("11:00 PM"));
		

		
		hBox.getChildren().add(eveningTimeBox);
		hBox.getChildren().add(eveningTimePeriodBox);

		dayContent.setCenter(hBox);
		
		
		
		
		topDayContent.setCenter(dayText);

		dayRoot.setLeft(leftOutline);
		dayRoot.setRight(rightOutline);
		dayRoot.setBottom(bottomOutline);
		dayRoot.setTop(topOutline);
		dayRoot.setCenter(dayContent);
		dayContent.setTop(topDayContent);
		topDayContent.setRight(exit);
		
		
		
		this.getChildren().add(dayRoot);
		
	
	}
	

}

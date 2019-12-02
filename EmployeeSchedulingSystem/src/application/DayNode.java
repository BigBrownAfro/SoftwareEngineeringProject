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
	
		BackgroundFill mainBackgroundFill = new BackgroundFill(Color.WHITE, null, null);
		Background mainBackground = new Background(mainBackgroundFill);
	
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
		
		HBox hBox = new HBox();
		VBox morningTimeBox = new VBox(56);
		VBox morningTimePeriodBox = new VBox();
		VBox eveningTimeBox = new VBox(56);
		VBox eveningTimePeriodBox = new VBox();
		StackPane morningTimePeriodBoxPane = new StackPane();
		StackPane morningTimePeriodBoxPane1 = new StackPane();
		
		hBox.setBackground(new Background(new BackgroundFill(Color.THISTLE, null, null)));
		//dayContent.getChildren().add(dayText);
		//dayContent.getChildren().add(testText);
		
		//TimeSlotNode morning12Node = new TimeSlotNode(day, new TimeSlot(new TimePeriod(0,50)));
		//TimeSlotNode morning1230Node = new TimeSlotNode(day, new TimeSlot(new TimePeriod(50,100)));
		//TimeSlotNode morning1Node = new TimeSlotNode(day, new TimeSlot(new TimePeriod(100,150)));
		//TimeSlotNode morning130Node = new TimeSlotNode(day, new TimeSlot(new TimePeriod(200,250)));
		//TimeSlotNode morning2Node = new TimeSlotNode(day, new TimeSlot(new TimePeriod(300,350)));
		//TimeSlotNode morning230Node = new TimeSlotNode(day, new TimeSlot(new TimePeriod(400,450)));
		
		int startCount = 0;
		int endCount = 50;
		for(int i = 0; i < 24; i++) {
			morningTimeSlotArray[i] = new TimeSlotNode(day, new TimeSlot(new TimePeriod(startCount, endCount)));
			startCount += 50;
			endCount += 50;
		}
		
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
		
		
		/*
		Rectangle morning12Period = new Rectangle(0,0,100,36);
		Rectangle morning1230Period = new Rectangle(100,36);
		Rectangle morning1Period = new Rectangle(100,36);
		Rectangle morning130Period = new Rectangle(100,36);
		Rectangle morning2Period = new Rectangle(100,36);
		Rectangle morning230Period = new Rectangle(100,36);
		Rectangle morning3Period = new Rectangle(100,36);
		Rectangle morning330Period = new Rectangle(100,36);
		Rectangle morning4Period = new Rectangle(100,36);
		Rectangle morning430Period = new Rectangle(100,36);
		Rectangle morning5Period = new Rectangle(100,36);
		Rectangle morning530Period = new Rectangle(100,36);
		Rectangle morning6Period = new Rectangle(100,36);
		Rectangle morning630Period = new Rectangle(100,36);
		Rectangle morning7Period = new Rectangle(100,36);
		Rectangle morning730Period = new Rectangle(100,36);
		Rectangle morning8Period = new Rectangle(100,36);
		Rectangle morning830Period = new Rectangle(100,36);
		Rectangle morning9Period = new Rectangle(100,36);
		Rectangle morning930Period = new Rectangle(100,36);
		Rectangle morning10Period = new Rectangle(100,36);
		Rectangle morning1030Period = new Rectangle(100,36);
		Rectangle morning11Period = new Rectangle(100,36);
		Rectangle morning1130Period = new Rectangle(100,36);
		*/
	
		
	

		//morning1Node.rectangle.setOpacity(0);
		//morning2Node.rectangle.setOpacity(0);
		
	//	VBox allEmployees = new VBox();
		
		//allEmployees.getChildren().addAll(morning12Node.employeeText,morning12Node.employee2);
		
		//morningTimePeriodBox.getChildren().add(morning12Node.rectangle);
		//morningTimePeriodBox.getChildren().add(morning12Node.employeeText);
		
	//	for(int i = 0; i < 24; i++) {
	//		morningTimePeriodBoxPane.getChildren().add(morningTimeSlotArray[i].rectangle);
	//	}
		//morningTimePeriodBoxPane.getChildren().addAll(morning12Node.rectangle,allEmployees);

//		for(int i = 0; i < morningTimeSlotArray.length-1; i ++) {
	//		morningTimePeriodBox.getChildren().add(morningTimeSlotArray[i]);
		//}
		
	//	for(Employee employee: parent.settings.employees) {
	//		allEmployees.getChildren().add(new Text(employee.getFirstName() + " " + employee.getLastName()));
	//	}
		
		//Need StackPane for every TimeSlot?
	//	morningTimePeriodBoxPane.getChildren().add(allEmployees);
		
		
	
		
		// Instead of creating timeslots here, just copy timeslots from the day.
		
		
		
		
		
		for (TimeSlot timeSlot: day.timeSlots) {
			System.out.println(timeSlot.timePeriod.getStart());
		}
		
		for(int i = 0; i < 24; i++) {
			morningTimePaneArray[i] = new StackPane();
			morningTimePaneArray[i].getChildren().add(morningTimeSlotArray[i].rectangle);
			morningTimePaneArray[i].getChildren().add(morningTimeSlotArray[i].employeeText);
		}
		
		
		for(int i = 0; i < 24; i++) {
			morningTimePeriodBox.getChildren().add(morningTimePaneArray[i]);

		}
		
		//morningTimePeriodBox.getChildren().add(morningTimePeriodBoxPane);
		//morningTimePeriodBox.getChildren().add(morningTimePeriodBoxPane1);
	
		/*
		morningTimePeriodBox.getChildren().add(morning1230Node.rectangle);
		morningTimePeriodBox.getChildren().add(morning1Node.rectangle);
		morningTimePeriodBox.getChildren().add(morning130Node.rectangle);
		morningTimePeriodBox.getChildren().add(morning2Node.rectangle);
		morningTimePeriodBox.getChildren().add(morning230Node.rectangle);
		morningTimePeriodBox.getChildren().add(morning3Period);
		morningTimePeriodBox.getChildren().add(morning330Period);
		morningTimePeriodBox.getChildren().add(morning4Period);
		morningTimePeriodBox.getChildren().add(morning430Period);
		morningTimePeriodBox.getChildren().add(morning5Period);
		morningTimePeriodBox.getChildren().add(morning530Period);
		morningTimePeriodBox.getChildren().add(morning6Period);
		morningTimePeriodBox.getChildren().add(morning630Period);
		morningTimePeriodBox.getChildren().add(morning7Period);
		morningTimePeriodBox.getChildren().add(morning730Period);
		morningTimePeriodBox.getChildren().add(morning8Period);
		morningTimePeriodBox.getChildren().add(morning830Period);
		morningTimePeriodBox.getChildren().add(morning9Period);
		morningTimePeriodBox.getChildren().add(morning930Period);
		morningTimePeriodBox.getChildren().add(morning10Period);
		morningTimePeriodBox.getChildren().add(morning1030Period);
		morningTimePeriodBox.getChildren().add(morning11Period);
		morningTimePeriodBox.getChildren().add(morning1130Period);
		*/
		
	
		
		
		//morning930Period.setOpacity(1);

		
		hBox.getChildren().add(morningTimeBox);
		hBox.getChildren().add(morningTimePeriodBox);
		
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
		
		Rectangle evening12Period = new Rectangle(0,0,100,36);
		Rectangle evening1230Period = new Rectangle(100,36);
		Rectangle evening1Period = new Rectangle(100,36);
		Rectangle evening130Period = new Rectangle(100,36);
		Rectangle evening2Period = new Rectangle(100,36);
		Rectangle evening230Period = new Rectangle(100,36);
		Rectangle evening3Period = new Rectangle(100,36);
		Rectangle evening330Period = new Rectangle(100,36);
		Rectangle evening4Period = new Rectangle(100,36);
		Rectangle evening430Period = new Rectangle(100,36);
		Rectangle evening5Period = new Rectangle(100,36);
		Rectangle evening530Period = new Rectangle(100,36);
		Rectangle evening6Period = new Rectangle(100,36);
		Rectangle evening630Period = new Rectangle(100,36);
		Rectangle evening7Period = new Rectangle(100,36);
		Rectangle evening730Period = new Rectangle(100,36);
		Rectangle evening8Period = new Rectangle(100,36);
		Rectangle evening830Period = new Rectangle(100,36);
		Rectangle evening9Period = new Rectangle(100,36);
		Rectangle evening930Period = new Rectangle(100,36);
		Rectangle evening10Period = new Rectangle(100,36);
		Rectangle evening1030Period = new Rectangle(100,36);
		Rectangle evening11Period = new Rectangle(100,36);
		Rectangle evening1130Period = new Rectangle(100,36);
		
		eveningTimePeriodBox.getChildren().add(evening12Period);
		eveningTimePeriodBox.getChildren().add(evening1230Period);
		eveningTimePeriodBox.getChildren().add(evening1Period);
		eveningTimePeriodBox.getChildren().add(evening130Period);
		eveningTimePeriodBox.getChildren().add(evening2Period);
		eveningTimePeriodBox.getChildren().add(evening230Period);
		eveningTimePeriodBox.getChildren().add(evening3Period);
		eveningTimePeriodBox.getChildren().add(evening330Period);
		eveningTimePeriodBox.getChildren().add(evening4Period);
		eveningTimePeriodBox.getChildren().add(evening430Period);
		eveningTimePeriodBox.getChildren().add(evening5Period);
		eveningTimePeriodBox.getChildren().add(evening530Period);
		eveningTimePeriodBox.getChildren().add(evening6Period);
		eveningTimePeriodBox.getChildren().add(evening630Period);
		eveningTimePeriodBox.getChildren().add(evening7Period);
		eveningTimePeriodBox.getChildren().add(evening730Period);
		eveningTimePeriodBox.getChildren().add(evening8Period);
		eveningTimePeriodBox.getChildren().add(evening830Period);
		eveningTimePeriodBox.getChildren().add(evening9Period);
		eveningTimePeriodBox.getChildren().add(evening930Period);
		eveningTimePeriodBox.getChildren().add(evening10Period);
		eveningTimePeriodBox.getChildren().add(evening1030Period);
		eveningTimePeriodBox.getChildren().add(evening11Period);
		eveningTimePeriodBox.getChildren().add(evening1130Period);
		
		for (Node node: eveningTimePeriodBox.getChildren()) {
			((Shape) node).setFill(Color.MEDIUMPURPLE);
		}

		
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

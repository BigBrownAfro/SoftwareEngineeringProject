package application;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DayNode extends BorderPane {
	double width = 900;
	double height = 895;
	boolean isActive = true;
	CalendarNode parent;
	Text dayText;
	Text testText;

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
		
		VBox vBox = new VBox();
		
		vBox.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		dayContent.getChildren().add(dayText);
		//dayContent.getChildren().add(testText);
		
		vBox.getChildren().add(new Text("00:00"));
		vBox.getChildren().add(new Text("01:00"));
		vBox.getChildren().add(new Text("02:00"));
		vBox.getChildren().add(new Text("03:00"));
		vBox.getChildren().add(new Text("04:00"));
		vBox.getChildren().add(new Text("05:00"));
		vBox.getChildren().add(new Text("06:00"));
		vBox.getChildren().add(new Text("07:00"));
		vBox.getChildren().add(new Text("08:00"));
		vBox.getChildren().add(new Text("09:00"));
		vBox.getChildren().add(new Text("10:00"));
		vBox.getChildren().add(new Text("11:00"));
		vBox.getChildren().add(new Text("12:00"));

		dayContent.setCenter(vBox);
		
		
		
		
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

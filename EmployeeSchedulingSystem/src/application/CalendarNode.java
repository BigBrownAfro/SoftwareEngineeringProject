package application;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CalendarNode extends StackPane {
	Calendar calendar;
	Setup settings; //To be deleted
	double width;
	double height;
	TilePane dayTilePane;
	Text monthNameText;
	
	CalendarNode(Calendar calendar, double width, double height){
		this.width = width;
		this.height = height;
		
		//Link database --------------------------------------------------------------------------------------------------------------
		this.calendar = calendar;
		
		//For testing purposes
		this.settings = calendar.settings;
		//settings.staticShifts.add(new TimePeriod(000,800)); //12AM to 8AM
		settings.staticShifts.add(new TimePeriod(800,1600)); //8AM to 4PM
		settings.staticShifts.add(new TimePeriod(1600,2400)); //4PM to 12AM
		
		Employee elgia = new Employee("Elijah", "Williams", 'J', 1005);
		elgia.addAvailableDay("Monday");
		elgia.addAvailableDay("Tuesday");
		elgia.addAvailableDay("Wednesday");
		elgia.addAvailableDay("Thursday");
		elgia.addAvailableDay("Friday");
		elgia.addAvailableTime(800, 2400);
		
		Employee janet = new Employee("Janet", "Jackson", 'P', 1006);
		janet.addAvailableDay("Wednesday");
		janet.addAvailableDay("Thursday");
		janet.addAvailableDay("Friday");
		janet.addAvailableTime(800, 1600);
		
		Employee george = new Employee("George", "Lopez", 1009);
		george.addAvailableDay("Sunday");
		george.addAvailableDay("Monday");
		george.addAvailableDay("Tuesday");
		george.addAvailableDay("Wednesday");
		george.addAvailableDay("Friday");
		george.addAvailableDay("Saturday");
		george.addAvailableTime(1600, 2400);
		
		settings.employees.add(elgia);
		settings.employees.add(janet);
		settings.employees.add(george);
		
		settings.workDays.add("Monday");
		settings.workDays.add("Tuesday");
		settings.workDays.add("Wednesday");
		settings.workDays.add("Thursday");
		settings.workDays.add("Friday");
		
		settings.dateExemptions.add(new Date("July", 4));
		settings.dateExemptions.add(new Date("December", 25));
		
		boolean hasNoErrors = calendar.generateCalendar();
		if(hasNoErrors) {
			System.out.println("Calendar was created with no errors");
		}else {
			System.out.println("Calendar was created with errors");
		}
		
		//Setup GUI -------------------------------------------------------------------------------------------------------------------
		//create a gradient for the background color of the calendar
		Stop[] stops = new Stop[] {
				new Stop(0, Color.rgb(180, 130, 130)),
				new Stop(1, Color.rgb(150, 100, 150))
		};
		LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
		
		//create a vBox which we will insert the top, middle, and bottom content into
		VBox vBox = new VBox();
		
		//setup vBox size and background color
		vBox.setMinSize(width, height);
		vBox.setBackground(new Background(new BackgroundFill(gradient, null, null)));
		
		//add the vBox the calendar node's children
		this.getChildren().add(vBox);
		
		//Build the top of the calendar in the first slot of the vBox
		this.buildHeader(vBox);
		
		//Build the middle of the calendar with the day names
		this.buildDayNames(vBox);
		
		//Build the bottom of the calendar with a tile pane for the days
		this.buildDayTiles(vBox);
	}
	
	/**
	 * Builds the head of the calendar with a left arrow, month name, and right arrow.
	 * @param vBox
	 */
	private void buildHeader(VBox vBox) {
		//adding a BorderPane to vBox for the month name and the arrows
		BorderPane pageTop = new BorderPane();
		//pageTop.minWidth(width);
		pageTop.setPadding(new Insets(20,20,10,20));
		vBox.getChildren().add(pageTop);
		
		//setup month name textNode
		monthNameText = new Text(calendar.getMonth().name);
		monthNameText.setFont(new Font("times new roman", 32));
		
		//creating arrows
		StackPane arrowL = new Arrow("left");
		StackPane arrowR = new Arrow("right");
		
		//set functions for click events on arrows
		arrowL.setOnMouseClicked(event -> {
			this.previousMonth();
		});
		
		arrowR.setOnMouseClicked(event -> {
			this.nextMonth();
		});

		//add the left arrow to the top of the page
		pageTop.setLeft(arrowL);
		
		//add the text for the month to the top of the page
		pageTop.setCenter(monthNameText);

		//add the right arrow to the top of the page
		pageTop.setRight(arrowR);
	}
	
	/**
	 * Builds the middle of the calendar with the names of the days of the week
	 * @param vBox
	 */
	private void buildDayNames(VBox vBox) {
		//adding an hBox to vBox for the day names
		HBox dayNamesBox = new HBox();
		//dayNamesBox.minWidth(width);
		dayNamesBox.setPadding(new Insets(5,5,5, width / 16));
		dayNamesBox.setSpacing(width / 11);
		dayNamesBox.getChildren().addAll(new Text("Sunday"), new Text("Monday"), new Text("Tuesday"), new Text("Wednesday"), new Text("Thursday"), new Text("Friday"), new Text("Saturday"));
		vBox.getChildren().add(dayNamesBox);
	}
	
	/**
	 * Builds the bottom of the calendar with the grid of day tiles
	 */
	private void buildDayTiles(VBox vBox) {
		//adding a TilePane to the vBox to act as a grid for our days
		dayTilePane = new TilePane();
		dayTilePane.setPadding(new Insets(5, 5, 5, 7));
		dayTilePane.setPrefColumns(7);
		dayTilePane.setVgap(1);
		dayTilePane.setHgap(1);
		vBox.getChildren().add(dayTilePane);
		
		//Insert day tiles into the tile pane
		refreshDayTiles();
	}
	
	/**
	 * Clears the current day tile pane and puts in a new set of day tiles according to the selected month
	 */
	private void refreshDayTiles() {
		//Clear the DayTiles out of the tile pane
		dayTilePane.getChildren().clear();
		
		//calculate the offset of where to put the first day of the current month
		int offset = calendar.getDay(0).dayOfWeekIndex;
		
		//Fill in slots of previous month before first day of current month
		for(int i = 0; i < offset; i++) {
			//Find the previous month's day
			Month pMonth = calendar.peekPreviousMonth();
			Day pDay = pMonth.getDay((pMonth.days.length - 1) - ((offset - 1) - i));
			
			//Setup DayTile
			DayTile dayTile = new DayTile(pDay , (width - 10.0)/7.0 - 3, (width - 10.0)/7.0 - 3);

			//Darken the dayTile's rectangle
			dayTile.rectangle.setOpacity(.3);
			
			//setup an event handler for clicking on a tile
			dayTile.setOnMouseClicked(event -> {
				dayTile.day.printDate();
			});
			
			//Setup an event handler for hovering over the tile
			dayTile.setOnMouseEntered(event -> {
				//Darken the dayTile rectangle by setting opacity to %40
				dayTile.rectangle.setOpacity(.4);
			});
			
			//setup an event handle for exiting a hover over a tile
			dayTile.setOnMouseExited(event -> {
				//Return dayTile rectangle to %20 opacity
				dayTile.rectangle.setOpacity(.3);
			});
			
			//add the tile(rectangle) to the tilePane
			dayTilePane.getChildren().add(dayTile);
		}
		
		//add in day tiles
		for(int i = 0; i < calendar.getMonth().days.length; i++) {
			//Setup DayTile
			DayTile dayTile = new DayTile(calendar.getDay(i), (width - 10.0)/7.0 - 3, (width - 10.0)/7.0 - 3);
			
			//setup an event handler for clicking on a tile
			dayTile.setOnMouseClicked(event -> {
				dayTile.day.printDate();
			});
			
			//Setup an event handler for hovering over the tile
			dayTile.setOnMouseEntered(event -> {
				//Darken the dayTile rectangle by setting opacity to %40
				dayTile.rectangle.setOpacity(.3);
			});
			
			//setup an event handle for exiting a hover over a tile
			dayTile.setOnMouseExited(event -> {
				//Return dayTile rectangle to %20 opacity
				dayTile.rectangle.setOpacity(.1);
			});
			
			//add the tile(rectangle) to the tilePane
			dayTilePane.getChildren().add(dayTile);
		}
		
		//add in extra days from the next month to the tilePane to fill it completely
		int temp_currentDays = dayTilePane.getChildren().size();
		for (int i = 0; i < 42 - temp_currentDays; i++) {
			//Find the next month's day
			Month nMonth = calendar.peekNextMonth();
			Day nDay = nMonth.getDay(i);
			
			//Setup DayTile
			DayTile dayTile = new DayTile(nDay , (width - 10.0)/7.0 - 3, (width - 10.0)/7.0 - 3);
			
			//Darken the dayTile's rectangle since it's not part of the current month
			dayTile.rectangle.setOpacity(.3);
			
			//setup an event handler for clicking on a tile
			dayTile.setOnMouseClicked(event -> {
				dayTile.day.printDate();
			});
			
			//Setup an event handler for hovering over the tile
			dayTile.setOnMouseEntered(event -> {
				//Darken the dayTile rectangle by setting opacity to %40
				dayTile.rectangle.setOpacity(.4);
			});
			
			//setup an event handle for exiting a hover over a tile
			dayTile.setOnMouseExited(event -> {
				//Return dayTile rectangle to %20 opacity
				dayTile.rectangle.setOpacity(.3);
			});
			
			//add the tile(rectangle) to the tilePane
			dayTilePane.getChildren().add(dayTile);
		}
	}
	
	/**
	 * Moves the calendar to the next month and updates visuals
	 */
	private void nextMonth() {
		calendar.nextMonth();
		monthNameText.setText(calendar.getMonth().name);
		refreshDayTiles();
	}
	
	/**
	 * Moves the calendar to the previous month and updates visuals
	 */
	private void previousMonth() {
		calendar.previousMonth();
		monthNameText.setText(calendar.getMonth().name);
		refreshDayTiles();
	}
}

package view;

import java.io.FileInputStream;


import controller.FileController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Car;

public class MainWindow extends Application {

	public BorderPane layout;
	public HBox menubarbox;
	public HBox filterbox;
	public HBox Topmenubox;	
	public Text title;
	public ImageView imageview,imageview1;
	public Scene scene1,scene2;
	public Stage window;
	AddVehicle addvehicle = new AddVehicle();
	public ChoiceBox<String> VehicleType,Seats,Status,Make;
	
	Car car = new Car();
	VehicleView vehicleview = new VehicleView();
	Car dataset = new Car(); 
	FileController filecont = new FileController();
	
	public VBox center;
	ScrollBar s = new ScrollBar(); 
	public VBox v1 =new VBox();
	ScrollPane Scroller;
	boolean flag=true;
	
	
	public static void main(String[] args) {
        launch(args);
    }

	
	@Override
	public void start(Stage primaryStage) throws Exception {

		
		topmenu();
		window = primaryStage;		
        scene1 = new Scene(layout);
		primaryStage.setScene(scene1);
		primaryStage.setTitle("Thrifty Rental Vehicle Management");
		primaryStage.setMaximized(true);
		primaryStage.show();
		
	}
	
	public void topmenu() throws Exception
	{
		car.All();
        storing_id();

		menubar();
        filtermenu();
        Title();
		Topmenubox = new HBox();
	    Topmenubox.getChildren().addAll(menubarbox,title,filterbox);
	    Topmenubox.setSpacing(50);
	    layout = new BorderPane();
        
	    
        
        layout.setTop(Topmenubox);
		Scroller = new ScrollPane(v1);
        layout.setLeft(Scroller);        
        background();
 
	}
	
	public void background()
	{
		BackgroundFill background_fill = new BackgroundFill(Color.LIGHTGRAY,CornerRadii.EMPTY, Insets.EMPTY); 
		Background background = new Background(background_fill); 
		Topmenubox.setBackground(background); 
		
		BackgroundFill background_fill1 = new BackgroundFill(Color.GRAY,CornerRadii.EMPTY, Insets.EMPTY); 
		Background background1 = new Background(background_fill1); 
		layout.setBackground(background1);
		
	}
	
	
	
	public void Title() {
		title = new Text();
		title.setText("THRIFTY RENT VEHICLE SYSTEM");
		title.setFont(Font.font("Times New Roman",FontWeight.BOLD,35));
		title.setFill(Color.BLUE);
		title.setStroke(Color.BLACK);
		title.setStrokeWidth(1);
				
	}
	
	public void menubar ()
	{
		menubarbox = new HBox();
		
		MenuBar menubar = new MenuBar();
		Menu menu = new Menu("Menu");
		MenuItem Import = new MenuItem("Import");
		
		Import.setOnAction(e -> {
			try {
				filecont.importDataFromFile(window);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		MenuItem Export = new MenuItem("Export");
		Export.setOnAction(e -> {
			try {
				filecont.exportDataToFile(window);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		MenuItem AddVehicle = new MenuItem("Add Vehicle");
		AddVehicle.setOnAction(e -> addvehicle.addvehicle(car));
		
		MenuItem Exit = new MenuItem("Exit");
		Exit.setOnAction(e -> System.exit(0));
		menu.getItems().addAll(Import,Export,AddVehicle,Exit);
        menubar.getMenus().addAll(menu); 

        menubarbox.getChildren().addAll(menubar);
        
        
	}
	
	public void filtermenu()
	{
		filterbox = new HBox();
		Label VehType = new Label("Vehicle Type");
		VehType.setPadding(new Insets(4));
	    VehicleType = new ChoiceBox<>(); 
	    VehicleType.getItems().addAll("All", "Car", "Van");
	    VehicleType.setValue("All");
	    
	    VehicleType.valueProperty().addListener((obs, oldvalue,newvalue) ->{
	    	if (newvalue.equals("Van"))
	    	car.vehicle_select("V");
	    	else if(newvalue.equals("Car"))
	    		car.vehicle_select("C");
	    	else car.All();
	    	try {
	    		flag=true;
				storing_id();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    });
	    
	    
	    Label seat = new Label("Seat Type");
	    seat.setPadding(new Insets(4));
	    Seats = new ChoiceBox<>(); 
	    Seats.getItems().addAll("All", "4", "5","7", "11");
	    Seats.setValue("All");
	    
	    Seats.valueProperty().addListener((obs, oldvalue,newvalue) ->{
	    	if (newvalue.equals("4"))
	    	car.seater("4");
	    	else if(newvalue.equals("5"))
	    		car.seater("5");
	    	else if(newvalue.equals("7"))
	    		car.seater("7");
	    	else if(newvalue.equals("11"))
	    		car.seater("11");
	    	else car.All();
	    	try {
	    		flag=true;
				storing_id();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    });
	    
	    Label status = new Label("Status");
	    status.setPadding(new Insets(4));
	    Status = new ChoiceBox<>(); 
	    Status.getItems().addAll("All", "Available","Rented");
	    Status.setValue("All");
	    
	    Status.valueProperty().addListener((obs, oldvalue,newvalue) ->{
	    	if (newvalue.equals("Available"))
	    	car.Available("Y");
	    	else if(newvalue.equals("Rented"))
	    		car.Available("N");
	    	else car.All();
	    	try {
	    		flag=true;
				storing_id();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    });
	    
	    
	    Label make = new Label("Vehicle Make");
	    make.setPadding(new Insets(4));
	    Make = new ChoiceBox<>(); 
	    Make.getItems().addAll("All", "Hyundai","Toyota","Suzuki");
	    Make.setValue("All");
	    
	    Make.valueProperty().addListener((obs, oldvalue,newvalue) ->{
	    	if (newvalue.equals("Hyundai"))
	    	car.make("HYUNDAI");
	    	else if(newvalue.equals("Toyota"))
	    		car.make("TOYOTA");
	    	else if(newvalue.equals("Suzuki"))
	    		car.make("SUZUKI");
	    	else car.All();
	    	try {
	    		flag=true;
				storing_id();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    });
	    
	    filterbox.getChildren().addAll(VehType,VehicleType,seat,Seats,status,Status,make,Make);
        filterbox.setPadding(new Insets(5,0,0,0));
        
	}
	
	public void image() throws Exception
	{
		
		center = new VBox();
		
		if(flag==true)
		{
		FileInputStream backimage = new FileInputStream ("C:\\Users\\Vignesh\\Desktop\\images\\backcar.jpg");
		Image imagebackground = new Image(backimage,1350,400,false,false);
		imageview1 = new ImageView(imagebackground);
		vehicleview.view(dataset);
		
		center.getChildren().addAll(imageview1,vehicleview.imagebox);
		flag=false;
		}
		
		else {
		vehicleview.view(dataset);
		
		center.getChildren().addAll(vehicleview.imagebox);
		}
		BackgroundFill background_fill2 = new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY); 
		Background background2 = new Background(background_fill2); 
		center.setBackground(background2);
		
		
		s.setMin(0);
	    s.setMax(400);
	    s.setMinWidth(50);
	    s.setMinHeight(150);
	    s.setOrientation(Orientation.VERTICAL);

		s.valueProperty().addListener(event ->{
        	layout.setTranslateY(20 - s.getValue());
        });
		
	}
	
	public void storing_id() throws Exception
	{
		v1.getChildren().clear();
		for(String i :car.arr1)
		{
			dataset = car.data(i);
			
			image();
			v1.getChildren().addAll(center);

		}
			
	}
		
	
}


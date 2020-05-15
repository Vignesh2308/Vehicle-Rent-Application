package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Car;


public class AddVehicle {
	public static Stage window2;
	public Scene scene2;
	public Text textHeading;
	public TextField vehicle_id_text,engine_capacity_text,cost_text;
	public HBox vehicle_type_box,Brand_box,model_box,vehicle_class_box,seatbox,engine_type_box,engine_capacity_box,cost_box,specs_box,vehiclestatus_box;
	String seats[]= {"4","5","7","11"};
	String engine[]= {"MANUAL","AUTOMATIC"};
	String vehicle[] = {"Car","Van"};
	String status[] = {"Available","Rented"};
	public RadioButton vehicle_type1,vehicle_type2,seat1,seat2,seat3,seat4,vehiclestatus1,vehiclestatus2,engine_type1,engine_type2;
	public ToggleGroup vehicle_type_toggle,seat_toggle,engine_type_toggle,vehiclestatus_toggle;
	public CheckBox specs1,specs2,specs3,specs4,specs5;
	public ComboBox<String> year_combo,brand_combo,model_combo,vehicle_class_combo;
	public void addvehicle(Car det)
	{
		window2 = new Stage();
		window2.initModality(Modality.APPLICATION_MODAL);
		window2.setMinWidth(250);
		
		textHeading = new Text("Add a new Vehicle!");
		textHeading.setFont(new Font(20));

		
		Label vehicle_type = new Label("Vehicle Type:");
		vehicle_type.setPrefWidth(150);
		vehicle_type1 = new RadioButton("CAR");
		vehicle_type2 = new RadioButton("VAN");	
		vehicle_type_toggle = new ToggleGroup();
		vehicle_type1.setToggleGroup(vehicle_type_toggle);
		vehicle_type2.setToggleGroup(vehicle_type_toggle);
		
		
		vehicle_type_box= new HBox();
		vehicle_type_box.getChildren().addAll(vehicle_type,vehicle_type1,vehicle_type2);
		
		Label year = new Label("Year:");
		year.setPrefWidth(150);
		HBox year_box = new HBox();
		year_combo= new ComboBox<String>();
		year_combo.setValue("SELECT");
		for(int j=1990; j<=2019;j++)
		{
			year_combo.getItems().add(String.valueOf(j));
		}
		year_combo.setVisibleRowCount(5);
		year_box= new HBox();
		year_box.getChildren().addAll(year,year_combo);
	
		Label Brand = new Label("Brand:");
		Brand.setPrefWidth(150);
		brand_combo= new ComboBox<String>();
		brand_combo.getItems().addAll("HYUNDAI","TOYOTA","MARUTHI SUZUKI");
		brand_combo.setValue("SELECT");
		brand_combo.valueProperty().addListener((obs, oldvalue,newvalue) -> { 
			
			model(newvalue);});
		Brand_box= new HBox();
		Brand_box.getChildren().addAll(Brand,brand_combo);

		Label model = new Label("Model:");
		model.setPrefWidth(150);
		model_combo= new ComboBox<String>();
		model_combo.setValue("SELECT");
		model_box= new HBox();
		model_box.getChildren().addAll(model,model_combo);
		
		Label vehicle_class = new Label("Vehicle Category:");
		vehicle_class.setPrefWidth(150);
		vehicle_class_box = new HBox();
		vehicle_class_combo= new ComboBox<String>();
		vehicle_class_combo.getItems().addAll("ECONOMY","PREMIUM","LUXURY");
		vehicle_class_combo.setValue("SELECT");
		vehicle_class_box= new HBox();
		vehicle_class_box.getChildren().addAll(vehicle_class,vehicle_class_combo);
		

		Label seat = new Label("Seats:");
		seat.setPrefWidth(150);
		seat1 = new RadioButton(seats[0]);
		seat2 = new RadioButton(seats[1]);	
		seat3 = new RadioButton(seats[2]);	
		seat4 = new RadioButton(seats[3]);
		seat_toggle = new ToggleGroup();
		seat1.setToggleGroup(seat_toggle);
		seat2.setToggleGroup(seat_toggle);
		seat3.setToggleGroup(seat_toggle);
		seat4.setToggleGroup(seat_toggle);
		seatbox = new HBox();
		seatbox.getChildren().addAll(seat,seat1,seat2,seat3,seat4);
		seatbox.setSpacing(10);

		vehicle_type1.setOnAction(e ->{
		seat4.setDisable(true);
		seat1.setDisable(false);	
		seat2.setDisable(false);
		seat3.setDisable(false);
		});
		
		vehicle_type2.setOnAction(e ->{
			seat1.setDisable(true);	
			seat2.setDisable(true);
			seat3.setDisable(true);
			seat4.setDisable(false);
			});
		
		Label engine_type = new Label("Engine Type:");
		engine_type.setPrefWidth(150);
		engine_type1 = new RadioButton(engine[0]);
		engine_type2 = new RadioButton(engine[1]);
		engine_type_toggle = new ToggleGroup();
		engine_type1.setToggleGroup(engine_type_toggle);
		engine_type2.setToggleGroup(engine_type_toggle);
		engine_type_box= new HBox();
		engine_type_box.getChildren().addAll(engine_type,engine_type1,engine_type2);
		engine_type_box.setSpacing(10);
		
		
		Label engine_capacity = new Label("Engine Capacity:");
		engine_capacity.setPrefWidth(150);
		engine_capacity_text = new TextField();
		engine_capacity_text.setPrefColumnCount(20);
		engine_capacity_text.setPromptText("Enter the Engine Capacity");
		engine_capacity_text.setMaxWidth(Double.MAX_VALUE);
		engine_capacity_box = new HBox();
		engine_capacity_box.getChildren().addAll(engine_capacity,engine_capacity_text);
		
		Label cost = new Label("Cost:");
		cost.setPrefWidth(150);
		cost_text = new TextField();
		cost_text.setPrefColumnCount(20);
		cost_text.setPromptText("Enter the Cost/Day");
		cost_text.setMaxWidth(Double.MAX_VALUE);
		cost_box = new HBox();
		cost_box.getChildren().addAll(cost,cost_text);
		
		Label specs = new Label("Facilities:");
		specs.setPrefWidth(150);
		specs1 = new CheckBox("Air Conditioner");
		specs2 = new CheckBox("Central Locking");
		specs3 = new CheckBox("Air Bags");
		specs4 = new CheckBox("Stereo");
		specs5 = new CheckBox("Electric Windows");
		specs_box= new HBox();
		specs_box.getChildren().addAll(specs,specs1,specs2,specs3,specs4,specs5);
		specs_box.setSpacing(5); 
		

		
		HBox submit = button(det);
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(vehicle_type_box,Brand_box,
				year_box,model_box,vehicle_class_box,seatbox,engine_type_box,engine_capacity_box,cost_box,
				specs_box,submit);
		vbox.setSpacing(15);
		vbox.setPadding(new Insets(10));
		
		scene2 = new Scene(vbox,650,550);
		window2.setScene(scene2);
		window2.setTitle("Add Vehicle");
		window2.showAndWait();
	}
	
	
	

	public void model(String brand)
	{
		if(brand.equals("TOYOTA"))
		{
			model_combo.getItems().clear();
			model_combo.getItems().addAll("LAND CRUISER","ETIOS CROSS","TOYOTA YARIS");
			model_combo.setValue("SELECT");
		}
		else if(brand.equals("MARUTHI SUZUKI"))
		{
			model_combo.getItems().clear();
			model_combo.getItems().addAll("MARUTHI CIAZ","BALENO","SWIFT DESIRE");
			model_combo.setValue("SELECT");
		}
		else if(brand.equals("HYUNDAI"))
		{
			model_combo.getItems().clear();
			model_combo.getItems().addAll("HYUNDAI ACCENT","HYUNDAI TUCSON","HYUNDAI I10");
			model_combo.setValue("SELECT");
		}
		
	}
		
	
	public HBox button(Car det)
	{
		Button addbutton = new Button("Add");
		addbutton.setPrefWidth(80);
		addbutton.setOnAction(e ->{
			
			if(vehicle_type1.isSelected())
				det.setVehicle_id("C");
			else det.setVehicle_id("V");
			det.setMake(brand_combo.getValue().toString());
			det.setYear(year_combo.getValue().toString());
			det.setVehicle_name(model_combo.getValue().toString());
			det.setVehicle_class(vehicle_class_combo.getValue().toString());
			if(seat1.isSelected())
				det.setSeats("4");
			else if(seat2.isSelected())
				det.setSeats("5");
			else if(seat3.isSelected())
				det.setSeats("7");
			else if(seat4.isSelected())
				det.setSeats("11");
			if(engine_type1.isSelected())
				det.setEngine_type("MANUAL");
			if(engine_type2.isSelected())
				det.setEngine_type("AUTOMATIC");
			System.out.println(engine_capacity_text.getText().toString());
			if(engine_capacity_text.getText().toString()==null)
				System.out.println("hello");
			det.setEngine_capacity(engine_capacity_text.getText());
			det.setAmount(cost_text.getText());
			if(specs1.isSelected())
			det.setAc("Y");
			else det.setAc("N");
			if(specs2.isSelected())
				det.setLock("Y");
			else det.setLock("N");
			if(specs3.isSelected())
				det.setAir_bags("Y");
			else det.setAir_bags("N");
			if(specs4.isSelected())
				det.setStereo_type("Y");
			else det.setStereo_type("N");
			if(specs5.isSelected())
				det.setWindow_type("Y");
			else det.setWindow_type("N");
			det.setAvailability("Y");
			
			det.vehicle();
			
		});
		Button cancelbutton = new Button("Cancel");
		cancelbutton.setPrefWidth(80);
		cancelbutton.setOnAction(e -> window2.close());

		Region spacer = new Region();
		HBox boxbottom = new HBox(10, spacer, addbutton, cancelbutton);
		HBox.setHgrow(spacer, Priority.ALWAYS);
		boxbottom.setPadding(new Insets(20, 10, 20, 10));

		return boxbottom;
	}
}

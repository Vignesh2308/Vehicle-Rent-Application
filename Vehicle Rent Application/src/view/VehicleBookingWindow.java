package view;

import java.text.ParseException;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Car;
import model.RentDetails;

public class VehicleBookingWindow {

	public static Stage window;
	public Scene scene;
	public VBox vbox;
	public HBox choose,rent_start;
	public Button ok,cancel;
	public static TextField cust_id_text,cust_name_text,no_of_days_text;
	public static DatePicker rent,rent1;
	public RentDetails cont=new RentDetails();
	public RentDetails boo = new RentDetails();
	public boolean flag,flag1;
	public void booking(Car data)
	{
		
		vbox= new VBox();
		Text textHeading = new Text("Enter your Details");
		textHeading.setFont(new Font(20));
		
		
		Label cust_id = new Label("Customer Id:");
		cust_id.setPrefWidth(150);
		cust_id.setFont(Font.font("Times New Roman",FontWeight.BOLD,15));
		cust_id_text = new TextField();
		cust_id_text.setPrefColumnCount(20);
		cust_id_text.setPromptText("Enter the Customer ID");
		cust_id_text.setMaxWidth(Double.MAX_VALUE);
		HBox cust_id_box=new HBox();
		cust_id_box.getChildren().addAll(cust_id,cust_id_text);
		
		Label cust_name = new Label("Customer Name:");
		cust_name.setPrefWidth(150);
		cust_name.setFont(Font.font("Times New Roman",FontWeight.BOLD,15));
		cust_name_text = new TextField();
		cust_name_text.setPrefColumnCount(20);
		cust_name_text.setPromptText("Enter the Customer Name");
		cust_name_text.setMaxWidth(Double.MAX_VALUE);
		HBox cust_name_box=new HBox();
		cust_name_box.getChildren().addAll(cust_name,cust_name_text);
		
		
		Label rent_date = new Label("Rent Date:");
		rent_date.setPrefWidth(150);
		rent_date.setFont(Font.font("Times New Roman",FontWeight.BOLD,15));
		rent = new DatePicker();
		rent_start = new HBox();
		rent_start.getChildren().addAll(rent_date,rent);
		
		
		Label no_of_days = new Label("No of Days:");
		no_of_days.setPrefWidth(150);
		no_of_days.setFont(Font.font("Times New Roman",FontWeight.BOLD,15));
		no_of_days_text = new TextField();
		no_of_days_text.setPrefColumnCount(20);
		no_of_days_text.setPromptText("Enter No of Days");
		no_of_days_text.setMaxWidth(Double.MAX_VALUE);
		HBox no_of_days_box=new HBox();
		no_of_days_box.getChildren().addAll(no_of_days,no_of_days_text);
		
		HBox submit = rentbutton(data);
		
		
		vbox.getChildren().addAll(textHeading,cust_id_box,cust_name_box,rent_start,no_of_days_box,submit);
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(10));

		
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(250);
		scene = new Scene(vbox,500,300);
		window.setScene(scene);
		window.setTitle("Vehicle Booking Window");
		window.showAndWait();
	}
	
	
	public HBox rentbutton(Car data)
	{
		ok = new Button("Ok");
		ok.setPrefWidth(80);
		ok.setOnAction(e -> {
			
		bookinsert(data);
			
		});
		cancel = new Button("Cancel");
		cancel.setPrefWidth(80);
		cancel.setOnAction(e -> window.close());

		Region spacer = new Region();
		HBox boxbottom = new HBox(10, spacer, ok, cancel);
		HBox.setHgrow(spacer, Priority.ALWAYS);
		boxbottom.setPadding(new Insets(20, 10, 20, 10));

		return boxbottom;
	}

	public void bookinsert(Car data) {
		
		
		data.total_ids();
		int c=data.total.size()+1;
		System.out.println(c);
		cont.setno_of_days(no_of_days_text.getText());
		cont.setCust_id(cust_id_text.getText());
		cont.setCust_name(cust_name_text.getText());
		cont.setRent_date(rent.getValue().toString());
		cont.setEst_return_date(rent.getValue().plusDays(2).toString());
		cont.setVehicle_id(data.getVehicle_id());
		cont.setRent_id(String.valueOf(c));
		
		flag=boo.booking(data,cont);	
//		}
//		catch (Exception e) {
//			MandatoryFieldException.mandatoryfield();
//		}
	}

	
	public void return_vehicle(Car data)
	{	
		Text textHeading = new Text("Enter the Details");
		textHeading.setFont(new Font(20));
		
		Label rent_date = new Label("Return Date:");
		rent_date.setPrefWidth(150);
		rent_date.setFont(Font.font("Times New Roman",FontWeight.BOLD,15));
		
		rent1 = new DatePicker();
		
		rent_start = new HBox();
		rent_start.getChildren().addAll(rent_date,rent1);
		
		HBox submit = returnbutton(data);
		vbox = new VBox();
		vbox.getChildren().addAll(textHeading,rent_start,submit);
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(10));

		
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(250);
		scene = new Scene(vbox,500,200);
		window.setScene(scene);
		window.setTitle("Vehicle Return Window");
		window.showAndWait();
	}
	
	public HBox returnbutton(Car data)
	{
		ok = new Button("Ok");
		ok.setPrefWidth(80);
		ok.setOnAction(e -> {
			try {
				return_update(data);
				window.close();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		});
		
		cancel = new Button("Cancel");
		cancel.setPrefWidth(80);
		cancel.setOnAction(e -> window.close());

		Region spacer = new Region();
		HBox boxbottom = new HBox(10, spacer, ok, cancel);
		HBox.setHgrow(spacer, Priority.ALWAYS);
		boxbottom.setPadding(new Insets(20, 10, 20, 10));

		return boxbottom;
	}
	
	public void return_update(Car data) throws ParseException
	{
//		System.out.println(cont.getEst_return_date());
		
		System.out.println(rent1.getValue());
		cont.setAct_return_date(rent1.getValue().toString());
		
		
//		System.out.println((int)ChronoUnit.DAYS.between(cont.getest_return_date(),rent1.getValue()));
//		int b=(int)ChronoUnit.DAYS.between(rent.getValue(),rent1.getValue());
//		cont.setno_of_days(String.valueOf(b*100));
		
		flag1=boo.return_vehicle(data,cont);
//		boo.return_veh2(cont, data);
	}

	
	
}

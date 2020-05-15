package view;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Car;
import model.RentDetails;

public class MoreInfo {
	public Stage window,window2;
	public Scene scene,scene2;
	public ImageView imageview;
	public HBox imagebox,menu;
	public VBox vbox,fullview;
	public TableView<RentDetails> table;
	public RentDetails rent1 = new RentDetails();
	public RentDetails rent2 = new RentDetails();
	public Button setmaintanance,completemaintanance,ok,cancel;
	
	public void info(Car data) throws FileNotFoundException
	{
		fullview = new VBox();
		
		FileInputStream file = new FileInputStream ("C:\\Users\\Vignesh\\Desktop\\images\\"+data.getImage());
		Image image = new Image(file,400,250,true,true);
		imageview = new ImageView(image);
		
		VBox detail = new VBox();
		Label Car_Name = new Label("Model :	"+data.getVehicle_name());
		Car_Name.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
		
		Label vehicle_id = new Label("Vehicle ID :	"+data.getVehicle_id());
		vehicle_id.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
		
		Label reg_year = new Label("Registration Year :	"+data.getYear());
		reg_year.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));

		Label make = new Label("Brand :	"+data.getMake());
		make.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
		
		HBox buttonbox = new HBox();
		setmaintanance = new Button("Maintanance");
		completemaintanance = new Button("Complete Maintanance");
		
		if(data.getAvailability().equalsIgnoreCase("Y") )
		{
			setmaintanance.setDisable(false);
			completemaintanance.setDisable(true);
			
		}
		else
		{
			setmaintanance.setDisable(true);
			completemaintanance.setDisable(true);
		}
		setmaintanance.setOnAction(e -> maintanance());
		completemaintanance.setDisable(true);
		
		completemaintanance.setOnAction(e -> completemaintanance());
		
		Button back = new Button("Back to Main Window");
		back.setOnAction(e -> window2.close());
		
		buttonbox.getChildren().addAll(setmaintanance,completemaintanance,back);
		buttonbox.setSpacing(15);
		
		detail.getChildren().addAll(vehicle_id,make,Car_Name,reg_year,buttonbox);
		detail.setPadding(new Insets(35,0,0,25));
		detail.setSpacing(15);
		
		imagebox = new HBox();
		imagebox.getChildren().addAll(imageview,detail);
		imagebox.setSpacing(10);
		

		data.rent_id_store(data.getVehicle_id());
		ObservableList<RentDetails> price = FXCollections.observableArrayList();

		for (String i : data.rent_id_arr)
		{
			
		rent1=rent2.rent(i);
		TableColumn<RentDetails, String> cust_id = new TableColumn<>("Customer ID");
		cust_id.setMinWidth(50);
		
		TableColumn<RentDetails, String> cust_name = new TableColumn<>("Customer Name");
		cust_name.setMinWidth(50);
		
		TableColumn<RentDetails, String> rent_date = new TableColumn<>("Rented Date");
		rent_date.setMinWidth(50);
		
		TableColumn<RentDetails, String> return_date = new TableColumn<>("Return Date");
		return_date.setMinWidth(50);
//		cust_id.setCellValueFactory(new PropertyValueFactory<RentDetails, String>(""));
		
		TableColumn<RentDetails, String> est_return_date = new TableColumn<>("Estimated Return Date");
		est_return_date.setMinWidth(50);
		
		TableColumn<RentDetails, String> act_return_date = new TableColumn<>("Actual Return Date");
		act_return_date.setMinWidth(50);
		
		return_date.getColumns().addAll(est_return_date,act_return_date);
		
		
		TableColumn<RentDetails, String> act_fee = new TableColumn<>("Actual Fee");
		act_fee.setMinWidth(50);
		
		TableColumn<RentDetails, String> late_fee = new TableColumn<>("Late Fee");
		late_fee.setMinWidth(50);
		 
		TableColumn<RentDetails, String> total_fee = new TableColumn<>("Total Fee");
		total_fee.setMinWidth(50);
		
		price.add(new RentDetails(rent1.getRent_id(),rent1.getCust_id(),
				rent1.getVehicle_id(),rent1.getCust_name(),rent1.getRent_date(),rent1.getAct_return_date(),
				rent1.getEst_return_date(),
				rent1.getActual_fee(),rent1.getLate_fee(),rent1.getTotal_fee()));
		
		cust_id.setCellValueFactory(new PropertyValueFactory<RentDetails, String>("cust_id"));
		cust_name.setCellValueFactory(new PropertyValueFactory<RentDetails, String>("cust_name"));
		rent_date.setCellValueFactory(new PropertyValueFactory<RentDetails, String>("rent_date"));
		est_return_date.setCellValueFactory(new PropertyValueFactory<RentDetails, String>("est_return_date"));
		act_return_date.setCellValueFactory(new PropertyValueFactory<RentDetails, String>("act_return_date"));
		act_fee.setCellValueFactory(new PropertyValueFactory<RentDetails, String>("actual_fee"));
		late_fee.setCellValueFactory(new PropertyValueFactory<RentDetails, String>("late_fee"));
		total_fee.setCellValueFactory(new PropertyValueFactory<RentDetails,
				String>(String.valueOf("total_fee")));

		
		
		table=new TableView<>();
		table.setItems(price);
		table.getColumns().addAll(cust_id,cust_name,rent_date,return_date,act_fee,late_fee,total_fee);
		

		}
		fullview.getChildren().addAll(imagebox,table);
		window2 = new Stage();
		window2.initModality(Modality.APPLICATION_MODAL);
		window2.setMinWidth(250);
		scene2 = new Scene(fullview,850,550);
		window2.setScene(scene2);
		window2.setTitle(data.getVehicle_name());
		window2.showAndWait();
	}
	
	
	public void completemaintanance() {
		
		Label name = new Label("Maintanance End Date:");
		name.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
		
		ok = new Button("Ok");
		ok.setDisable(true);
		cancel = new Button("Cancel");
		
		
		DatePicker maintain = new DatePicker();
		maintain.setOnAction(e ->{
			ok.setDisable(false);			
		});
		
		ok.setOnAction(e -> {
			completemaintanance.setDisable(true);
			setmaintanance.setDisable(false);
			window.close();
		});
		
		cancel.setOnAction(e -> window.close());
		
		menu = new HBox();
		menu.getChildren().addAll(ok,cancel);
		menu.setSpacing(10);
		
		vbox = new VBox();
		vbox.getChildren().addAll(name,maintain,menu);
		vbox.setSpacing(30);
		vbox.setPadding(new Insets(20,0,0,20));
		
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(250);
		scene = new Scene(vbox,200,200);
		window.setScene(scene);
		window.setTitle("Maintanance Window");
		window.showAndWait();
	
		
	}


	public void maintanance()
	{
		Label name = new Label("Maintanance Date:");
		name.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
		ok = new Button("Ok");
		ok.setDisable(true);
		cancel = new Button("Cancel");
		
		
		DatePicker maintain = new DatePicker();
		maintain.setOnAction(e ->{
			ok.setDisable(false);			
		});
		
		ok.setOnAction(e -> {
			completemaintanance.setDisable(false);
			setmaintanance.setDisable(true);
			window.close();
		});
		
		cancel.setOnAction(e -> window.close());
		
		menu = new HBox();
		menu.getChildren().addAll(ok,cancel);
		menu.setSpacing(10);
		
		vbox = new VBox();
		vbox.getChildren().addAll(name,maintain,menu);
		vbox.setSpacing(30);
		vbox.setPadding(new Insets(20,0,0,20));
		
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(250);
		scene = new Scene(vbox,200,200);
		window.setScene(scene);
		window.setTitle("Maintanance Window");
		window.showAndWait();
	}
	

	

}

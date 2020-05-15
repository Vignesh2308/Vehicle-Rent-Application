package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Car;
public class VehicleView {
	 
	public HBox imagebox;
	public ImageView imageview,imageview3;
	Car checkdata = new Car();
	MoreInfo info = new MoreInfo();
	VehicleBookingWindow vehbook = new VehicleBookingWindow();
	
	public void view(Car checkdata) throws Exception
	{
		imagebox = new HBox();
		
		if(checkdata.getImage().equals("None"))
		{
			FileInputStream file = new FileInputStream ("C:\\Users\\Vignesh\\Desktop\\images\\No_image.jpg");
			Image image = new Image(file,400,500,true,true);
			imageview = new ImageView(image);
		}
		
		else
		{
			FileInputStream file = new FileInputStream ("C:\\Users\\Vignesh\\Desktop\\images\\"+checkdata.getImage());
			Image image = new Image(file,400,250,true,true);
			imageview = new ImageView(image);
		}
		
		
		
		VBox detail = new VBox();
		Label Car_Name = new Label(checkdata.getVehicle_name());
		Car_Name.setFont(Font.font("Times New Roman",FontWeight.BOLD,25));
		
		Label vehicle_class = new Label(checkdata.getVehicle_class());
		vehicle_class.setFont(Font.font("Times New Roman",FontWeight.BOLD,15));
		
		Label engine_type = new Label("Engine Type: "+checkdata.getEngine_type());
		engine_type.setFont(Font.font("Times New Roman",FontWeight.BOLD,15));
		
		Label engine_capacity = new Label("Engine Capacity: "+checkdata.getEngine_capacity());
		engine_capacity.setFont(Font.font("Times New Roman",FontWeight.BOLD,15));

		HBox ac = new HBox(check(checkdata.getAc()," Air Conditioner  "));
		HBox lock = new HBox(check(checkdata.getLock()," Central Locking  "));
		ac.getChildren().addAll(lock);
		
		HBox airbag = new HBox(check(checkdata.getAir_bags()," Air Bags  "));
		HBox stereo = new HBox(check(checkdata.getStereo_type()," STEREO  "));
		HBox window_type = new HBox(check(checkdata.getWindow_type()," Electric Windows  "));
		airbag.getChildren().addAll(stereo,window_type);
		
		Button moreinfo = new Button("more info");
		moreinfo.setId(checkdata.getVehicle_id());
		
		moreinfo.setOnAction(e -> {
			try {
				info.info(checkdata);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        moreinfo.setStyle("-fx-background-color: BLUE ;-fx-text-fill: WHITE; -fx-font-weight:BOLD");

		detail.getChildren().addAll(Car_Name,vehicle_class,engine_type,engine_capacity,ac,airbag,moreinfo);
		detail.setSpacing(10);
		
		
		VBox col3 = new VBox();
		
		Label amount = new Label(checkdata.getAmount()+" RM");
		amount.setFont(Font.font("Times New Roman",FontWeight.BOLD,25));
		
		
		Button book = new Button("Book Now");
		
		book.setStyle("-fx-background-color: BLUE;-fx-text-fill: WHITE; -fx-font-weight:BOLD");

		Button Return = new Button("Return");
		
        Return.setOnAction(e -> {
        	vehbook.return_vehicle(checkdata);
        	if(vehbook.flag1)
        	{
        		book.setDisable(false);
				Return.setDisable(true);
				info.setmaintanance.setDisable(false); 	
        	}
        });
		Return.setStyle("-fx-background-color: BLUE;-fx-text-fill: WHITE; -fx-font-weight:BOLD");
		
		book.setOnAction(e -> {
			vehbook.booking(checkdata);
			if(vehbook.flag)
			{
				book.setDisable(true);
				Return.setDisable(false);
			}
		});
		if(checkdata.getAvailability().equalsIgnoreCase("Y"))
			{
			book.setDisable(false);
			Return.setDisable(true);
			}
		else {
			book.setDisable(true);
			Return.setDisable(false);
		}
		
        Text days = new Text("For 4 Days");
        days.setFont(Font.font("Times New Roman",FontWeight.BOLD,25));
        
		col3.getChildren().addAll(amount,days,book,Return);
		col3.setSpacing(10);

		imagebox.getChildren().addAll(imageview,detail,col3);
		imagebox.setPrefWidth(400);
		imagebox.setPadding(new Insets(50,0,0,80));
		imagebox.setMargin(imageview, new Insets(0,100,10,0));
		imagebox.setMargin(detail, new Insets(0,100,10,0));
		imagebox.setStyle("-fx-border-style: solid inside;");

		
	}
	
	public HBox check(String c,String a) throws Exception
	{
		HBox hb = new HBox();
		if(c.equals("Y"))
		{
			FileInputStream backimage = new FileInputStream ("C:\\Users\\Vignesh\\Desktop\\images\\correct.jpg");
			Image image = new Image(backimage,20,20,false,false);
			imageview3 = new ImageView(image);
			Label label = new Label(a);
			hb.getChildren().addAll(imageview3,label);
			
			
			return hb;
		}
		else {
			FileInputStream backimage = new FileInputStream ("C:\\Users\\Vignesh\\Desktop\\images\\wrong.png");
			Image image = new Image(backimage,20,20,false,false);
			imageview3 = new ImageView(image);
			Label label = new Label(a);
			hb.getChildren().addAll(imageview3,label);
			return hb;
		}
		
		
		
	}
	

}

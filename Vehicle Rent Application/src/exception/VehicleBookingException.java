package exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class VehicleBookingException {
	
	public static void invalid_customer_name()
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Customer Name Voilation");
        alert.setHeaderText("Invalid Customer Name Format");
        alert.setContentText("Please provide Alphabets Only");
        alert.showAndWait();
	}
	
	public static void invalid_customer_id()
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Customer ID Voilation");
        alert.setHeaderText("Invalid Customer ID");
        alert.setContentText("Please provide numericals only");
        alert.showAndWait();
	}
	
	public static void invalid_no_of_days()
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("No Of Days Voilation");
        alert.setHeaderText("Invalid No of days");
        alert.setContentText("Please provide less than 5 days");
        alert.showAndWait();
	}
	

}

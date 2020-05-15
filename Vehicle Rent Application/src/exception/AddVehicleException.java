package exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AddVehicleException {

	public static void invalid_engine_capacity_Exception()
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Invalid Engine Capacity");
        alert.setHeaderText("Invalid Engine Capacity Format");
        alert.setContentText("Please provide Numericals Only");
        alert.showAndWait();
	}
	
	public static void invalid_cost_Exception()
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Invalid Cost");
        alert.setHeaderText("Invalid Cost Format");
        alert.setContentText("Please provide Numericals Only");
        alert.showAndWait();
	}

	
	
}

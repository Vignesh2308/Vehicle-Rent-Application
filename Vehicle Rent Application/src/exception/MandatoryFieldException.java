package exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MandatoryFieldException {
	
	
	public static void mandatoryfield()
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Mandatory Field Exception");
        alert.setHeaderText("Mandatory Field Exception..!");
        alert.setContentText("Please provide all Fields");
        alert.showAndWait();
	}
}

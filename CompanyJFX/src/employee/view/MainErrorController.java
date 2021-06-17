package employee.view;

import employee.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainErrorController {
	private Main main;
	
	@FXML
	private Button button;
	
	@FXML
	private void closeMainError() {
		 Stage stage = (Stage)button.getScene().getWindow();
		    stage.close();
	}
	
}

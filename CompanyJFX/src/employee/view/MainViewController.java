package employee.view;

import java.io.IOException;

import employee.Main;
import javafx.fxml.FXML;

public class MainViewController {
	private Main main;
	
	@FXML
	private void goHome() throws IOException {
		main.showMainIterm();
	}
	
}

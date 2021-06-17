package employee.operator;

import java.io.IOException;

import employee.Main;
import javafx.fxml.FXML;

public class EmployeeCheckController {
	private Main main;
	
	@FXML
	public void goAddNewEmployeeStage() throws IOException {
		main.showAddEmpStage();
	}
	@FXML
	public void goWorksonStage() throws IOException {
		main.showWorksonStage();
	}
	@FXML
	public void goAddDependentStage() throws IOException {
		main.showDependentStage();
	}
	
	@FXML
	public void showEmployeeInfo() throws IOException {
		main.showDetailsStage();
	}
}

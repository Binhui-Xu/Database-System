package employee.operator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import employee.DBConnction;
import employee.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddDependentController {
	private Main main;
	ObservableList<String> sexList=FXCollections.observableArrayList("F","M");
	
	
    @FXML
    private TextField essnField;

    @FXML
    private TextField dnameField;

    @FXML
    private TextField relaField;

    @FXML
    private ChoiceBox<String> sexBox;

    @FXML
    private DatePicker bdatePicker;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button detailButton;

    @FXML
    private Label resultLabel;
    
    
    public AddDependentController() {
    	conn=DBConnction.connect();
	}
    
    @FXML
    private void initialize() {
    	resultLabel.setVisible(false);
    	sexBox.setItems(sexList);
    }
    
    Connection conn=null;
    PreparedStatement ps = null;
    ResultSet rs=null;
    
    //check if the employee exist in employees
    private Boolean isInemployees() {
		String essn=essnField.getText();
		String sql="select ssn from employee where ssn=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, essn);
			int i=ps.executeUpdate();
			if(i!=0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}
    
    
    private Boolean add() {
    	String essn=essnField.getText();
    	String dname=dnameField.getText();
    	String sex=null;
    	if(sexBox.getValue()!=null) {
    		sex=sexBox.getValue().toString();
    	}		
    	Date bdate=null;
    	if(bdatePicker.getValue()!=null) {
    		bdate=Date.valueOf(bdatePicker.getValue());
    	}		
    	String relationship=relaField.getText();
    	String sql="insert into dependent values(?,?,?,?,?)";
    	try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, essn);
			ps.setString(2, dname);
			ps.setString(3, sex);
			ps.setDate(4, bdate);
			ps.setString(5, relationship);
			int i =ps.executeUpdate();
			if(i!=0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    	return false;
    }
    
    @FXML
    private void addDependent() {
    	if(!isInemployees()) {
    		resultLabel.setText("Cannot find the employee.");
    		resultLabel.setVisible(true);
    	}else if(dnameField.getText().isEmpty()) {
    		resultLabel.setText("dependent_name cannot empty!");
    		resultLabel.setVisible(true);
    	}else if(add()) {
    		resultLabel.setText("Add successfully!");
    		resultLabel.setVisible(true);
    	}else {
    		resultLabel.setText("Error!");
    		resultLabel.setVisible(true);
    	}
    }
    
    @FXML
    public void exitStage() {
	    Stage stage = (Stage)cancelButton.getScene().getWindow();
	    stage.close();
	}
	
	@FXML
    public void ShowDetails() throws IOException {
    	main.showDetailsStage();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}


package employee.operator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import employee.DBConnction;
import employee.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AddNewEmployeeController {
	private Main main;
	ObservableList<String> sexList=FXCollections.observableArrayList("F","M");
	ObservableList<Integer> dnoList=FXCollections.observableArrayList(1,4,5);
	ObservableList<String> projectList=FXCollections.observableArrayList("ProductX","ProductY","ProductZ","Computerization","Reorganization","Newbenefits",null);
	
		
	//employee informations
    @FXML
    private TextField fnameField;
    @FXML
    private TextField minitField;
    @FXML
    private TextField ssnField;
    @FXML
    private TextField addressField;
    @FXML
    private ChoiceBox sexBox;
    @FXML
    private TextField salaryField;
    @FXML
    private TextField superssnField;
    @FXML
    private ChoiceBox dnoBox;
    @FXML
    private TextField emailField;
    @FXML
    private DatePicker dateofbrith;
    @FXML
    private TextField lnameField;   
    @FXML
    public Label resultLabel;      
    @FXML
    private Button cancelButton; 
    @FXML 
    private Button okButton;
    
    //Project informations
    @FXML
    private ComboBox projectBox;
    @FXML
    private TextField hourField;
    @FXML
    private Label timeLabel;
    
    //dependent informations    
    @FXML
    private CheckBox withdependentBox;  
    @FXML
    private CheckBox withoutdependentBox;
    @FXML
    private Label selectLabel;
    @FXML
    private TextField depNameField;
    @FXML
    private ChoiceBox depSexBox;
    @FXML
    private DatePicker bdateOfDep;
    @FXML
    private TextField relationship;
    @FXML
    private GridPane depInfo;

    @FXML
    private void initialize() {
    	resultLabel.setVisible(false);
    	depInfo.setVisible(false);
    	timeLabel.setVisible(false);
    	selectLabel.setVisible(false);
    	sexBox.setItems(sexList);
    	dnoBox.setItems(dnoList);
    	projectBox.setItems(projectList);
    	depSexBox.setItems(sexList);
    }
    
    @FXML
    private void handleWithdependentBox() {
    	if(withdependentBox.isSelected())
    		withoutdependentBox.setSelected(false);
    	depInfo.setVisible(true);
    }
    @FXML
    private void handleWithoutdependentBox() {
    	if(withoutdependentBox.isSelected())
    		withdependentBox.setSelected(false);
    	depInfo.setVisible(false);
    }
    
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs=null;
    
    public AddNewEmployeeController() {
        conn = DBConnction.connect();
    }
    
    
    @FXML
    private Boolean checkTime() {
    	double workhour=0.0;
    	if(hourField.getText().trim().isEmpty()) {
    		return false;
    	}else {
    		workhour+=Double.parseDouble(hourField.getText());
    	} 
		if(workhour>40 || workhour<0) {
			return false;
		}
		return true;
    }
    
    private Boolean checkSsn() {
    	String ssn=null;
    	if(ssnField.getText()!=null)
    		ssn=ssnField.getText();
    	String sql="select ssn from employee where ssn=?";
    	try {
    		ps=conn.prepareStatement(sql);
			ps.setString(1, ssn);
			int i=ps.executeUpdate();
			if(i!=0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return false;
    }
    
    private Boolean addEmp() {
    	if(fnameField.getText().isEmpty() || ssnField.getText().isEmpty()|| lnameField.getText().isEmpty()) {
    		resultLabel.setText("Name and ssn cannot be empty.");
    		resultLabel.setVisible(true);
    		return false;
    	}
    	if(checkSsn()) {
    		resultLabel.setText("This ssn already used.");
    		resultLabel.setVisible(true);
    		return false;
    	}
    	int pno=getPno();
    	if(pno<0) {
    		timeLabel.setText("Please select a project");
    		timeLabel.setVisible(true);
    		return false;
    	}
    	if(!checkTime()) {
    		timeLabel.setText("Working time cannot exceed 40 or lower than 0.");
    		timeLabel.setVisible(true);
    		return false;
    	}
    	if(!withdependentBox.isSelected() && !withoutdependentBox.isSelected() ) {
    		selectLabel.setVisible(true);
    		return false;
    	}
		if(withoutdependentBox.isSelected()){
			return true;
    	}	
		if(withdependentBox.isSelected()){
			if(depNameField.getText().isEmpty()) {
				resultLabel.setText("Dependent's name cannot be empty.");
				resultLabel.setVisible(true);
				return false;
			}
		}
    	//employee informations
		String fname=fnameField.getText();
		String minit=minitField.getText();
		String lname=lnameField.getText();
		String ssn=ssnField.getText();
		Date bdate=null;
		if(dateofbrith.getValue()!=null) {
			bdate=Date.valueOf(dateofbrith.getValue());
		}
		String address=addressField.getText();
		String sex=null;
		if(sexBox.getValue()!=null) {
			sex=sexBox.getValue().toString();
		}
		int salary =0;
		if(!salaryField.getText().isEmpty()) {
			salary=Integer.parseInt(salaryField.getText());
		}
		String superssn=superssnField.getText();
		Integer dno=null;
		if(dnoBox.getValue()!=null) {
			dno=Integer.parseInt(dnoBox.getValue().toString());
		}
		String email=emailField.getText();
		int result=0;
		String sql="insert into employee values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, fname);
			ps.setString(2, minit);
			ps.setString(3, lname);
			ps.setString(4, ssn);
			ps.setDate(5, bdate);
			ps.setString(6, address);
			ps.setString(7, sex);
			ps.setInt(8, salary);
			ps.setString(9, superssn);
			ps.setObject(10, dno);
			ps.setString(11, email);
			result=ps.executeUpdate();
			if(result!=0 && addWorkOn()&& addDependent()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(ps!=null)
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return false;
    }
    
    private Boolean addWorkOn() {
    	int pno=getPno();
    	String essn=ssnField.getText();
    	double hour=Double.parseDouble(hourField.getText());
    	String pname=projectBox.getValue().toString();
		String sql="insert into works_on values(?,?,?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, essn);
			ps.setInt(2, pno);
			ps.setDouble(3, hour);
			int i=ps.executeUpdate();
			if(i!=0) {
				return true;
			}
		}catch (SQLException e) {
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
    
    private Boolean addDependent() {
    	String essn=ssnField.getText();
    	String depname=depNameField.getText();
    	String depsex=null;
    	if(depSexBox.getValue()!=null) {
    		depsex=depSexBox.getValue().toString();
    	}
    	Date bdateofdep=null;
    	if(bdateOfDep.getValue()!=null) {
    		bdateofdep=Date.valueOf(bdateOfDep.getValue());
    	}
    	
    	String relation=relationship.getText();
    	String sql="insert into dependent values(?,?,?,?,?)";
    	try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, essn);
			ps.setString(2, depname);
			ps.setString(3, depsex);
			ps.setDate(4, bdateofdep);
			ps.setString(5, relation);
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
    
    private int getPno() {
    	String name=null;
    	if(projectBox.getValue()!=null) {
    		name=projectBox.getValue().toString();
    	}
    	String sql="select pnumber from project where pname=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, name);
			rs=ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
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
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} 
		return -1;
    }

    
    @FXML
    public void addNewEmployee() {
    	if(addEmp()) {
    		resultLabel.setText("Succeed!");
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



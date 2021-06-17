package employee.operator;

import java.io.IOException;
import java.sql.Connection;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddWorksonController {
	private Main main;
	private int pno=0;
	ObservableList<String> projectList=FXCollections.observableArrayList(null,"ProductX","ProductY","ProductZ","Computerization","Reorganization","Newbenefits");
    
	@FXML
	private TextField essnField;
	@FXML
    private ChoiceBox projectBox;

    @FXML
    private TextField hourField;
    
    @FXML
    private Button timeButton;
    
    @FXML
    private Label timeLabel;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label resultLabel;
    
    Connection conn=null;
    PreparedStatement ps = null;
    ResultSet rs=null;
    
    public AddWorksonController() {
    	conn=DBConnction.connect();
	}
    
	@FXML
    private void initialize() {
		timeLabel.setVisible(false);
    	resultLabel.setVisible(false);
    	projectBox.setItems(projectList);
    }
	
	@FXML
	private Double showRemainTime() {
		if(!isInEmployees()) {
			timeLabel.setText("Cannot find the employee.");
			timeLabel.setVisible(true);
		}else if(projectBox.getValue()==null) {
			timeLabel.setText("project is empty!");
			timeLabel.setVisible(true);
		}
		Double remain=0.0;
		String essn=essnField.getText();
		String sql="select sum(hours) from works_on where essn=? group by essn";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, essn);
			rs=ps.executeQuery();
			if(rs.next()) {
				remain=40-rs.getDouble(1);
				if(remain<=0) {
					timeLabel.setText("Remain time is: "+remain+". No more time!");
					timeLabel.setVisible(true);
				}else {
					timeLabel.setText("Remain time is: "+remain);
					timeLabel.setVisible(true);
				}
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
		return remain;
	}
	
	private Boolean checkTime() {
    	double workhour=showRemainTime();
    	if(!hourField.getText().trim().isEmpty()) 
			workhour+=Double.parseDouble(hourField.getText());
		if(workhour>40) {
			resultLabel.setText("THE WORKING TIME CANNOT EXCEED 40!!");
			System.out.println("total is "+workhour);
			return false;
		}
		return true;
    }
	
	
	
	//check if the employee already works on this project
	private Boolean isExist() {
		String essn=essnField.getText();
		int pnumber=getPno();
		String sql="select essn,pno from works_on where essn=? and pno=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, essn);
			ps.setInt(2, pnumber);
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
	
	//check if the employee exist
	private Boolean isInEmployees() {
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
	
	//get pno by pname
	private int getPno() {
		String pname=projectBox.getValue().toString();
		String sql="select pnumber from project where pname=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, pname);
			rs=ps.executeQuery();
			if(rs.next()) {
				pno=rs.getInt(1);
				return pno;
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
		return 0;
	}
	
	//add new work_on record
	private Boolean add() {
		pno=getPno();
		if(pno==0 || hourField.getText()==null) {
			return false;
		}	
		String essn=essnField.getText();
		Double hours=Double.parseDouble(hourField.getText());
		String sql="insert into works_on values(?,?,?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, essn);
			ps.setInt(2, pno);
			ps.setDouble(3, hours);
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
	
	@FXML
	private void addWorkson() {
		if(!isInEmployees()) {
			resultLabel.setText("Cannot find the employee.");
			resultLabel.setVisible(true);
		}else if(!checkTime()) {
			resultLabel.setVisible(true);
		}else if(projectBox.getValue()==null) {
			resultLabel.setText("project cannot empty!");
			resultLabel.setVisible(true);
		}else if(isExist()) {
			resultLabel.setText("The employee already works on this project.");
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
































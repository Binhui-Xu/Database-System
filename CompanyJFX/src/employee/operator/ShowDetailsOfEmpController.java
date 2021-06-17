package employee.operator;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import employee.DBConnction;
import employee.Dependent;
import employee.Employee;
import employee.Workson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ShowDetailsOfEmpController {
	
	@FXML
	private TableView<Employee> tableEmployee;
	@FXML
	private TableView<Workson> tableWorkson;
	@FXML
	private TableView<Dependent> tableDep;
	@FXML
	private TableColumn<Employee,String> colFname;
	@FXML
	private TableColumn<Employee,String> colMinit;
	@FXML
	private TableColumn<Employee,String> colLname;
	@FXML
	private TableColumn<Employee,String> colSsn;
	@FXML
	private TableColumn<Employee,Date> colBdate; 
	@FXML
	private TableColumn<Employee,String> colAddress;
	@FXML
	private TableColumn<Employee,String> colSex;
	@FXML
	private TableColumn<Employee,Integer> colSalary;
	@FXML
	private TableColumn<Employee,String> colSuperssn;
	@FXML
	private TableColumn<Employee,Integer> colDno;
	@FXML
	private TableColumn<Employee,String> colEmail;
    @FXML
    private TableColumn<Employee, String> colEssn;

    @FXML
    private TableColumn<Employee, Integer> colPno;

    @FXML
    private TableColumn<Employee, Double> colHours;

    @FXML
    private TableColumn<Employee, String> colDEssn;

    @FXML
    private TableColumn<Employee, String> colDname;

    @FXML
    private TableColumn<Employee, Date> colDBdate;

    @FXML
    private TableColumn<Employee, String> colDsex;

    @FXML
    private TableColumn<Employee, String> colRelationship;
    
    @FXML
    private Button closeButton;
    
    private Connection conn;
    private ResultSet rs=null;
	private ObservableList<Employee> dataemployee=FXCollections.observableArrayList();
	private ObservableList<Workson> dataworkson=FXCollections.observableArrayList();
	private ObservableList<Dependent> datadependent=FXCollections.observableArrayList();
	
	public ShowDetailsOfEmpController() {
		conn=DBConnction.connect();	
	}
	
	@FXML
	public void initialize() {
		buildEmployeeDate();
		buildWorksonData();
		buildDependentData();
	}
	
	
	private void buildEmployeeDate() {
		//data=FXCollections.observableArrayList();
		String sql="select * from employee";
		try {
			rs=conn.createStatement().executeQuery(sql);
			while(rs.next()) {
				Employee employee=new Employee();
				employee.setFname(rs.getString("fname"));
				employee.setMinit(rs.getString("minit"));
				employee.setLname(rs.getString("lname"));
				employee.setSsn(rs.getString("ssn"));
				employee.setBdate(rs.getDate("bdate"));
				employee.setAddress(rs.getString("address"));
				employee.setsex(rs.getString("sex"));
				employee.setSalary(rs.getInt("salary"));
				employee.setSuperssn(rs.getString("superssn"));
				employee.setDNo(rs.getInt("dno"));
				employee.setEmail(rs.getString("email"));
				dataemployee.add(employee);
			}
			colFname.setCellValueFactory(new PropertyValueFactory<>("fname"));
			colMinit.setCellValueFactory(new PropertyValueFactory<>("minit"));
			colLname.setCellValueFactory(new PropertyValueFactory<>("lname"));
			colSsn.setCellValueFactory(new PropertyValueFactory<>("ssn"));
			colBdate.setCellValueFactory(new PropertyValueFactory<>("bdate"));
			colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
			colSex.setCellValueFactory(new PropertyValueFactory<>("sex"));
			colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
			colSuperssn.setCellValueFactory(new PropertyValueFactory<>("superssn"));
			colDno.setCellValueFactory(new PropertyValueFactory<>("dno"));
			colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
			tableEmployee.setItems(dataemployee);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void buildWorksonData() {
		String sql="select * from works_on";
		try {
			rs=conn.createStatement().executeQuery(sql);
			while(rs.next()) {
				Workson wo=new Workson();
				wo.setEssn(rs.getString("essn"));
				wo.setPno(rs.getInt("pno"));
				wo.setHours(rs.getDouble("hours"));
				dataworkson.add(wo);
			}
			colEssn.setCellValueFactory(new PropertyValueFactory<>("essn"));
			colPno.setCellValueFactory(new PropertyValueFactory<>("pno"));
			colHours.setCellValueFactory(new PropertyValueFactory<>("hours"));
			tableWorkson.setItems(dataworkson);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void buildDependentData() {
		String sql="select * from dependent";
		try {
			rs=conn.createStatement().executeQuery(sql);
			while(rs.next()) {
				Dependent dp=new Dependent();
				dp.setEssn(rs.getString("essn"));
				dp.setDname(rs.getString("dependent_name"));
				dp.setSex(rs.getString("sex"));
				dp.setBdate(rs.getDate("bdate"));
				dp.setRelationship(rs.getString("relationship"));
				datadependent.add(dp);
			}
			colDEssn.setCellValueFactory(new PropertyValueFactory<>("essn"));
			colDname.setCellValueFactory(new PropertyValueFactory<>("dname"));
			colDsex.setCellValueFactory(new PropertyValueFactory<>("sex"));
			colDBdate.setCellValueFactory(new PropertyValueFactory<>("bdate"));
			colRelationship.setCellValueFactory(new PropertyValueFactory<>("relationship"));
			tableDep.setItems(datadependent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
    public void exitStage() {
	    Stage stage = (Stage)closeButton.getScene().getWindow();
	    stage.close();
	}
}



























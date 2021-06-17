package employee.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import employee.DBConnction;
import employee.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainItermController {
	private Main main;
	
	@FXML
    private TextField textFeild;
	

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
	
	@FXML
	private void goCheck() throws IOException {
		if(logIn().equals("susccess")) {
			main.showCheckResult();
		}else {
			main.showMainError();
		}
		
	}
	
	public MainItermController() {
        conn = DBConnction.connect();
        
    }
	
	private String logIn() {
		String status="susccess";
		String ssn=textFeild.getText().toString();
		String sql="SELECT mgrssn FROM department WHERE mgrssn=?";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, ssn);
			rs=ps.executeQuery();
			if(!rs.next()) {
				status="wrong";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//conn.close();
		return status;
	}
	
}

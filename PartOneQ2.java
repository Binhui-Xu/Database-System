import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PartOneQ2 {

	public static void main(String[] args)throws ClassNotFoundException,SQLException{
		String url="jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
		String user="bxu4";
		String password="gralathe";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn=DriverManager.getConnection(url,user,password);
		System.out.println(conn);
		
		String sql="SELECT LNAME,SSN,HOURS FROM EMPLOYEE,DEPT_LOCATIONS,PROJECT,WORKS_ON  WHERE DNO=DNUMBER AND DLOCATION='Houston' AND DNUMBER=DNUM AND PNAME='ProductZ' AND PNUMBER=PNO AND ESSN=SSN";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		while(rs.next()) {
			String lname=rs.getString(1);
			int ssn=rs.getInt(2); 
			int hours=rs.getInt(3);
			System.out.format("%15s%10s%4d"+"\n", lname,ssn,hours);
		}
		rs.close();
		st.close();
	}
}

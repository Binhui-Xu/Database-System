import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PartOneQ1 {
	public static void main(String[] args)throws ClassNotFoundException,SQLException{
		String url="jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
		String user="bxu4";
		String password="gralathe";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn=DriverManager.getConnection(url,user,password);
		System.out.println(conn);
		
		String sql="select lname,ssn from employee,department where Dno=Dnumber and Dname='Research'";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		while(rs.next()) {
			String lname=rs.getString(1);
			int ssn=rs.getInt(2); 
			System.out.format("%15s%10s"+"\n", lname,ssn);
		}
		rs.close();
		st.close();
	}
}

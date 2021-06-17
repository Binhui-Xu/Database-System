package employee;

import java.io.Serializable;
import java.sql.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employee implements Serializable {
	private StringProperty fname=new SimpleStringProperty();
	private StringProperty minit=new SimpleStringProperty();
	private StringProperty lname=new SimpleStringProperty();
	private StringProperty ssn=new SimpleStringProperty();
	private ObjectProperty bdate=new SimpleObjectProperty();
	private StringProperty address=new SimpleStringProperty();
	private StringProperty sex=new SimpleStringProperty();
	private IntegerProperty salary=new SimpleIntegerProperty();
	private StringProperty superssn=new SimpleStringProperty();
	private IntegerProperty dno=new SimpleIntegerProperty();
	private StringProperty email=new SimpleStringProperty();
	
	public Employee() {
		
	}

	public String getFname() {
		return fname.get();
	}

	public void setFname(String value) {
		fname.set(value);
	}
	
	public StringProperty fnameProperty() {
		return fname;
	}

	public String getMinit() {
		return minit.get();
	}

	public void setMinit(String value) {
		minit.set(value);
	}
	
	public StringProperty minitProperty() {
		return minit;
	}
	
	public String getLname() {
		return lname.get();
	}

	public void setLname(String value) {
		lname.set(value);
	}
	
	public StringProperty lnameProperty() {
		return lname;
	}
	
	public String getSsn() {
		return ssn.get();
	}

	public void setSsn(String value) {
		ssn.set(value);
	}
	
	public StringProperty ssnProperty() {
		return ssn;
	}
	
	public Object getBdate() {
		return bdate.get();
	}

	public void setBdate(Object value) {
		bdate.set(value);
	}
	
	public ObjectProperty bdateProperty() {
		return bdate;
	}
	
	public String getAddress() {
		return address.get();
	}

	public void setAddress(String value) {
		address.set(value);
	}
	
	public StringProperty addressProperty() {
		return address;
	}
	
	public String getSex() {
		return sex.get();
	}

	public void setsex(String value) {
		sex.set(value);
	}
	
	public StringProperty sexProperty() {
		return sex;
	}
	
	public int getSalary() {
		return salary.get();
	}

	public void setSalary(int value) {
		salary.set(value);
	}
	
	public IntegerProperty salaryProperty() {
		return salary;
	}
	
	public String getSuperssn() {
		return superssn.get();
	}

	public void setSuperssn(String value) {
		superssn.set(value);
	}
	
	public StringProperty superssnProperty() {
		return superssn;
	}
	
	public int getDno() {
		return dno.get();
	}

	public void setDNo(int value) {
		dno.set(value);
	}
	
	public IntegerProperty dnoProperty() {
		return dno;
	}
	
	public String getemail() {
		return email.get();
	}

	public void setEmail(String value) {
		email.set(value);
	}
	
	public StringProperty emailProperty() {
		return email;
	}
	

	@Override
	public String toString() {
		return "Employee [fname=" + fname + ", minit=" + minit + ", lname=" + lname + ", ssn=" + ssn + ", bdate="
				+ bdate + ", address=" + address + ", sex=" + sex + ", salary=" + salary + ", superssn=" + superssn
				+ ", dno=" + dno + ", email=" + email + "]";
	}
	
	

//	public Employee(String fname, String minit, String lname,
//			String ssn, Date bdate, String address, String sex,
//			int salary, String superssn, int dno,
//			String email) {
//		this.fnameCol = new SimpleStringProperty(fname);
//		this.minitCol = new SimpleStringProperty(minit);
//		this.lnameCol = new SimpleStringProperty(lname);
//		this.ssnCol = new SimpleStringProperty(ssn);
//		this.bdateCol =  new SimpleObjectProperty(bdate);
//		this.addressCol = new SimpleStringProperty(address);
//		this.sexCol = new SimpleStringProperty(sex);
//		this.salaryCol = new SimpleIntegerProperty(salary);
//		this.superssnCol = new SimpleStringProperty(superssn);
//		this.dnoCol = new SimpleIntegerProperty(dno);
//		this.emailCol = new SimpleStringProperty(email);
//	}

	
	
	
	
	
	
	
}

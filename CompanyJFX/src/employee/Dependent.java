package employee;

import java.io.Serializable;
import java.sql.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Dependent implements Serializable {
	private StringProperty essn=new SimpleStringProperty();
	private StringProperty dname=new SimpleStringProperty();
	private StringProperty sex=new SimpleStringProperty();
	private ObjectProperty bdate=new SimpleObjectProperty();
	private StringProperty relationship=new SimpleStringProperty();
	
	public Dependent() {
		
	}

	public String getEssn() {
		return essn.get();
	}

	public void setEssn(String value) {
		essn.set(value);
	}
	
	public StringProperty essnProperty() {
		return essn;
	}
	
	public String getDname() {
		return dname.get();
	}

	public void setDname(String value) {
		dname.set(value);
	}
	
	public StringProperty dnameProperty() {
		return dname;
	}
	
	public String getSex() {
		return sex.get();
	}

	public void setSex(String value) {
		sex.set(value);
	}
	
	public StringProperty sexProperty() {
		return sex;
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
	
	public String getRelationship() {
		return relationship.get();
	}

	public void setRelationship(String value) {
		relationship.set(value);
	}
	
	public StringProperty relationshipProperty() {
		return relationship;
	}

	@Override
	public String toString() {
		return "Dependent [essn=" + essn + ", depname=" + dname + ", sex=" + sex + ", bdate=" + bdate
				+ ", relationship=" + relationship + "]";
	}
	
	
}

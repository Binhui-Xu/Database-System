package employee;

import java.io.Serializable;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Workson implements Serializable {
	private StringProperty essn=new SimpleStringProperty();
	private IntegerProperty pno=new SimpleIntegerProperty();
	private DoubleProperty hours=new SimpleDoubleProperty();
	
	public Workson() {
		
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
	
	public int getPno() {
		return pno.get();
	}

	public void setPno(int value) {
		pno.set(value);
	}
	
	public IntegerProperty pnoProperty() {
		return pno;
	}
	
	public double getHours() {
		return hours.get();
	}

	public void setHours(double value) {
		hours.set(value);
	}
	
	public DoubleProperty hoursProperty() {
		return hours;
	}

	@Override
	public String toString() {
		return "Workson [essn=" + essn + ", pno=" + pno + ", hours=" + hours + "]";
	}
	
	
}

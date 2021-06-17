package employee;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {
	private static Stage primaryStage;
	private static BorderPane borderpane;
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage=primaryStage;
		this.primaryStage.setTitle("company");
		showMainView();
		showMainIterm();
	}
	
	public static void showMainIterm() throws IOException {
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainIterm.fxml"));
		BorderPane iterms=loader.load();
		borderpane.setCenter(iterms);	
	}
	
	public static void showMainError() throws IOException {
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainError.fxml"));
		BorderPane errorinfo=loader.load();
		
		Stage addstage=new Stage();
		addstage.setTitle("ssn error");
		addstage.initModality(Modality.WINDOW_MODAL);
		addstage.initOwner(primaryStage);
		Scene scene=new Scene(errorinfo);
		addstage.setScene(scene);
		addstage.showAndWait();
	}

	private void showMainView() throws IOException {
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainView.fxml"));
		borderpane=loader.load();
		Scene scene=new Scene(borderpane);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void showCheckResult() throws IOException {
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(Main.class.getResource("operator/EmployeeCheck.fxml"));
		BorderPane checkresult=loader.load();
		borderpane.setCenter(checkresult);
	}
	
	public static void showAddEmpStage() throws IOException {
		
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(Main.class.getResource("operator/AddNewEmployee.fxml"));
		BorderPane addnewemp=loader.load();
		Stage addstage=new Stage();
		addstage.setTitle("Add New Employee");
		addstage.initModality(Modality.WINDOW_MODAL);
		addstage.initOwner(primaryStage);
		Scene scene=new Scene(addnewemp);
		addstage.setScene(scene);
		addstage.showAndWait();
	}
	
	public static void showWorksonStage() throws IOException {
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(Main.class.getResource("operator/Addworkson.fxml"));
		BorderPane addnewemp=loader.load();
		Stage addstage=new Stage();
		addstage.setTitle("Allocate Project");
		addstage.initModality(Modality.WINDOW_MODAL);
		addstage.initOwner(primaryStage);
		Scene scene=new Scene(addnewemp);
		addstage.setScene(scene);
		addstage.showAndWait();
	}
	public static void showDependentStage() throws IOException {
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(Main.class.getResource("operator/AddDependent.fxml"));
		BorderPane addnewemp=loader.load();
		Stage addstage=new Stage();
		addstage.setTitle("Add Dependent");
		addstage.initModality(Modality.WINDOW_MODAL);
		addstage.initOwner(primaryStage);
		Scene scene=new Scene(addnewemp);
		addstage.setScene(scene);
		addstage.showAndWait();
	}
	
	public static void showDetailsStage() throws IOException {
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(Main.class.getResource("operator/ShowDetailsOfEmp.fxml"));
		BorderPane addnewemp=loader.load();
		Stage addstage=new Stage();
		addstage.setTitle("Add Dependent");
		addstage.initModality(Modality.NONE);
		addstage.initOwner(primaryStage);
		Scene scene=new Scene(addnewemp);
		addstage.setScene(scene);
		addstage.showAndWait();
	}
	


	public static void main(String[] args) {
		launch(args);
	}

}

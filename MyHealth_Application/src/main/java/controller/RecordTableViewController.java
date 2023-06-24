package main.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.java.model.Model;
import main.java.model.User;
import main.java.model.UserRecord;

public class RecordTableViewController {
	@FXML
	private TableView healthrecord;
	@FXML
	private TableColumn date;
	@FXML
	private TableColumn weight;
	@FXML
	private TableColumn temperature;
	@FXML
	private TableColumn bloodpressurelow;
	@FXML
	private TableColumn bloodpressurehigh;
	@FXML
	private TableColumn notes;
	@FXML
	private Button gobackhome;
	
	private Model model;
	private Stage stage;
	private Stage parentStage;
	private UserRecord records;
	
	
	//Constructor
	public RecordTableViewController(Stage parentStage, Model model)
	{
		this.stage = new Stage();
		this.model = model;
		this.parentStage = parentStage;
	}
	
	@FXML
	public void initialize() {
		
		User user;
		user = model.getCurrentUser();
		
		// Populate the table view with all the records of the user
		date.setCellValueFactory( new PropertyValueFactory<UserRecord, String>("date"));
		weight.setCellValueFactory( new PropertyValueFactory<UserRecord, Double>("weight"));
		temperature.setCellValueFactory( new PropertyValueFactory<UserRecord, Double>("temperature"));
		bloodpressurelow.setCellValueFactory( new PropertyValueFactory<UserRecord, Double>("lowbp"));
		bloodpressurehigh.setCellValueFactory( new PropertyValueFactory<UserRecord, Double>("highbp"));
		notes.setCellValueFactory( new PropertyValueFactory<UserRecord, String>("notes"));
		
		for(UserRecord x: user.getrecords())
		{
		healthrecord.getItems().add(x);
		}
		
		// Navigate back home
		gobackhome.setOnAction(event -> {
			stage.close();	 
		});
		
	}
	
	// To show the stage
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 800, 500);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("View all records");
		stage.show();
	}
	
	

}

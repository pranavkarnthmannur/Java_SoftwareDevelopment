package main.java.controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.java.model.Model;
import main.java.model.User;
import main.java.model.UserRecord;
import javafx.scene.control.TableColumn;

public class editRecordViewController {
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
	private Button toedit;
	@FXML
	private Button gobackhome;
	@FXML
	private Label edited;
	
	private Model model;
	private Stage stage;
	private Stage parentStage;
	private UserRecord records;
	
	// Constructor 
	public editRecordViewController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}
	
	@FXML
	public void initialize() {
		
		User user;
		user = model.getCurrentUser();
		
		// Populate the table with all the user's records
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
		
		// Navigate back to the home page
		gobackhome.setOnAction(event -> {
			stage.close();	 
		});
		
		// Edit the chosen record
		toedit.setOnAction(event ->{
			
			// Selected record by the user which is to be edited
			UserRecord tobeedit = (UserRecord) healthrecord.getSelectionModel().getSelectedItem();
			
			if(!(tobeedit == null))
			{
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/editoldRecordView.fxml"));
				editoldRecordViewController editoldviewController = new editoldRecordViewController(stage, model, tobeedit);

				loader.setController(editoldviewController);
				Pane root = loader.load();

				editoldviewController.showStage(root);
				stage.close();
				
				} 
			catch (IOException e) {
				System.out.println(e.getMessage());
				}
			}
			else
			{
				edited.setText("NO RECORDS");
				edited.setTextFill(Color.RED);
			}
    	});
		
	}
	
	// Show the stage
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 800, 500);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Update Record");
		stage.show();
	}

}

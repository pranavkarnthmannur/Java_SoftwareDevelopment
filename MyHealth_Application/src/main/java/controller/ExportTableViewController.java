package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.java.model.Model;
import main.java.model.User;
import main.java.model.UserRecord;
import javafx.scene.control.TableColumn;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.ListIterator;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class ExportTableViewController {
	
	@FXML
	private Button exportall;
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
	@FXML
	private Label exportlabel;
	
	private Model model;
	private Stage stage;
	private Stage parentStage;
	private UserRecord records;
	

	
	public ExportTableViewController(Stage parentStage, Model model)
	{
		
		this.stage = new Stage();
		this.model = model;
		this.parentStage = parentStage;

	}
	
	
	@FXML
	public void initialize() {
		
		User user;
		user = model.getCurrentUser();
		

		date.setCellValueFactory( new PropertyValueFactory<UserRecord, String>("date"));
		weight.setCellValueFactory( new PropertyValueFactory<UserRecord, Double>("weight"));
		temperature.setCellValueFactory( new PropertyValueFactory<UserRecord, Double>("temperature"));
		bloodpressurelow.setCellValueFactory( new PropertyValueFactory<UserRecord, Double>("lowbp"));
		bloodpressurehigh.setCellValueFactory( new PropertyValueFactory<UserRecord, Double>("lowbp"));
		notes.setCellValueFactory( new PropertyValueFactory<UserRecord, String>("notes"));
		healthrecord.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		
		for(UserRecord x: user.getrecords())
		{
		healthrecord.getItems().add(x);
		}
		
		
		exportall.setOnAction(event ->{
			ObservableList<UserRecord> tobeexported  = (ObservableList<UserRecord>) healthrecord.getSelectionModel().getSelectedItems();
			PrintWriter pw;
			
			if(!tobeexported.isEmpty())
			{
			try
			{
				FileWriter fw = new FileWriter("MyHealthRecords.txt",true);
				BufferedWriter bw = new BufferedWriter(fw);
				if(user != null)
				{
					model.setCurrentUser(user);
					try
					{
						for(UserRecord rec: tobeexported)
						{
							exportlabel.setText("EXPORTED");
							exportlabel.setTextFill(Color.GREEN);
							bw.write(rec.getrID()+","+rec.getDate()+","+rec.getWeight()+","+rec.getTemperature()+","+rec.getLowbp()+","+rec.getHighbp()+","+rec.getNotes()+"\n");
						}
						bw.close();
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
					}
				}
				
			}
			catch(IOException e)
			{
				System.out.println(e.getMessage());
			}
			}
			else
			{
			 exportlabel.setText("NO RECORDS");
			 exportlabel.setTextFill(Color.RED);	
			}
			
		});
	
		gobackhome.setOnAction(event -> {
			stage.close();	 
		});
		
	}
	
	
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 800, 500);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Export records");
		stage.show();
	}
	
	
	
	

}

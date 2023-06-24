package main.java.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.java.model.Model;
import main.java.model.User;
import main.java.model.UserRecord;

public class NewRecordViewController {

	@FXML
	private Button gobackhome;
	@FXML
	private Button add;
	@FXML
	private Label errorrecord;
	@FXML
	private TextField date;
	@FXML
	private TextField lbp;
	@FXML
	private TextField hbp;
	@FXML
	private TextField temp;
	@FXML
	private TextField wt;
	@FXML
	private TextArea note;
	
	private Stage stage;
	private Stage parentStage;
	private Model model;
	
	String date1;
	double weight;
	double lowbp;
	double temperature;
	double highbp;
	String notes;
	
	// Constructor
	public NewRecordViewController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}
	
	// Check date format
	public static boolean isValidDate(String date)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		try
		{
			formatter.parse(date);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	// Check length of notes
	public static boolean isLength50(String s)
	{
		String[] words50 = s.split(" ");
		if(words50.length <= 50)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	// Checking greater value for BP high and low
	public static boolean isGreater(String a, String b)
	{	
		
		if(Double.parseDouble(b)!=0 && Double.parseDouble(a)!=0)
		{
		if(Double.parseDouble(b)>Double.parseDouble(a))
		{
			return true;
		}
		else
		{
			return false;
		}
		}
		else
		{
			return false;
		}
	}
	
	// Checking if the value is double
	public static boolean isDouble(String number)
	{	
		try
		{
			Double.parseDouble(number);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}	
	
	@FXML
	public void initialize() {
		
		// Adding new records to the DB 
		add.setOnAction(event -> {
			User user;
			UserRecord record = new UserRecord();
			try {
				user = model.getCurrentUser();
				if (user != null) {
					model.setCurrentUser(user);
					try {
						// Input validation for all the fields	
						if(!date.getText().isEmpty() && isValidDate(date.getText()))
						{
							date1 = date.getText();
							
							if(!wt.getText().isEmpty() || !temp.getText().isEmpty() || (!lbp.getText().isEmpty() && !hbp.getText().isEmpty()) || !note.getText().isEmpty())
							{
								if(!wt.getText().isEmpty() && isDouble(wt.getText()))
								{
									weight = Double.parseDouble(wt.getText());
								}
								else
								{
									weight = 0;
								}
								
								if(!temp.getText().isEmpty() && isDouble(temp.getText()))
								{
									temperature = Double.parseDouble(temp.getText());
								}
								
								else
								{
									temperature = 0;
								}
								
								if( (!lbp.getText().isEmpty() && !hbp.getText().isEmpty()) && isDouble(lbp.getText()) && isDouble(hbp.getText()) && isGreater(lbp.getText(),hbp.getText()))
								{
									lowbp = Double.parseDouble(lbp.getText());
									highbp = Double.parseDouble(hbp.getText());
								}
								
								else
								{
									lowbp = 0;
									highbp = 0;
								}
								
								if(!note.getText().isEmpty() && isLength50(note.getText()))
								{
									notes = note.getText();
								}
								else
								{
									notes = " ";
								}
								
								if(isGreater(Double.toString(lowbp),Double.toString(highbp)))
								{
									
									// Adding new records under the user's records
									UserRecord newrecord = model.getUserDao().addUserRecord(date1, weight, temperature, lowbp, highbp, notes, user.getUsername());
									newrecord.setrID(model.getUserDao().addnewrecordID());
									System.out.println(newrecord.getrID());
									model.getCurrentUser().setrecords(newrecord);
									
									errorrecord.setText("RECORD Added");
									errorrecord.setTextFill(Color.GREEN);
									
									// Clearing all fields 
									date.clear();
									wt.clear();
									temp.clear();
									lbp.clear();
									hbp.clear();
									note.clear();
									
								}
								
								else
								{
									errorrecord.setText(
											"Temperature must be a digit \r\n"
											+ "Blood Pressure must be a digit \r\n"
											+ "Weight must be a digit \r\n"
											+ "Note must not exceed 50 words \r\n"
											+ "High BP value must be higher than LowBP");
									errorrecord.setTextFill(Color.RED);
								}
								
							}
								
							else
							{
								errorrecord.setText("Please fill in atleast one of the fields \r\n"
										+ "1. Weight \r\n"
										+ "2. Temperature \r\n"
										+ "3. Blood pressure \r\n"
										+ "4. Notes ");
								errorrecord.setTextFill(Color.RED);
							}
						
						}
						
						else
						{
							errorrecord.setText("Please enter the date \r\n"
									+ "Date format must be dd/MM/yyyy");
							errorrecord.setTextFill(Color.RED);
						}

						} 
					
					catch (Exception e) {
						errorrecord.setText("Enter valid inputs");
						errorrecord.setTextFill(Color.RED);
						}
					
					} 
				} 
			
			catch (Exception e) {
				errorrecord.setText("User error");
				errorrecord.setTextFill(Color.RED);
			}
		});
		
		// Navigate to the home page
		gobackhome.setOnAction(event -> {
			stage.close();	 
		});
	}
	
	// Show the stage
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 800, 500);
		stage.setScene(scene);
		stage.setResizable(true);
		stage.setTitle("Add new record");
		stage.show();
	}
	
}

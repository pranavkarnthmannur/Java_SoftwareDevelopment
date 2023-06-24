package main.java.controller;

import java.time.format.DateTimeFormatter;


import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.java.model.Model;
import main.java.model.User;
import main.java.model.UserRecord;
import javafx.scene.control.TextArea;
import main.java.model.User;
import main.java.model.UserRecord;
import javafx.stage.Stage;
import main.java.model.Model;

public class editoldRecordViewController {
	@FXML
	private TextField date;
	@FXML
	private TextField wt;
	@FXML
	private TextField temp;
	@FXML
	private TextField lbp;
	@FXML
	private TextField hbp;
	@FXML
	private TextArea note;
	@FXML
	private Button editrec;
	@FXML
	private Button gobackhome;
	@FXML
	private Label errorrecord;
	
	private Model model;
	private Stage stage;
	private Stage parentStage;
	private UserRecord records;
	
	String date1;
	double weight;
	double lowbp;
	double temperature;
	double highbp;
	String notes;
	
	public editoldRecordViewController(Stage parentStage, Model model, UserRecord records) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
		this.records = records;
	}

	// Input validation: checking date format
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
	
	// Input validation: checking length of notes
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
	
	// Input validation: checking the BP values low and high
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
			return true;
		}
	}
	
	// Input validation: checking if the entered input is double
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
		
		// Setting the labels with old values of the user's record
		date.setText(records.getDate());
		wt.setText(Double.toString(records.getWeight()));
		temp.setText(Double.toString(records.getTemperature()));
		lbp.setText(Double.toString(records.getLowbp()));
		hbp.setText(Double.toString(records.getHighbp()));
		note.setText(records.getNotes());
				
		// To edit the record 
		editrec.setOnAction(event -> {
			User user;
			user = model.getCurrentUser();
			UserRecord record = new UserRecord();
			try {
				user = model.getCurrentUser();
				if (user != null) {
					model.setCurrentUser(user);
					try {
						
						// Input validation
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
									
									model.getUserDao().edituserrecorddetails(date1, weight, temperature, lowbp, highbp, notes, records.getrID());
									
									for(UserRecord rec : model.getCurrentUser().getrecords()) {
										
										if(rec.getrID() == records.getrID()) {
											rec.setDate(date1);
											rec.setNotes(notes);
											rec.setTemperature(temperature);
											rec.setWeight(weight);
											rec.setLowbp(lowbp);
											rec.setHighbp(highbp);
										}
									}					
									
									errorrecord.setText("Record has been Updated");
									errorrecord.setTextFill(Color.GREEN);

									date.clear();
									wt.clear();
									temp.clear();
									lbp.clear();
									hbp.clear();
									note.clear();
									
								}
								
								// Error messages
								else
								{
									
									errorrecord.setText(
											"Temperature must be a digit \r\n"
											+ "Blood Pressure must be a digit \r\n"
											+ "Weight must be a digit \r\n"
											+ "Note must not exceed 50 words");
									errorrecord.setTextFill(Color.RED);
								}
								
							}
							
							// Error messages
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
						
						// Error messages
						else
						{
							errorrecord.setText("Please enter the date \r\n"
									+ "Date format must be dd/MM/yyyy");
							errorrecord.setTextFill(Color.RED);
						}

						} 
					
					catch (Exception e) {
						System.out.println(e.getMessage());
						}
					
					} 
				} 
			
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		});
		
		
		// Navigate back to the home page
		gobackhome.setOnAction(event -> {
			stage.close();	 
		});

	}

	
	// To show the stage
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 800, 500);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Edit Record");
		stage.show();
	}
	

}

package main.java.controller;

import java.io.File;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.model.Model;
import main.java.model.User;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class EditProfileViewController {
	@FXML
	private Button changedp;
	@FXML
	private Label editusername;
	@FXML
	private Label error;
	@FXML
	private TextField newfname;
	@FXML
	private TextField newsname;
	@FXML
	private Button changedetails;
	@FXML
	private Button gobackhome;
	@FXML
	private ImageView viewdp;
	private Stage stage;
	private Stage parentStage;
	private Model model;
	final FileChooser fc = new FileChooser();
	
	// Constructor
	public EditProfileViewController(Stage parentstage, Model model) {
		// TODO Auto-generated constructor stub
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}
	
	@FXML
	public void initialize() {
		
		// To edit the user's details
		changedetails.setOnAction(event -> {
			User user;
			
			// User will have to enter both the details: firtsname and lastname
			if(!newfname.getText().isEmpty() && !newsname.getText().isEmpty())
			{
				try
				{
					user = model.getCurrentUser();
					if(user!=null)
					{
						model.getUserDao().edituserdetails(newfname.getText(), newsname.getText(), user);
						model.getCurrentUser().setFirstname(newfname.getText());
						model.getCurrentUser().setLastname(newsname.getText());
						error.setText("UPDATED");
						error.setTextFill(Color.GREEN);
					}
				}
				catch(Exception e){
					error.setText(e.getMessage());
				}
				stage.close();
			}
			else
			{
				error.setText("Please enter firstname and lastname");
				error.setTextFill(Color.RED);
			}
		});
		
		// Set profile picture
		changedp.setOnAction(event ->{
			
			fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png","*.jpg","*.gif"));
			File file = fc.showOpenDialog(null);
			
			if (file != null)
			{
				viewdp.setImage(new Image(file.toURI().toString()));
			}
			else
			{
				System.out.println("File Invalid");
			}
		}
		);
		
		
		
		// Navigate back to the home page
		gobackhome.setOnAction(event -> {
			stage.close();	 
		});
		
	}

	public void showStage(Pane root) {
		Scene scene = new Scene(root, 800, 500);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Edit profile details");
		stage.show();
	}


}

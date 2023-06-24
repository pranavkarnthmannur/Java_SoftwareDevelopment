package main.java.controller;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.model.Model;
import main.java.model.User;

public class ViewProfileController {
	@FXML
	private Label viewusername;
	@FXML
	private Label viewfirst;
	@FXML
	private Label viewlast;
	@FXML
	private Button gobackhome;
	@FXML
	private ImageView viewdp;
	@FXML
	private Button changedp;
	private Stage stage;
	private Stage parentStage;
	private Model model;
	final FileChooser fc = new FileChooser();
	
	// Constructor
	public ViewProfileController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}
	
	
	@FXML
	public void initialize(){
		
		// Change profile picture
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
		
		// Go back home page
		gobackhome.setOnAction(event -> {
			stage.close();	 
		});
	}
		
	// Showing the stage
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 800, 500);
		viewusername.setText(model.getCurrentUser().getUsername());
		viewfirst.setText(model.getCurrentUser().getFirstname());
		viewlast.setText(model.getCurrentUser().getLastname());
		stage.setScene(scene);
		stage.setResizable(true);
		stage.setTitle("Profile");
		stage.show();
	}
}

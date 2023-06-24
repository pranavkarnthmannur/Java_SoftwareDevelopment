package main.java.controller;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import main.java.dao.UserDao;
import main.java.model.Model;
import main.java.model.User;

public class HomeController {
	private Model model;
	private Stage stage;
	private Stage parentStage;
	@FXML
	private MenuItem viewProfile; // Corresponds to the Menu item "viewProfile" in HomeView.fxml
	@FXML
	private MenuItem updateProfile; // // Corresponds to the Menu item "updateProfile" in HomeView.fxml
	@FXML
	private MenuItem logout;
	@FXML
	private MenuItem delete;
	@FXML
	private MenuItem export;
	@FXML
	private MenuItem edit;
	@FXML
	private MenuItem about;
	@FXML
	private MenuItem newRecord;
	@FXML
	private MenuItem viewallrecords;
	@FXML
	private Label addusername;
	@FXML
	private Label error;
	
	// Constructor
	public HomeController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}
	
	@FXML
	public void initialize() {
		
		// View profile view 
		viewProfile.setOnAction(event -> {

			User user;
			
			try {
				user = model.getCurrentUser();
				if (user != null) {
					model.setCurrentUser(user);
					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ViewProfile.fxml"));
						ViewProfileController viewprofileController = new ViewProfileController(stage, model);

						loader.setController(viewprofileController);
						Pane root = loader.load();

						viewprofileController.showStage(root);
						} 
					catch (IOException e) {
						error.setText("Stage error");
						error.setTextFill(Color.RED);
						}
					} 
				} 
			catch (Exception e) {
				error.setText("User error");
				error.setTextFill(Color.RED);
			}
		});
		
		// Delete record view 
		delete.setOnAction(event -> {

			User user;
			
			try {
				user = model.getCurrentUser();
				if (user != null) {
					model.setCurrentUser(user);
					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/delRecordView.fxml"));
						delRecordViewController delrecordviewController = new delRecordViewController(stage, model);

						loader.setController(delrecordviewController);
						Pane root = loader.load();
						delrecordviewController.showStage(root);
						} 
					catch (IOException e) {
						error.setText("Stage error");
						error.setTextFill(Color.RED);
						}
					} 
				} 
			catch (Exception e) {
				error.setText("User error");
				error.setTextFill(Color.RED);
			}
		});
		
		// Edit profile view
		edit.setOnAction(event -> {

			User user;
			
			try {
				user = model.getCurrentUser();
				if (user != null) {
					model.setCurrentUser(user);
					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/editRecordView.fxml"));
						editRecordViewController editrecordviewController = new editRecordViewController(stage, model);

						loader.setController(editrecordviewController);
						Pane root = loader.load();
						editrecordviewController.showStage(root);
						} 
					catch (IOException e) {
						error.setText("Stage error");
						error.setTextFill(Color.RED);
						}
					} 
				} 
			catch (Exception e) {
				error.setText("User error");
				error.setTextFill(Color.RED);
			}
		});
		
		// Update profile view 
		updateProfile.setOnAction(event -> {

			User user;
			
			try {
				user = model.getCurrentUser();
				if (user != null) {
					model.setCurrentUser(user);
					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditProfileView.fxml"));
						EditProfileViewController editprofileviewController = new EditProfileViewController(stage, model);

						loader.setController(editprofileviewController);
						Pane root = loader.load();
						
						editprofileviewController.showStage(root);

						
						} 
					catch (IOException e) {
						error.setText("Stage error");
						error.setTextFill(Color.RED);
						}
					} 
				} 
			catch (Exception e) {
				error.setText("User error");
				error.setTextFill(Color.RED);
			}
		});
		
		// To create a new record view 
		newRecord.setOnAction(event -> {

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NewRecordView.fxml"));
				NewRecordViewController newrecordviewController = new NewRecordViewController(stage, model);

				loader.setController(newrecordviewController);
				Pane root = loader.load();

				newrecordviewController.showStage(root);
				} 
			catch (IOException e) {
				error.setText("Stage error");
				error.setTextFill(Color.RED);
				}
		});
		
		// To view all record in the table 
		viewallrecords.setOnAction(event -> {

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RecordTableView.fxml"));
				RecordTableViewController recordtableviewController = new RecordTableViewController(stage, model);

				loader.setController(recordtableviewController);
				Pane root = loader.load();

				recordtableviewController.showStage(root);
				} 
			catch (IOException e) {
				error.setText("Stage error");
				error.setTextFill(Color.RED);
				}
		});
		
		// To export all record to the file
		export.setOnAction(event -> {

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ExportTableView.fxml"));
				ExportTableViewController exporttableviewController = new ExportTableViewController(stage, model);

				loader.setController(exporttableviewController);
				Pane root = loader.load();

				exporttableviewController.showStage(root);
				} 
			catch (IOException e) {
				error.setText("Stage error");
				error.setTextFill(Color.RED);
				}
			
		});

		
		// To logout from the Application
		logout.setOnAction(event -> {

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
				LoginController loginController = new LoginController(stage, model);

				loader.setController(loginController);
				Pane root = loader.load();

				loginController.showStage(root);
				} 
			catch (IOException e) {
				error.setText("Stage error");
				error.setTextFill(Color.RED);
				}
		});
		
		// About application version
				about.setOnAction(event -> {

					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AboutView.fxml"));
						AboutViewController aboutController = new AboutViewController(stage, model);

						loader.setController(aboutController);
						Pane root = loader.load();

						aboutController.showStage(root);
						} 
					catch (IOException e) {
						error.setText("Stage error");
						error.setTextFill(Color.RED);
						}
				});
	}
	
	// To show the stage
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 800, 500);
		addusername.setText(model.getCurrentUser().getFirstname()+" "+model.getCurrentUser().getLastname());
		Font font = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 25);
		addusername.setFont(font);
		addusername.setTextFill(Color.RED);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Home");
		stage.show();
	}

}

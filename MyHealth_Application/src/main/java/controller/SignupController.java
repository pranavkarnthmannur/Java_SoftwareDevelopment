package main.java.controller;

import java.sql.SQLException;

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

public class SignupController {
	@FXML
	private TextField username;
	@FXML
	private TextField firstname;
	@FXML
	private TextField lastname;
	@FXML
	private TextField password;
	@FXML
	private Button createUser;
	@FXML
	private Button close;
	@FXML
	private Label status;

	private Stage stage;
	private Stage parentStage;
	private Model model;

	public SignupController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}

	@FXML
	public void initialize() {
		
		// Creating new user - signup
		createUser.setOnAction(event -> {
			
			// Input validation
			if (!username.getText().isEmpty() && !password.getText().isEmpty() && !firstname.getText().isEmpty() && !lastname.getText().isEmpty()) 
			{
				User user;
				try {
					user = model.getUserDao().createUser(username.getText(), password.getText(), firstname.getText(), lastname.getText());
					if (user != null) {
						status.setText("Created " + user.getUsername());
						status.setTextFill(Color.GREEN);
					} else {
						status.setText("Cannot create user");
						status.setTextFill(Color.RED);
					}
				} catch (SQLException e) {
					status.setText("Please enter valid details, username exists.");
					status.setTextFill(Color.RED);
				}

			} else {
				status.setText("Empty username or password");
				status.setTextFill(Color.RED);
			}
		});
		
		// Close the stage
		close.setOnAction(event -> {
			stage.close();
			parentStage.show();
		});
	}
	
	// Show stage 
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 800, 500);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Sign up");
		stage.show();
	}
}

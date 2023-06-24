package main.java.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.java.model.Model;
import main.java.model.User;
import main.java.model.UserRecord;

public class LoginController {
	@FXML
	private TextField name;
	@FXML
	private PasswordField password;
	@FXML
	private Label message;
	@FXML
	private Button login;
	@FXML
	private Button signup;
	@FXML
	private Button close;

	private Model model;
	private Stage stage;
	
	// Constructor
	public LoginController(Stage stage, Model model) {
		this.stage = stage;
		this.model = model;
	}

	@FXML
	public void initialize() {
		
		// Log in to the application
		login.setOnAction(event -> {
			
			// Input validation
			if (!name.getText().isEmpty() && !password.getText().isEmpty()) {
				User user;
				try {
					user = model.getUserDao().getUser(name.getText(), password.getText());
					if (user != null) {
						model.setCurrentUser(user);
						
						List<UserRecord> userrec = model.getUserDao().exisitingRecords(user.getUsername());
						if (userrec!=null)
								{
							// Setting all records when user logs in
							for(UserRecord rec: userrec)
							{
								model.getCurrentUser().setrecords(rec);
							}
								}
						
						// Moves to home page on log in
						try {
							FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeView.fxml"));
							HomeController homeController = new HomeController(stage, model);

							loader.setController(homeController);
							VBox root = loader.load();

							homeController.showStage(root);
							stage.close();
							
						} catch (IOException e) {
							
							message.setText("Stage error");
							message.setTextFill(Color.RED);
						}

					} else {
						message.setText("Wrong username or password");
						message.setTextFill(Color.RED);
					}
				} catch (SQLException e) {
					message.setText("User error");
					message.setTextFill(Color.RED);
				}

			} else {
				message.setText("Empty username or password");
				message.setTextFill(Color.RED);
			}
			name.clear();
			password.clear();
		});
		
		// Navigates to signup view 
		signup.setOnAction(event -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignupView.fxml"));

				// Customize controller instance
				SignupController signupController = new SignupController(stage, model);

				loader.setController(signupController);
				VBox root = loader.load();

				signupController.showStage(root);
				
				// Clear all fields
				message.setText("");
				name.clear();
				password.clear();

				stage.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
				message.setText(e.getMessage());
			}
		});
		
		// close application
		close.setOnAction(event -> Platform.exit());
	}

	public void showStage(Pane root) {
		Scene scene = new Scene(root, 800, 500);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Welcome to MyHealth");
		stage.show();
	}
}

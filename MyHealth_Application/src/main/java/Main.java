package main.java;

import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;

import main.java.model.Model;
import main.java.model.UserRecord;
import main.java.controller.LoginController;

public class Main extends Application {
	private Model model;
	private Record record;

	@Override
	public void init() {
		model = new Model();
	}
	
	// Loads the login page and start of the My Health Application
	@Override
	public void start(Stage primaryStage) {
		try {
			model.setup();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));

			// Customize controller instance
			LoginController loginController = new LoginController(primaryStage, model);
			primaryStage.setTitle("Welcome to MyHealth");
			loader.setController(loginController);

			GridPane root = loader.load();

			loginController.showStage(root);
		} catch (IOException | SQLException | RuntimeException e) {
			Scene scene = new Scene(new Label(e.getMessage()), 200, 100);
			primaryStage.setTitle("Error");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}
	
	// Main Method
	public static void main(String[] args) {
		launch(args);
	}
}

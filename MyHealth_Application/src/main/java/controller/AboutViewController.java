package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.java.model.Model;

public class AboutViewController {
	
	private Model model;
	private Stage stage;
	private Stage parentStage;

	
	public AboutViewController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}
	
	// To show the stage
		public void showStage(Pane root) {
			Scene scene = new Scene(root, 400, 200);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("About");
			stage.show();
		}
	
}

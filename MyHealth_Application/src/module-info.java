module LoginDemoAgain {
	requires java.sql;
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;


	opens main.java to javafx.graphics, javafx.fxml;
	opens main.java.controller;
	opens main.java.model to javafx.graphics, javafx.fxml,javafx.base;
}
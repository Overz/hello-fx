package com.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class MainController implements Initializable {
	@FXML
	private VBox content; // required
	@FXML
	private VBox navBar; // required
	@FXML
	private VBox wrapperContent;
	@FXML
	private ContentController contentController;
	@FXML
	private NavBarController navBarController;

	private Map<String, VBox> screens;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		navBarController.setMainController(this);
		contentController.setMainController(this);
	}

	protected void switchScene(String id) {
		try {
			VBox screen = this.screens.get(id);
			if (screen != null) {
				GridPane.setHgrow(screen, Priority.ALWAYS); // extend screen
				VBox.setVgrow(screen, Priority.ALWAYS); // extend screen
				wrapperContent.getChildren().set(0, screen);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String, VBox> getScreens() {
		return screens;
	}

	public void setScreens(Map<String, VBox> screens) {
		this.screens = screens;
	}
}

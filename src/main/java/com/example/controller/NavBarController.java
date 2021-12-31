package com.example.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class NavBarController implements Initializable {
	@FXML
	public JFXButton btnScreenOne;
	@FXML
	public JFXButton btnScreenTwo;
	@FXML
	public JFXButton btnHelp;
	@FXML
	public GridPane grid;

	private MainController mainController;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnScreenOne.setId("content");
		btnScreenTwo.setId("screen-2");
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	/**
	 * change screen and button color
	 *
	 * @param evt event when button was selected
	 */
	public void onAction(ActionEvent evt) {
		ObservableList<Node> nodes = grid.getChildren();
		Object source = evt.getSource();

		for (Node node : nodes) {
			if (node instanceof JFXButton) {
				node.setStyle("-fx-background-color: #4b4949"); // gray
				if (node.equals(source)) {
					node.setStyle("-fx-background-color: #5252b7"); // blue
					mainController.switchScene(node.getId());
				}
			}
		}
	}
}

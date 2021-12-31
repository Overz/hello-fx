package com.example.view;

import com.example.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

import static com.example.configs.Configs.ConfigKeys.LOGO;
import static com.example.configs.Configs.ConfigKeys.NAME;
import static com.example.configs.Configs.getProperty;
import static com.example.configs.Configs.loadProperties;
import static java.util.Objects.requireNonNull;

public class Main extends Application {

	public static void main(String[] args) {
		try {
			loadProperties();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("main.fxml"));
		Scene mainScene = new Scene(mainLoader.load(), 1200, 800);

		MainController ctrl = mainLoader.getController();
		ctrl.setScreens(new HashMap<>() {{
			put("content", FXMLLoader.load(getClass().getResource("content.fxml")));
			put("navbar", FXMLLoader.load(getClass().getResource("navbar.fxml")));
			put("screen-2", FXMLLoader.load(getClass().getResource("screen-2.fxml")));
		}});

		stage.setTitle(getProperty(NAME));
		stage.setMaximized(true);
		stage.setScene(mainScene);
		stage.getIcons().add(new Image(getClass().getResourceAsStream(getProperty(LOGO))));
		stage.show();
	}
}

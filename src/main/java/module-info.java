module com.example.hellofx {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires com.jfoenix;
	requires static lombok;

	exports com.example.view;
	opens com.example.view to javafx.fxml;

	exports com.example.controller;
	opens com.example.controller to javafx.fxml;

	exports com.example.models.vo;
	opens com.example.models.vo;
}

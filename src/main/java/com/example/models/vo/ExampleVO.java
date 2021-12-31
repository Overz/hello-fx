package com.example.models.vo;

import javafx.beans.property.SimpleStringProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExampleVO {
	private SimpleStringProperty title;
	private SimpleStringProperty description;

	public ExampleVO(String title, String description) {
		this(new SimpleStringProperty(title), new SimpleStringProperty(description));
	}

	public SimpleStringProperty titleProperty() {
		return title;
	}

	public SimpleStringProperty descriptionProperty() {
		return description;
	}
}

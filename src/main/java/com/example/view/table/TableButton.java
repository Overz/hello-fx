package com.example.view.table;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TableButton<S, T> extends TableCell<S, T> {
	private final JFXButton btn;

	public TableButton(Icon icon, EventHandler<ActionEvent> evt) {
		String v = icon.getValue();
		this.btn = new JFXButton(
			null,
			v != null
				? new ImageView(new Image(getClass().getResourceAsStream(icon.getValue())))
				: null
		);
		btn.setMinWidth(80);
		btn.setMinHeight(30);
		btn.setMaxHeight(30);
		btn.setOnAction(evt);
	}

	@Override
	protected void updateItem(T item, boolean empty) {
		super.updateItem(item, empty);
		if (empty) {
			setGraphic(null);
		} else {
			setGraphic(btn);
		}
	}

	public enum Icon {
		INFO("/com/example/images/help_1.png"),
		EDIT("/com/example/images/edit.png"),
		USE("/com/example/images/plus.png"),
		NULL(null);

		final String key;

		Icon(String key) {
			this.key = key;
		}

		public String getValue() {
			return this.key;
		}
	}
}

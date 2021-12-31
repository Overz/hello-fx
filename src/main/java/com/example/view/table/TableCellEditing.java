package com.example.view.table;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableCellEditing<S, T> extends TableCell<S, T> {
	private TextField textField;

	@Override
	public void startEdit() {
		super.startEdit();

		if (textField == null) {
			createTextField();
		}

		setGraphic(textField);
		setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		textField.selectAll();
	}

	@Override
	public void cancelEdit() {
		super.cancelEdit();

		setText(String.valueOf(getItem()));
		setContentDisplay(ContentDisplay.TEXT_ONLY);
	}

	@Override
	public void updateItem(T item, boolean empty) {
		super.updateItem(item, empty);

		if (empty) {
			setText(null);
			setGraphic(null);
		} else if (isEditing()) {
			if (textField != null) {
				textField.setText(getString());
			}
			setGraphic(textField);
			setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		} else {
			setText(getString());
			setContentDisplay(ContentDisplay.TEXT_ONLY);
		}
	}

	private void createTextField() {
		textField = new TextField(getString());
		textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
		textField.setOnKeyPressed(t -> {
			if (t.getCode() == KeyCode.ENTER) {
				commitEdit((T) textField.getText());
			} else if (t.getCode() == KeyCode.ESCAPE) {
				cancelEdit();
			}
		});
	}

	private String getString() {
		return getItem() == null ? "" : getItem().toString();
	}
}

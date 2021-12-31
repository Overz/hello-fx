package com.example.controller;

import com.example.models.vo.ExampleVO;
import com.example.view.table.TableButton;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;

import static com.example.view.table.TableButton.Icon.*;

public class ContentController implements Initializable {
	private MainController mainController;

	@FXML
	public TextField txtSearch;
	@FXML
	private TableView<ExampleVO> table;
	@FXML
	private JFXButton btnFilters;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configureTable();
	}

	public void configureTable() {
		ObservableList<ExampleVO> observableList = FXCollections.observableArrayList();
		for (int i = 0; i < 10; i++) {
			ExampleVO item = new ExampleVO("C" + i, "V" + i);
			observableList.add(item);
		}

		table.getColumns().addAll(
			addNormalColumn("Title", ExampleVO::titleProperty, 150, 300),
			addNormalColumn("Description", ExampleVO::descriptionProperty, 200, Integer.MAX_VALUE),
			addButtonColumn("Info", INFO, e -> System.out.println("Info...")),
			addButtonColumn("Edit", EDIT, e -> System.out.println("Editar...")),
			addButtonColumn("Use", USE, e -> System.out.println("Usar..."))
		);

		table.setItems(addSearch(observableList));
	}

	private SortedList<ExampleVO> addSearch(ObservableList<ExampleVO> observableList) {
		FilteredList<ExampleVO> filteredData = new FilteredList<>(observableList, e -> true);
		txtSearch.textProperty().addListener((observable, oldValue, newValue) ->
			filteredData.setPredicate(search ->
				newValue.isEmpty() ||
					newValue.isBlank() ||
					search
						.getTitle()
						.getValue()
						.toLowerCase()
						.contains(newValue.toLowerCase()))
		);

		SortedList<ExampleVO> sortData = new SortedList<>(filteredData);
		sortData.comparatorProperty().bind(table.comparatorProperty());

		return sortData;
	}

	private <S, T> TableColumn<S, T> addButtonColumn(String title, TableButton.Icon icon, EventHandler<ActionEvent> evt) {
		TableColumn<S, T> col = (TableColumn<S, T>) column(title, ReadOnlyObjectWrapper::new);
		col.setMinWidth(100);
		col.setMaxWidth(100);
		col.setSortable(false);
		col.setStyle("-fx-alignment: CENTER");
		col.setCellFactory(p -> new TableButton<>(icon, evt));
		return col;
	}

	private <S, T> TableColumn<S, T> addNormalColumn(String title, Function<S, ObservableValue<T>> propery, int minWidth, int maxWidth) {
		TableColumn<S, T> col = column(title, propery);
		col.setMinWidth(minWidth);
		col.setMaxWidth(maxWidth);
		col.setStyle("-fx-alignment: CENTER-LEFT;");
		return col;
	}

	private <S, T> TableColumn<S, T> column(String title, Function<S, ObservableValue<T>> property) {
		TableColumn<S, T> col = new TableColumn<>(title);
		col.setEditable(false);
		col.setReorderable(false);
		col.setCellValueFactory(cel -> property.apply(cel.getValue()));
		return col;
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}

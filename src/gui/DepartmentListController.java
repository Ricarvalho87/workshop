package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable {

	private DepartmentService service;

	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}

	@FXML
	private Button btNew;

	@FXML
	private TableView<Department> tableViewDepartments;

	@FXML
	private TableColumn<Department, Integer> tableColumnId;

	@FXML
	private TableColumn<Department, String> tableColumnName;

	ObservableList<Department> obsList;

	@FXML
	private void onBtNewAction() {
		System.out.println("New");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	public void updateTableview() {
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Department> list = service.findAll();
		obsList=FXCollections.observableArrayList(list);
		tableViewDepartments.setItems(obsList);
	}

	private void initializeNodes() {
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
	

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewDepartments.prefHeightProperty().bind(stage.heightProperty());

	}

}

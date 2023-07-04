package com.example.shopapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class StorageController implements Initializable {
    @FXML
    private TableView<Storage> tableView;
    @FXML
    private TableColumn<Storage, Integer> rowColumn;
    @FXML
    private TableColumn<Storage, Integer> idColumn;
    @FXML
    private TableColumn<Storage, String> nameColumn;
    @FXML
    private TableColumn<Storage, Integer> amountColumn;
    @FXML
    private TableColumn<Storage, String> managerColumn;
    @FXML
    private TableColumn<Storage, String> addressColumn;
    @FXML
    private TableColumn actionsColumn;

    private ObservableList<Storage> storages;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        storages = FXCollections.observableArrayList();

        
    }

    private void load() {

    }

}

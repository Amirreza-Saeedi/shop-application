package com.example.shopapplication;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;

public class StorageController extends Application implements Initializable {
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
    private TableColumn<Storage, Integer> valueColumn;
    @FXML
    private TableColumn<Storage, String> managerColumn;
    @FXML
    private TableColumn<Storage, String> addressColumn;
    @FXML
    private TableColumn<Storage, Void> actionsColumn;
    @FXML
    private Label errorLabel;
    @FXML
    private Button addButton;

    private ObservableList<Storage> storages = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initializeColumns(); // set cell values
        loadStorages(); // fill observableList
        tableView.setItems(storages); // link table values to storages

        errorLabel.setVisible(false);
    }

    private void initializeColumns() {
        // table columns
        rowColumn.setCellValueFactory(new PropertyValueFactory<>("row"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        managerColumn.setCellValueFactory(new PropertyValueFactory<>("manager"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        addButtonsToTable(); // action col
    }

    public void loadStorages() {
        ArrayList<Storage> list = new ArrayList<>();

        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            Statement statement1 = connection.createStatement();
            String sql = "Select * FROM Storages";
            ResultSet resultSet1 = statement1.executeQuery(sql);

            Statement statement2 = connection.createStatement();
            ResultSet resultSet2;
            for (int i = 1; resultSet1.next();i++) {
                // init storage
                String manager = resultSet1.getString("manager");
                String name = resultSet1.getString("name");
                String address = resultSet1.getString("address");
                int id = resultSet1.getInt("storageId");
                int amount = 0;
                int value  = 0;

                // calculate from AllCommodities
                sql = "SELECT sum(number) as amount, sum(number*price) as value from AllCommodities " +
                        "where storageId='" + id + "'";
                resultSet2 = statement2.executeQuery(sql);

                if (resultSet2.next()) {
                    amount = resultSet2.getInt("amount");
                    value = resultSet2.getInt("value");
                }

                // create storage
                Storage storage = new Storage(name, i, id, amount, value, address, manager);
                System.out.println("storage = " + storage);
                list.add(storage);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        storages.setAll(list); // fill observable list

//        // sample
//        storages.addAll(
//                new Storage("asdfl", 1, 1, 3, "alkdf", "lksdf"),
//                new Storage("asdfl", 2, 2, 3, "alkdf", "lksdf"),
//                new Storage("asdfl", 3, 3, 3, "alkdf", "lksdf"),
//                new Storage("asdfl", 4, 4, 3, "alkdf", "lksdf")
//        );
    }

    public void add() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addStorageDialog.fxml"));
        Parent parent = fxmlLoader.load();

        AddStorageDialogController dialogController = fxmlLoader.getController();
        dialogController.setAll(this, errorLabel);

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("New Storage");
        stage.setResizable(false);
        stage.showAndWait(); // todo not a proper way to get result

    }

    private void addButtonsToTable() {

        Callback<TableColumn<Storage, Void>, TableCell<Storage, Void>> callback = new Callback<>() {

            @Override
            public TableCell<Storage, Void> call(TableColumn<Storage, Void> storageVoidTableColumn) {
                final TableCell<Storage, Void> cell = new TableCell<>() {
                    // delete
                    private final Button deleteButton = new Button("Delete");
                    {
                        deleteButton.setOnAction((ActionEvent e) -> {
//                            Storage storage = getTableView().getItems().get(getIndex());
//                            storages.remove(storage);
                            delete(getTableView().getItems().get(getIndex()));
                        });
                    }

                    // edit
                    private final Button editButton = new Button("Edit");
                    {
                        editButton.setOnAction(e -> {
                            System.out.println("edit");
                        });
                    }

                    private final HBox hBox = new HBox(editButton, deleteButton);
                    {
                        hBox.setSpacing(5);
                    }


                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(hBox);
                        }
                    }

                };

                return cell;
            }
        };

        actionsColumn.setCellFactory(callback);
    }

    private void delete(Storage storage) {
        // list of options
        ObservableList<Integer> options = FXCollections.observableArrayList();
        for (Storage value : storages) {
            if (!value.getId().equals(storage.getId())) // except the storage itself
                options.add(value.getId());
        }

        // set combo box
        ComboBox<Integer> comboBox = new ComboBox<>(options);
        comboBox.setPromptText("Storage ID");

        // choice dialog
        ChoiceDialog<Integer> dialog = new ChoiceDialog<>();
        dialog.setContentText("Storage '" + storage.getId() + "' will be deleted.");
        dialog.setTitle("Delete");
        dialog.setHeaderText("Select the storage commodities will be transferred to.");

        // VBox to hold the ComboBox
        VBox vbox = new VBox();
        vbox.getChildren().add(comboBox);
        dialog.getDialogPane().setContent(vbox);

        // convert the result to Integer
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return comboBox.getValue();
            }
            return null;
        });

        // making decision
        Optional<Integer> result = dialog.showAndWait();

         // result handling
        result.ifPresent(selectedOption -> {
            /**
             * update and delete
             * */

            int fromId = storage.getId();
            int toId = selectedOption;

            try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
                // update: change storageIds in AllCommodities
                Statement statement = connection.createStatement();
                String sql = "UPDATE AllCommodities set storageId='" + toId + "' where storageId='" + fromId + "'";
                int resultSet = statement.executeUpdate(sql);
                System.out.println("resultSet after update = " + resultSet);

                // delete: in Storages
                sql = "delete from Storages where storageId='" + fromId + "'";
                resultSet = statement.executeUpdate(sql);
                System.out.println("resultSet after delete = " + resultSet);

//                // edit observableList
//                storages.
//                storages.remove(storage);
                // reload
                loadStorages();

                // showError: successful
                ErrorMessage.showError(errorLabel, "Deletion is done successfully.", 5, Color.GREEN);

            } catch (SQLException | ClassNotFoundException e) {
                System.err.println(e);
                e.printStackTrace();
                ErrorMessage.showError(errorLabel, "Deletion failed.", 5, Color.RED);
            }
        });

    }


    public static void main(String[] args) {launch();}

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("storage.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

}

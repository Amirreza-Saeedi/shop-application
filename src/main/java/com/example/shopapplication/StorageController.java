package com.example.shopapplication;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
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
    private TableColumn<Storage, BigDecimal> valueColumn;
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

    private Admin admin;

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

        TableColumn[] columns = {rowColumn, idColumn, nameColumn, amountColumn,
                valueColumn, managerColumn, addressColumn, actionsColumn};

        for (TableColumn c : columns) {
            c.setReorderable(false);
        }
    }

    public void loadStorages() {
        /**
         * also fills observable list
         * */
        ArrayList<Storage> list = new ArrayList<>();

        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            Statement statement1 = connection.createStatement();
            String sql = "Select * FROM Storages";
            ResultSet resultSet1 = statement1.executeQuery(sql);

            Statement statement2 = connection.createStatement();
            ResultSet resultSet2;
            for (int i = 1; resultSet1.next();i++) {
                // init storage
                int id = resultSet1.getInt("storageId");
                String manager = resultSet1.getString("manager");
                String name = resultSet1.getString("name");
                String address = resultSet1.getString("address");
                int amount = 0;
                BigDecimal value  = new BigDecimal(0);

                // calculate from AllCommodities
                sql = "SELECT sum(number) as amount, sum(number*price) as value from AllCommodities " +
                        "where storageId='" + id + "'";
                resultSet2 = statement2.executeQuery(sql);

                if (resultSet2.next()) {
                    amount = resultSet2.getInt("amount");
                    value = BigDecimal.valueOf(resultSet2.getDouble("value"));
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
                            edit(getTableView().getItems().get(getIndex()));
                        });
                    }

                    private final Button chartButton = new Button("Chart");
                    { // chart button
                        chartButton.setOnAction(event -> {
                            chart(getTableView().getItems().get(getIndex()));
                        });
                    }

                    private final HBox hBox = new HBox(editButton, deleteButton, chartButton);
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

    private void chart(Storage storage) {
        System.out.println("StorageManagementController.chart");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("storage-chart.fxml"));

        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        StorageChartController controller = loader.getController();
        controller.setAll(storage);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();

        loadStorages();
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
        // todo load here

    }

    private ObservableList<Integer> createOptions(Storage storage) {
        /**
         * returns comboBox with storage ids except of given storage
         * */
        // list of options
        ObservableList<Integer> options = FXCollections.observableArrayList();
        for (Storage value : storages) {
            if (!value.getId().equals(storage.getId())) // except the storage itself
                options.add(value.getId());
        }

        return options;
    }

    private void delete(Storage storage) {
        // set combo box
        ComboBox<Integer> comboBox = new ComboBox<>(createOptions(storage));
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


                loadStorages(); // reload

                // logs
                StorageLog.logStorageDeletion(fromId, storage.getAmount(),
                        storage.getValue().doubleValue(), toId, connection);
                StorageLog.logStorageMerger(toId, storage.getAmount(),
                        storage.getValue().doubleValue(), storage.getName(), connection);
                // showError: successful
                ErrorMessage.showError(errorLabel, "Deletion is done successfully.", 5, Color.GREEN);

            } catch (SQLException | ClassNotFoundException e) {
                System.err.println(e);
                e.printStackTrace();
                ErrorMessage.showError(errorLabel, "Deletion failed.", 5, Color.RED);
            }
        });

    }

    private void edit(Storage storage)  {
        System.out.println("edit");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("storage-management.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        StorageManagementController controller = loader.getController();
        controller.setAll(storage, createOptions(storage)); // todo

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        loadStorages();
    }


    public static void main(String[] args) {launch();}

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("storage.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void logs() {
        System.out.println("StorageController.logs");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("storage-logs.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        StorageLogsController controller = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        loadStorages();

    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void toHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();

            HomeController controller = loader.getController();
            controller.setUser(admin);

            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

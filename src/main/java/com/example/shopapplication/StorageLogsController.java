package com.example.shopapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StorageLogsController implements Initializable {
    @FXML
    private TableView<StorageLog> tableView;
    @FXML
    private TableColumn<StorageLog, Integer> rowColumn;
    @FXML
    private TableColumn<StorageLog, Integer> storageColumn;
    @FXML
    private TableColumn<StorageLog, Integer> amountColumn;
    @FXML
    private TableColumn<StorageLog, Double> valueColumn;
    @FXML
    private TableColumn<StorageLog, String> descriptionsColumn;
    @FXML
    private TableColumn<StorageLog, String> typeColumn;
    @FXML
    private TableColumn<StorageLog, String> dateColumn;
    @FXML
    private TableColumn<StorageLog, String> timeColumn;
    @FXML
    private TableColumn<StorageLog, Void> actionColumn;
    @FXML
    private Label errorLabel;
    ObservableList<StorageLog> storageLogs = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLabel.setVisible(false);
        tableView.setEditable(false);
        initColumns();
        loadLogs();
        tableView.setItems(storageLogs);
    }

    private void addButtonsToTable() {

        Callback<TableColumn<StorageLog, Void>, TableCell<StorageLog, Void>> callback = new Callback<>() {

            @Override
            public TableCell<StorageLog, Void> call(TableColumn<StorageLog, Void> storageVoidTableColumn) {
                final TableCell<StorageLog, Void> cell = new TableCell<>() {

                    final Button clearButton = new Button("Clear");
                    {
                        clearButton.setOnAction(event -> {
                            clear(getTableView().getItems().get(getIndex()));
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(clearButton);
                        }
                    }

                };

                return cell;
            }
        };

        actionColumn.setCellFactory(callback);
    }

    public void clearAll() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getButtonTypes().setAll(ButtonType.NO, ButtonType.YES);
        alert.setTitle("Clear All");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("All logs will be cleared for good.");

        if (alert.showAndWait().get() == ButtonType.YES) {
            try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
                Statement statement = connection.createStatement();
                int resultSet = statement.executeUpdate("delete from StorageLogs");

            } catch (SQLException | ClassNotFoundException e) {
                ErrorMessage.showError(errorLabel, "Error occurred.", 5, Color.RED);
                throw new RuntimeException(e);
            }

            loadLogs();
            ErrorMessage.showError(errorLabel, "All logs cleared.", 5, Color.GREEN);
        }
    }

    private void clear(StorageLog log) {
        System.out.println("StorageLogsController.clear");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getButtonTypes().setAll(ButtonType.NO, ButtonType.YES);
        alert.setTitle("Clear log");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("This log will be cleared for good.");

        if (alert.showAndWait().get() == ButtonType.YES) {
            try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
                Statement statement = connection.createStatement();
                String sql = "delete from StorageLogs where logId='" + log.getStorageId() + "'";
                int resultSet = statement.executeUpdate(sql);

            } catch (SQLException | ClassNotFoundException e) {
                ErrorMessage.showError(errorLabel, "Error occurred.", 5, Color.RED);
                throw new RuntimeException(e);
            }

            loadLogs();
            ErrorMessage.showError(errorLabel, "Log cleared.", 5, Color.GREEN);
        }
    }

    private void loadLogs() {
        ArrayList<StorageLog> list = new ArrayList<>();
        try(Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            Statement statement = connection.createStatement();
            String sql = "select * from StorageLogs;";
            ResultSet resultSet = statement.executeQuery(sql);

            for (int i = 1; resultSet.next(); i++) {
                int id = resultSet.getInt("logId");
                int storageId = resultSet.getInt("storageId");
                int amount = resultSet.getInt("amount");
                double value = resultSet.getDouble("value");
                String descriptions = resultSet.getString("descriptions");
                String type = resultSet.getString("type");
                String time = resultSet.getString("time");
                String date = resultSet.getString("date");

                StorageLog log = new StorageLog(i, id, storageId, amount, value, date, time, type, descriptions);
                list.add(log);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        storageLogs.setAll(list);
    }

    private void initColumns() {
        rowColumn.setCellValueFactory(new PropertyValueFactory<>("row"));
        storageColumn.setCellValueFactory(new PropertyValueFactory<>("storageId"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        descriptionsColumn.setCellValueFactory(new PropertyValueFactory<>("descriptions"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        TableColumn[] columns = {rowColumn, storageColumn, amountColumn,
                valueColumn, descriptionsColumn, typeColumn, dateColumn, timeColumn, actionColumn};
        for (TableColumn c : columns) {
            c.setReorderable(false);
        }

        addButtonsToTable();
    }

    public void close() {
        Stage stage = (Stage) errorLabel.getScene().getWindow();
        stage.close();
    }

    public void export() {
        System.out.println("StorageLogsController.export");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Logs as CSV File");

        // Set the initial directory
        FileSystemView fileSystemView = FileSystemView.getFileSystemView();
        fileChooser.setInitialDirectory(fileSystemView.getHomeDirectory());

        // restrict the user to only save files with a .csv extension
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show the save file dialog and get the selected file
        File file = fileChooser.showSaveDialog(new Stage());

        if (file != null) {
            System.out.println("Selected file: " + file.getAbsolutePath());
            try {
                writeExcel(file);
            } catch (IOException e) {
                ErrorMessage.showError(errorLabel, "An error occurred.", 5, Color.RED);
                throw new RuntimeException(e);
            }
            ErrorMessage.showError(errorLabel, "Logs saved successfully.", 5, Color.GREEN);
        }
    }

    public void writeExcel(File file) throws IOException {
        Writer writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            String cols = "Row/ Storage/ Amount/ Value/ Descriptions/ Type/ Date/ Time\n";
            writer.write(cols);
            for (StorageLog log : storageLogs) {
                String text = log.getRow() + "/ " + log.getStorageId() + "/ " + log.getAmount()
                        + "/ " + log.getValue() + "/ " +
                        log.getDescriptions() + "/ " + log.getType() + "/ " + log.getDate() + "/ "
                        + log.getTime() + "\n";
                writer.write(text);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ErrorMessage.showError(errorLabel, "An error occurred.", 5, Color.RED);
        }
        finally {
            assert writer != null;
            writer.flush();
            writer.close();
        }
    }
}

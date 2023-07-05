package com.example.shopapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StorageManagementController implements Initializable {
    @FXML
    private TableView<Commodity> tableView;
    @FXML
    private TableColumn<Commodity, Integer> idColumn;
    @FXML
    private TableColumn<Commodity, Integer> amountColumn;
    @FXML
    private TableColumn<Commodity, String> titleColumn;
    @FXML
    private TableColumn<Commodity, String> brandColumn;
    @FXML
    private TableColumn<Commodity, String> typeColumn;
    @FXML
    private TableColumn<Commodity, String> priceColumn;
    @FXML
    private TableColumn<Commodity, String> dateColumn;
    @FXML
    private TableColumn<Commodity, String> sellerColumn;
    @FXML
    private TableColumn<Commodity, Void> transportColumn;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField valueTextField;
    @FXML
    private TextField amountTextField;
    @FXML
    private Label errorLabel;
    @FXML
    private Button applyButton;
    @FXML
    private Button closeButton;
    private Storage storage;



    private ObservableList<Commodity> commodities = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLabel.setVisible(false);
        initializeColumns();

    }

    private void loadCommodities(int storageId) {
        ArrayList<Commodity> list = new ArrayList<>();

        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            // commodities
            Statement statement = connection.createStatement();
            String sql = "SELECT * from allCommodities where storageId='" + storageId + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String type = resultSet.getString("type");
                String price = resultSet.getString("price");
                String brand = resultSet.getString("brand");
                String title = resultSet.getString("title");
                String date = resultSet.getString("date");
                String sellerId = resultSet.getString("userName");
                int number = resultSet.getInt("number");
                int commodityId = resultSet.getInt("commodityId");

                Commodity commodity = new Commodity(type, price, brand, title, date, sellerId, number, commodityId);

                list.add(commodity);
            }

            // total amount and value
            sql = "SELECT sum(number) as amount, sum(price*number) as value from allCommodities where storageId='" + storageId + "'";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                String amount = resultSet.getString("amount");
                String value = resultSet.getString("value");
                amountTextField.setText(amount);
                valueTextField.setText(value);
            }


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        commodities.setAll(list);
    }

    private void initializeColumns() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("commodityId"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        sellerColumn.setCellValueFactory(new PropertyValueFactory<>("sellerId"));
    }

    private void setTransportCell(ObservableList<Integer> options) {
        Callback<TableColumn<Commodity, Void>, TableCell<Commodity, Void>> callback = new Callback<>() {

            @Override
            public TableCell<Commodity, Void> call(TableColumn<Commodity, Void> storageVoidTableColumn) {
                final TableCell<Commodity, Void> cell = new TableCell<>() {

                    private final ComboBox<Integer> comboBox = new ComboBox<>(options);
                    private final Button applyButton = new Button("Apply");

                    { // combo box
                        comboBox.setPromptText("Storage ID");
                        comboBox.getSelectionModel().selectedItemProperty().
                                addListener((observableValue, oldValue, newValue) -> {
                                    System.out.println(newValue);
                                    applyButton.setDisable(false);
                        });
                    }

                    { // applyButton button
                        applyButton.setDisable(true);
                        applyButton.setOnAction(e -> {
                            int toStorageId = comboBox.getValue();
                            apply(getTableView().getItems().get(getIndex()), toStorageId);
                        });

                    }

                    private final HBox hBox = new HBox(comboBox, applyButton);
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

        transportColumn.setCellFactory(callback);
    }

    public void setAll(Storage storage, ObservableList<Integer> options) {
        setTransportCell(options); // customize cell
        this.storage = storage;

        // set observable list
        loadCommodities(storage.getId());
        tableView.setItems(commodities);
    }

    private void setStorageNodes(Storage storage) {
        idTextField.setText(storage.getId() + "");
        nameTextField.setText(storage.getName());
    }


    public void apply(Commodity commodity, int toStorageId) {
        System.out.println("applyButton");

        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            // update storage ids in allCommodities
            Statement statement = connection.createStatement();
            String sql = "update AllCommodities set storageId='" + toStorageId + "' " +
                    "where commodityId='" + commodity.getCommodityId() + "'";
            int resultSet = statement.executeUpdate(sql);

            // successful
            ErrorMessage.showError(errorLabel, "Successfully transferred.", 5, Color.GREEN);

        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
            System.err.println(e);
            e.printStackTrace();
            ErrorMessage.showError(errorLabel, "Transport failed.", 5, Color.RED);
        }

        loadCommodities(storage.getId()); // reload
    }

    public void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}

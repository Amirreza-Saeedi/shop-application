package com.example.shopapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
                int isAuction = resultSet.getInt("isAuction");

                String imageName = resultSet.getString("imageName");
                Image image = new Image(imageName);

                Commodity commodity = new Commodity(type, price, brand, title, date, sellerId, number, commodityId, isAuction);

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
                    private final Button deleteButton = new Button("Delete");
                    { // delete button
                        deleteButton.setOnAction(event -> {
                            delete(getTableView().getItems().get(getIndex()));
                        });
                    }

                    private final VBox vBox = new VBox(comboBox, applyButton);

                    private final HBox hBox = new HBox(vBox, deleteButton);
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

    private void delete(Commodity commodity) {
        System.out.println("StorageManagementController.delete");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getButtonTypes().setAll(ButtonType.NO, ButtonType.YES);
        alert.setTitle("Delete");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Click 'YES' and this commodity will be removed for good.");
        if (alert.showAndWait().get() == ButtonType.YES) {
            deleteCommodity(commodity);
        }
    }

    private void deleteCommodity(Commodity commodity) {
        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            Statement statement = connection.createStatement();
            String str = "where commodityId='" + commodity.getCommodityId() + "'";

            // auction:
            returnChargeToAccount(commodity, connection);
            String sql = "delete from auction " + str;
            int resultSet = statement.executeUpdate(sql);

            // commodities:
            sql = "delete from allCommodities " + str;
            resultSet = statement.executeUpdate(sql);


            // commodityVotes:
            sql = "delete from commodityVotes " + str;
            resultSet = statement.executeUpdate(sql);

            // comments:
            sql = "delete from comments " + str;
            resultSet = statement.executeUpdate(sql);

            // baskets:
            sql = "delete from baskets " + str;
            resultSet = statement.executeUpdate(sql);

            // logs
            StorageLog.logCommodityDeletion(storage.getId(), commodity.getNumber(),
                    Double.parseDouble(commodity.getPrice()), commodity.getTitle(), connection);

            ErrorMessage.showError(errorLabel, commodity.getTitle() + " removed successfully.", 5, Color.GREEN);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        loadCommodities(storage.getId());
    }

    private void returnChargeToAccount(Commodity commodity, Connection connection) throws SQLException {
        if (commodity.getIsAuction() > 0) {
            Statement statement = connection.createStatement();
            // find auction
            String sql = "select * from auction where auctionId='" + commodity.getIsAuction() + "';";
            ResultSet resultSet = statement.executeQuery(sql);


            if (resultSet.next()) {

                String buyerName = resultSet.getString("buyerUsername");
                String buyerType = resultSet.getString("buyerType");
                int mostPrice = resultSet.getInt("mostPrice");

                Auction auction = new Auction(buyerName, buyerType, mostPrice);


                // find user
                String table = auction.getBuyerType() + "s";
                sql = "select * from " + table + " where username='" + auction.getBuyerName() + "'";
                resultSet = statement.executeQuery(sql);

                if (resultSet.next()) {
                    // update user
                    int charge = resultSet.getInt("charge");
                    int newCharge = (int) (charge + auction.getMostPrice());
                    sql = "update " + table + " set charge='" + newCharge +
                            "' where username='" + auction.getBuyerName() + "'";
                    if (statement.executeUpdate(sql) > 0)
                        System.out.println("charge returned");
                    ;
                }
            } else {
                System.err.println("StorageManagementController.returnChargeToAccount");
                System.err.println("auction not found");
            }
        }
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

            // logs
            StorageLog.logCommodityExportation(storage.getId(), commodity.getNumber(),
                    Double.parseDouble(commodity.getPrice()), commodity.getTitle(), connection);
            StorageLog.logCommodityImportation(toStorageId, commodity.getNumber(),
                    Double.parseDouble(commodity.getPrice()), commodity.getTitle(), connection);

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

package com.example.shopapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PurchaseHistoryController implements Initializable {
    @FXML
    private TableView<Purchase> tableView;
    @FXML
    private TableColumn<Purchase, Integer> rowColumn;
    @FXML
    private TableColumn<Purchase, Integer> commodityColumn;
    @FXML
    private TableColumn<Purchase, String> typeColumn;
    @FXML
    private TableColumn<Purchase, Integer> numberColumn;
    @FXML
    private TableColumn<Purchase, String> priceColumn;
    @FXML
    private TableColumn<Purchase, String> brandColumn;
    @FXML
    private TableColumn<Purchase, String> dateColumn;
    @FXML
    private TableColumn<Purchase, String> discountColumn;
    @FXML
    private Label errorLabel;

    private User user;
    private String userType;

    private ObservableList<Purchase> purchases = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initColumns();
        tableView.setItems(purchases);
    }

    private void initColumns() {
        rowColumn.setCellValueFactory(new PropertyValueFactory<>("row"));
        commodityColumn.setCellValueFactory(new PropertyValueFactory<>("commodityId"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("priceOfOne"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        discountColumn.setCellValueFactory(new PropertyValueFactory<>("discountCode"));

        TableColumn[] columns = {rowColumn, commodityColumn, typeColumn, numberColumn,
                priceColumn, brandColumn, discountColumn, discountColumn};
        for (TableColumn column : columns) {
            column.setReorderable(false);
        }
    }

    private void loadPurchases() {
        ArrayList<Purchase> list = new ArrayList<>();

        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            Statement statement = connection.createStatement();
            String sql = "select * from purchases where userId='" + user.getUsername() +
                    "' and user='" + userType + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            for (int i = 1; resultSet.next(); i++) {
                int commodityId = resultSet.getInt("commodityID");
                int number = resultSet.getInt("number");
                String type = resultSet.getString("type");
                String price = resultSet.getString("priceOfOne");
                String date = resultSet.getString("date");
                String discountCode = resultSet.getString("discountCode");
                String brand = resultSet.getString("brand");

                Purchase purchase = new Purchase(i, commodityId, date, discountCode, type, number, price, brand);

                list.add(purchase);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        purchases.setAll(list);
    }

    public void clearAll() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        alert.setTitle("Clear All");
        alert.setHeaderText("Are you suer?");
        alert.setContentText("Your purchases history will be cleared for good.");

        if (alert.showAndWait().get() == ButtonType.YES) {
            try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
                Statement statement = connection.createStatement();
                String sql = "delete from purchases where userId='" + user.getUsername() +
                        "' and user='" + userType + "'";
                int resultSet = statement.executeUpdate(sql);

                ErrorMessage.showError(errorLabel, "History cleared.", 5, Color.GREEN);

            } catch (SQLException | ClassNotFoundException e) {
                ErrorMessage.showError(errorLabel, "Error occurred.", 5, Color.RED);
                throw new RuntimeException(e);
            }
        }

        loadPurchases();
    }

    public void setAll(User user) {
        this.user = user;
        if (user instanceof Customer) {
            userType = "customer";
        } else if (user instanceof Admin) {
            userType = "admin";
        } else if (user instanceof Seller) {
            userType = "seller";
        }

        loadPurchases();
    }
}


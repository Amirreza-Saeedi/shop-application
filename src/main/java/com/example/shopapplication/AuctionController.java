package com.example.shopapplication;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AuctionController implements Initializable {
    @FXML
    private Button applyButton;
    @FXML
    private Button closeButton;
    @FXML
    private TextField titleTextField;
    @FXML
    private TextField endDateTextField;
    @FXML
    private TextField bidderTextField;
    @FXML
    private TextField numberTextField;
    @FXML
    private TextField reservePriceTextField;
    @FXML
    private TextField mostPriceTextField;
    @FXML
    private TextField buyerTextField;
    @FXML
    private TextField newTextField;
    @FXML
    private TextField addTextField;
    @FXML
    private Label errorLabel;

    private String userType;
    private User user;
    private Commodity commodity;
    private int auctionId;


    public void setAll(User user, String userType, Commodity commodity) {
        auctionId = commodity.getAuctionId();
        this.user = user;
        this.commodity = commodity;
        System.out.println("auctionId = " + auctionId);
        this.userType = userType;

        loadAll();
    }

    private void loadAll() {
        loadUser();
        loadAuction();
        loadCommodity();
    }

    private void loadCommodity() {
        try(Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            Statement statement = connection.createStatement();
            String sql = "";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next())

        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
            System.err.println(e);
            e.printStackTrace();


        }
    }

    private void loadAuction() {

    }

    private void loadUser() {

    }

    public void apply() {
        System.out.println("AuctionController.apply");

    }

    public void close() {
        System.out.println("AuctionController.close");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

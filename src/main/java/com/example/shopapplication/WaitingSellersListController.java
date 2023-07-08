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
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WaitingSellersListController extends Application implements Initializable {
    @FXML
    private TableView<Seller> tableView;
    @FXML
    private TableColumn<Seller, String> usernameColumn;
    @FXML
    private TableColumn<Seller, String> passwordColumn;
    @FXML
    private TableColumn<Seller, String> firstnameColumn;
    @FXML
    private TableColumn<Seller, String> lastnameColumn;
    @FXML
    private TableColumn<Seller, String> emailColumn;
    @FXML
    private TableColumn<Seller, String> companyColumn;
    @FXML
    private TableColumn<Seller, String> phoneColumn;
    @FXML
    private TableColumn<Seller, Void> actionColumn;
    @FXML
    private Label errorLabel;
    private ObservableList<Seller> sellers = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLabel.setVisible(false);
        initColumns();
        loadSellers();
        tableView.setItems(sellers);
    }

    private void initColumns() {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        companyColumn.setCellValueFactory(new PropertyValueFactory<>("company"));
        setActionColumn();

        TableColumn[] columns = {usernameColumn, firstnameColumn, passwordColumn,
                lastnameColumn, emailColumn, phoneColumn, companyColumn, actionColumn};
        for (TableColumn c : columns) {
            c.setReorderable(false);
        }
    }

    private void setActionColumn() {

        Callback<TableColumn<Seller, Void>, TableCell<Seller, Void>> callback = new Callback<>() {

            @Override
            public TableCell<Seller, Void> call(TableColumn<Seller, Void> storageVoidTableColumn) {
                final TableCell<Seller, Void> cell = new TableCell<>() {

                    private final Button allowButton = new Button("Allow");

                    {
                        allowButton.setOnAction(event -> {
                            allow(getTableView().getItems().get(getIndex()));
                        });
                    }

                    private final Button denyButton = new Button("Deny");

                    {
                        denyButton.setOnAction(event -> {
                            deny(getTableView().getItems().get(getIndex()));
                        });
                    }

                    private final HBox hBox = new HBox(denyButton, allowButton);

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

        actionColumn.setCellFactory(callback);
    }

    private void deny(Seller seller) {
        System.out.println("WaitingSellersListController.deny");
        delete(seller);

        loadSellers();

        ErrorMessage.showError(errorLabel, "Deleted.", 5, Color.GREEN);
    }

    private void delete(Seller seller) {
        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            Statement statement = connection.createStatement();
            String sql = "delete from waitingSellers where username='" + seller.getUsername() + "';";
            int resultSet = statement.executeUpdate(sql);

        } catch (SQLException | ClassNotFoundException e) {
            ErrorMessage.showError(errorLabel, "Error in deleting seller from list.", 5, Color.RED);
            throw new RuntimeException(e);
        }
    }

    private void allow(Seller seller) {
        System.out.println("WaitingSellersListController.allow");
        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            Statement statement = connection.createStatement();
            String sql = "insert into sellers " +
                    "(username,password,firstname,lastname,company,email,phone) " +
                    "values ('" + seller.getUsername() + "','" + seller.getPassword() + "','" + seller.getFirstname() +
                    "','" + seller.getLastname() + "','" + seller.getCompany() + "','" + seller.getEmail() + "','" +
                    seller.getPhone() + "')";
            int resultSet = statement.executeUpdate(sql);
            if (resultSet == 0) {
                ErrorMessage.showError(errorLabel, "Error occurred in signing up.", 5, Color.RED);
                return;
            }


        } catch (SQLException | ClassNotFoundException e) {
            ErrorMessage.showError(errorLabel, "Error occurred in signing up.", 5, Color.RED);
            throw new RuntimeException(e);
        }

        delete(seller);
        
        ErrorMessage.showError(errorLabel, "Seller signed up.", 5, Color.GREEN);
        
        loadSellers();
    }

    private void loadSellers() {
        ArrayList<Seller> list = new ArrayList<>();
        
        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            Statement statement = connection.createStatement();
            String sql = "select * from waitingSellers;";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String pass     = resultSet.getString("password");
                String first    = resultSet.getString("firstname");
                String last     = resultSet.getString("lastname");
                String email    = resultSet.getString("email");
                String company  = resultSet.getString("company");
                String phone    = resultSet.getString("phone");

                Seller seller = new Seller(username, pass, first, last, email, phone, company);
                list.add(seller);
            }


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        sellers.setAll(list);
    }

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("waiting-sellers-list.fxml"));
        Parent root = loader.load();


//        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setResizable(false);
//        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.showAndWait();
        stage.show();
    }
}

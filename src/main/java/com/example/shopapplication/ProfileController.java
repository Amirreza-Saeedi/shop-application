package com.example.shopapplication;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ProfileController  {
    @FXML
    private Button basket;
    @FXML
    private Button deposit;
    @FXML
    private Button history;
    @FXML
    private TableColumn<Person, String> company;
    @FXML
    private TableColumn<Person, String> email;
    @FXML
    private TableColumn<Person, String> lastName;
    @FXML
    private TableColumn<Person, String> name;
    @FXML
    private TableColumn<Person, String> numberPhone;
    @FXML
    private AnchorPane pane;
    @FXML
    private TableColumn<Person, String> password;
    @FXML
    private TableView<Person> table;
    @FXML
    private TableColumn<Person, String> userName;
    @FXML
    private TableColumn<Person,String> userType;
    @FXML
    private ListView<String> discountCodesList;
    @FXML
    private Label walletCredit;
    private User user;
    private Person person;
    private String companyName = "";
    private ObservableList<Person> list;

    private void setTable(){
        list = FXCollections.observableArrayList(person);
        company.setCellValueFactory(new PropertyValueFactory<Person,String>("company"));
        email.setCellValueFactory(new PropertyValueFactory<Person,String>("email"));
        lastName.setCellValueFactory(new PropertyValueFactory<Person,String>("lastname"));
        name.setCellValueFactory(new PropertyValueFactory<Person,String>("firstname"));
        password.setCellValueFactory(new PropertyValueFactory<Person,String>("password"));
        userName.setCellValueFactory(new PropertyValueFactory<Person,String>("username"));

            numberPhone.setCellValueFactory(new PropertyValueFactory<Person,String>("phone"));

        table.setItems(list);
    }
    public void showDiscountCodes(){
        try(Connection connection = new DatabaseConnectionJDBC().getConnection()){
            String sql = "SELECT * FROM Purchases WHERE userId = '" + user.getUsername() + "' AND user = '" + user.toString() + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                String code = rs.getString("discountCode");
                if (!code.equals("null"))
                discountCodesList.getItems().add(code);
            }

        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }
    public void backToHome(ActionEvent event) throws IOException {
        new Login(user).loginToHome((Node) event.getSource());
    }
    public void goToBasket(ActionEvent event){
        Node node = (Node) event.getSource();
        FXMLLoader loader = new FXMLLoader(Login.class.getResource("basket.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BasketController basketController = loader.getController();
        basketController.setUser(user);
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));

    }
    public void increaseCredit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("walletChargePrice.fxml"));
        Parent parent = loader.load();

        WalletChargePriceController walletChargePriceController = loader.getController();
        walletChargePriceController.setLastStage((Stage) deposit.getScene().getWindow());
        walletChargePriceController.setUser(user);

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
//        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Charge Price");
        stage.setResizable(false);
        stage.showAndWait();
    }
    public void setUser(User user){
        if (user == null){
            throw new NullPointerException("user is null");
        }
        this.user = user;
        if (user instanceof  Seller){
            companyName = ((Seller) user).getCompany();
        }
        person = new Person(user);
        setTable();
        showDiscountCodes();
        walletCredit.setText(String.valueOf(user.getCharge()));
    }
}

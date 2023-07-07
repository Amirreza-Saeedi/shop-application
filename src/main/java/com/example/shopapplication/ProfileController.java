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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.channels.AcceptPendingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    @FXML
    private Button btn_basket;
    @FXML
    private Button btn_deposit;
    @FXML
    private Button btn_history;
    @FXML
    private TableColumn<User, String> company;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> lastName;
    @FXML
    private TableColumn<User, String> name;
    @FXML
    private TableColumn<User, String> numberPhone;
    @FXML
    private AnchorPane pane;
    @FXML
    private TableColumn<User, String> password;
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> userName;
    @FXML
    private TableColumn<User,String> userType;
    @FXML
    private ListView<String> discountCodesList;
    private User user;
    private String companyName = "";
    private ObservableList<User> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
    private void setTable(){
        list = FXCollections.observableArrayList(user);
        company.setCellValueFactory(new PropertyValueFactory<User,String>("company"));
        email.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        lastName.setCellValueFactory(new PropertyValueFactory<User,String>("lastname"));
        name.setCellValueFactory(new PropertyValueFactory<User,String>("firstname"));
        password.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
        userName.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        if (user instanceof Seller){
            userType.setCellValueFactory(new PropertyValueFactory("seller"));
            userType.setCellFactory(column -> new TableCell<User, String>() {
                @Override
                protected void updateItem(String type, boolean empty) {
                    super.updateItem(type, empty);

                        setText(type);
                }
            });
        } else if (user instanceof Customer) {
            userType.setCellValueFactory(new PropertyValueFactory("customer"));
        } else if (user instanceof Admin) {
            userType.setCellValueFactory(new PropertyValueFactory("admin"));
        }
//            numberPhone.setCellValueFactory(new PropertyValueFactory<User,String>(user.getName()));

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
    public void setUser(User user){
        if (user == null){
            throw new NullPointerException("user is null");
        }
        this.user = user;
        if (user instanceof  Seller){
            companyName = ((Seller) user).getCompany();
        }
        setTable();
        showDiscountCodes();
    }

    public void history() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("purchase-history.fxml"));
        Parent parent;
        try {
            parent = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        PurchaseHistoryController controller = loader.getController();
        controller.setAll(user);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
        stage.showAndWait();
    }
}

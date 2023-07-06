package com.example.shopapplication;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.mail.search.IntegerComparisonTerm;
import java.awt.*;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class BasketController implements Initializable {

    @FXML
    private Label warning00;
    @FXML
    private Label warning10;
    @FXML
    private Label warning01;
    @FXML
    private Label warning11;
    @FXML
    private Label warning02;
    @FXML
    private Label warning12;
    @FXML
    private Label totalPriceLabel;
    @FXML
    private AnchorPane anchorPane00;
    @FXML
    private AnchorPane anchorPane10;
    @FXML
    private AnchorPane anchorPane01;
    @FXML
    private AnchorPane anchorPane11;
    @FXML
    private AnchorPane anchorPane02;
    @FXML
    private AnchorPane anchorPane12;

    @FXML
    private ImageView image00;
    @FXML
    private ImageView image10;
    @FXML
    private ImageView image01;
    @FXML
    private ImageView image11;
    @FXML
    private ImageView image02;
    @FXML
    private ImageView image12;

    @FXML
    private ImageView delete00;
    @FXML
    private ImageView delete10;
    @FXML
    private ImageView delete01;
    @FXML
    private ImageView delete11;
    @FXML
    private ImageView delete02;
    @FXML
    private ImageView delete12;

    @FXML
    private Label title00;
    @FXML
    private Label title10;
    @FXML
    private Label title01;
    @FXML
    private Label title11;
    @FXML
    private Label title02;
    @FXML
    private Label title12;

    @FXML
    private Label errorLabel;
    @FXML
    private Spinner<Integer> number00;
    @FXML
    private Spinner<Integer> number10;
    @FXML
    private Spinner<Integer> number01;
    @FXML
    private Spinner<Integer> number11;
    @FXML
    private Spinner<Integer> number02;
    @FXML
    private Spinner<Integer> number12;

    @FXML
    private Label price00;
    @FXML
    private Label price10;
    @FXML
    private Label price01;
    @FXML
    private Label price11;
    @FXML
    private Label price02;
    @FXML
    private Label price12;

    @FXML
    private Label page;

    @FXML
    private Button goToNextPageButton;
    @FXML
    private Button goToPreviousPageButton;
    private double totalPrice = 0;

    private SpinnerValueFactory<Integer> number00ValueFactory;
    private SpinnerValueFactory<Integer> number10ValueFactory;
    private SpinnerValueFactory<Integer> number01ValueFactory;
    private SpinnerValueFactory<Integer> number11ValueFactory;
    private SpinnerValueFactory<Integer> number02ValueFactory;
    private SpinnerValueFactory<Integer> number12ValueFactory;
    private User user;

    private ArrayList<Commodity> commodities = new ArrayList<>();
    private ArrayList<Integer> commodityIds = new ArrayList<>();
    private ArrayList<Integer> basketIds = new ArrayList<>();
    private ArrayList<Integer> commodityNumbers = new ArrayList<>();

    private int maxNumber00;
    private int maxNumber10;
    private int maxNumber01;
    private int maxNumber11;
    private int maxNumber02;
    private int maxNumber12;
    private Connection connection;
    private Statement stmt;
    private ArrayList<Integer> maxNumbers = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            DatabaseConnectionJDBC databaseConnectionJDBC = new DatabaseConnectionJDBC();
            connection = databaseConnectionJDBC.getConnection();
            stmt = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

      numbersListeners();


        checkToVisibleNextPageButton();
        checkToVisiblePreviousButton();
    }
    private void selectCommodities() throws SQLException {
        hideAnchorPanes();
        page.setText("1");
        String sql;

            String userType = null;
            if (user instanceof Customer) userType = "customer";
            else if (user instanceof Seller) userType = "seller";
            else if(user instanceof Admin) userType = "admin";
            sql = "SELECT * FROM Baskets WHERE userId = '" + user.getUsername() + "' AND user = '" + userType + "';";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            commodityIds.clear();
            commodityNumbers.clear();
            commodities.clear();
            while (rs.next()){
                commodityIds.add(rs.getInt("commodityId"));
                commodityNumbers.add(rs.getInt("number"));
                basketIds.add(rs.getInt("basketId"));
            }
            for (int i = 0; i < commodityIds.size(); i++) {
                sql = "SELECT * FROM AllCommodities WHERE commodityId = " + commodityIds.get(i);
                rs = stmt.executeQuery(sql);
                String type = rs.getString("Type");
                String brand = rs.getString("Brand");
                String price = rs.getString("Price");
                String ratio = rs.getString("Ratio");
                String title = rs.getString("Title");
                int isAuction = rs.getInt("isAuction");
                int number = rs.getInt("number");
                maxNumbers.add(number);
                commodities.add(new Commodity(type,brand,price,ratio,title,commodityNumbers.get(i),commodityIds.get(i),isAuction,basketIds.get(i)));
                rs.next();

            }
            calculateTotalPrice();

            showAnchorPanes(1);
            checkToVisibleNextPageButton();
            checkToVisiblePreviousButton();
            rs.close();
            stmt.close();

    }
    private void numbersListeners(){
        number00.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*6) - 6;
            int id = commodities.get(page).getBasketId();
            int currentNum = number00ValueFactory.getValue();
            String sql = "UPDATE Baskets SET number = ? WHERE basketId = ?";
            PreparedStatement pstmt = null;
            try {
                pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1, currentNum);
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }

            commodities.get(page).setNumber(currentNum);
            double thisPrice = Double.parseDouble(commodities.get(page).getPrice()) * commodities.get(page).getNumber();
            price00.setText(String.valueOf(thisPrice));
            calculateTotalPrice();
        });
        number10.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*6) - 6 +1;
            int id = commodities.get(page).getBasketId();
            int currentNum = number10ValueFactory.getValue();
            String sql = "UPDATE Baskets SET number = ? WHERE basketId = ?";
            PreparedStatement pstmt = null;
            try {
                pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1, currentNum);
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }

            commodities.get(page).setNumber(currentNum);
            double thisPrice = Double.parseDouble(commodities.get(page).getPrice()) * commodities.get(page).getNumber();
            price10.setText(String.valueOf(thisPrice));
            calculateTotalPrice();
        });
        number01.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*6) - 6 +2;
            int id = commodities.get(page).getBasketId();
            int currentNum = number01ValueFactory.getValue();
            String sql = "UPDATE Baskets SET number = ? WHERE basketId = ?";
            PreparedStatement pstmt = null;
            try {
                pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1, currentNum);
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
            commodities.get(page).setNumber(currentNum);
            double thisPrice = Double.parseDouble(commodities.get(page).getPrice()) * commodities.get(page).getNumber();
            price01.setText(String.valueOf(thisPrice));
            calculateTotalPrice();
        });
        number11.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*6) - 6 +3;
            int id = commodities.get(page).getBasketId();
            int currentNum = number11ValueFactory.getValue();
            String sql = "UPDATE Baskets SET number = ? WHERE basketId = ?";
            PreparedStatement pstmt = null;
           try {
               pstmt = connection.prepareStatement(sql);
               pstmt.setInt(1, currentNum);
               pstmt.setInt(2, id);
               pstmt.executeUpdate();
           }catch (SQLException e){
               throw new RuntimeException(e);
           }
            commodities.get(page).setNumber(currentNum);
            double thisPrice = Double.parseDouble(commodities.get(page).getPrice()) * commodities.get(page).getNumber();
            price11.setText(String.valueOf(thisPrice));
            calculateTotalPrice();
        });
        number02.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*6) - 6 +4;
            int id = commodities.get(page).getBasketId();
            int currentNum = number02ValueFactory.getValue();
            String sql = "UPDATE Baskets SET number = ? WHERE basketId = ?";
            PreparedStatement pstmt = null;
           try {
               pstmt = connection.prepareStatement(sql);
               pstmt.setInt(1, currentNum);
               pstmt.setInt(2, id);
               pstmt.executeUpdate();
           }catch (SQLException e){
               throw new RuntimeException(e);
           }
            commodities.get(page).setNumber(currentNum);
            double thisPrice = Double.parseDouble(commodities.get(page).getPrice()) * commodities.get(page).getNumber();
            price02.setText(String.valueOf(thisPrice));
            calculateTotalPrice();
        });
        number12.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*6) - 6 +5;
            int id = commodities.get(page).getBasketId();
            int currentNum = number12ValueFactory.getValue();
            String sql = "UPDATE Baskets SET number = ? WHERE basketId = ?";
            PreparedStatement pstmt = null;
         try {
             pstmt = connection.prepareStatement(sql);
             pstmt.setInt(1, currentNum);
             pstmt.setInt(2, id);
             pstmt.executeUpdate();
         }catch (SQLException e){
             throw new RuntimeException(e);
         }
            commodities.get(page).setNumber(currentNum);
            double thisPrice = Double.parseDouble(commodities.get(page).getPrice()) * commodities.get(page).getNumber();
            price12.setText(String.valueOf(thisPrice));
            calculateTotalPrice();
        });
    }
    private void calculateTotalPrice(){
        totalPrice = 0;
        for (int i = 0; i < commodities.size(); i++) {
            double x = Double.parseDouble(commodities.get(i).getPrice()) * commodities.get(i).getNumber();
            totalPrice += x;
        }
        totalPriceLabel.setText(String.valueOf(totalPrice));
    }
    @FXML
    private void goToNextPageButtonOnAction(ActionEvent event){
        goToPreviousPageButton.setVisible(true);
        int pageNum = Integer.parseInt(page.getText());
        pageNum++;
        showAnchorPanes(pageNum);
        page.setText(String.valueOf(pageNum));
        checkToVisibleNextPageButton();
    }
    @FXML
    private void goToPreviousPageButtonOnAction(){
        int pageNume = Integer.parseInt(page.getText());
        pageNume--;
        showAnchorPanes(pageNume);
        page.setText(String.valueOf(pageNume));
        checkToVisiblePreviousButton();
        checkToVisiblePreviousButton();
    }
    private void hideAnchorPanes(){
        anchorPane00.setVisible(false);
        anchorPane10.setVisible(false);
        anchorPane01.setVisible(false);
        anchorPane11.setVisible(false);
        anchorPane02.setVisible(false);
        anchorPane12.setVisible(false);
    }
    private void showAnchorPanes(int page){
        hideAnchorPanes();
        page = (page*6) - 6;
        if (page < commodities.size()){
            anchorPane00.setVisible(true);
            number00ValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,maxNumbers.get(page));
            number00ValueFactory.setValue(commodities.get(page).getNumber());
            number00.setValueFactory(number00ValueFactory);
            double price = Double.parseDouble(commodities.get(page).getPrice())*commodities.get(page).getNumber();
            String priceText = String.valueOf(price);
            price00.setText(priceText);
            title00.setText(commodities.get(page).getTitle());
            if (commodities.get(page).isAuction != 0) warning00.setVisible(true);
            else warning00.setVisible(false);
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane10.setVisible(true);
            number10ValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,maxNumbers.get(page));
            number10ValueFactory.setValue(commodities.get(page).getNumber());
            number10.setValueFactory(number10ValueFactory);
            double price = Double.parseDouble(commodities.get(page).getPrice())*commodities.get(page).getNumber();
            String priceText = String.valueOf(price);
            price10.setText(priceText);
            title10.setText(commodities.get(page).getTitle());
            if (commodities.get(page).isAuction != 0) warning10.setVisible(true);
            else warning10.setVisible(false);
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane01.setVisible(true);
            number01ValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,maxNumbers.get(page));
            number01ValueFactory.setValue(commodities.get(page).getNumber());
            number01.setValueFactory(number01ValueFactory);
            double price = Double.parseDouble(commodities.get(page).getPrice())*commodities.get(page).getNumber();
            String priceText = String.valueOf(price);
            price01.setText(priceText);
            title01.setText(commodities.get(page).getTitle());
            if (commodities.get(page).isAuction != 0) warning01.setVisible(true);
            else warning01.setVisible(false);
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane11.setVisible(true);
            number11ValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,maxNumbers.get(page));
            number11ValueFactory.setValue(commodities.get(page).getNumber());
            number11.setValueFactory(number11ValueFactory);
            double price = Double.parseDouble(commodities.get(page).getPrice())*commodities.get(page).getNumber();
            String priceText = String.valueOf(price);
            price11.setText(priceText);
            title11.setText(commodities.get(page).getTitle());
            if (commodities.get(page).isAuction != 0) warning11.setVisible(true);
            else warning11.setVisible(false);
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane02.setVisible(true);
            number02ValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,maxNumbers.get(page));
            number02ValueFactory.setValue(commodities.get(page).getNumber());
            number02.setValueFactory(number02ValueFactory);
            double price = Double.parseDouble(commodities.get(page).getPrice())*commodities.get(page).getNumber();
            String priceText = String.valueOf(price);
            price02.setText(priceText);
            title02.setText(commodities.get(page).getTitle());
            if (commodities.get(page).isAuction != 0) warning02.setVisible(true);
            else warning02.setVisible(false);
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane12.setVisible(true);
            number12ValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,maxNumbers.get(page));
            number12ValueFactory.setValue(commodities.get(page).getNumber());
            number12.setValueFactory(number12ValueFactory);
            double price = Double.parseDouble(commodities.get(page).getPrice())*commodities.get(page).getNumber();
            String priceText = String.valueOf(price);
            price12.setText(priceText);
            title12.setText(commodities.get(page).getTitle());
            if (commodities.get(page).isAuction != 0) warning12.setVisible(true);
            else warning12.setVisible(false);
        }
    }

    private void checkToVisibleNextPageButton(){
        int pageNum = Integer.parseInt(page.getText());
        pageNum++;
        pageNum = (pageNum * 14) - 14;
        if (pageNum >= commodities.size()){
            pageNum = (pageNum + 13)/14;
            page.setText(String.valueOf(pageNum));
            goToNextPageButton.setVisible(false);
        }else goToNextPageButton.setVisible(true);
    }
    private void checkToVisiblePreviousButton(){
        int pageNum = Integer.parseInt(page.getText());
        if (pageNum == 1) goToPreviousPageButton.setVisible(false);
        else goToPreviousPageButton.setVisible(true);
    }

    public void backToHome(ActionEvent event) throws IOException {
        new Login(user).loginToHome((Node) event.getSource());
    }
    public void setDelete00OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*6) - 6;
        String sql = "DELETE FROM Baskets WHERE basketId = " + commodities.get(page).getBasketId();
        commodities.remove(page);

            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);

        selectCommodities();
    }
    public void setDelete10OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*6) - 6+1;
        String sql = "DELETE FROM Baskets WHERE basketId = " + commodities.get(page).getBasketId();
        commodities.remove(page);

             stmt = connection.createStatement();
            stmt.executeUpdate(sql);

        selectCommodities();
    }
    public void setDelete01OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*6) - 6+2;
        String sql = "DELETE FROM Baskets WHERE basketId = " + commodities.get(page).getBasketId();
        commodities.remove(page);

             stmt = connection.createStatement();
            stmt.executeUpdate(sql);

        selectCommodities();
    }
    public void setDelete11OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*6) - 6+3;
        String sql = "DELETE FROM Baskets WHERE basketId = " + commodities.get(page).getBasketId();
        commodities.remove(page);

             stmt = connection.createStatement();
            stmt.executeUpdate(sql);

        selectCommodities();
    }
    public void setDelete02OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*6) - 6+4;
        String sql = "DELETE FROM Baskets WHERE basketId = " + commodities.get(page).getBasketId();
        commodities.remove(page);

             stmt = connection.createStatement();
            stmt.executeUpdate(sql);

        selectCommodities();
    }
    public void setDelete12OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*6) - 6+5;
        String sql = "DELETE FROM Baskets WHERE basketId = " + commodities.get(page).getBasketId();
        commodities.remove(page);

             stmt = connection.createStatement();
            stmt.executeUpdate(sql);

        selectCommodities();
    }

    public void submitOnAction(ActionEvent event){
        boolean isAllowedToSubmit = true;
        for (int i = 0; i < commodities.size(); i++) {
            if (commodities.get(i).isAuction != 0){
                isAllowedToSubmit = false;
                errorLabel.setText("some of your commodities are in the\n auction!\t\t\n delete them from your basket first.");
                break;
            }
        }
        if (isAllowedToSubmit){
            Node node = (Node) event.getSource();
            FXMLLoader loader = new FXMLLoader(Login.class.getResource("payment.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            PaymentController paymentController = loader.getController();
            paymentController.setUser(user);
            paymentController.setPricePaymentPage(totalPrice);
            paymentController.setCommoditiesPaymentPage(commodities);
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
        }
    }
    public void setCommoditiesBasketPage(ArrayList<Commodity> commodities){
        this.commodities.addAll(commodities);
    }

    public void setUser(User user)  {
        if (user == null){
            throw new NullPointerException();
        }else{
            this.user = user;
        }
        try {
            selectCommodities();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

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
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class productsManagingController implements Initializable {
    @FXML
    private Button goToNextPageButton;
    @FXML
    private Button goToPreviousPageButton;
    @FXML
    private Label page;
    @FXML
    private Label typeInfo;
    @FXML
    private AnchorPane anchorPane00;
    @FXML
    private AnchorPane  anchorPane10;
    @FXML
    private AnchorPane anchorPane20;
    @FXML
    private AnchorPane anchorPane30;
    @FXML
    private AnchorPane anchorPane40;
    @FXML
    private AnchorPane anchorPane50;
    @FXML
    private AnchorPane anchorPane60;
    @FXML
    private AnchorPane anchorPane01;
    @FXML
    private AnchorPane anchorPane11;
    @FXML
    private AnchorPane anchorPane21;
    @FXML
    private AnchorPane anchorPane31;
    @FXML
    private AnchorPane anchorPane41;
    @FXML
    private AnchorPane anchorPane51;
    @FXML
    private AnchorPane anchorPane61;
    @FXML
    private Label title00;
    @FXML
    private Label title10;
    @FXML
    private Label title20;
    @FXML
    private Label title30;
    @FXML
    private Label title40;
    @FXML
    private Label title50;
    @FXML
    private Label title60;
    @FXML
    private Label title01;
    @FXML
    private Label title11;
    @FXML
    private Label title21;
    @FXML
    private Label title31;
    @FXML
    private Label title41;
    @FXML
    private Label title51;
    @FXML
    private Label title61;
    @FXML
    private Spinner<Integer> number00,number10,number20,number30,number40,number50
            ,number60,number01,number11,number21,number31,number41,number51,number61;
    @FXML
    private TextField price00,price10,price20,price30,price40,price50,price60,price01,price11,price21,price31,price41,price51,price61;
    SpinnerValueFactory<Integer> numberValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
    SpinnerValueFactory<Integer> numberValueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
    SpinnerValueFactory<Integer> numberValueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
    SpinnerValueFactory<Integer> numberValueFactory4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
    SpinnerValueFactory<Integer> numberValueFactory5 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
    SpinnerValueFactory<Integer> numberValueFactory6 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
    SpinnerValueFactory<Integer> numberValueFactory7 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
    SpinnerValueFactory<Integer> numberValueFactory8 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
    SpinnerValueFactory<Integer> numberValueFactory9 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
    SpinnerValueFactory<Integer> numberValueFactory10 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
    SpinnerValueFactory<Integer> numberValueFactory11 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
    SpinnerValueFactory<Integer> numberValueFactory12 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
    SpinnerValueFactory<Integer> numberValueFactory13 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
    SpinnerValueFactory<Integer> numberValueFactory14 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
    Connection connection;
    Statement stmt;
    ResultSet rs;
    private User user;
    private User user2;
    private ArrayList<Commodity> commodities = new ArrayList<>();

    private void hideAnchorPanes(){
        anchorPane00.setVisible(false);
        anchorPane10.setVisible(false);
        anchorPane20.setVisible(false);
        anchorPane30.setVisible(false);
        anchorPane40.setVisible(false);
        anchorPane50.setVisible(false);
        anchorPane60.setVisible(false);
        anchorPane01.setVisible(false);
        anchorPane11.setVisible(false);
        anchorPane21.setVisible(false);
        anchorPane31.setVisible(false);
        anchorPane41.setVisible(false);
        anchorPane51.setVisible(false);
        anchorPane61.setVisible(false);
    }

    private void showAnchorPanes(int page){
        hideAnchorPanes();
        page = page*14 - 14;
        if (page < commodities.size()){
            anchorPane00.setVisible(true);
            title00.setText(commodities.get(page).getTitle());
            price00.setText(commodities.get(page).getPrice());
            numberValueFactory.setValue(commodities.get(page).getNumber());
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane10.setVisible(true);
            title10.setText(commodities.get(page).getTitle());
            price10.setText(commodities.get(page).getPrice());
            numberValueFactory2.setValue(commodities.get(page).getNumber());
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane20.setVisible(true);
            title20.setText(commodities.get(page).getTitle());
            price20.setText(commodities.get(page).getPrice());
            numberValueFactory3.setValue(commodities.get(page).getNumber());
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane30.setVisible(true);
            title30.setText(commodities.get(page).getTitle());
            price30.setText(commodities.get(page).getPrice());
            numberValueFactory4.setValue(commodities.get(page).getNumber());
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane40.setVisible(true);
            title40.setText(commodities.get(page).getTitle());
            price40.setText(commodities.get(page).getPrice());
            numberValueFactory5.setValue(commodities.get(page).getNumber());
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane50.setVisible(true);
            title50.setText(commodities.get(page).getTitle());
            price50.setText(commodities.get(page).getPrice());
            numberValueFactory6.setValue(commodities.get(page).getNumber());
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane60.setVisible(true);
            title60.setText(commodities.get(page).getTitle());
            price60.setText(commodities.get(page).getPrice());
            numberValueFactory7.setValue(commodities.get(page).getNumber());
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane01.setVisible(true);
            title01.setText(commodities.get(page).getTitle());
            price01.setText(commodities.get(page).getPrice());
            numberValueFactory8.setValue(commodities.get(page).getNumber());
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane11.setVisible(true);
            title11.setText(commodities.get(page).getTitle());
            price11.setText(commodities.get(page).getPrice());
            numberValueFactory9.setValue(commodities.get(page).getNumber());
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane21.setVisible(true);
            title21.setText(commodities.get(page).getTitle());
            price21.setText(commodities.get(page).getPrice());
            numberValueFactory10.setValue(commodities.get(page).getNumber());
            page++;
        }else  return;
        if (page < commodities.size()){
            anchorPane31.setVisible(true);
            title31.setText(commodities.get(page).getTitle());
            price31.setText(commodities.get(page).getPrice());
            numberValueFactory11.setValue(commodities.get(page).getNumber());
            page++;
        }else  return;
        if (page < commodities.size()){
            anchorPane41.setVisible(true);
            title41.setText(commodities.get(page).getTitle());
            price41.setText(commodities.get(page).getPrice());
            numberValueFactory12.setValue(commodities.get(page).getNumber());
            page++;
        }else  return;
        if (page < commodities.size()){
            anchorPane51.setVisible(true);
            title51.setText(commodities.get(page).getTitle());
            price51.setText(commodities.get(page).getPrice());
            numberValueFactory13.setValue(commodities.get(page).getNumber());
            page++;
        }else  return;
        if (page < commodities.size()){
            anchorPane61.setVisible(true);
            title61.setText(commodities.get(page).getTitle());
            price61.setText(commodities.get(page).getPrice());
            numberValueFactory14.setValue(commodities.get(page).getNumber());
        }
    }

    private void checkToVisibleNextButton(){
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
        goToPreviousPageButton.setVisible(pageNum != 1);
    }


    @FXML
    private void goToNextPageButtonOnAction(ActionEvent event){
        goToPreviousPageButton.setVisible(true);
        int pageNum = Integer.parseInt(page.getText());
        pageNum++;
        showAnchorPanes(pageNum);
        page.setText(String.valueOf(pageNum));
        checkToVisibleNextButton();
    }
    @FXML
    private void goToPreviousPageButtonOnAction(){
        int pageNume = Integer.parseInt(page.getText());
        pageNume--;
        showAnchorPanes(pageNume);
        page.setText(String.valueOf(pageNume));
        checkToVisiblePreviousButton();
        checkToVisibleNextButton();
    }

    public void setUser(User user) {
        if (user == null) {
            throw new NullPointerException("User is null");
        }
        this.user = user;
        typeInfo.setText(user.getUsername());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DatabaseConnectionJDBC databaseConnectionJDBC = new DatabaseConnectionJDBC();
            connection = databaseConnectionJDBC.getConnection();
            stmt = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        number00.setValueFactory(numberValueFactory);
        number10.setValueFactory(numberValueFactory2);
        number20.setValueFactory(numberValueFactory3);
        number30.setValueFactory(numberValueFactory4);
        number40.setValueFactory(numberValueFactory5);
        number50.setValueFactory(numberValueFactory6);
        number60.setValueFactory(numberValueFactory7);
        number01.setValueFactory(numberValueFactory8);
        number11.setValueFactory(numberValueFactory9);
        number21.setValueFactory(numberValueFactory10);
        number31.setValueFactory(numberValueFactory11);
        number41.setValueFactory(numberValueFactory12);
        number51.setValueFactory(numberValueFactory13);
        number61.setValueFactory(numberValueFactory14);

//        number00.valueProperty().addListener((observableValue, oldValue, newValue) -> {
//            int page = Integer.parseInt(this.page.getText());
//            page = (page*14) - 14;
//            int id = commodities.get(page).getCommodityId();
////            String sql = "UPDATE AllCommodities SET Number = "+ newValue + " WHERE commodityId = " + id ;
////            try {
////               stmt.executeUpdate(sql);
////            } catch (SQLException e) {
////                throw new RuntimeException(e);
////            }
////            while (true){
////                try {
////                    if (!rs.next()) break;
////                } catch (SQLException e) {
////                    throw new RuntimeException(e);
////                }
////
////            }
//            String sql = "UPDATE AllCommodities SET Number = ? WHERE commodityId = ?";
//            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
//                pstmt.setInt(1, newValue);
//                pstmt.setInt(2, id);
//                pstmt.executeUpdate();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        number10.valueProperty().addListener((observableValue, oldValue, newValue) -> {
//            int page = Integer.parseInt(this.page.getText());
//            page = (page*14) - 14 + 1;
//            int id = commodities.get(page).getCommodityId();
//            String sql = "UPDATE AllCommodities SET Number = "+ newValue + " WHERE commodityId = " + id;
//            try {
//                stmt.executeQuery(sql);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        number20.valueProperty().addListener((observableValue, oldValue, newValue) -> {
//            int page = Integer.parseInt(this.page.getText());
//            page = (page*14) - 14 + 2;
//            int id = commodities.get(page).getCommodityId();
//            String sql = "UPDATE AllCommodities SET Number = "+ newValue + " WHERE commodityId = " + id;
//            try {
//                stmt.executeQuery(sql);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        number30.valueProperty().addListener((observableValue, oldValue, newValue) -> {
//            int page = Integer.parseInt(this.page.getText());
//            page = (page*14) - 14+3;
//            int id = commodities.get(page).getCommodityId();
//            String sql = "UPDATE AllCommodities SET Number = "+ newValue + " WHERE commodityId = " + id;
//            try {
//                stmt.executeQuery(sql);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        number40.valueProperty().addListener((observableValue, oldValue, newValue) -> {
//            int page = Integer.parseInt(this.page.getText());
//            page = (page*14) - 14 +4 ;
//            int id = commodities.get(page).getCommodityId();
//            String sql = "UPDATE AllCommodities SET Number = "+ newValue + " WHERE commodityId = " + id;
//            try {
//                stmt.executeQuery(sql);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        number50.valueProperty().addListener((observableValue, oldValue, newValue) -> {
//            int page = Integer.parseInt(this.page.getText());
//            page = (page*14) - 14+5;
//            int id = commodities.get(page).getCommodityId();
//            String sql = "UPDATE AllCommodities SET Number = "+ newValue + " WHERE commodityId = " + id;
//            try {
//                stmt.executeQuery(sql);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        number60.valueProperty().addListener((observableValue, oldValue, newValue) -> {
//            int page = Integer.parseInt(this.page.getText());
//            page = (page*14) - 14+6;
//            int id = commodities.get(page).getCommodityId();
//            String sql = "UPDATE AllCommodities SET Number = "+ newValue + " WHERE commodityId = " + id;
//            try {
//                stmt.executeQuery(sql);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        number01.valueProperty().addListener((observableValue, oldValue, newValue) -> {
//            int page = Integer.parseInt(this.page.getText());
//            page = (page*14) - 14+7;
//            int id = commodities.get(page).getCommodityId();
//            String sql = "UPDATE AllCommodities SET Number = "+ newValue + " WHERE commodityId = " + id;
//            try {
//                stmt.executeQuery(sql);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        number11.valueProperty().addListener((observableValue, oldValue, newValue) -> {
//            int page = Integer.parseInt(this.page.getText());
//            page = (page*14) - 14+8;
//            int id = commodities.get(page).getCommodityId();
//            String sql = "UPDATE AllCommodities SET Number = "+ newValue + " WHERE commodityId = " + id;
//            try {
//                stmt.executeQuery(sql);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        number21.valueProperty().addListener((observableValue, oldValue, newValue) -> {
//            int page = Integer.parseInt(this.page.getText());
//            page = (page*14) - 14+9;
//            int id = commodities.get(page).getCommodityId();
//            String sql = "UPDATE AllCommodities SET Number = "+ newValue + " WHERE commodityId = " + id;
//            try {
//                stmt.executeQuery(sql);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        number31.valueProperty().addListener((observableValue, oldValue, newValue) -> {
//            int page = Integer.parseInt(this.page.getText());
//            page = (page*14) - 14+10;
//            int id = commodities.get(page).getCommodityId();
//            String sql = "UPDATE AllCommodities SET Number = "+ newValue + " WHERE commodityId = " + id;
//            try {
//                stmt.executeQuery(sql);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        number41.valueProperty().addListener((observableValue, oldValue, newValue) -> {
//            int page = Integer.parseInt(this.page.getText());
//            page = (page*14) - 14+11;
//            int id = commodities.get(page).getCommodityId();
//            String sql = "UPDATE AllCommodities SET Number = "+ newValue + " WHERE commodityId = " + id;
//            try {
//                stmt.executeQuery(sql);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        number51.valueProperty().addListener((observableValue, oldValue, newValue) -> {
//            int page = Integer.parseInt(this.page.getText());
//            page = (page*14) - 14+12;
//            int id = commodities.get(page).getCommodityId();
//            String sql = "UPDATE AllCommodities SET Number = "+ newValue + " WHERE commodityId = " + id;
//            try {
//                stmt.executeQuery(sql);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        number61.valueProperty().addListener((observableValue, oldValue, newValue) -> {
//            int page = Integer.parseInt(this.page.getText());
//            page = (page*14) - 14+13;
//            int id = commodities.get(page).getCommodityId();
//            String sql = "UPDATE AllCommodities SET Number = "+ newValue + " WHERE commodityId = " + id;
//            try {
//                stmt.executeQuery(sql);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        });

        selectCommodities();

    }

    public void selectCommodities(){

        System.out.println(user);

//        String sql = "SELECT * FROM AllCommodities WHERE userName = " + "'" + user.getUsername() + "'";
            String sql  = "SELECT * FROM AllCommodities";
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        commodities.clear();
        while (true){
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            int number1;
            String type1;
            String brand1;
            String price1;
            String ratio1;
            String title1;
            int commodityId;
            try {
                number1 = rs.getInt("Number");
                type1 = rs.getString("Type");
                brand1 = rs.getString("Brand");
                price1 = rs.getString("Price");
                ratio1 = rs.getString("Ratio");
                title1 = rs.getString("Title");
                commodityId = rs.getInt("commodityId");
                System.out.println("ID = " + commodityId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            commodities.add(new Commodity(type1,brand1,price1,ratio1,title1,number1,commodityId));

        }

        showAnchorPanes(1);
        checkToVisibleNextButton();
        checkToVisiblePreviousButton();
        try {
            rs.close();
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        number00.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14;
            int id  = commodities.get(page).getCommodityId();
            String sql2 = "UPDATE AllCommodities SET Number = ? WHERE commodityId = ?";
            PreparedStatement pstmt = null;
            try {
                pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1,numberValueFactory.getValue());
                pstmt.setInt(2,id);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        number10.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14+1;
            int id  = commodities.get(page).getCommodityId();
            String sql2 = "UPDATE AllCommodities SET Number = ? WHERE commodityId = ?";
            try {
                PreparedStatement pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, numberValueFactory2.getValue());
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException();
            }
        });
        number20.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14+2;
            int id  = commodities.get(page).getCommodityId();
            String sql2 = "UPDATE AllCommodities SET Number = ? WHERE commodityId = ?";
            try {
                PreparedStatement pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, numberValueFactory3.getValue());
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException();
            }
        });
        number30.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14+3;
            int id  = commodities.get(page).getCommodityId();
            String sql2 = "UPDATE AllCommodities SET Number = ? WHERE commodityId = ?";
            try {
                PreparedStatement pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, numberValueFactory4.getValue());
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException();
            }
        });
        number40.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14+4;
            int id  = commodities.get(page).getCommodityId();
            String sql2 = "UPDATE AllCommodities SET Number = ? WHERE commodityId = ?";
            try {
                PreparedStatement pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, numberValueFactory5.getValue());
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException();
            }
        });
        number50.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14+5;
            int id  = commodities.get(page).getCommodityId();
            String sql2 = "UPDATE AllCommodities SET Number = ? WHERE commodityId = ?";
            try {
                PreparedStatement pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, numberValueFactory6.getValue());
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException();
            }
        });
        number60.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14+6;
            int id  = commodities.get(page).getCommodityId();
            String sql2 = "UPDATE AllCommodities SET Number = ? WHERE commodityId = ?";
            try {
                PreparedStatement pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, numberValueFactory7.getValue());
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException();
            }
        });
        number01.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14+7;
            int id  = commodities.get(page).getCommodityId();
            String sql2 = "UPDATE AllCommodities SET Number = ? WHERE commodityId = ?";
            try {
                PreparedStatement pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, numberValueFactory8.getValue());
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException();
            }
        });
        number11.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14+8;
            int id  = commodities.get(page).getCommodityId();
            String sql2 = "UPDATE AllCommodities SET Number = ? WHERE commodityId = ?";
            try {
                PreparedStatement pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, numberValueFactory9.getValue());
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException();
            }
        });
        number21.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14+9;
            int id  = commodities.get(page).getCommodityId();
            String sql2 = "UPDATE AllCommodities SET Number = ? WHERE commodityId = ?";
            try {
                PreparedStatement pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, numberValueFactory10.getValue());
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException();
            }
        });
        number31.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14+10;
            int id  = commodities.get(page).getCommodityId();
            String sql2 = "UPDATE AllCommodities SET Number = ? WHERE commodityId = ?";
            try {
                PreparedStatement pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, numberValueFactory11.getValue());
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException();
            }
        });
        number41.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14+11;
            int id  = commodities.get(page).getCommodityId();
            String sql2 = "UPDATE AllCommodities SET Number = ? WHERE commodityId = ?";
            try {
                PreparedStatement pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, numberValueFactory12.getValue());
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException();
            }
        });
        number51.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14+12;
            int id  = commodities.get(page).getCommodityId();
            String sql2 = "UPDATE AllCommodities SET Number = ? WHERE commodityId = ?";
            try {
                PreparedStatement pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, numberValueFactory13.getValue());
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException();
            }
        });
        number61.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14+13;
            int id  = commodities.get(page).getCommodityId();
            String sql2 = "UPDATE AllCommodities SET Number = ? WHERE commodityId = ?";
            try {
                PreparedStatement pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, numberValueFactory14.getValue());
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException();
            }
        });
    }

    public void setDelete00OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14;
//        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'"+commodities.get(page).getType() + "'";
        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'" + commodities.get(page).getType() + "';";
        stmt.executeUpdate(sql);
        selectCommodities();
    }
    public void setDelete10OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 1;
        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'"+commodities.get(page).getType() + "';";
        rs = stmt.executeQuery(sql);
        selectCommodities();
    }
    public void setDelete20OnAction() throws SQLException{
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 2;
        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'"+commodities.get(page).getType() + "';";
        rs = stmt.executeQuery(sql);
        selectCommodities();
    }
    public void setDelete30OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14+3;
        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'"+commodities.get(page).getType() + "';";
        rs = stmt.executeQuery(sql);
        selectCommodities();
    }
    public void setDelete40OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 4;
        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'"+commodities.get(page).getType() + "';";
        rs = stmt.executeQuery(sql);
        selectCommodities();
    }
    public void setDelete50OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 5;
        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'"+commodities.get(page).getType() + "';";
        rs = stmt.executeQuery(sql);
        selectCommodities();
    }
    public void setDelete60OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 6;
        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'"+commodities.get(page).getType() + "';";
        rs = stmt.executeQuery(sql);
        selectCommodities();
    }
    public void setDelete01OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 7;
        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'"+commodities.get(page).getType() + "';";
        rs = stmt.executeQuery(sql);
        selectCommodities();
    }
    public void setDelete11OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 8;
        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'"+commodities.get(page).getType() + "';";
        rs = stmt.executeQuery(sql);
        selectCommodities();
    }
    public void setDelete21OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 9;
        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'"+commodities.get(page).getType() + "';";
        rs = stmt.executeQuery(sql);
        selectCommodities();
    }
    public void setDelete31OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 10;
        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'"+commodities.get(page).getType() + "';";
        rs = stmt.executeQuery(sql);
        selectCommodities();
    }
    public void setDelete41OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 11;
        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'"+commodities.get(page).getType() + "';";
        rs = stmt.executeQuery(sql);
        selectCommodities();
    }
    public void setDelete51OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 12;
        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'"+commodities.get(page).getType() + "';";
        rs = stmt.executeQuery(sql);
        selectCommodities();
    }
    public void setDelete61OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 13;
        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'"+commodities.get(page).getType() + "';";
        rs = stmt.executeQuery(sql);
        selectCommodities();
    }

    public void setBackToHomeButtonOnAction(ActionEvent event) throws IOException {
        new Login(user).loginToHome((Node) event.getSource());
    }

    public void setRecordButtonOnAction(ActionEvent event) throws IOException {
//        int page = Integer.parseInt(this.page.getText());
//        page = (page*14) - 14;
//
//        int id = commodities.get(page).getCommodityId();
//        String sql = "UPDATE AllCommodities SET Number = "+ numberValueFactory.getValue() + " WHERE commodityId = " + id +";";
//               stmt.executeUpdate(sql);

//            String sql2 = "UPDATE AllCommodities SET Price = '" + price00.getText() + "' WHERE commodityId = " + id + ";";
//            stmt.executeUpdate(sql2);

//        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
//        PreparedStatement pstmt = connection.prepareStatement(sql2);
//        pstmt.setString(1, price00.getText());
//        pstmt.setInt(2, id);
//        pstmt.executeUpdate();
        new Login(user).loginToHome((Node) event.getSource());
    }
    public void setPrice00OnTextChanged() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14;

        int id = commodities.get(page).getCommodityId();
        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql2);
        pstmt.setString(1, price00.getText());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
    public void setPrice10OnTextChanged() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14+1;

        int id = commodities.get(page).getCommodityId();
        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql2);
        pstmt.setString(1, price10.getText());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
    public void setPrice20OnTextChanged() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14+2;

        int id = commodities.get(page).getCommodityId();
        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql2);
        pstmt.setString(1, price20.getText());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
    public void setPrice30OnTextChanged() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14+3;

        int id = commodities.get(page).getCommodityId();
        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql2);
        pstmt.setString(1, price30.getText());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
    public void setPrice40OnTextChanged() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14+4;

        int id = commodities.get(page).getCommodityId();
        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql2);
        pstmt.setString(1, price40.getText());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
    public void setPrice50OnTextChanged() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14+5;

        int id = commodities.get(page).getCommodityId();
        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql2);
        pstmt.setString(1, price50.getText());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
    public void setPrice60OnTextChanged() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14+6;

        int id = commodities.get(page).getCommodityId();
        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql2);
        pstmt.setString(1, price60.getText());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
    public void setPrice01OnTextChanged() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14+7;

        int id = commodities.get(page).getCommodityId();
        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql2);
        pstmt.setString(1, price01.getText());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
    public void setPrice11OnTextChanged() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14+8;

        int id = commodities.get(page).getCommodityId();
        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql2);
        pstmt.setString(1, price11.getText());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
    public void setPrice21OnTextChanged() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14+9;

        int id = commodities.get(page).getCommodityId();
        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql2);
        pstmt.setString(1, price21.getText());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
    public void setPrice31OnTextChanged() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14+10;

        int id = commodities.get(page).getCommodityId();
        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql2);
        pstmt.setString(1, price31.getText());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
    public void setPrice41OnTextChanged() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14+11;

        int id = commodities.get(page).getCommodityId();
        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql2);
        pstmt.setString(1, price41.getText());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
    public void setPrice51OnTextChanged() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14+12;

        int id = commodities.get(page).getCommodityId();
        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql2);
        pstmt.setString(1, price51.getText());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
    public void setPrice61OnTextChanged() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14+13;

        int id = commodities.get(page).getCommodityId();
        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql2);
        pstmt.setString(1, price61.getText());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }

}

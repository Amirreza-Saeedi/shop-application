package com.example.shopapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TestController implements Initializable {
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
    Connection connection;
    Statement stmt;
    ResultSet rs;
    private User user;
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
            number00.setPromptText(String.valueOf(commodities.get(page).getNumber()));
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane10.setVisible(true);
            title10.setText(commodities.get(page).getTitle());
            price10.setText(commodities.get(page).getPrice());
            number10.setPromptText(String.valueOf(commodities.get(page).getNumber()));
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane20.setVisible(true);
            title20.setText(commodities.get(page).getTitle());
            price20.setText(commodities.get(page).getPrice());
            number20.setPromptText(String.valueOf(commodities.get(page).getNumber()));
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane30.setVisible(true);
            title30.setText(commodities.get(page).getTitle());
            price30.setText(commodities.get(page).getPrice());
            number30.setPromptText(String.valueOf(commodities.get(page).getNumber()));
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane40.setVisible(true);
            title40.setText(commodities.get(page).getTitle());
            price40.setText(commodities.get(page).getPrice());
            number40.setPromptText(String.valueOf(commodities.get(page).getNumber()));
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane50.setVisible(true);
            title50.setText(commodities.get(page).getTitle());
            price50.setText(commodities.get(page).getPrice());
            number50.setPromptText(String.valueOf(commodities.get(page).getNumber()));
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane60.setVisible(true);
            title60.setText(commodities.get(page).getTitle());
            price60.setText(commodities.get(page).getPrice());
            number60.setPromptText(String.valueOf(commodities.get(page).getNumber()));
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane01.setVisible(true);
            title01.setText(commodities.get(page).getTitle());
            price01.setText(commodities.get(page).getPrice());
            number01.setPromptText(String.valueOf(commodities.get(page).getNumber()));
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane11.setVisible(true);
            title11.setText(commodities.get(page).getTitle());
            price11.setText(commodities.get(page).getPrice());
            number11.setPromptText(String.valueOf(commodities.get(page).getNumber()));
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane21.setVisible(true);
            title21.setText(commodities.get(page).getTitle());
            price21.setText(commodities.get(page).getPrice());
            number21.setPromptText(String.valueOf(commodities.get(page).getNumber()));
            page++;
        }else  return;
        if (page < commodities.size()){
            anchorPane31.setVisible(true);
            title31.setText(commodities.get(page).getTitle());
            price31.setText(commodities.get(page).getPrice());
            number31.setPromptText(String.valueOf(commodities.get(page).getNumber()));
            page++;
        }else  return;
        if (page < commodities.size()){
            anchorPane41.setVisible(true);
            title41.setText(commodities.get(page).getTitle());
            price41.setText(commodities.get(page).getPrice());
            number41.setPromptText(String.valueOf(commodities.get(page).getNumber()));
            page++;
        }else  return;
        if (page < commodities.size()){
            anchorPane51.setVisible(true);
            title51.setText(commodities.get(page).getTitle());
            price51.setText(commodities.get(page).getPrice());
            number51.setPromptText(String.valueOf(commodities.get(page).getNumber()));
            page++;
        }else  return;
        if (page < commodities.size()){
            anchorPane61.setVisible(true);
            title61.setText(commodities.get(page).getTitle());
            price61.setText(commodities.get(page).getPrice());
            number61.setPromptText(String.valueOf(commodities.get(page).getNumber()));
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

    public void setBackToHomeButtonOnAction(ActionEvent event) throws IOException {
        new Login(user).loginToHome((Node) event.getSource());

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
        SpinnerValueFactory<Integer> numberValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
        number00.setValueFactory(numberValueFactory);
        number10.setValueFactory(numberValueFactory);
        number20.setValueFactory(numberValueFactory);
        number30.setValueFactory(numberValueFactory);
        number40.setValueFactory(numberValueFactory);
        number50.setValueFactory(numberValueFactory);
        number60.setValueFactory(numberValueFactory);
        number01.setValueFactory(numberValueFactory);
        number21.setValueFactory(numberValueFactory);
        number31.setValueFactory(numberValueFactory);
        number41.setValueFactory(numberValueFactory);
        number51.setValueFactory(numberValueFactory);
        number61.setValueFactory(numberValueFactory);

        selectCommodities();

    }

    public void selectCommodities(){

        String sql = "SELECT * FROM AllCommodities WHERE userName = " + "'" + user.getUsername() + "'";
//            String sql  = "SELECT * FROM AllCommodities";
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
                commodityId = rs.getInt("commodity-id");
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
    }

    public void setDelete00OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14;
        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'"+commodities.get(page).getType() + "'";
        rs = stmt.executeQuery(sql);
        selectCommodities();
    }
    public void setDelete10OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 1;
        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'"+commodities.get(page).getType() + "'";
        rs = stmt.executeQuery(sql);
        selectCommodities();
    }
    public void setDelete20OnAction() throws SQLException{
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 2;
        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'"+commodities.get(page).getType() + "'";
        rs = stmt.executeQuery(sql);
        selectCommodities();
    }
    public void setDelete30OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14+3;
        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'"+commodities.get(page).getType() + "'";
        rs = stmt.executeQuery(sql);
        selectCommodities();
    }
    public void setDelete40OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 4;
        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'"+commodities.get(page).getType() + "'";
        rs = stmt.executeQuery(sql);
        selectCommodities();
    }
    public void setDelete50OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 5;
        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'"+commodities.get(page).getType() + "'";
        rs = stmt.executeQuery(sql);
        selectCommodities();
    }
    public void setDelete60OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 6;
        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'"+commodities.get(page).getType() + "'";
        rs = stmt.executeQuery(sql);
        selectCommodities();
    }
    public void setDelete01OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 7;
        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'"+commodities.get(page).getType() + "'";
        rs = stmt.executeQuery(sql);
        selectCommodities();
    }
    public void setDelete11OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 8;
        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'"+commodities.get(page).getType() + "'";
        rs = stmt.executeQuery(sql);
        selectCommodities();
    }
    public void setDelete21OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 9;
        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'"+commodities.get(page).getType() + "'";
        rs = stmt.executeQuery(sql);
        selectCommodities();
    }
    public void setDelete31OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 10;
        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'"+commodities.get(page).getType() + "'";
        rs = stmt.executeQuery(sql);
        selectCommodities();
    }
    public void setDelete41OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 11;
        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'"+commodities.get(page).getType() + "'";
        rs = stmt.executeQuery(sql);
        selectCommodities();
    }
    public void setDelete51OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 12;
        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'"+commodities.get(page).getType() + "'";
        rs = stmt.executeQuery(sql);
        selectCommodities();
    }
    public void setDelete61OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 13;
        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'"+commodities.get(page).getType() + "'";
        rs = stmt.executeQuery(sql);
        selectCommodities();
    }
}


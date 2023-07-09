package com.example.shopapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class chartController {
    @FXML
    private BarChart chart;
    @FXML
    private TextField userNameTextField;
    private User user;

    public void showUserChart(){
        chart.getData().clear();

        XYChart.Series income = new XYChart.Series();
        XYChart.Series cost = new XYChart.Series();


        income.setName("Income"); //this will display as legend
        cost.setName("Cost");
        try(Connection connection = new DatabaseConnectionJDBC().getConnection()){
            String sql = "SELECT * FROM sellersChart WHERE userName = '" + userNameTextField.getText() + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                String date = rs.getString("date");
                double incomeCash = Double.parseDouble(rs.getString("income"));
                income.getData().add(new XYChart.Data(date,incomeCash));
                double costCash = Double.parseDouble(rs.getString("cost"));
                cost.getData().add(new XYChart.Data(date,costCash));
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

        //adding series to chart
        chart.getData().addAll(income,cost);
    }

    public void backToHome(ActionEvent event){
        Node node = (Node) event.getSource();
        FXMLLoader loader = new FXMLLoader(Login.class.getResource("Home.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HomeController homeController = loader.getController();
        homeController.setUser(user);
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
    }
    public void setUser(User user){
        if (user == null){
            throw new NullPointerException("User is null");
        }
        this.user = user;
    }
}

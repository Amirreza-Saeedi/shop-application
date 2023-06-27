package com.example.shopapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Objects;
import java.sql.*;


public class Main extends Application {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        launch(args);
    }
    @Override
    public void start(Stage stage)  {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            System.out.println("YEEESssssssssssssss");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Kala Mala");
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

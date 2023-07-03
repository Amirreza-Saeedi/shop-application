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
            AppWorkflow.username = "reza";
            AppWorkflow.connection = new DatabaseConnectionJDBC().getConnection();
//            Parent root = FXMLLoader.load(getClass().getResource("basket.fxml"));
//            Parent root = FXMLLoader.load(getClass().getResource("production-page.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
            System.out.println("YEEESssssssss");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.centerOnScreen();
            stage.setTitle("FoodFirst");
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }

        String str = "\n\n\nasd\n\n\nsdkfj\n\n\n\n\nksjd    fldsjfsjdf\n\n\n\n\n\n\n";
        System.out.println(str.trim());
    }
}

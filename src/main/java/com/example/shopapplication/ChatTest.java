package com.example.shopapplication;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatTest extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root1 = new FXMLLoader(getClass().getResource("chat.fxml")).load();
        Parent root2 = new FXMLLoader(getClass().getResource("chat.fxml")).load();
        Scene scene1 = new Scene(root1);
        Scene scene2 = new Scene(root2);

        // start server
        ChatServer server = new ChatServer();
        Admin admin = new Admin("admin", "admin");
        Seller seller = new Seller("asghar", "asghar");
        ChatController client1 = new ChatController(admin, seller);
        ChatController client2 = new ChatController(seller, admin);

        Stage stage1 = new Stage();
        stage1.setScene(scene1);
        stage1.setTitle("stage 1");
        stage1.setX(50);

        Stage stage2 = new Stage();
        stage2.setScene(scene2);
        stage2.setTitle("stage 2");
        stage2.setX(700);

        stage1.show();
        stage2.show();


    }
}

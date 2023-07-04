package com.example.shopapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ChatTest extends Application implements Runnable {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
//        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("chat.fxml"));
//        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("chat.fxml"));
//        Parent root1 = loader1.load();
//        Parent root2 = loader2.load();
//
//        // start server
//        Thread serverThread = new Thread(new ChatServer());
//        serverThread.setPriority(10);
//        serverThread.start();
//
//
//        Admin admin = new Admin("admin", "admin");
//        Seller seller = new Seller("asghar", "asghar");
//
//        ChatController client1Controller = loader1.getController();
//        client1Controller.setAll(admin, seller); // set all
//        Scene scene1 = new Scene(root1);
//        Stage stage1 = new Stage();
//        stage1.setScene(scene1);
//        stage1.setTitle("Chat with " + seller.getUsername());
//        stage1.setX(50);
//        stage1.setResizable(false);
//        stage1.initStyle(StageStyle.UNDECORATED);
//
//        ChatController client2Controller = loader2.getController();
//        client2Controller.setAll(seller, admin); // set all
//        Scene scene2 = new Scene(root2);
//        Stage stage2 = new Stage();
//        stage2.setScene(scene2);
//        stage2.setTitle("Chat with " + admin.getUsername());
//        stage2.setX(700);
//        stage2.setResizable(false);
//        stage2.initStyle(StageStyle.UNDECORATED);
//
//        Thread thread1 = new Thread(client1Controller);
//        Thread thread2 = new Thread(client2Controller);
//        thread1.start();
//        thread2.start();
//
//        stage1.show();
//        stage2.show();

        // server


        FXMLLoader loader = new FXMLLoader(getClass().getResource("chat.fxml"));
        Parent root = loader.load();

        ChatController controller = loader.getController();
    }

    @Override
    public void run() {

    }
}

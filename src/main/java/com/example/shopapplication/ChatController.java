package com.example.shopapplication;

import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ChatController implements Initializable {

    public ChatController(User sender, User receiver) throws Exception {
        // determine Admin & Seller
        Admin admin;
        Seller seller;
        if (sender instanceof Admin && receiver instanceof Seller) {
            admin = (Admin) sender;
            seller = (Seller) receiver;
        } else if (sender instanceof Seller && receiver instanceof Admin) {
            admin = (Admin) receiver;
            seller = (Seller) sender;
        } else {
            throw new Exception("just 1 admin and 1 seller is accepted");
        }

        System.out.println("ChatController.ChatController");
        System.out.println("sender = " + sender);
        System.out.println("receiver = " + receiver);
        System.out.println("seller = " + seller);
        System.out.println("admin = " + admin);

        
    }

    public ChatController() {
        System.out.println("ChatController.ChatController");
        System.out.println("no args");
    }


    public static void main(String[] args) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("ChatController.initialize");
    }
}

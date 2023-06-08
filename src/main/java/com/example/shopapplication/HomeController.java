package com.example.shopapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private ListView<String> groupingList;
    String[] commodity = {"Mobile" , "Digital","Home needs","Clothes","Super Market","Books and Stationery","Toys","Beauty","Sport","Tools","Car and Motorcycle"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        groupingList.getItems().addAll(commodity);

    }
    public void groupingListOnMouseClicked(ActionEvent e){
            String item = groupingList.getSelectionModel().getSelectedItem();
            switch (item){
                case "Mobile":
                    break;
                case "Digital":
                    break;
                case "Home needs":
                    break;
                case "Clothes":
                    break;
                case "Super Market":
                    break;
                case "Books and Stationery":
                    break;
                case "Toys":

                    break;
                case "Beauty":

                    break;
                case "Sport":

                    break;
                case "Tools":
                    break;
                case "Car and Motorcycle":
                    break;
            }

    }
}

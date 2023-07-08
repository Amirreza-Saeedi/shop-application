package com.example.shopapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChatListController implements Initializable {
    @FXML
    private ListView<Seller> listView;
    private ObservableList<Seller> sellers = FXCollections.observableArrayList();
    private final String ADMIN = "admin";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadSellers();
        setListViewAction();
        setListViewCells();
    }

    private void setListViewCells() {
        // to show seller username
        listView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Seller> call(ListView<Seller> param) {
                ListCell<Seller> cell = new ListCell<>() {
                    @Override
                    protected void updateItem(Seller seller, boolean empty) {
                        super.updateItem(seller, empty);
                        if (empty || seller == null) {
                            setText(null);
                        } else {
                            setText(seller.getUsername());
                        }
                    }
                };
                return cell;
            }
        });
    }

    private void setListViewAction() {
        listView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal != null && !newVal.equals(oldVal)) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("chat.fxml"));
                    Parent root = loader.load();
                    ChatController controller = loader.getController();
                    controller.setAll(newVal, ADMIN);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setResizable(false);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void loadSellers() {
        ArrayList<Seller> list = new ArrayList<>();

        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {

            Statement statement = connection.createStatement();
            String sql = "select * from Sellers order by username asc;";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                Seller seller = new Seller(username);
                list.add(seller);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        sellers.setAll(list);
        listView.setItems(sellers);
    }
}

package com.example.shopapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BasketController implements Initializable {
    public Button btn_submit;
    public Text totalPrice;
    public ListView list;

    private ObservableList<Commodity> observableList;

    public BasketController() {
        observableList = FXCollections.observableArrayList();
        String userQuery = "SELECT * FROM USERS WHERE username='" + AppWorkflow.username + "';";
        String commodityQuery = "SELECT * FROM AllCommodities";
        try {
            Statement stmt = AppWorkflow.connection.createStatement();
            ResultSet rs = stmt.executeQuery(userQuery);
            String basket_title = rs.getString("basket");
            var item_list = basket_title.split(",");
            rs = stmt.executeQuery(commodityQuery);


            ArrayList<Commodity> communist = new ArrayList<>();

            while (rs.next()) {
                for (int i = 0; i < item_list.length; i++) {
                    String item_name = item_list[i].split(":")[0];
                    int item_count = Integer.parseInt(item_list[i].split(":")[1]);
                    if (rs.getString("Title").contains(item_name)) {

                        Commodity c = new Commodity();
                        try{

                            c.image = new Image(new ByteArrayInputStream(rs.getBytes("image")));
                        }catch (Exception e){
//                            e.printStackTrace();
                            c.image = null;
                        }
                        c.title = rs.getString("Title");
                        c.number = item_count;
                        c.price = String.valueOf(rs.getInt("Price"));

//                        System.out.println(c);
                        communist.add(c);
                    }
                }

            }
            observableList.addAll(communist);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        list.setItems(observableList);
        list.setCellFactory(item_list -> new CustomCell());



    }
}

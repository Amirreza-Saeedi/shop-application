package com.example.shopapplication;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomCell extends ListCell<Commodity> implements Initializable {
    public ImageView image;
    public Text name;
    public Text price;
    public AnchorPane pane;
    public Text count;
    public Button btn_plus;
    public Button btn_minus;

    public CustomCell() {


    }

    private void loadFXML() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("customCell.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

//            loadFXML();
        }
        catch (Exception e){

        }
    }

    @Override
    protected void updateItem(Commodity commodity, boolean b) {
        super.updateItem(commodity, b);

        if(b || commodity == null || commodity.number <= 0) {
            setText(null);
            setGraphic(null);
        } else {


            FXMLLoader mLLoader = null;
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("customCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


            System.out.println(commodity);
//            if(commodity.image != null)
                image.setImage(new Image("E:\\Job\\Java Assignments\\shop-application-main\\src\\main\\resources\\bank.png"));
            price.setText(commodity.price * commodity.number + "");
            name.setText(commodity.title);
            count.setText(commodity.number + "");

           btn_minus.onMouseClickedProperty().setValue(new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent mouseEvent) {
                   commodity.number--;
                   updateItem(commodity,false);
               }
           });

           btn_plus.onMouseClickedProperty().setValue(new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent mouseEvent) {
                   commodity.number++;
                   updateItem(commodity,false);
               }
           });

            setText(null);
            setGraphic(pane);
        }
    }
}

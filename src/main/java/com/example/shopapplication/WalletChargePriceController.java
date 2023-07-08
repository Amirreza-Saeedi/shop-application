package com.example.shopapplication;

import com.example.shopapplication.regex.MyRegex;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WalletChargePriceController implements Initializable {

    @FXML
    private TextField price;

    private User user;
    private Stage lastStage;
    public void setLastStage(Stage stage){
        this.lastStage = stage;
    }
    public void goToPortalBankAndCloseLastStage(ActionEvent event){
        if (price.getText().length() == 0){
            price.setStyle("-fx-border-color: red;");
        }else {
            lastStage.close();
            goToBankPortal(event);
        }
    }
    void goToBankPortal(ActionEvent event) {

                Node node = (Node) event.getSource();
                FXMLLoader loader = new FXMLLoader(Login.class.getResource("portalPage.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                PortalPageController portalPageController = loader.getController();
                portalPageController.setUser(user);
                portalPageController.isWallet = true;
                portalPageController.setPricePortalPage(Double.parseDouble(price.getText()));
                Stage stage = (Stage) node.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.centerOnScreen();
    }

    public void setUser(User user){
        this.user = user;
        price.setText(String.valueOf(user.getCharge()));
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Pattern pattern = Pattern.compile(MyRegex.doubleRegex);
        price.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                Matcher matcher = pattern.matcher(newValue);
                if (!matcher.matches() && price.getText().length() > 0){
                    price.setText(oldValue);
                }
            }
        });
    }
}

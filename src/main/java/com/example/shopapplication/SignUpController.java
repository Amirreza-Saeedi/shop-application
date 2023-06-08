package com.example.shopapplication;

import com.example.shopapplication.LoginController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController extends Application implements Initializable {
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private PasswordField confirmationPasswordField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField companyTextField;
    @FXML
    private Label signUpMessageLabel;


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(SignUpController.class.getResource("sign-up-scene.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] items = {"User", "Seller"};
        comboBox.getItems().addAll(items);
    }

    @FXML
    private void switchVisibilityIcon() {

    }

    public void cancel(ActionEvent event) {
    }


    public void signUp(ActionEvent event) {
    }
}

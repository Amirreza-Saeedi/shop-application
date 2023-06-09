package com.example.shopapplication;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    private TextField passwordTextField;
    @FXML
    private PasswordField passwordConfirmationPasswordField;
    @FXML
    private TextField passwordConfirmationTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField companyTextField;
    @FXML
    private Label signUpMessageLabel;
    @FXML
    private ImageView visibilityImageView;

    private Image visibleIcon;
    private Image invisibleIcon;


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
        // combo box
        String[] items = {"User", "Seller"};
        comboBox.getItems().addAll(items);

        // images
        try {
            visibleIcon     = new Image(new FileInputStream("src\\main\\resources\\com\\example\\" +
                    "shopapplication\\icons\\visible.png"));
            invisibleIcon   = new Image(new FileInputStream("src\\main\\resources\\com\\example\\" +
                    "shopapplication\\icons\\invisible.png"));
            visibilityImageView.setImage(invisibleIcon);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void switchVisibilityIcon() {
        if (visibilityImageView.getImage() == invisibleIcon) {
            visibilityImageView.setImage(visibleIcon);

            passwordTextField.                  setVisible(true);
            passwordPasswordField.              setVisible(false);
            passwordConfirmationTextField.      setVisible(true);
            passwordConfirmationPasswordField.  setVisible(false);

            passwordTextField.                  setText(passwordPasswordField.getText());
            passwordConfirmationTextField.      setText(passwordConfirmationPasswordField.getText());

        } else {
            visibilityImageView.setImage(invisibleIcon);

            passwordPasswordField.              setVisible(true);
            passwordTextField.                  setVisible(false);
            passwordConfirmationPasswordField.  setVisible(true);
            passwordConfirmationTextField.      setVisible(false);

            passwordPasswordField.              setText(passwordTextField.getText());
            passwordConfirmationPasswordField.  setText(passwordConfirmationTextField.getText());
        }
    }

    public void cancel(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void signUp(ActionEvent event) {
    }
}

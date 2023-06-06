package com.example.shopapplication;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends Application implements Initializable {
    @FXML
    private TextField usernameTextField;
    @FXML
    private Hyperlink forgottenPasswordHyperlink;
    @FXML
    private Hyperlink createAccountHyperlink;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private ImageView visibilityImageView;
    @FXML
    private Button loginButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label errorLabel;
    @FXML
    private ComboBox<String> comboBox;


    private Image visibleIcon;
    private Image invisibleIcon;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] roles = {"User", "Seller", "Admin"};
        comboBox.setItems(FXCollections.observableArrayList(roles));
        comboBox.setValue(comboBox.getItems().get(0));

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

    public void close() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        System.out.println("LoginController.close");

    }

    public void login() {
        if (usernameTextField.getText().equals("") || // if a field is empty
                (passwordTextField.isVisible() ? passwordTextField.getText().equals("") :
                        passwordPasswordField.getText().equals(""))) {
            errorLabel.setText("Enter username and password fields.");

        } else {
            if (validate());
        }
        System.out.println("LoginController.login");

    }

    private boolean validate() {
        return false;
    }

    public void switchToSignUp() {
        System.out.println("LoginController.switchToSignUp");
    }

    public void switchToPasswordRestoration() {
        System.out.println("LoginController.switchToPasswordRestoration");
    }
    public void switchVisibilityIcon() {
        System.out.println("LoginController.switchVisibilityIcon");
        if (visibilityImageView.getImage() == invisibleIcon) {
            visibilityImageView.setImage(visibleIcon);
            passwordTextField.      setVisible(true);
            passwordPasswordField.  setVisible(false);
            passwordTextField.setText(passwordPasswordField.getText());

        } else {
            visibilityImageView.setImage(invisibleIcon);
            passwordPasswordField.  setVisible(true);
            passwordTextField.      setVisible(false);
            passwordPasswordField.setText(passwordTextField.getText());
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(getClass().getResource(""));
        FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("login-scene.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
}

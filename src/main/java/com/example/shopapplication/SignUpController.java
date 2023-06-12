package com.example.shopapplication;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    @FXML
    private Button nextButton;
    @FXML
    private Label companyLabel;
    private TextField[] textFields;
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
        String[] items = {"Customer", "Seller"};
        comboBox.getItems().addAll(items);
        comboBox.setValue(items[0]);

        // add change listener to text fields
        ChangeListener<String> changeListener = (observableValue, oldValue, newValue) -> {
            nextButtonChangeListener();
        };
        textFields = new TextField[]{usernameTextField, firstnameTextField, lastnameTextField, passwordTextField,
                passwordConfirmationTextField, passwordPasswordField, passwordConfirmationPasswordField,
                emailTextField, companyTextField};
        for (TextField textField : textFields) {
            textField.textProperty().addListener(changeListener);
        }

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

    public void next(ActionEvent event) {
        String signUpMessage = "";
        SignUp signUp = null;
        boolean validation = false;
        User user = null;


//        sign up step 1: validate inputs

//        sign up step 2: check username uniqueness

//        sign up step 3: verify email

//        sign up step 4: get admin permission
        try {
            String username = usernameTextField.getText();
            String password = getPassword();
            String firstname = firstnameTextField.getText();
            String lastname = lastnameTextField.getText();
            String email = emailTextField.getText();
            String comboBoxValue = comboBox.getValue();

            if (comboBoxValue == "Customer") {
                user = new Customer(username, password, firstname, lastname, email);
            } else if (comboBoxValue == "Seller") {
                user = new Seller(username, password, firstname, lastname, email);
            }

            signUp = new SignUp(user);

            if (signUp.validate() && signUp.verify()) {
                signUpMessage = "Welcome!";
            }


        } catch (Exception e) {
            signUpMessage = e.getMessage();
            System.err.println(e);
        }

        signUpMessageLabel.setText(signUpMessage);

    }

    private String getPassword() {
        String password1;
        String password2;
        if (visibilityImageView.getImage() == invisibleIcon) {
            password1 = passwordPasswordField.              getText();
            password2 = passwordConfirmationPasswordField.  getText();
        } else {
            password1 = passwordTextField.                  getText();
            password2 = passwordConfirmationTextField.      getText();
        }

        if (password1.equals(password2)) { // if they match
            return password1;
        } else { // if not matched
            return "";
        }
    }

    public void nextButtonChangeListener() {
        System.out.println("SignUpController.nextButtonChangeListener");
        System.out.println("getPassword() = " + getPassword());
        if (usernameTextField.getText().equals("") || getPassword().equals("") || // keep next button disabled
                firstnameTextField.getText().equals("") || lastnameTextField.getText().equals("") ||
                emailTextField.getText().equals("") ||
                (comboBox.getSelectionModel().isSelected(1) && companyTextField.getText().equals(""))) {
            nextButton.setDisable(true);
        } else {
            nextButton.setDisable(false);
        }

        final int LIMIT = 20;
        for (TextField textField : textFields) {
            if (textField != emailTextField) {
                String text = textField.getText();
                textField.setText(text.length() > LIMIT ? text.substring(0, LIMIT) : text);
            }
        }
    }

    public void switchCompanyRow(ActionEvent event) {
        if (comboBox.getValue() == "Seller") {
            companyLabel.       setVisible(true);
            companyTextField.   setVisible(true);
        } else {
            companyLabel.       setVisible(false);
            companyTextField.   setVisible(false);
        }
        nextButtonChangeListener();
    }
}

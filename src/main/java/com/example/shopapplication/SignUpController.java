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
            listenToTextFieldsChange();
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
            String company = companyTextField.getText();

            if (comboBoxValue == "Customer") {
                user = new Customer(username, password, firstname, lastname, email);
            } else if (comboBoxValue == "Seller") {
                user = new Seller(username, password, firstname, lastname, email, company);
            }

            signUp = new SignUp(user);

            if (signUp.validate() && signUp.verify()) { // to next step
                System.out.println("ysesssss");
                signUpMessage = "Welcome!";
                FXMLLoader loader = new FXMLLoader(getClass().getResource("email-confirmation-scene.fxml"));
                Parent root = loader.load();

                EmailConfirmationController emailConfirmationController = loader.getController();
                emailConfirmationController.setSignUp(signUp);

                Scene scene = new Scene(root);
                Stage stage = (Stage) nextButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
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

    public void listenToTextFieldsChange() {
        System.out.println("SignUpController.listenToTextFieldsChange");
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

    public void switchCompanyRow() {
        if (comboBox.getSelectionModel().isSelected(1)) { // if is seller
            companyLabel.       setVisible(true);
            companyTextField.   setVisible(true);
        } else { // if customer
            companyLabel.       setVisible(false);
            companyTextField.   setVisible(false);
        }
        listenToTextFieldsChange(); //  check for enabling next button
    }

    public void setUserTextFields(User user) {
        usernameTextField.setText(user.getUsername());
        firstnameTextField.setText(user.getFirstname());
        lastnameTextField.setText(user.getLastname());
        passwordPasswordField.setText(user.getPassword());
        passwordConfirmationPasswordField.setText(user.getPassword());
        emailTextField.setText(user.getEmail());
        if (user instanceof Seller) {
            Seller seller = (Seller) user;
            comboBox.setValue(comboBox.getItems().get(1));
            companyTextField.setText(seller.getCompany());
            switchCompanyRow();
        }
    }
}

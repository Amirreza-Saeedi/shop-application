package com.example.shopapplication;

import com.example.shopapplication.exceptions.SignUpException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RequestForSignUpController implements Initializable {
    @FXML
    private Label messageLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private Label companyLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField companyTextField;

    private SignUp signUp;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleLabel.setText("Customer identities:");
        companyLabel.setVisible(false);
        companyTextField.setVisible(false);
    }

    public void setSignUp(SignUp signUp) {
        try {
            if (signUp == null)
                throw new NullPointerException();
            this.signUp = signUp;
        } catch (NullPointerException e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }

    public void fillTextFields(User user) {
        try {
            if (user == null)
                throw new NullPointerException();
            usernameTextField.setText(user.getUsername());
            nameTextField.setText(user.getFirstname() + " " + user.getLastname());
            emailTextField.setText(user.getEmail());
            if (user instanceof Seller) {
                titleLabel.setText("Seller identities:");
                companyLabel.setVisible(true);
                companyTextField.setVisible(true);
            }

        } catch (NullPointerException e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }

    public void finish(ActionEvent event) {
        Alert alert;
        String contentText;
        String headerText;
        String message = "";

        try {
            if (signUp.signUp()) {
                alert = new Alert(Alert.AlertType.CONFIRMATION);

                if (signUp.getUser() instanceof Customer) { // customer
                    headerText = "You are successfully signed up.";
                    contentText = "If you want to log in to your account click OK, else click Cancel.";
                    alert.setContentText(contentText);
                    alert.setHeaderText(headerText);

                    if (alert.showAndWait().get() == ButtonType.OK) { // ok
                        // todo login directly
                        Login.login(signUp.getUser()); // log in to account
                        message = "log in...";

                    } else { // cancel
                        // todo return to home page
                        message = "back to home page...";
                        switchToHomePage();
                    }

                } else if (signUp.getUser() instanceof Seller) { // seller
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    headerText = "Your request is sent to admins for their confirmation.";
                    contentText = "As soon as admins answer to your request you'll be alerted through your email." +
                            " Click OK to return to Home Page."; // todo send answer as an email
                    alert.setContentText(contentText);
                    alert.setHeaderText(headerText);
                    alert.show();
                    message = "back to home page...";
                    // todo return to home page
                    switchToHomePage();

                }

            } else {
                // todo
                alert = new Alert(Alert.AlertType.ERROR);
                headerText = "A problem has been occurred!";
                contentText = "Click OK to return to Home Page.";
                alert.setContentText(contentText);
                alert.setHeaderText(headerText);
                if (alert.showAndWait().get() == ButtonType.OK) {
                    switchToHomePage();
                }
                message = "back to home page...";
            }

        } catch (SignUpException | SQLException | ClassNotFoundException e) {
            System.err.println(e);
            e.printStackTrace();
        }

        messageLabel.setText(message);


    }

    private void switchToHomePage() {
    }

    public void cancel(ActionEvent event) {

    }
}

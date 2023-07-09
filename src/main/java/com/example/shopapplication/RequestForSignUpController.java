package com.example.shopapplication;

import com.example.shopapplication.exceptions.SignUpException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Node;

import java.io.IOException;
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
    @FXML
    private TextField phoneTextField;

    private SignUp signUp;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messageLabel.setVisible(false);
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
            phoneTextField.setText(user.getPhone());
            if (user instanceof Seller) {
                titleLabel.setText("Seller identities:");
                companyTextField.setText(((Seller) user).getCompany());
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

                if (signUp.getUser() instanceof Customer) { // 1- customer
                    headerText = "You are successfully signed up.";
                    contentText = "If you want to log in to your account click OK, else click Cancel.";
                    alert.setContentText(contentText);
                    alert.setHeaderText(headerText);

                    if (alert.showAndWait().get() == ButtonType.OK) { // ok
                        message = "Returning to home...";
                        ErrorMessage.showError(messageLabel, message ,5, Color.RED);
                        sleep();
                        // log in to account
                        Login login = new Login(signUp.getUser());
                        login.loginToHome((Node) event.getSource());

                    } else { // cancel
                        message = "back to home page...";
                        ErrorMessage.showError(messageLabel, message ,5, Color.RED);
                        sleep();
                        HomeController.toHome((Node) event.getSource());
                    }

                } else if (signUp.getUser() instanceof Seller) { // 2- seller
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    headerText = "Your request is sent to admins for their confirmation.";
                    contentText = "As soon as admins answer to your request you'll be alerted through your email." +
                            " Click OK to return to Home Page."; // todo send answer as an email
                    alert.setContentText(contentText);
                    alert.setHeaderText(headerText);
                    alert.show();
                    message = "back to home page...";
                    ErrorMessage.showError(messageLabel, message ,5, Color.RED);
                    HomeController.toHome((Node) event.getSource());
                }

            } else { // 3- error
                alert = new Alert(Alert.AlertType.ERROR);
                headerText = "A problem has been occurred!";
                contentText = "Click OK to return to Home Page.";
                alert.setContentText(contentText);
                alert.setHeaderText(headerText);
//                if (alert.showAndWait().get() == ButtonType.OK) {
//                }
                alert.show();
                message = "back to home page...";
                ErrorMessage.showError(messageLabel, message ,5, Color.RED);
                sleep();
                HomeController.toHome((Node) event.getSource());
            }

        } catch (SignUpException | SQLException | ClassNotFoundException e) {
            System.err.println(e);
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        ErrorMessage.showError(messageLabel, message ,5, Color.RED);
    }



    public void cancel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    private void sleep() throws InterruptedException {
        Thread.sleep(1000);
    }
}

package com.example.shopapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PortalPageController implements Initializable {
    @FXML
    private Label cash;
    @FXML
    private TextField cardNumberTextField;
    @FXML
    private TextField cvv2TextField;
    @FXML
    private TextField monthDateTextField;
    @FXML
    private TextField yearDateTextField;
    @FXML
    private TextField captchaTextField;
    @FXML
    private TextField cardPassTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private Button payButton;
    @FXML
    private Label errorsLabel;
    @FXML
            private Label paymentNotify;
    @FXML
            private Button backToHomeButton;
    @FXML
            private ImageView BankIcon;
    @FXML
            private Label info1;
    @FXML
            private Label info2;
    @FXML
            private Label info3;
    @FXML
    private Label info4;
    @FXML
            private Label info5;
    @FXML
    private Label info6;
    @FXML
            private Label info7;
    Pattern emailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Pattern numberPattern = Pattern.compile("\\d*");
        cardNumberTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            Matcher matcher = numberPattern.matcher(newValue);
            if (!matcher.matches()) {
                cardNumberTextField.setText(oldValue);
            }
        });
        cardNumberTextField.textProperty().addListener((observableValue, s, t1) -> {
            cardNumberTextField.setStyle("-fx-border-color: none;");
        });
        cvv2TextField.textProperty().addListener((observable, oldValue, newValue) -> {
            Matcher matcher = numberPattern.matcher(newValue);
            if (!matcher.matches()) {
                cvv2TextField.setText(oldValue);
            }
        });
        cvv2TextField.textProperty().addListener((observableValue, s, t1) -> {
            cvv2TextField.setStyle("-fx-border-color: none;");
        });
        monthDateTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            Matcher matcher = numberPattern.matcher(newValue);
            if (!matcher.matches()) {
                monthDateTextField.setText(oldValue);
            }
        });
        monthDateTextField.textProperty().addListener((observableValue, s, t1) -> {
            monthDateTextField.setStyle("-fx-border-color: none;");
        });
        yearDateTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            Matcher matcher = numberPattern.matcher(newValue);
            if (!matcher.matches()) {
                yearDateTextField.setText(oldValue);
            }
        });
        yearDateTextField.textProperty().addListener((observableValue, s, t1) -> {
            yearDateTextField.setStyle("-fx-border-color: none;");
        });
        captchaTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            Matcher matcher = numberPattern.matcher(newValue);
            if (!matcher.matches()) {
                captchaTextField.setText(oldValue);
            }
        });
        captchaTextField.textProperty().addListener((observableValue, s, t1) -> {
            captchaTextField.setStyle("-fx-border-color: none;");
        });
        cardPassTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            Matcher matcher = numberPattern.matcher(newValue);
            if (!matcher.matches()) {
                cardPassTextField.setText(oldValue);
            }
        });
        cardPassTextField.textProperty().addListener((observableValue, s, t1) -> {
            cardPassTextField.setStyle("-fx-border-color: none;");
        });
        emailTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            Matcher matcher = emailPattern.matcher(newValue);
            if (!matcher.matches()) {
                emailTextField.setStyle("-fx-border-color: red;");
            } else {
                emailTextField.setStyle("-fx-border-color: none;");
            }
        });
    }
    public void setPayOnAction(ActionEvent event){
        if (cardNumberTextField.getText().length() != 16) {
            errorsLabel.setText("incorrect card number");
            cardNumberTextField.setStyle("-fx-border-color: red;");
        } else if (cvv2TextField.getText().length() !=3) {
            errorsLabel.setText("incorrect cvv2");
            cvv2TextField.setStyle("-fx-border-color: red;");
        } else if (monthDateTextField.getText().length() != 2 || Integer.parseInt(monthDateTextField.getText()) < 1 || Integer.parseInt(monthDateTextField.getText()) > 12) {
            errorsLabel.setText("incorrect card month");
            monthDateTextField.setStyle("-fx-border-color: red;");
        } else if (yearDateTextField.getText().length() != 2 || Integer.parseInt(yearDateTextField.getText()) < 0) {
             errorsLabel.setText("incorrect card year");
             yearDateTextField.setStyle("-fx-border-color: red;");
        }else if (captchaTextField.getText() == null){
            errorsLabel.setText("fill the captcha blank correct");
            captchaTextField.setStyle("-fx-border-color: red;");
        } else if (cardPassTextField.getText().length() != 8) {
            errorsLabel.setText("fill the password blank");
            cardPassTextField.setStyle("-fx-border-color: red;");
        } else if (!emailPattern.matcher(emailTextField.getText()).matches() || emailTextField.getText() == null) {
            errorsLabel.setText("fill the email blank correct");
            emailTextField.setStyle("-fx-border-color: red;");
        }else {
            info1.setVisible(false);
            info2.setVisible(false);
            info3.setVisible(false);
            info4.setVisible(false);
            info5.setVisible(false);
            info6.setVisible(false);
            info7.setVisible(false);
            payButton.setVisible(false);
            cash.setVisible(false);
            BankIcon.setVisible(false);
            errorsLabel.setVisible(false);
            cardNumberTextField.setVisible(false);
            cvv2TextField.setVisible(false);
            monthDateTextField.setVisible(false);
            yearDateTextField.setVisible(false);
            captchaTextField.setVisible(false);
            cardPassTextField.setVisible(false);
            emailTextField.setVisible(false);
            paymentNotify.setVisible(true);
            backToHomeButton.setVisible(true);
        }
    }
    public void setBackToHomeButtonOnAction(ActionEvent event){
        Stage stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(  "Home.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(60);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

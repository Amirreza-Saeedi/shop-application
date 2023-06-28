package com.example.shopapplication;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductRegistrationController implements Initializable {
    @FXML
    private Label typeTextField;
    @FXML
    private Label brandTextField;
    @FXML
    private Label priceTextField;
    @FXML
    private Label TitleTextField;
    @FXML
    private Label numberTextField;
    @FXML
    private Button record;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Pattern numberPattern = Pattern.compile("\\d*");
        typeTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            Matcher matcher = numberPattern.matcher(newValue);
            if (!matcher.matches()){
                typeTextField.setText(oldValue);
            }
        });
        priceTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            Matcher matcher = numberPattern.matcher(newValue);
            if (!matcher.matches()){
                priceTextField.setText(oldValue);
            }
        });
        numberTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            Matcher matcher = numberPattern.matcher(newValue);
            if (!matcher.matches()){
                numberTextField.setText(oldValue);
            }
        });
    }
}

package com.example.shopapplication;

import javafx.beans.value.ChangeListener;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmailConfirmationController implements Initializable {
    @FXML
    private TextField codeTextField;
    @FXML
    private Button nextButton;
    @FXML
    private Label codeLabel;

    SignUp signUp;

    public void setSignUp(SignUp signUp) { // only a valid signUp will be set
        this.signUp = signUp;
        codeLabel.setText("Enter 6-digit code sent to '" + signUp.getUser().getEmail() + "':");
    }

    public void back(ActionEvent event) throws IOException {
        System.out.println("EmailConfirmationController.back");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sign-up-scene.fxml"));
        System.out.println("EmailConfirmationController.back");
        Parent root = loader.load();
        System.out.println("EmailConfirmationController.back");

        // fill fields as before
        SignUpController signUpController = loader.getController();
        signUpController.setUserTextFields(signUp.getUser());

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChangeListener<String> changeListener = (observableValue, s, t1) -> listenToCodeTextFieldChange();
        codeTextField.textProperty().addListener(changeListener);
    }

    private void listenToCodeTextFieldChange() {
        System.out.println("EmailConfirmationController.listenToCodeTextFieldChange");
        try {
            final int LIMIT = 6;
            StringBuffer str = new StringBuffer(codeTextField.getText());
            int length = str.length();
            int lastIndex = length - 1;
            char lastChar = str.charAt(length - 1);
            if (length > LIMIT || !('0' <= lastChar && lastChar <= '9')) {
                str.deleteCharAt(lastIndex);
                codeTextField.setText(str.toString());
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }
}

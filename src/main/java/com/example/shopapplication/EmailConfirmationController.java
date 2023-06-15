package com.example.shopapplication;

import com.example.shopapplication.exceptions.UsernameAlreadyExistsException;
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
    @FXML
    private Label messageLabel;

    SignUp signUp;
    int code;

    public void setSignUp(SignUp signUp) { // only a valid signUp will be set
        try {
            if (signUp == null)
                throw new NullPointerException();
            this.signUp = signUp;
            codeLabel.setText("Enter 6-digit code sent to '" + signUp.getUser().getEmail() + "':");
        } catch (NullPointerException e) {
            System.err.println(e);
            e.printStackTrace();
        }
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
        // add change listener
        ChangeListener<String> changeListener = (observableValue, s, t1) -> listenToCodeTextFieldChange();
        codeTextField.textProperty().addListener(changeListener);

        // todo send code to email
        code = 123456;
    }

    public void next() {
        try {
            int input = Integer.parseInt(codeTextField.getText());
            if (code == input) {
                messageLabel.setText("Continue...");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("request-for-sign-up.fxml"));
                Parent root = loader.load();

                RequestForSignUpController controller = loader.getController();
                controller.setSignUp(signUp);
                controller.fillTextFields(signUp.getUser());

                Stage stage = (Stage) nextButton.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } else {
                messageLabel.setText("Wrong!");
            }

        } catch (IOException e) {
            System.err.println(e);
            e.printStackTrace();
        }

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

            nextButton.setDisable(str.length() != LIMIT);

        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }
}

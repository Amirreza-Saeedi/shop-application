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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
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
    private Label loginMessageLabel;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Pane captchaPane;
    @FXML
    private TextField captchaTextField;
    @FXML
    private Text captchaText;


    private Image visibleIcon;
    private Image invisibleIcon;
    private Captcha captcha;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginMessageLabel.setVisible(false);

        // combo box
        String[] roles = {"Customer", "Seller", "Admin"};
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

        // captcha
        captcha = new Captcha(60_000_000, 5);
        updateCaptcha();

    }

    public void cancel() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
    }

    public void login() {
        // check for empty fields:
        if (usernameTextField.getText().equals("") ||
                getPasswordText().equals("") ||
                captchaTextField.getText().equals("")) { 

            loginMessageLabel.setTextFill(Color.RED);
            loginMessageLabel.setText("Fulfill the fields.");

        } else { // check inputs:
            String username = usernameTextField.getText();
            String password = getPasswordText();

            User user = null;
            if (comboBox.getValue() == "Customer")
                user = new Customer(username, password);
            else if (comboBox.getValue() == "Seller")
                user = new Seller(username, password);
            else if (comboBox.getValue() == "Admin")
                user = new Admin(username, password);

//            // check user & pass validation:
//            Login login = new Login(user);
//            boolean validation = false;
//            switch (comboBox.getValue()) { // verify base on combo box value
//                case "Customer":
//                    validation = login.validateCustomerLogin(username, password);
//                    break;
//                case "Seller":
//                    validation = login.validateSellerLogin(username, password);
//                    break;
//                case "Admin":
//                    validation = login.validateAdminLogin(username, password);
//                default:
//                    System.err.println(new Exception("combo box selection does not exist."));
//            }

            user = Login.getCompleteUser(user);

            // make decision:
            if (user == null) { // 1- invalid user & pass
                ErrorMessage.showError(loginMessageLabel, "Username or password is invalid.", 5, Color.RED);

            } else if (captcha.isExpired()) { // 2- captcha expired
                ErrorMessage.showError(loginMessageLabel, "Captcha expired.", 5, Color.RED);

            } else if (!captchaTextField.getText().equalsIgnoreCase(captcha.getCode())) { // 3- mismatched captcha
                ErrorMessage.showError(loginMessageLabel, "Captcha mismatched.", 5, Color.RED);

            } else { // 4- sign in successfully
                ErrorMessage.showError(loginMessageLabel, "Returning to home...", 5, Color.GREEN);
                try {
                    Sound.login();
                } catch (UnsupportedAudioFileException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (LineUnavailableException e) {
                    throw new RuntimeException(e);
                }


                try {
                    Thread.sleep(1000);
                    FXMLLoader loader = new FXMLLoader(Login.class.getResource("home.fxml"));
                    Parent root = loader.load();
                    HomeController homeController = loader.getController();
                    homeController.setUser(user);
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.centerOnScreen();
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void switchToSignUp() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sign-up-scene.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
    }

    public void switchToPasswordRestoration() {
        System.out.println("LoginController.switchToPasswordRestoration");
    }
    public void switchVisibilityIcon() {
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

    private String getPasswordText() {
        return passwordTextField.isVisible()
                ? passwordTextField.getText()
                : passwordPasswordField.getText();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("login-scene.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        stage.centerOnScreen();
    }

    public void updateCaptcha() { // change captcha CODE and color
        Random random = new Random();
        Color randomColor = new Color(random.nextDouble(), random.nextDouble(), random.nextDouble(), 1.0);
        captchaText.setFill(randomColor);
        captcha.newCode();
        captchaText.setText(captcha.getCode());
    }
}

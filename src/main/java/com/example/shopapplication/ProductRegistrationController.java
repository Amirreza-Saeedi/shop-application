package com.example.shopapplication;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductRegistrationController implements Initializable {
    @FXML
    private TextField typeTextField;
    @FXML
    private TextField brandTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField titleTextField;
    @FXML
    private TextField numberTextField;
    @FXML
    private Button record;
    @FXML
    private Button chooseImageButton;
    @FXML
    private ImageView imageView;

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
        chooseImageButton.setOnAction(event -> {
            // Create a FileChooser object to allow the user to select an image file
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Image File");

            // Set the file extension filter to allow only image files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
            fileChooser.getExtensionFilters().add(extFilter);

            // Show the file chooser dialog and wait for the user to select a file
            Stage primaryStage = new Stage();
            File selectedFile = fileChooser.showOpenDialog(primaryStage);

            // If a file was selected, load it into the image view and display its path in the label
            if (selectedFile != null) {
                Image image = new Image(selectedFile.toURI().toString());
                imageView.setImage(image);
            }
        });
    }

    public void recordOnAction(ActionEvent event){

    }

}

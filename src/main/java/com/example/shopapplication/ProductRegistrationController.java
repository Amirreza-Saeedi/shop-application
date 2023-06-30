package com.example.shopapplication;

import com.example.shopapplication.regex.MyRegex;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    @FXML
    private TextArea properties;
    @FXML
    private ComboBox<String> groupSelector;
    @FXML
    private Label error;
    @FXML
    private Button cancelButton;
    private User user;

    private ObservableList<String> groupSelectorOptions = FXCollections.observableArrayList("Break fast","Dairy","Fruit and Vegetables","Grocery","Protein","Snack");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        groupSelector.setItems(groupSelectorOptions);
        Pattern numberPattern = Pattern.compile(MyRegex.numberRegex);
        Pattern stringPattern = Pattern.compile(MyRegex.nameRegex);
        typeTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            Matcher matcher = stringPattern.matcher(newValue);
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
    private void switchToHome(ActionEvent event , String sceneName)  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneName + ".fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        HomeController homeController = loader.getController();
        homeController.setUser(user);
        stage.setX(50);
    }

    public void recordOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (groupSelector.getValue().equals("Select group")){
            error.setText("Choose a group");
            groupSelector.setStyle("-fx-border-color: red;");
        } else if (typeTextField.getText().length() == 0) {
            error.setText("Write type of your product");
            typeTextField.setStyle("-fx-border-color: red;");
        } else if (brandTextField.getText().length() == 0) {
            error.setText("Write brand of your product");
            brandTextField.setStyle("-fx-border-color: red;");
        } else if (priceTextField.getText().length() == 0) {
            error.setText("No price!");
            priceTextField.setStyle("-fx-border-color: red;");
        } else if (titleTextField.getText().length() == 0) {
            error.setText("No title!");
            titleTextField.setStyle("-fx-border-color: red;");
        } else if (numberTextField.getText().length() == 0) {
            error.setText("No number1");
            numberTextField.setStyle("-fx-border-color: red;");
        } else if (properties.getText().length() == 0) {
            error.setText("Write some properties");
            properties.setStyle("-fx-border-color: red;");
        }else {
            DatabaseConnectionJDBC databaseConnection = new DatabaseConnectionJDBC();
            Connection connection = databaseConnection.getConnection();

            String type = typeTextField.getText();
            String brand = brandTextField.getText();
            String price = priceTextField.getText();
            String title = titleTextField.getText();
            int number = Integer.parseInt(numberTextField.getText());
            String group = groupSelector.getValue();
            String propertiesText = properties.getText();
            Image image = imageView.getImage();

            String sql = "INSERT INTO AllCommodities (Type, Brand, Price, Title, Number, group, properties, image) VALUES (" + "'" + type + "', '" + brand + "', '" + price + "', '" + title + "', " + number + ", '" + group + "', '" + propertiesText + "', " + image;
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, type);
            pstmt.setString(2, brand);
            pstmt.setString(3, price);
            pstmt.setString(4, title);
            pstmt.setInt(5, number);
            pstmt.setString(6, group);
            pstmt.setString(7, propertiesText);
            pstmt.setBlob(8, (Blob) image);

            int rowsAffected = pstmt.executeUpdate();

// Close the PreparedStatement object and the database connection
            pstmt.close();
            connection.close();
            switchToHome(event,"Home");
        }
    }
    public void cancelButtonOnAction(ActionEvent event){
        error.setText("Returning to home...");
        switchToHome(event,"Home");

    }
    public void setUser(User user) {
        if (user == null) {
            throw new NullPointerException("User is null");
        }
        this.user = user;
    }

}

package com.example.shopapplication;

import com.example.shopapplication.regex.MyRegex;

import java.io.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductRegistrationController implements Initializable {
    @FXML
    private ComboBox<String> typeBox;
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
    @FXML
    private ComboBox<Integer> storeRoomBox;
    private ObservableList<Integer> storeRoomBoxOptions;
    private User user;
    private File selectedFile;
    private String group;

    private ObservableList<String> groupSelectorOptions = FXCollections.observableArrayList("Break fast","Dairy","Fruit and Vegetables","Grocery","Protein","Snack");
    private ObservableList<String> typeBoxOptions;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        groupSelector.setItems(groupSelectorOptions);
        Pattern numberPattern = Pattern.compile(MyRegex.numberRegex);
        Pattern stringPattern = Pattern.compile(MyRegex.nameRegex);
        Pattern doublePattern = Pattern.compile(MyRegex.doubleRegex);
        priceTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            Matcher matcher = doublePattern.matcher(newValue);
            if (!matcher.matches() && priceTextField.getText().length() != 0){
                priceTextField.setText(oldValue);
            }
        });
        numberTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            Matcher matcher = numberPattern.matcher(newValue);
            if (!matcher.matches() && numberTextField.getText().length() != 0){
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
            selectedFile = fileChooser.showOpenDialog(primaryStage);

            // If a file was selected, load it into the image view and display its path in the label
            if (selectedFile != null) {
                Image image = new Image(selectedFile.toURI().toString());
                imageView.setImage(image);
            }
        });
        groupSelector.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            group = makeCorrectGroup(newValue);
            fillTypeBox(group);
        });

        fillStoreBox();
    }

    private void fillStoreBox(){
        ArrayList<Integer> storages = new ArrayList<>();
        try(Connection connection = new DatabaseConnectionJDBC().getConnection()){
            String sql = "SELECT * FROM Storages";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                int storageId = rs.getInt("storageId");
                storages.add(storageId);
            }
            storeRoomBoxOptions = FXCollections.observableArrayList(storages);
            storeRoomBox.setItems(storeRoomBoxOptions);
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }
    public void recordOnAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        if (groupSelector.getValue().equals("Select group")){
            error.setText("Choose a group");
            groupSelector.setStyle("-fx-border-color: red;");
        } else if (typeBox.getValue().length() == 0) {
            error.setText("Write type of your product");
            typeBox.setStyle("-fx-border-color: red;");
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
        } else if (storeRoomBox.getValue().equals(null)) {
            error.setText("Select a store room");
            storeRoomBox.setStyle("-fx-border-color: red;");
        } else {

            try (Connection connection = new DatabaseConnectionJDBC().getConnection();
            Statement statement = connection.createStatement()) {
                String type = typeBox.getValue();
                type = type.toLowerCase();
                String brand = brandTextField.getText();
                brand = brand.toLowerCase();
                String price = priceTextField.getText();
                String title = titleTextField.getText();
                int storageId = storeRoomBox.getValue();

                int number = Integer.parseInt(numberTextField.getText());
                String propertiesText = properties.getText();
                String username = user.getUsername();

                // Read the image file into a byte array
//                InputStream inputStream = new FileInputStream(selectedFile.getPath());
//                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                byte[] buffer = new byte[4096];
//                int bytesRead = -1;
//                while ((bytesRead = inputStream.read(buffer)) != -1) {
//                    outputStream.write(buffer, 0, bytesRead);
//                }
//                byte[] imageBytes = outputStream.toByteArray();

                String imageName = selectedFile.getName();
//                String sql = "INSERT INTO AllCommodities (Type, Brand, Price, Ratio, Title, Number, groupp, properties, image, userName, storageId) VALUES ('"
//                        + type + "', '" + brand + "', '" + price + "', '" +"0', '"+ title + "', " + number + ", '" + group
//                        + "', '" + propertiesText + "', '" + Base64.getEncoder().encodeToString(imageBytes) + "', '" + user.getUsername() + "', " + storageId + ")";
//            String sql = "INSERT INTO AllCommodities (Type, Brand, Price, Title, Number, groupp, properties) VALUES ('jam', 'jamavaran', '9', 'jamavaran jam', 5, 'BreackFastCommodities', 'delisous jam')";

                String sql = "INSERT INTO AllCommodities (Type, Brand, Price, Ratio, Title, Number, groupp, properties, userName, storageId, imageName) VALUES ('"
                        + type + "', '" + brand + "', '" + price + "', '" +"0', '"+ title + "', " + number + ", '" + group
                        + "', '" + propertiesText + "', '" + user.getUsername() + "', " + storageId + ", '" + imageName +  "')";

                statement.executeUpdate(sql);



// Close the PreparedStatement object and the database connection
//                pstmt.close();
                connection.close();
            }catch (SQLException e){
                System.err.println(e);
                e.printStackTrace();
            }
            try {
                Sound.productRegistered();
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }
            error.setText("Returning to home...");
            new Login(user).loginToHome((Node) event.getSource());
        }
    }
    public void cancelButtonOnAction(ActionEvent event) throws IOException {
        error.setText("Returning to home...");
        new Login(user).loginToHome((Node) event.getSource());
    }
    public void setUser(User user) {
        if (user == null) {
            throw new NullPointerException("User is null");
        }
        this.user = user;
    }
    private String makeCorrectGroup(String group){
        switch (group){
            case "Break fast":
                group = "BreakFastCommodities";
                break;
            case "Dairy":
                group = "DairyCommodities";
                break;
            case "Fruit and Vegetables":
                group = "FruitAndVegetablesCommodities";
                break;
            case "Grocery":
                group = "GroceryCommodities";
                break;
            case "Protein":
                group = "ProteinCommodities";
                break;
            case "Snack":
                group = "SnackCommodities";
                break;
        }
        return group;
    }
    private void fillTypeBox(String group){
        switch (group){
            case "BreakFastCommodities":
                typeBoxOptions = FXCollections.observableArrayList("ArdeHalva","Honey","Jam");
                typeBox.setItems(typeBoxOptions);
                break;
            case "DairyCommodities":
                typeBoxOptions = FXCollections.observableArrayList("Cheese","Cream","Milk","Yogurt");
                typeBox.setItems(typeBoxOptions);
                break;
            case "FruitAndVegetablesCommodities":
                typeBoxOptions = FXCollections.observableArrayList("Apple","Apricot","Banana","Grape","Kiwi",
                        "Lemon","Mongo","Orange","Peach","WaterMelon");
                typeBox.setItems(typeBoxOptions);
                break;
            case "GroceryCommodities":
                typeBoxOptions = FXCollections.observableArrayList("Beans","Bread","Lord","Oil","Pasta",
                        "PickledCucumber","Rice","Sauce","Sugar","SugarLeaf");
                typeBox.setItems(typeBoxOptions);
                break;
            case "ProteinCommodities":
                typeBoxOptions = FXCollections.observableArrayList("Eggs","Fish","Meat","SausageAndBologna","Shrimp","Tuna");
                typeBox.setItems(typeBoxOptions);
                break;
            case "SnackCommodities":
                typeBoxOptions = FXCollections.observableArrayList("Biscuit","Cake","Cheetos","ChipsAndPopcorn","Chocolate","Nuts");
                typeBox.setItems(typeBoxOptions);
                break;
        }
    }

}
//            String sql = "INSERT INTO AllCommodities (Type, Brand, Price, Title, Number, groupp, properties, image) VALUES (" + "'" + type + "', '" + brand + "', '" + price + "', '" + title + "', " + number + ", '" + group + "', '" + propertiesText + "', " + image +")";
//            String sql = "INSERT INTO AllCommodities (Type, Brand, Price, Title, Number, groupp, properties, image) VALUES ('type', 'brand', 'price', 'title', number, 'group', 'propertiesText', 'image')";

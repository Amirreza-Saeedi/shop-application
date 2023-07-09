package com.example.shopapplication;

import com.example.shopapplication.regex.MyRegex;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiscountRegistrationController implements Initializable {

    @FXML
    private TextField code;
    @FXML
    private TextField percent;
    @FXML
    private Button submit;
    @FXML
    private Label error;
    private User user;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Pattern percentPattern = Pattern.compile(MyRegex.percentRegex);
        percent.textProperty().addListener((observable, oldValue, newValue) -> {
            Matcher matcher = percentPattern.matcher(newValue);
            if (!matcher.matches() && percent.getText().length() > 0) {
                percent.setText(oldValue);
            }
        });
    }

    public void setSubmitOnAction(ActionEvent event){
        if (code.getText().length() == 0){
            code.setStyle("-fx-border-color: red;");
            error.setText("Enter a discount CODE");
        } else if (percent.getText().length() == 0) {
            percent.setStyle("-fx-border-color: red;");
            error.setText("Enter the discount percent");
        } else {
           try(Connection connection = new DatabaseConnectionJDBC().getConnection()){
               String sql = "INSERT INTO discountCodes (code, percent) VALUES ('" + code.getText() + "', "
                       + Integer.parseInt(percent.getText()) + ")";
               Statement statement = connection.createStatement();
               statement.executeUpdate(sql);

               backToHome(event);
           }catch (SQLException | ClassNotFoundException e){
               throw new RuntimeException(e);
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
        }
    }

    public void backToHome(ActionEvent event) throws IOException {
        new Login(user).loginToHome((Node) event.getSource());
    }
    public void setUser(User user){
        if (user == null){
            throw new NullPointerException("user is null");
        }
        this.user = user;
    }
}

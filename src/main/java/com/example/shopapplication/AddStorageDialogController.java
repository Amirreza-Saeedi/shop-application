package com.example.shopapplication;

import com.example.shopapplication.regex.MyRegex;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddStorageDialogController implements Initializable {
    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField managerTextField;
    @FXML
    private TextArea addressTextArea;
    @FXML
    private Label errorLabel;
    private Label parentErrorLabel;
    private StorageController parentController;

    public void add() {
        String name = nameTextField.getText();
        String manager = managerTextField.getText();
        String address = addressTextArea.getText();

        if (name.equals("") || manager.equals("") || address.equals("")) { // error
            ErrorMessage.showError(errorLabel, "All fields must be filled.", 5, Color.RED);
            return;
        }

        addStorageToDatabase(name, manager, address); // add new storage
        cancel(); // close dialog
    }

    private void addStorageToDatabase(String name, String manager, String address) {
        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            Statement statement = connection.createStatement();
            String sql = "insert into Storages (name, manager, address) values ('" +
                    name + "','" + manager + "','" + address + "')";
            int resultSet = statement.executeUpdate(sql);

            // successful message
            ErrorMessage.showError(parentErrorLabel, "Storage added successfully.", 5, Color.GREEN);

        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
            System.err.println(e);
            e.printStackTrace();
            // failed message
            ErrorMessage.showError(parentErrorLabel, "Adding failed.", 5, Color.RED);

        } finally { // load anyway
            parentController.loadStorages();
        }
    }

    public void cancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.setOnCloseRequest(null);
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // text fields:
        nameTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            Pattern pattern = Pattern.compile(MyRegex.storageNameRegex);
            Matcher matcher = pattern.matcher(newValue);
            if (!matcher.matches() && !newValue.equals("")) {
                nameTextField.setText(oldValue);
            }
        });

        managerTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            Pattern pattern = Pattern.compile(MyRegex.managerRegex);
            Matcher matcher = pattern.matcher(newValue);
            if (!matcher.matches() && !newValue.equals("")) {
                managerTextField.setText(oldValue);
            }
        });

        errorLabel.setVisible(false);
    }

    private void setParentErrorLabel(Label label) {
        parentErrorLabel = label;
    }

    private void setParentController(StorageController controller) {
        parentController = controller;
    }

    public void setAll(StorageController controller, Label errorLabel) {
        setParentController(controller);
        setParentErrorLabel(errorLabel);
    }
}

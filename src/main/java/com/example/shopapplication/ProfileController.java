package com.example.shopapplication;

import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    public Text name;
    public Text username;
    public TableColumn content;
    public TableColumn type;
    public Button btn_basket;
    public Button btn_history;
    public Button btn_deposit;
    public AnchorPane pane;
    public TableView table;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(AppWorkflow.username == null){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("No user Logon");
            errorAlert.setContentText("please login first!");
            errorAlert.showAndWait();
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.close();
        }else {
            String query = "SELECT * FROM USERS WHERE username='"+AppWorkflow.username+"';";
            try {
                Statement stmt = AppWorkflow.connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                name.setText(rs.getString("firstName")+" " + rs.getString("lastName"));
                username.setText(rs.getString("username"));
                TableColumn nameColumn = new TableColumn("Info");
                nameColumn.setCellValueFactory(new PropertyValueFactory<>("info"));

                TableColumn surnameColumn = new TableColumn("Value");
                surnameColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

                table.getColumns().addAll(nameColumn, surnameColumn);

                TableRow mail = new TableRow("email", rs.getString("email"));
                TableRow number = new TableRow("telephone", rs.getString("phoneNumber"));
                TableRow password = new TableRow("password", rs.getString("password"));
                TableRow role = new TableRow("role", rs.getString("role"));
                if(role.getValue() == "Seller"){
                    role.setValue(role.value + " : " + "company"); // set company
                }
                TableRow balance = new TableRow("balance", rs.getString("balance"));
                TableRow disCode = new TableRow("discount Code", rs.getString("discount"));

                table.getItems().add(mail);
                table.getItems().add(number);
                table.getItems().add(password);
                table.getItems().add(role);
                table.getItems().add(balance);
                table.getItems().add(disCode);


                btn_basket.onMouseClickedProperty().setValue(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {

                    }
                });
                btn_deposit.onMouseClickedProperty().setValue(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        // load stage
                    }
                });
                btn_history.onMouseClickedProperty().setValue(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        // load stage
                    }
                });
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }



    }
}

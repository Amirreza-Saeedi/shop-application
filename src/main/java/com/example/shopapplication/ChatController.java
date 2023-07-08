package com.example.shopapplication;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ChatController implements Initializable {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vBox;
    @FXML
    private TextArea textArea;
    @FXML
    private Button sendButton;
    @FXML
    private Label chatLabel;
    private String you;
    private Seller seller;
    private final String SELLER = "seller";
    private final String ADMIN = "admin";
    public void setAll(Seller seller, String you) {
        if (!you.equalsIgnoreCase(SELLER) && !you.equalsIgnoreCase(ADMIN)) {
            throw new RuntimeException("not allowed");
        }
        this.you = you;
        this.seller = seller;

        if (you.equalsIgnoreCase(SELLER)) {
            chatLabel.setText("Chat with admin");
        } else {
            chatLabel.setText("Chat with seller(" + seller.getUsername() + ")");
        }

        loadChats();
    }

    public void send() {
        System.out.println("ChatController.send");
        String message = textArea.getText().trim();
        if (!message.isEmpty()) {

            sendMessage(message); // show message

            // insert into Chats
            try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
                Statement statement = connection.createStatement();
                String date = LocalDateTime.now().toString();
                String sql = "insert into Chats " +
                        "(\"from\",seller,message,\"date\") " +
                        "values ('" + you + "','" + seller.getUsername() + "','" + message + "','" + date + "')";
                int resultSet = statement.executeUpdate(sql);

            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void loadChats() {
        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            Statement statement = connection.createStatement();

            // first delete older than 2 weeks
            final int DEADLINE_WEEKS = 2;
            String deadLine = LocalDateTime.now().minusWeeks(DEADLINE_WEEKS).toString();
            String sql = "delete from chats where date<'" + deadLine + "';";
            statement.executeUpdate(sql);

            // read chats
            sql = "select * from chats where seller='" + seller.getUsername() + "' " +
                    "order by date asc;";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String from = resultSet.getString("from");
                String seller = resultSet.getString("seller");
                String message = resultSet.getString("message").trim();
                if (from.equalsIgnoreCase(you)) {
                    sendMessage(message);
                } else {
                    receiveMessage(message);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    private void sendMessage(String message) {
        if (!message.isEmpty()) {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_RIGHT);
            hBox.setPadding(new Insets(5, 5, 5, 10));

            Text text = new Text(message);
            TextFlow textFlow = new TextFlow(text);

            textFlow.setStyle("-fx-color: rgb(239,242,255); " +
                    "-fx-background-color: rgb(15,125,242); " +
                    "-fx-background-radius: 20px");

            textFlow.setPadding(new Insets(5, 10, 5, 10));
            text.setFill(Color.color(.934, .945, .996));

            hBox.getChildren().add(textFlow);
            vBox.getChildren().add(hBox);

            textArea.clear();
        }
    }

    private void receiveMessage(String message) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5, 5, 5, 10));

        Text text = new Text(message);
        TextFlow textFlow = new TextFlow(text);

        textFlow.setStyle("-fx-background-color: rgb(233,233,235); " +
                "-fx-background-radius: 20px");
        textFlow.setPadding(new Insets(5, 10, 5, 10));

        hBox.getChildren().add(textFlow);
        vBox.getChildren().add(hBox);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vBox.heightProperty().addListener((observableValue, oldVal, newVal) -> {
            scrollPane.setVvalue((Double) newVal);
        });


    }
}

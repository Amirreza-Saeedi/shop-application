package com.example.shopapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChatController implements Initializable {

    @FXML
    private TableView<Message> chatTableView;
    @FXML
    private TableColumn<Message, String> senderTableColumn;
    @FXML
    private TableColumn<Message, Integer> rowTableColumn;
    @FXML
    private TableColumn<Message, LocalDateTime> dateTimeTableColumn;
    @FXML
    private TableColumn<Message, String> messageTableColumn;
    @FXML
    private TextArea messageTextArea;
    @FXML
    private Button sendButton;

    private User from;
    private User to;
    private Admin admin;
    private Seller seller;

    private ObservableList<Message> messages = FXCollections.observableArrayList();

    public void setAll(User from, User to) throws Exception {
        /**
         * just 1 admin and 1 seller is accepted.
         * */
        // determine Admin & Seller
        this.from = from;
        this.to = to;

        if (from instanceof Admin && to instanceof Seller) {
            admin = (Admin) from;
            seller = (Seller) to;
        } else if (from instanceof Seller && to instanceof Admin) {
            admin = (Admin) to;
            seller = (Seller) from;
        } else {
            throw new Exception("just 1 admin and 1 seller is accepted");
        }

        System.out.println("ChatController.ChatController");
        System.out.println("from = " + from);
        System.out.println("to = " + to);
        System.out.println("seller = " + seller);
        System.out.println("admin = " + admin);

        // auto-clear
        autoClear();

        // load
        load();

    }

//    public ChatController(User from, User to) throws Exception {
//        /**
//         * just 1 admin and 1 seller is accepted.
//         * */
//        // determine Admin & Seller
//        this.from = from;
//        this.to = to;
//
//        if (from instanceof Admin && to instanceof Seller) {
//            admin = (Admin) from;
//            seller = (Seller) to;
//        } else if (from instanceof Seller && to instanceof Admin) {
//            admin = (Admin) to;
//            seller = (Seller) from;
//        } else {
//            throw new Exception("just 1 admin and 1 seller is accepted");
//        }
//
//        System.out.println("ChatController.ChatController");
//        System.out.println("from = " + from);
//        System.out.println("to = " + to);
//        System.out.println("seller = " + seller);
//        System.out.println("admin = " + admin);
//
//        // auto-clear
//        autoClear();
//
//        // load
//        load();
//
//    }

    public ChatController() {
        System.out.println("ChatController.ChatController");
        System.out.println("no args");
    }


    public static void main(String[] args) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("ChatController.initialize");

        senderTableColumn.setCellValueFactory(new PropertyValueFactory<>("from"));
        senderTableColumn.setSortable(false);

        messageTableColumn.setCellValueFactory(new PropertyValueFactory<>("message"));
        messageTableColumn.setSortable(false);

        dateTimeTableColumn.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        dateTimeTableColumn.setSortable(false);

        rowTableColumn.setCellValueFactory(new PropertyValueFactory<>("row"));
        rowTableColumn.setSortable(false);
    }

    private void load() {
        /**
         * loads chat history form Chats
         * */

        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            // read form Chats all seller's messages
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Chats where " +
                    "sellerId ='" + seller.getUsername() +"' ORDER BY date ASC, time ASC ";
            ResultSet resultSet = statement.executeQuery(sql);

            ArrayList<Message> list = new ArrayList<>();

            for (int i = 1; resultSet.next(); i++) {

                String from = resultSet.getString("from");
                String sender = (from.equals("admin")) // from
                        ? "Admin: " + resultSet.getString("adminId")
                        : "Seller: " + resultSet.getString("sellerId");
                String messageTxt = resultSet.getString("message"); // message
                // date and time
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                String dateTime = resultSet.getString("date") + " " + resultSet.getString("time");
                LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);

                // add message to list
                Message message = new Message(sender, messageTxt, localDateTime, i);
                list.add(message);
            }


            messages.addAll(list);
            chatTableView.setItems(messages);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void autoClear() {
        /**
         * clears messages sent more than 2 weeks ago.
         * */

        final int WEEKS = 2;
        LocalDate weeksAgoDate = LocalDate.now().minusWeeks(WEEKS);
//        LocalTime nowTime = LocalTime.now();
        System.out.println("ChatController.autoClear");
        System.out.println(weeksAgoDate);
//        System.out.println(nowTime);

        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            // delete older than WEEKS history from Chats
            Statement statement = connection.createStatement(); // todo how to apply date and time conditions together?
            String sql = "DELETE FROM Chats WHERE date<'" + weeksAgoDate + "'";
            int resultSet = statement.executeUpdate(sql);
            System.out.println("resultSet = " + resultSet);


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

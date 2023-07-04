package com.example.shopapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @FXML
    private Button exitButton;

    static class FromAndTo {
        private User from;
        private User to;
        private Admin admin;
        private Seller seller;

        @Override
        public String toString() {
            return "{from = " + from.getUsername() + ", to = " + to.getUsername() +
                    ", admin = " + admin.getUsername() + ", seller = " + seller.getUsername() + "}";

        }

        public User getFrom() {
            return from;
        }

        public void setFrom(User from) {
            this.from = from;
        }

        public User getTo() {
            return to;
        }

        public void setTo(User to) {
            this.to = to;
        }

        public Admin getAdmin() {
            return admin;
        }

        public void setAdmin(Admin admin) {
            this.admin = admin;
        }

        public Seller getSeller() {
            return seller;
        }

        public void setSeller(Seller seller) {
            this.seller = seller;
        }
    }

    FromAndTo fromAndTo = new FromAndTo();

    // server-client
    private Socket client;
    private String host = "localhost";
    private BufferedReader in;
    private PrintWriter out;
    private boolean done = false;



    private ObservableList<Message> messages = FXCollections.observableArrayList();

    public void setAll(User from, User to) throws Exception {
        /**
         * just 1 admin and 1 seller is accepted.
         * */
        // determine Admin & Seller

        fromAndTo.setFrom(from);
        fromAndTo.setTo(to);

        if (from instanceof Admin && to instanceof Seller) {
            fromAndTo.setAdmin((Admin) from);
            fromAndTo.setSeller((Seller) to);
        } else if (from instanceof Seller && to instanceof Admin) {
            fromAndTo.setAdmin((Admin) to);
            fromAndTo.setSeller((Seller) from);
        } else {
            throw new Exception("just 1 admin and 1 seller is accepted");
        }

        System.out.println("ChatController.ChatController");
        System.out.println("from = " + from);
        System.out.println("to = " + to);
        System.out.println("seller = " + fromAndTo.getSeller());
        System.out.println("admin = " + fromAndTo.getAdmin());

        // auto-clear chats
        autoClear();

        // load chats
        load();

//        connectToServer();
    }

//    private void connectToServer() throws IOException {
//        // connect to server
//        client = new Socket(host, ChatServer.port);
//        System.out.println("connected to server.");
//        out = new PrintWriter(client.getOutputStream(), true);
//        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
//        out.println("hello server");
//
//    }

    public ChatController() {
        System.out.println("ChatController.ChatController");
        System.out.println("default constructor");

    }

    public void close() {
        System.out.println("ChatController.close");
    }

    private void shutDown() {
        done = true;
        try {
            in.close();
            out.close();
            if (!client.isClosed()) {
                client.close();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public void run() {
//        try {
//            client = new Socket(host, ChatServer.port); // connect to server
//            System.out.println("client " + fromAndTo.getFrom() + " connected.");
//            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
//            out = new PrintWriter(client.getOutputStream(), true);
//
//            out.print(fromAndTo); // pass from-and-to to server
////            in.read(); // temp
//
//            // handle in-messages
//            String message;
//            while (!done) {
//
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public void send() {
        String message = messageTextArea.getText();




    }

    public void exit() {
        shutDown();


        Stage stage = (Stage) exitButton.getScene().getWindow();
//        stage.getOnCloseRequest().handle(null);
        stage.close();
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
                    "sellerId ='" + fromAndTo.getSeller().getUsername() +"' ORDER BY date ASC, time ASC ";
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

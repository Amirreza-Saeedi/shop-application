package com.example.shopapplication;

import com.example.shopapplication.regex.MyRegex;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductionController implements Initializable {

    public ImageView mainImageView;

    public ImageView star1ImageView;

    public ImageView star2ImageView;

    public ImageView star3ImageView;

    public ImageView star4ImageView;

    public ImageView star5ImageView;
    private ImageView[] starImageViews;
    public Label productLabel;

    public Text typeText;

    public Text priceText;

    public Text brandText;

    public Text rateText;

    public Text dateText;

    public Text availableText;

    public Text auctionText;

    public Text votesText;

    public Text userVoteText;

    public ListView commentsListView;
    @FXML
    private Hyperlink homeHyperlink;
    @FXML
    private TextArea detailsTextArea;
    @FXML
    private Label usernameLabel;
    @FXML
    private Tooltip productTooltip;
    @FXML
    private Text errorText;

    @FXML
    private TextArea commentTextArea;
    @FXML
    private Label remainingCharsLabel;
    @FXML
    private Button sendButton;

    private ObservableList<Comment> comments;
    private Commodity commodity;
    private User user;
    private String userType;
    private boolean hasBought; // true means user has signed in & bought the product
    private int voteId; // != 0 means user takes a place in CommodityVotes table and new changes will be applied in that place



    public ProductionController() {

    }

    public void loadComments() {

        comments = FXCollections.observableArrayList();

        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            System.out.println("ProductionController.loadComments");
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Comments WHERE commodityId='" + commodity.getCommodityId() + "' ORDER BY date,time Desc"; // todo desc doesnt work
            ResultSet resultSet = statement.executeQuery(sql);

            ArrayList<Comment> arrayList = new ArrayList<>(10);
            for (int i = 1; resultSet.next(); ++i) {
                String fullName = resultSet.getString("fullName");
                String message = resultSet.getString("message");
                String username = resultSet.getString("userId");
                String userType = resultSet.getString("user");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                String dateTime = resultSet.getString("date") + " " + resultSet.getString("time");
                LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
                arrayList.add(new Comment(fullName, message, username, userType, localDateTime, i));
                System.out.println("fullName = " + fullName);
                System.out.println("message = " + message);
                System.out.println("username = " + username);
                System.out.println("localDateTime = " + localDateTime);;
            }

            comments.addAll(arrayList);
            commentsListView.setItems(comments);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        commentsListView.setItems(comments);
        commentsListView.setCellFactory(listView -> new CommentCell());

        // images
        starImageViews = new ImageView[]{
                star1ImageView, star2ImageView, star3ImageView, star4ImageView, star5ImageView
        };

        // error text
        errorText.setVisible(false);

        commentTextArea.textProperty().addListener((observableValue, oldValue, newValue) -> {
            Pattern pattern = Pattern.compile(MyRegex.commentRegex);
            Matcher matcher = pattern.matcher(newValue);
            int remaining;
            final int LIMIT = 100;
            if (!matcher.matches()) {
                commentTextArea.setText(oldValue);
                remaining = 100 - oldValue.length();
            } else {
                remaining = 100 - newValue.length();
            }
            remainingCharsLabel.setText(remaining + "/" + LIMIT);
        });
    }

    public void toNewComment() {
        System.out.println("ProductionController.toNewComment");
    }

    public void send() {
        System.out.println("ProductionController.send");
    }

    public void add() {
    }

    public void toHome() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        Parent root = loader.load();

        if (user != null) {
            HomeController controller = loader.getController();
            controller.setUser(user);
        }

        Stage stage = (Stage) homeHyperlink.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void setAll(Commodity commodity, User user) {
        /*commodity cant be null,
         * user may be null*/
        setCommodity(commodity);
        if (user != null) {
            setUser(user);
        }
        updateRatesAndVotes();

        loadComments(); // todo remove
    }

    private boolean submitVote(int newVote) { // todo exception needed?
        /**Inserts/updates user vote in table.
         * Sets voteId if insertion is successful.
         * */

        try(Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            Statement statement = connection.createStatement();
            String sql;
            // CommodityVotes table:
            if (voteId != 0) { // update
                sql = "UPDATE CommodityVotes SET vote='" + newVote + "' " +
                        "WHERE voteId='" + voteId + "'";
            } else { // insert
                sql = "INSERT INTO CommodityVotes (userId,user,vote,commodityId) " +
                        "VALUES ('" + user.getUsername() + "','" +
                        userType + "','" + newVote + "','" + commodity.getCommodityId() + "')";
            }
            int resultSet = statement.executeUpdate(sql);
            if (resultSet != 1) {
                throw new Exception("resultSet = " + resultSet);
            }



            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }

        return false;
    }

    private void updateRatesAndVotes() {
        /**
         * Called in setAll() and after user changes his vote in vote() and it returns true.
         * Just gets data!
         * Counts votes
         * vote == 0 considered no vote.
         * */
        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            Statement statement = connection.createStatement();
            String sql = "SELECT count(vote) as votes FROM CommodityVotes WHERE " +
                    "commodityId='" + commodity.getCommodityId() + "' and " +
                    "NOT vote='" + 0 + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                int votes = resultSet.getInt("votes"); // votes
                votesText.setText(votes + "");
            }

            sql = "SELECT * from AllCommodities where commodityId='" + commodity.getCommodityId() + "'";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                rateText.setText(resultSet.getString("ratio"));
            } else {
                throw new Exception();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void updateVote() { // use voteId for optimization
        /**
         * Sets voteId
         * Called just before updateRatedAndVotes.
         * */
        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            Statement statement = connection.createStatement();
            String sql = "select * from CommodityVotes where " +
                    "commodityId='" + commodity.getCommodityId() + "' and " +
                    "userId='" + user.getUsername() + "' and " +
                    "user='" + userType + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                int vote = resultSet.getInt("vote");
                userVoteText.setText(vote + ""); // user vote
//                starImageViews[vote - 1].setOpacity(1.0); // vote image
                voteId = resultSet.getInt("voteId"); // voteId

            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private boolean vote(int number) { // false if vote not applied
        /**user vote will be applied only if hasBought == true:
         * Inserts new vote and rate in related tables.
         * Updates page after insertion from tables.
         * */

        System.out.println("ProductionController.vote");
        System.out.println("number = " + number);
        String errorMessage = "";
        Color color = Color.RED;

        if (hasBought) {
            int newVote;
            int index = number - 1;
            if (starImageViews[index].getOpacity() == 1.0) { // deselect and vote = 0
                starImageViews[index].setOpacity(0.5); // change opacity
                newVote = 0;

            } else { // select new, deselect old and vote
                for (int i = 0; i < starImageViews.length; i++) {
                    starImageViews[i].setOpacity((i != index) ? 0.5 : 1);
                }
                newVote = number;
            }

            if (submitVote(newVote) && submitRate()) {
                updateVote();
                updateRatesAndVotes();
                errorMessage = "Vote successfully submitted.";
                color = Color.GREEN;

            } else {
                errorMessage = "Vote submission failed.";

            }

        } else if (user == null) {
            errorMessage = "You need to log in to vote if you have already bought this commodity.";

        } else {
            errorMessage = "You have not bought this commodity yet.";

        }
        showError(errorText, errorMessage, 5, color);


        return false;
    }

    private boolean submitRate() {
        /**
         * Calculate rate and update
         * */
        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            Statement statement = connection.createStatement();
            String sql = "SELECT sum(vote) as totalVotes, count(vote) as votes FROM CommodityVotes WHERE " +
                    "commodityId='" + commodity.getCommodityId() + "' and " +
                    "NOT vote='" + 0 + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                int votes = resultSet.getInt("votes"); // vote
                int totalVotes = resultSet.getInt("totalVotes"); // totalVotes
                float rate = (votes != 0 ? ((float) totalVotes) / votes : 0); // rate
                System.out.println("totalVotes = " + totalVotes);
                System.out.println("votes = " + votes);
                System.out.println("rate = " + rate);

                // Update ratio AllCommodities table
                if (rate != 0) {
                    String rateString  = new BigDecimal(rate).setScale(1, BigDecimal.ROUND_HALF_UP).toString();
                    sql = "UPDATE AllCommodities SET ratio='" + rateString + "' " +
                            "WHERE commodityId='" + commodity.getCommodityId() + "'";
                    int resultSet2 = statement.executeUpdate(sql);
                    if (resultSet2 != 1) {
                        throw new Exception("resultSet2 = " + resultSet2);
                    }

                    return true;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public void setCommodity(Commodity commodity) { // set commodity and its labels
        if (commodity == null) {
            throw new NullPointerException("commodity is null");
        }

        // 1- commodity object
        this.commodity = commodity;

        productLabel.setText(commodity.getTitle());
        productTooltip.setText(commodity.getTitle());
        typeText.setText(commodity.getType());
        priceText.setText(commodity.getPrice());
        brandText.setText(commodity.getBrand());
        rateText.setText(commodity.getRatio());
        availableText.setText(commodity.getNumber() + "");
        if (commodity.getImage() != null) {
            mainImageView.setImage(commodity.getImage());
        }
        if (commodity.getProperties() != null && !commodity.getProperties().equals("")) {
            detailsTextArea.setText(commodity.getProperties());
        }

        System.out.println("ProductionController.setCommodity");
        System.out.println(commodity);

        try { // 2- get others from database

            Connection connection = new DatabaseConnectionJDBC().getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM AllCommodities WHERE commodityId='" + commodity.getCommodityId() + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                System.out.println("isAuction = " + resultSet.getString("isAuction"));
                auctionText.setText(resultSet.getString("isAuction").equals("true") ? "Yes" : "No");
                dateText.setText(resultSet.getString("date"));

            } else {
                throw new Exception("commodity not exist");
            }

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }


//        rateText.setText(commodity.getRate());
//        availableText.setText(commodity.getNumber());
//        votesText.setText(commodity.getVotes());


    }


    public void setUser(User user) {
        /**- User is not null!
         * - Called once in initialization
         * - inits:
         * 1. Sets user obj & type
         * 2. Sets related components
         * 3. Determines hasBought value
         * - Vote == 0 is ignored and considered as no vote,
         * and has no effect in votes & rate calculations.
         * - Sets voteId if vote is found.
         * */

        // 1. user:
        this.user = user; // 1- obj

        if (user instanceof Customer) { // 2- userType
            userType = "customer";
        } else if (user instanceof Seller) {
            userType = "seller";
        } else if (user instanceof Admin) {
            userType = "admin";
        } else {
            try {
                throw new Exception("user type not exist.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


        // 2. labels:
        usernameLabel.setText(user.getUsername() + " (" + userType + ")"); // username

        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            Statement statement = connection.createStatement();
            String sql = "select * from CommodityVotes where " +
                    "commodityId='" + commodity.getCommodityId() + "' and " +
                    "userId='" + user.getUsername() + "' and " +
                    "user='"        + userType + "'";;
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                int vote = resultSet.getInt("vote");
                userVoteText.setText(vote + ""); // user vote
                starImageViews[vote - 1].setOpacity(1.0); // vote image
                voteId = resultSet.getInt("voteId"); // voteId

            }

            // 3. hasBought:
            sql = "select * from Purchases where " +
                    "userId='"      + user.getUsername()        + "' and " +
                    "user='"        + userType                  + "' and " +
                    "commodityId='" + commodity.getCommodityId() + "' ;";
            resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                hasBought = true; // hasBought
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }





    /**Just specify which image is clicked*/
    public void vote1() {
        vote(1);
    }
    public void vote2() {
        vote(2);
    }
    public void vote3() {
        vote(3);
    }
    public void vote4() {
        vote(4);
    }
    public void vote5() {
        vote(5);
    }


    private void showError(Text text, String message, int durationSeconds, Color color) {
        if (message.equals(""))
            return;
        text.setFill(color);
        text.setText(message);
        text.setVisible(true);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(durationSeconds),
                event -> text.setVisible(false)));
        timeline.play();
    }

}

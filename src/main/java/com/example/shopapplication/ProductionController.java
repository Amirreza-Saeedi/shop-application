package com.example.shopapplication;

import com.example.shopapplication.regex.MyRegex;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    public ListView<Comment> commentsListView;
    @FXML
    private Hyperlink homeHyperlink;
    @FXML
    private Button auctionButton;
    @FXML
    private TextArea detailsTextArea;
    @FXML
    private Label usernameLabel;
    @FXML
    private Tooltip productTooltip;
    @FXML
    private Text errorText;
    // comment tab
    @FXML
    private TextArea commentTextArea;
    @FXML
    private Label remainingCharsLabel;
    @FXML
    private Button sendButton;
    @FXML
    private Tab newCommentTab;
    // basket and add
    @FXML
    private Label basketLabel;
    @FXML
    private ImageView basketImageView;
    @FXML
    private ImageView addImageView;
    @FXML
    private Label inBasketLabel;

    private ObservableList<Comment> comments;
    private Commodity commodity;
    private User user;
    private String userType;
    private boolean hasBought; // true means user has signed in & bought the product
    private int voteId; // != 0 means user takes a place in CommodityVotes table and new changes will be applied in that place
    private int basketId; // != 0 not have any bast already
    private int currentCommodityInBasket;



    public ProductionController() {

    }

    public void loadComments() {
        /**
         * Called by clicking on comments tab.
         * Loads all comments.
         * */

        comments = FXCollections.observableArrayList();

        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            System.out.println("ProductionController.loadComments");
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Comments WHERE commodityId='" + commodity.getCommodityId() +
                    "' ORDER BY date DESC, time Desc";
            ResultSet resultSet = statement.executeQuery(sql);

            ArrayList<Comment> arrayList = new ArrayList<>();
            for (int i = 1; resultSet.next(); ++i) {
                String fullName = resultSet.getString("fullName");
                String message = resultSet.getString("message");
                String username = resultSet.getString("userId");
                String userType = resultSet.getString("user");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                String dateTime = resultSet.getString("date") + " " + resultSet.getString("time");
                LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
                String hasBought = resultSet.getString("hasBought");
                arrayList.add(new Comment(fullName, message, username, userType,
                            localDateTime, i, hasBought.equals("true")));

                System.out.println("hasBought = " + hasBought);
                System.out.println("fullName = " + fullName);
                System.out.println("message = " + message);
                System.out.println("username = " + username);
                System.out.println("localDateTime = " + localDateTime);
            }

            comments.addAll(arrayList);
            commentsListView.setItems(comments);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set cells
        commentsListView.setItems(comments);
        commentsListView.setCellFactory(listView -> new CommentCell());

        // images
        starImageViews = new ImageView[]{
                star1ImageView, star2ImageView, star3ImageView, star4ImageView, star5ImageView
        };

        // error text
        errorText.setVisible(false);

        // new comment
        commentTextArea.textProperty().addListener((observableValue, oldValue, newValue) -> {
            Pattern pattern = Pattern.compile(MyRegex.commentRegex);
            Matcher matcher = pattern.matcher(newValue);
            int remaining;
            final int LIMIT = 200;
            if (!matcher.matches()) {
                commentTextArea.setText(oldValue);
                remaining = LIMIT - oldValue.length();
            } else {
                remaining = LIMIT - newValue.length();
            }
            remainingCharsLabel.setText(remaining + "/" + LIMIT);
            sendButton.setDisable(remaining >= LIMIT);
        });

        // basket
        basketImageView.setVisible(false);
        basketLabel.setVisible(false);
    }

    public void toNewComment() {
        System.out.println("ProductionController.toNewComment");
    }

    public void send() {
        /**
         * todo directly in comment table or awaiting list?
         *
         * */
        System.out.println("ProductionController.send");
        String message = commentTextArea.getText().trim();

        // todo global zone
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String time = formatter.format(LocalTime.now());
        formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String date = formatter.format(LocalDate.now());

        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {

            // read from Purchases
            Statement statement = connection.createStatement();
            String sql = "select * from Purchases where " +
                    "commodityId='" + commodity.getCommodityId() + "' and userId='" + user.getUsername() + "' and " +
                    "user='" + userType + "'";
            ResultSet resultSet1 = statement.executeQuery(sql);
            boolean bought = false;
            if (resultSet1.next()) {
                bought = true;
            }
            System.out.println("bought = " + bought);

            // insert into Comments
            sql = "INSERT INTO Comments (userId, user, fullName, commodityId, message, date, time, hasBought) " +
                    "VALUES ('" + user.getUsername() + "','" + userType + "','" + user.getFullName() + "','" +
                    commodity.getCommodityId() + "','" + message + "','" + date + "','" + time + "','" + bought + "')";
            int resultSet2 = statement.executeUpdate(sql);

            if (resultSet2 != 1) { // failed
                showError(errorText, "Comment sending failed.", 5, Color.RED);
                throw new Exception("result set != 1");

            } else { // sent
                showError(errorText, "Comment successfully sent.", 5, Color.GREEN);
                commentTextArea.setEditable(false);
                sendButton.setDisable(true);
            }

        } catch (SQLException | ClassNotFoundException e) {
            showError(errorText, "Error in connecting to database", 5, Color.RED);
            System.err.println(e);
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }

    public void add() {
        System.out.println("ProductionController.add");
        if (user == null) { // user has to be singed in
            showError(errorText, "You need to sign in first.", 5, Color.RED);
            return;
        } else if (commodity.getAuctionId() != 0) {
            showError(errorText, "This commodity is on auction.", 5, Color.RED);
            return;
        }

        if (commodity.getNumber() > currentCommodityInBasket) {
            try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
                Statement statement = connection.createStatement();
                String sql;
                int resultSet;

                if (basketId == 0) { // insert
                    sql = "INSERT INTO Baskets (commodityId,number,userId,user) VALUES " +
                            "('" + commodity.getCommodityId() +
                            "','" + 1 +
                            "','" + user.getUsername() +
                            "','" + userType + "')";
                    resultSet = statement.executeUpdate(sql);

                    if (resultSet == 1) { // successful
                        initializeBasketIdAndNumber();
                        currentCommodityInBasket = 1;
                    } else {
                        throw new SQLException("resultSet = " + resultSet);
                    }

                } else { // update
                    sql = "UPDATE Baskets SET " +
                            "number='" + ++currentCommodityInBasket + "' where basketId='" + basketId + "'";
                    resultSet = statement.executeUpdate(sql);

                    if (resultSet != 1) { // failed
                        throw new SQLException("resultSet = " + resultSet);
                    }
                }

                // update commodity state in
                showError(errorText, commodity.getTitle() + " added to your basket.", 5, Color.GREEN);
                inBasketLabel.setText(currentCommodityInBasket + "");

            } catch (SQLException | ClassNotFoundException e) {
                showError(errorText, "Error in adding commodity to basket", 5, Color.RED);
                System.err.println(e);
                e.printStackTrace();
            }


        } else {
            showError(errorText, "This commodity is not available any more.", 5, Color.RED);
            System.out.println("commodity.getNumber() = " + commodity.getNumber());
        }

        loadBasket(); // at last
    }

    private void initializeBasketIdAndNumber() {
        /**
         * user is not null.
         * */
        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            Statement statement = connection.createStatement();
            String sql = "SELECT basketId,number from Baskets where " +
                    "commodityId='" + commodity.getCommodityId() + "' and " +
                    "userId='" + user.getUsername() + "' and " +
                    "user='" + userType + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                basketId = resultSet.getInt("basketId"); // basketId
                currentCommodityInBasket = resultSet.getInt("number"); // number
                System.out.println("basketId = " + basketId);
                System.out.println("currentCommodityInBasket = " + currentCommodityInBasket);
            }
        } catch (SQLException | ClassNotFoundException e) {
            showError(errorText, "Error in database connection occurred.", 5, Color.RED);
            throw new RuntimeException(e);
        }
    }

    public void toBasket() {
        try {
            Sound.basket();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }

        System.out.println("ProductionController.toBasket");
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
        stage.centerOnScreen();
    }

    public void setAll(Commodity commodity, User user) {
        /**commodity cant be null,
         * user may be null.
         * New comment tab settings.
         * Basket and add image view settings.
         * */
        this.commodity = commodity;
        this.user = user;
        loadCommodity(commodity.getCommodityId());

        if (user != null) {
            setUser(user);
            // comment
            newCommentTab.setDisable(false); // user may send comment
            newCommentTab.onSelectionChangedProperty().set(event -> { // clear text area if user has sent comment earlier
                if (!commentTextArea.isEditable()) {
                    commentTextArea.setText("");
                    commentTextArea.setEditable(true);
                }
            });
            // basket
            basketImageView.setVisible(true);
            basketLabel.setVisible(true);
            loadBasket();
            initializeBasketIdAndNumber();
            inBasketLabel.setText(currentCommodityInBasket + "");

        }

        updateRatesAndVotes();
    }

    private void loadBasket() {
        /**
         * Called after add and user is not null.
         * Calculate number of commodities in user basket,
         * and sets basket label.
         * */

        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            // read addition of all commodities user have in Baskets
            Statement statement = connection.createStatement();
            String sql = "SELECT sum(number) as sum FROM Baskets where " +
                    "userId='" + user.getUsername() + "' and " +
                    "user='" + userType + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) { // if has any basket
                basketLabel.setText(resultSet.getString("sum"));
            }



        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean submitVote(int newVote) { // todo exception needed?
        /**Inserts/updates user vote in table.
         * Sets voteId if insertion is successful.
         * */

        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {

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

        } catch (SQLException | ClassNotFoundException e) {
            showError(errorText, "Databse error.", 5, Color.RED);
            System.err.println(e);
            e.printStackTrace();
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

//    public void setCommodity(Commodity commodity) { // set commodity and its labels
//        if (commodity == null) {
//            throw new NullPointerException("commodity is null");
//        }
//
//        // 1- commodity object
//        this.commodity = commodity;
//
//        productLabel.setText(commodity.getTitle());
//        productTooltip.setText(commodity.getTitle());
//        typeText.setText(commodity.getType());
//        priceText.setText(commodity.getPrice());
//        brandText.setText(commodity.getBrand());
//        rateText.setText(commodity.getRatio());
//        availableText.setText(commodity.getNumber() + "");
//        if (commodity.getImage() != null) {
//            mainImageView.setImage(commodity.getImage());
//        }
//        if (commodity.getProperties() != null && !commodity.getProperties().equals("")) {
//            detailsTextArea.setText(commodity.getProperties());
//        }
//
//        System.out.println("ProductionController.setCommodity");
//        System.out.println(commodity);
//
//        try (Connection connection = new DatabaseConnectionJDBC().getConnection()){ // 2- get others from database
//            Statement statement = connection.createStatement();
//            String sql = "SELECT * FROM AllCommodities WHERE commodityId='" + commodity.getCommodityId() + "'";
//            ResultSet resultSet = statement.executeQuery(sql);
//
//            if (resultSet.next()) {
//                System.out.println("isAuction = " + resultSet.getString("isAuction"));
//                auctionText.setText(resultSet.getString("isAuction").equals("true") ? "Yes" : "No");
//                dateText.setText(resultSet.getString("date"));
//
//            } else {
//                throw new Exception("commodity not exist");
//            }
//
//        } catch (Exception e) {
//            System.out.println(e);
//            e.printStackTrace();
//        }
//
//
////        rateText.setText(commodity.getRate());
////        availableText.setText(commodity.getNumber());
////        votesText.setText(commodity.getVotes());
//
//
//    }

    private void loadCommodity(int commodityId) {
        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            Statement statement = connection.createStatement();
            String sql = "select * from allCommodities where commodityId='" + commodityId + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                String type = resultSet.getString("type");
                String brand = resultSet.getString("brand");
                String price = resultSet.getString("price");
                String ratio = resultSet.getString("ratio");
                String title = resultSet.getString("title");
                int number = resultSet.getInt("number");
                String date = resultSet.getString("date");
                String properties = resultSet.getString("properties");
                // todo image
                String sellerId = resultSet.getString("userName");
                int auctionId = resultSet.getInt("isAuction");

                Commodity c = new Commodity(type, price, ratio, brand, title, properties, date, number, commodityId, auctionId);

                typeText.setText(c.getType());
                brandText.setText(c.getBrand());
                priceText.setText(c.getPrice());
                rateText.setText(c.getRatio());
                productLabel.setText(c.getTitle());
                availableText.setText(c.getNumber() + "");
                dateText.setText(c.getDate());
                detailsTextArea.setText(c.getProperties());

                setAuctionNodes(c.getAuctionId());

                this.commodity = c;
            }


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setAuctionNodes(int auctionId) {
        System.out.println("auctionId = " + auctionId);
        System.out.println("user == null = " + user == null);
        auctionButton.setVisible(user != null && auctionId != 0);
        auctionText.setText((auctionId != 0) ? "Yes" : "No");
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
        this.user = user; // obj todo not a complete obj
        String table = "";

        if (user instanceof Customer) { // userType
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

            // CommodityVotes:
            String sql = "select * from CommodityVotes where " +
                    "commodityId='" + commodity.getCommodityId() + "' and " +
                    "userId='" + user.getUsername() + "' and " +
                    "user='"        + userType + "'";;
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                int vote = resultSet.getInt("vote");
                userVoteText.setText(vote + ""); // user vote
                if (vote > 0)
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

    public void toAuction() {
        System.out.println("ProductionController.toAuction");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("auction.fxml"));
            Parent root = loader.load();

            AuctionController controller = loader.getController();
            controller.setAll(user, userType, commodity);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            loadCommodity(commodity.getCommodityId());
//            loadBasket();

        } catch (IOException e) {
            throw new RuntimeException(e);
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

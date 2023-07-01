package com.example.shopapplication;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ProductionController implements Initializable {
    
    public ImageView mainImageView;
    
    public ImageView star1ImageView;
    
    public ImageView star2ImageView;
    
    public ImageView star3ImageView;
    
    public ImageView star4ImageView;
    
    public ImageView star5ImageView;
    
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
    private Label userTypeLabel;

    private ObservableList<Comment> comments;
    private Commodity commodity;
    private User user;
    private boolean canVote;
    private String userType;

    public ProductionController() {
        comments = FXCollections.observableArrayList();
        comments.addAll(
                new Comment("dsf", "ksfjld", "fsdlkj", "dkslfj", 3),
                new Comment("dsf", "ksfjld", "fsdlkj", "dkslfj", 3),
                new Comment("dsf", "ksfjld", "fsdlkj", "dkslfj", 3),
                new Comment("dsf", "ksfjld", "fsdlkj", "dkslfj", 3)
        );

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        commentsListView.setItems(comments);
        commentsListView.setCellFactory( listView -> new CommentCell());
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
        calculateVotes();
        if (user != null) {
            setUser(user);
            initializeUserVote();
        }
    }

    private void calculateVotes() {
        try {
            Connection connection = new DatabaseConnectionJDBC().getConnection();
            Statement statement = connection.createStatement();
//            String

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCommodity(Commodity commodity) { // set commodity and its labels
        if (commodity == null) {
            throw new NullPointerException("commodity is null");
        }

        // 1- commodity object
        this.commodity = commodity;

        productLabel.setText(commodity.getTitle());
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
            String sql = "SELECT * FROM AllCommodities WHERE Number='" + commodity.getNumber() +
                    "' AND title='" + commodity.getTitle() + "'"; // todo number must be enough
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

    private void initializeUserVote() {
        /* user is not null in this method
        *
        * */

        try {
            Connection connection = new DatabaseConnectionJDBC().getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * from CommodityVotes where " +
                    "commodityId='" + 1 + "' and " + // todo replace 1 with id
                    "userId='" + user.getUsername() + "' and " +
                    "user='" + userType + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) { // vote found
                userVoteText.setText(resultSet.getString("vote"));
            }


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(e);;
        }
    }

    public void setUser(User user) {
        /*user is not null
        *
        * */

        this.user = user;

        // user type:
        if (user instanceof Customer) {
            userType = "(customer)";
        } else if (user instanceof Seller) {
            userType = "(seller)";
        } else if (user instanceof Admin) {
            userType = "(admin)";
        } else {
            try {
                throw new Exception("user type not exist.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // labels:
        usernameLabel.setText(user.getUsername());
        userTypeLabel.setText(userType);
    }

    public void setUserVoteText() {
        // todo get user vote from votes table
    }

    public void refresh() {
    }

}

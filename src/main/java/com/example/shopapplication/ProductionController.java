package com.example.shopapplication;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
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

    private ObservableList<Comment> comments;
    private Commodity commodity;
    private User user;

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

    public void toHome() {
    }

//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage stage) throws Exception {
////        FXMLLoader loader = new FXMLLoader(getClass().getResource("production-page.fxml"));
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("comment-cell.fxml"));
//
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }

    public void setCommodity(Commodity commodity) { // set commodity and its labels
        if (commodity == null) {
            throw new NullPointerException("commodity is null");
        }

        this.commodity = commodity;

        productLabel.setText(commodity.getTitle());
        typeText.setText(commodity.getType());
        priceText.setText(commodity.getPrice());
        brandText.setText(commodity.getBrand());
//        rateText.setText(commodity.getRate());
//        availableText.setText(commodity.getNumber());
//        votesText.setText(commodity.getVotes());
        mainImageView.setImage(commodity.getImage());

    }

    public void setUser(User user) {

    }

    public void setUserVoteText() {
        // todo get user vote from votes table
    }

    public void refresh() {
    }
}

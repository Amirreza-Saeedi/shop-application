package com.example.shopapplication;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProductionController extends Application {
    @FXML
    private ImageView mainImageView;
    @FXML
    private ImageView star1ImageView;
    @FXML
    private ImageView star2ImageView;
    @FXML
    private ImageView star3ImageView;
    @FXML
    private ImageView star4ImageView;
    @FXML
    private ImageView star5ImageView;
    @FXML
    private Label productLabel;
    @FXML
    private Text typeText;
    @FXML
    private Text priceText;
    @FXML
    private Text brandText;
    @FXML
    private Text rateText;
    @FXML
    private Text dateText;
    @FXML
    private Text availableText;
    @FXML
    private Text auctionText;
    @FXML
    private Text votesText;
    @FXML
    private Text userVoteText;



    private Commodity commodity;
    private User user;

    public void add() {
    }

    public void toHome() {
    }

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {

    }

    public void setCommodity(Commodity commodity) { // set commodity and its labels
        if (commodity == null) {
            throw new NullPointerException("commodity is null");
        }

        this.commodity = commodity;

        productLabel.setText(commodity.getTitle());
        typeText.setText(commodity.getType());
        priceText.setText(commodity.getPrice());
        brandText.setText(commodity.getBrand());
        rateText.setText(commodity.getRate());
        availableText.setText(commodity.getNumber());
        votesText.setText(commodity.getVotes());
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

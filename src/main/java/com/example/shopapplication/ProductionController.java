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
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductionController extends Application implements Initializable {
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
    @FXML
    private ListView<Comment> commentsListView;

    private ObservableList<Comment> comments = FXCollections.observableArrayList();
    private Commodity commodity;
    private User user;

    public ProductionController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comments.addAll(new Comment("ali", "ali", "ali", "ali", 3));
        commentsListView.setItems(comments);
        commentsListView.setCellFactory(new CommentCellFactory());
    }

    public void add() {
    }

    public void toHome() {
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("production-page.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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

package com.example.shopapplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

// commentCell controller:
public class CommentCell extends ListCell<Comment> {
    // Define the UI components for your comment cell
//    @FXML
//    private Label nameLabel;
//    @FXML
//    private Label usernameLabel;
//    @FXML
//    private Label userTypeLabel;
//    @FXML
//    private Label commentTextLabel;
//    @FXML
//    private Button replyButton;
//    @FXML
//    private Button upvoteButton;
//    @FXML
//    private Button downvoteButton;

    @FXML
    private Label senderLabel;
    @FXML
    private Label  messageLabel;
    @FXML
    private Tooltip senderTooltip;
    @FXML
    private Label asLabel;
    @FXML
    private Button likeButton;
    @FXML
    private Button dislikeButton;
    @FXML
    private Label votesLabel;


    public CommentCell() {
        loadFXML();
    }

    private void loadFXML() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("comment-cell.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void updateItem(Comment comment, boolean empty) {
        super.updateItem(comment, empty);

        // Update the UI components of your cell with the data from the comment
        if (empty || comment == null) {
            setText(null);
            setGraphic(null);
        } else {
//            authorLabel.setText(comment.getAuthor());
//            commentTextLabel.setText(comment.getText());
            // ...
            senderLabel.setText(comment.getSender());
            senderTooltip.setText(comment.getUsername());
            messageLabel.setText(comment.getMessage());
            asLabel.setText(comment.getUserType());
            votesLabel.setText(comment.getVotes() + "");

            setGraphic(this);
        }
    }
}

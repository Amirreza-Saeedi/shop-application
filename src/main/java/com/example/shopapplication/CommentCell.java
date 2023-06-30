package com.example.shopapplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CommentCell extends ListCell<Comment> implements Initializable {
    public Label senderLabel;
    public Label  messageLabel;
    public Tooltip senderTooltip;
    public Label asLabel;
    public Button likeButton;
    public Button dislikeButton;
    public Label votesLabel;
    public AnchorPane pane;


    public CommentCell() {
    }

    private void loadFXML() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("comment-cell.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadFXML();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
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
            FXMLLoader loader = null;
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("comment-cell.fxml"));
                loader.setController(this);
                try {
                    loader.load();
                } catch (Exception e) {
                    System.err.println(e);
                    e.printStackTrace();
                }
            }

            senderLabel.setText(comment.getSender());
            senderTooltip.setText(comment.getUsername());
            messageLabel.setText(comment.getMessage());
            asLabel.setText(comment.getUserType());
            votesLabel.setText(comment.getVotes() + "");

            setText(null);
            setGraphic(pane);
        }
    }

    public void like() {
        System.out.println("CommentCell.like");
    }

    public void dislike() {
        System.out.println("CommentCell.dislike");
    }
}

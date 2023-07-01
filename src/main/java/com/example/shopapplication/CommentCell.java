package com.example.shopapplication;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CommentCell extends ListCell<Comment> implements Initializable {
    public Label senderLabel;
    public Tooltip senderTooltip;
    public AnchorPane pane;
    public Label dateTimeLabel;
    public Text messageText;
    public Label numberLabel;



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

            senderLabel.setText(comment.getFullName() + " (" + comment.getUserType() + ")");
            senderTooltip.setText(comment.getUsername());
            messageText.setText(comment.getMessage());
            dateTimeLabel.setText(comment.getLocalDateTime().toString());
            numberLabel.setText(comment.getNumber() + "- ");

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

package com.example.shopapplication;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class CommentCellFactory implements Callback<ListView<Comment>, ListCell<Comment>> {
    @Override
    public ListCell<Comment> call(ListView<Comment> commentListView) {
        return new CommentCell();
    }
}

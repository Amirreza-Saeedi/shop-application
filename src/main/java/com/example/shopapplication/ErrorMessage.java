package com.example.shopapplication;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class ErrorMessage {
    public static void showError(Label label, String message, int durationSeconds, Color color) {
        if (message.equals(""))
            return;
        label.setTextFill(color);
        label.setText(message);
        label.setVisible(true);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(durationSeconds),
                event -> label.setVisible(false)));
        timeline.play();
    }

    public static void showError(Text text, String message, int durationSeconds, Color color) {
        if (message.equals(""))
            return;
        text.setFill(color);
        text.setText(message);
        text.setVisible(true);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(durationSeconds),
                event -> text.setVisible(false)));
        timeline.play();
    }

    public static void showError(Label label, String message, Color color) {
        label.setTextFill(color);
        label.setText(message);
        label.setVisible(true);
    }
}

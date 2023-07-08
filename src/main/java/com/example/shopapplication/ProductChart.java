package com.example.shopapplication;

import java.time.LocalDate;
import java.time.Month;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class ProductChart extends Application {

    @Override
    public void start(Stage stage) {
        // Create the x-axis and y-axis for the chart
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        // Create the bar chart and set the axes
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Product Sales");
        xAxis.setLabel("Date");
        yAxis.setLabel("Amount");

        // Create the data series for the chart
        XYChart.Series<String, Number> dailySeries = new XYChart.Series<>();
        dailySeries.setName("Daily Sales");
        XYChart.Series<String, Number> monthlySeries = new XYChart.Series<>();
        monthlySeries.setName("Monthly Sales");
        XYChart.Series<String, Number> yearlySeries = new XYChart.Series<>();
        yearlySeries.setName("Yearly Sales");

        // Add data to the daily series
        dailySeries.getData().add(new XYChart.Data<>("2023-07-01", 50));
        dailySeries.getData().add(new XYChart.Data<>("2023-07-02", 70));
        dailySeries.getData().add(new XYChart.Data<>("2023-07-03", 90));
        dailySeries.getData().add(new XYChart.Data<>("2023-07-04", 110));
        dailySeries.getData().add(new XYChart.Data<>("2023-07-05", 80));
        dailySeries.getData().add(new XYChart.Data<>("2023-07-06", 60));
        dailySeries.getData().add(new XYChart.Data<>("2023-07-07", 40));

        // Add data to the monthly series
        monthlySeries.getData().add(new XYChart.Data<>("July", 500));
        monthlySeries.getData().add(new XYChart.Data<>("August", 700));
        monthlySeries.getData().add(new XYChart.Data<>("September", 900));

        // Add data to the yearly series
        yearlySeries.getData().add(new XYChart.Data<>("2021", 10000));
        yearlySeries.getData().add(new XYChart.Data<>("2022", 15000));
        yearlySeries.getData().add(new XYChart.Data<>("2023", 20000));

        // Add the data series to the bar chart
        barChart.getData().addAll(dailySeries, monthlySeries, yearlySeries);

        // Create the scene and add the bar chart to it
        Scene scene = new Scene(barChart, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
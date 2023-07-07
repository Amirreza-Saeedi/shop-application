package com.example.shopapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StorageChartController implements Initializable {
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    private XYChart.Series<String, Number> series = new XYChart.Series<>();
    private Storage storage;
    private ObservableList<String> list = FXCollections.observableArrayList();
    private ArrayList<Number> dataList = new ArrayList<>(30);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initComboBox();

        barChart.setTitle("Storage Properties Chart");
        xAxis.setLabel("Date");
        yAxis.setLabel("Amount");
    }

    public void setAll(Storage storage) {
        this.storage = storage;
        loadDaily();
    }

    private void initComboBox() {
        list.setAll("Daily", "Monthly", "Yearly");
        comboBox.setItems(list);
        comboBox.setValue(list.get(0));
    }

    public void load() {
        switch (comboBox.getValue()) {
            case "Daily" -> loadDaily();
            case "Monthly" -> loadMonthly();
            case "Yearly" -> loadYearly();
        }
    }

    private void loadDaily() {
        System.out.println("StorageChartController.loadDaily");
        series.setName("Daily Properties");


        series.getData().setAll(dataList);
    }

    private void loadMonthly() {
        System.out.println("StorageChartController.loadMonthly");
    }

    private void loadYearly() {
        System.out.println("StorageChartController.loadYearly");
    }
}

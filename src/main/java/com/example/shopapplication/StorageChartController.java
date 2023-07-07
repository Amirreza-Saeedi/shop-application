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

import java.net.JarURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private XYChart.Data<String, Number>[] dataDaily = new XYChart.Data[30];
    private XYChart.Data<String, Number>[] dataMonthly = new XYChart.Data[12];
    private XYChart.Data<String, Number>[] dataYearly = new XYChart.Data[5];

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

        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            Statement statement = connection.createStatement();
            LocalDate localDate = LocalDate.now();
            localDate = localDate.minusDays(29);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = localDate.format(formatter);
//            String sql = "select * from StorageProperties where storageId='" + storage.getId() + "' and daily>='" + date +
//                    "' order by daily asc";
            String sql = "select * from StorageProperties where storageId='" + storage.getId() +
                    "' order by daily asc LIMIT 30";

            ResultSet resultSet = statement.executeQuery(sql);
            for (int i = 0; resultSet.next()); ++i {
                double value = resultSet.getDouble("value");
                int amount = resultSet.getInt("amount");


            }


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        series.getData().setAll(dataDaily);
    }

    private void loadMonthly() {
        System.out.println("StorageChartController.loadMonthly");
    }

    private void loadYearly() {
        System.out.println("StorageChartController.loadYearly");
    }
}

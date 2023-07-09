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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    private Storage storage;
    private ObservableList<String> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initComboBox();

//        barChart.setTitle("Storage Properties Chart");
        xAxis.setLabel("Date");
        yAxis.setLabel("Value (1$)");
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
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Daily Properties");

        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            Statement statement = connection.createStatement();
            String sql = "select * from StorageProperties where storageId='" + storage.getId() +
                    "' order by daily asc LIMIT 30";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                double value = resultSet.getDouble("value");
                int amount = resultSet.getInt("amount");
                String date = resultSet.getString("daily");
                series.getData().add(new XYChart.Data<>(date + "(" + amount + ")", value));
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        barChart.getData().setAll(series);
    }

    private void loadMonthly() {
        System.out.println("StorageChartController.loadMonthly");
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Monthly Properties");

        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            Statement statement = connection.createStatement();
            String sql = "SELECT MAX(id) AS id, storageid, MAX(daily) AS daily, monthly, " +
                    "MAX(yearly) AS yearly, AVG(amount) AS amount, AVG(value) AS value " +
                    "FROM StorageProperties " +
                    "Where storageId='" + storage.getId() + "' " +
                    "GROUP BY monthly " +
                    "ORDER BY daily ASC LIMIT 12";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                double value = resultSet.getDouble("value");
                int amount = resultSet.getInt("amount");
                String date = resultSet.getString("monthly");
                series.getData().add(new XYChart.Data<>(date + "(" + amount + ")", value));
            }


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        barChart.getData().setAll(series);
    }

    private void loadYearly() {
        System.out.println("StorageChartController.loadYearly");
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Yearly Properties");

        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            Statement statement = connection.createStatement();
            String sql = "SELECT MAX(id) AS id, storageid, MAX(daily) AS daily, MAX(monthly) AS monthly, " +
                    "yearly, AVG(amount) AS amount, AVG(value) AS value " +
                    "FROM StorageProperties " +
                    "Where storageId='" + storage.getId() + "' " +
                    "GROUP BY yearly " +
                    "ORDER BY daily ASC LIMIT 10";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                double value = resultSet.getDouble("value");
                int amount = resultSet.getInt("amount");
                String date = resultSet.getString("yearly");
                series.getData().add(new XYChart.Data<>(date + "(" + amount + ")", value));
            }


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        barChart.getData().setAll(series);
    }
}

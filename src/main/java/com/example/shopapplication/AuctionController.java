package com.example.shopapplication;

import com.example.shopapplication.regex.MyRegex;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuctionController implements Initializable {
    @FXML
    private Button applyButton;
    @FXML
    private Button closeButton;
    @FXML
    private TextField titleTextField;
    @FXML
    private TextField endDateTextField;
    @FXML
    private TextField bidderTextField;
    @FXML
    private TextField numberTextField;
    @FXML
    private TextField reservePriceTextField;
    @FXML
    private TextField mostPriceTextField;
    @FXML
    private TextField buyerTextField;
    @FXML
    private TextField newTextField;
    @FXML
    private TextField addTextField;
    @FXML
    private TextField chargeTextField;
    @FXML
    private Label errorLabel;

    private String userType;
    private User user;
    private Commodity commodity;
    private Auction auction;
    private boolean finished = false;


    public void setAll(User user, String userType, Commodity commodity) {
        this.user = user;
        this.commodity = commodity;
        this.userType = userType;

        loadAll();

        // todo start timer
        setTimer();
    }

    private void setTimer() {

    }

    private void loadAll() {
        loadUser();
        loadAuction(); // todo check for dead 1
        loadCommodity();
        setFieldsDisable();
    }

    private void loadCommodity() {
        try (Connection connection = new DatabaseConnectionJDBC().getConnection()){
            Statement statement = connection.createStatement();
            String sql = "select * from allcommodities where commodityId='" + commodity.getCommodityId() + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                String title = resultSet.getString("title");
                String seller = resultSet.getString("userName");
                String number = resultSet.getString("number");
                titleTextField.setText(title);
                bidderTextField.setText(seller);
                numberTextField.setText(number);

            } else
                throw new SQLException("where is commodity?");

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAuction() {
        try (Connection connection = new DatabaseConnectionJDBC().getConnection()){
            Statement statement = connection.createStatement();
            String sql = "select * from auction where auctionId='" + commodity.getIsAuction() + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                // auction obj & nodes
                String date = resultSet.getString("date");
                endDateTextField.setText(date);
                int id = resultSet.getInt("auctionId");
                String buyerName = resultSet.getString("buyerUsername");
                buyerTextField.setText(buyerName);
                String buyerType = resultSet.getString("buyerType");
                int basePrice = resultSet.getInt("basePrice");
                reservePriceTextField.setText(basePrice + "");
                int mostPrice = resultSet.getInt("mostPrice");
                mostPriceTextField.setText(mostPrice + "");
                String endDateTime = resultSet.getString("date");
                endDateTextField.setText(endDateTime);

                auction = new Auction(id, buyerName, buyerType, basePrice, mostPrice, endDateTime);
                String nowDateTime = LocalDateTime.now().toString();

//                //
//
//                if (nowDateTime.compareTo(endDateTime.toString()) > 0) {
//
//                }
            }


        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
            System.err.println(e);
            e.printStackTrace();
            ErrorMessage.showError(errorLabel, "Auction is not available.", Color.RED);
        }

    }

    private void loadUser() {
        try(Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            Statement statement = connection.createStatement();
            String table = userType + "s";
            String sql = "SELECT charge from " + table + " where username='" + user.getUsername() + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                double charge = resultSet.getDouble("charge");
                user.setCharge(charge);
                System.out.println("charge = " + charge);
                chargeTextField.setText(charge + "");
            } else
                throw new SQLException("where is user");

        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
            System.err.println(e);
            e.printStackTrace();
            ErrorMessage.showError(errorLabel, "Error in user.", 5, Color.RED);

        }
    }

    private void setFieldsDisable() {
        if (auction.getBuyerName().equals(user.getUsername()) && auction.getBuyerType().equals(userType)) {
            newTextField.setDisable(true);
            addTextField.setDisable(false);
        } else {
            newTextField.setDisable(false);
            addTextField.setDisable(true);
        }
    }

    public void apply() {
        System.out.println("AuctionController.apply");
        // todo check for dead 2
        if (!checkIfExpired()) {
            if (!newTextField.isDisable())
                setNewBid();
            else {
                addToBid();
            }
            loadAll();
        } else {
            ErrorMessage.showError(errorLabel, "Auction is finished.", Color.RED);
        }

    }

    private void setNewBid() {
        String newBid = newTextField.getText();
        BigDecimal newDecimal = new BigDecimal(newBid);
        BigDecimal baseDecimal = BigDecimal.valueOf(auction.getBasePrice());
        BigDecimal mostDecimal = BigDecimal.valueOf(auction.getMostPrice());

        if (newDecimal.compareTo(baseDecimal) <= 0 || newDecimal.compareTo(mostDecimal) <= 0) {
            ErrorMessage.showError(errorLabel, "New bid must be greater than reserve/most bid.", Color.RED);

        } else if (newDecimal.intValue() > user.getCharge()) {
            ErrorMessage.showError(errorLabel, "Your charge is not enough.", Color.RED);

        } else {
            returnChargeToAccount();
            insertNewBid(newDecimal.intValue());
        }

    }

    private void insertAddBid(int addBid) {
        try(Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            // find user
            Statement statement = connection.createStatement();
            String table = userType + "s";
            String sql = "select * from " + table + " where username='" + user.getUsername() + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                // decrease charge
                int curCharge = resultSet.getInt("charge");
                int newCharge = curCharge - addBid;
                sql = "update " + table + " set charge='" + newCharge +
                        "' where username='" + user.getUsername() + "'";
                if (statement.executeUpdate(sql) == 0) {
                    ErrorMessage.showError(errorLabel, "Error occurred", Color.RED);
                    throw new RuntimeException();
                }


                // update auction
                sql = "update auction set buyerType='" + userType + "', buyerUsername='" + user.getUsername() +
                        "', mostPrice='" + (addBid + auction.getMostPrice()) + "' where auctionId='" + auction.getId() + "'";
                if (statement.executeUpdate(sql) == 0) {
                    ErrorMessage.showError(errorLabel, "Error occurred", Color.RED);
                    throw new RuntimeException();
                }


                ErrorMessage.showError(errorLabel, "New bid placed successfully.", Color.GREEN);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void insertNewBid(int newBid) {
        try(Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            // find user
            Statement statement = connection.createStatement();
            String table = userType + "s";
            String sql = "select * from " + table + " where username='" + user.getUsername() + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                // decrease charge
                int curCharge = resultSet.getInt("charge");
                int newCharge = curCharge - newBid;
                sql = "update " + table + " set charge='" + newCharge +
                        "' where username='" + user.getUsername() + "'";
                if (statement.executeUpdate(sql) == 0) {
                    ErrorMessage.showError(errorLabel, "Error occurred", Color.RED);
                    throw new RuntimeException();
                }


                // update auction
                sql = "update auction set buyerType='" + userType + "', buyerUsername='" + user.getUsername() +
                        "', mostPrice='" + newBid + "' where auctionId='" + auction.getId() + "'";
                if (statement.executeUpdate(sql) == 0) {
                    ErrorMessage.showError(errorLabel, "Error occurred", Color.RED);
                    throw new RuntimeException();
                }


                ErrorMessage.showError(errorLabel, "New bid placed successfully.", Color.GREEN);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void returnChargeToAccount() {
        if (auction.getMostPrice() > 0) { // todo if has buyer

            try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {

                // find user
                Statement statement = connection.createStatement();
                String table = auction.getBuyerType() + "s";
                String sql = "select * from " + table + " where username='" + auction.getBuyerName() + "'";
                ResultSet resultSet = statement.executeQuery(sql);

                if (resultSet.next()) {
                    // update user
                    int charge = resultSet.getInt("charge");
                    int newCharge = (int) (charge + auction.getMostPrice());
                    sql = "update " + table + " set charge='" + newCharge +
                            "' where username='" + auction.getBuyerName() + "'";
                    if (statement.executeUpdate(sql) > 0)
                        System.out.println("charge returned");
                    ;
                }

            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private void addToBid() {
        int addBid = Integer.parseInt(addTextField.getText());


        if (addBid > user.getCharge()) {
            ErrorMessage.showError(errorLabel, "Your charge is not enough.", Color.RED);

        } else {
            insertAddBid(addBid);
        }

    }

    private boolean checkIfExpired() {
        /*
        *
        *
        * */

        String now = LocalDateTime.now().toString();
        if (!finished && now.compareToIgnoreCase(auction.getEndDateTime()) > 0) {
            finished = true;
            setWinner();
            loadAll();

        }

        return finished;

    }



    private void setWinner() {
        /**
         * delete auction and update commodity
         * */
        try (Connection connection = new DatabaseConnectionJDBC().getConnection()){
            auction.setWinner(connection);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        System.out.println("AuctionController.close");
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLabel.setVisible(false);
        addChangeListener(newTextField);
        addChangeListener(addTextField);
    }

    private void addChangeListener(TextField tf) {
        tf.textProperty().addListener((observableValue, oldVal, newVal) -> {
            Pattern pattern = Pattern.compile(MyRegex.numberRegex);
            Matcher matcher = pattern.matcher(newVal);
            if (!matcher.matches() && !newVal.equals("")) {
                tf.setText(oldVal);
            }
            applyButton.setDisable(tf.getText().equals(""));
        });
    }
}

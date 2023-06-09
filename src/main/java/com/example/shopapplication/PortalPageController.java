package com.example.shopapplication;

import com.example.shopapplication.regex.MyRegex;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PortalPageController implements Initializable {
    @FXML
    private Label cash;
    @FXML
    private TextField cardNumberTextField;
    @FXML
    private TextField cvv2TextField;
    @FXML
    private TextField monthDateTextField;
    @FXML
    private TextField yearDateTextField;
    @FXML
    private TextField captchaTextField;
    @FXML
    private TextField cardPassTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private Button payButton;
    @FXML
    private Label errorsLabel;
    @FXML
            private Label paymentNotify;
    @FXML
            private Button backToHomeButton;
    @FXML
            private ImageView BankIcon;
    @FXML
            private Label info1;
    @FXML
            private Label info2;
    @FXML
            private Label info3;
    @FXML
    private Label info4;
    @FXML
            private Label info5;
    @FXML
    private Label info6;
    @FXML
            private Label info7;
    @FXML
    private Pane captchaPane;
    @FXML
    private Text captchaText;
    private String table;
    public boolean isWallet = false;
    private String discountCode;
    private User user;
    private ArrayList<Commodity> basketCommodities = new ArrayList<>();
    private  Captcha captcha;
    private double price;
    private Pattern emailPattern = Pattern.compile(MyRegex.emailRegex);
    private String userType;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Pattern numberPattern = Pattern.compile(MyRegex.numberRegex);
        cardNumberTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            Matcher matcher = numberPattern.matcher(newValue);
            if (!matcher.matches()) {
                cardNumberTextField.setText(oldValue);
            }
        });
        cardNumberTextField.textProperty().addListener((observableValue, s, t1) -> {
            cardNumberTextField.setStyle("-fx-border-color: none;");
        });
        cvv2TextField.textProperty().addListener((observable, oldValue, newValue) -> {
            Matcher matcher = numberPattern.matcher(newValue);
            if (!matcher.matches()) {
                cvv2TextField.setText(oldValue);
            }
        });
        cvv2TextField.textProperty().addListener((observableValue, s, t1) -> {
            cvv2TextField.setStyle("-fx-border-color: none;");
        });
        monthDateTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            Matcher matcher = numberPattern.matcher(newValue);
            if (!matcher.matches()) {
                monthDateTextField.setText(oldValue);
            }
        });
        monthDateTextField.textProperty().addListener((observableValue, s, t1) -> {
            monthDateTextField.setStyle("-fx-border-color: none;");
        });
        yearDateTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            Matcher matcher = numberPattern.matcher(newValue);
            if (!matcher.matches()) {
                yearDateTextField.setText(oldValue);
            }
        });
        yearDateTextField.textProperty().addListener((observableValue, s, t1) -> {
            yearDateTextField.setStyle("-fx-border-color: none;");
        });

        captchaTextField.textProperty().addListener((observableValue, s, t1) -> {
            captchaTextField.setStyle("-fx-border-color: none;");
        });
        cardPassTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            Matcher matcher = numberPattern.matcher(newValue);
            if (!matcher.matches()) {
                cardPassTextField.setText(oldValue);
            }
        });
        cardPassTextField.textProperty().addListener((observableValue, s, t1) -> {
            cardPassTextField.setStyle("-fx-border-color: none;");
        });
        emailTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            Matcher matcher = emailPattern.matcher(newValue);
            if (!matcher.matches()) {
                emailTextField.setStyle("-fx-border-color: red;");
            } else {
                emailTextField.setStyle("-fx-border-color: none;");
            }
        });

        captcha = new Captcha(60_000_000, 5);
        updateCaptcha();
    }
    public void updateCaptcha() { // change captcha CODE and color
        Random random = new Random();
        Color randomColor = new Color(random.nextDouble(), random.nextDouble(), random.nextDouble(), 1.0);
        captchaText.setFill(randomColor);
        captcha.newCode();
        captchaText.setText(captcha.getCode());
    }
    public void setPayOnAction(ActionEvent event){
        if (cardNumberTextField.getText().length() != 16) {
            errorsLabel.setText("incorrect card number");
            cardNumberTextField.setStyle("-fx-border-color: red;");
        } else if (cvv2TextField.getText().length() !=3) {
            errorsLabel.setText("incorrect cvv2");
            cvv2TextField.setStyle("-fx-border-color: red;");
        } else if (monthDateTextField.getText().length() != 2 || Integer.parseInt(monthDateTextField.getText()) < 1 || Integer.parseInt(monthDateTextField.getText()) > 12) {
            errorsLabel.setText("incorrect card month");
            monthDateTextField.setStyle("-fx-border-color: red;");
        } else if (yearDateTextField.getText().length() != 2 || Integer.parseInt(yearDateTextField.getText()) < 0) {
             errorsLabel.setText("incorrect card year");
             yearDateTextField.setStyle("-fx-border-color: red;");
        }else if (captchaTextField.getText() == null){
            errorsLabel.setText("fill the captcha blank correct");
            captchaTextField.setStyle("-fx-border-color: red;");
        } else if (cardPassTextField.getText().length() != 8) {
            errorsLabel.setText("fill the password blank");
            cardPassTextField.setStyle("-fx-border-color: red;");
        } else if (!emailPattern.matcher(emailTextField.getText()).matches() || emailTextField.getText() == null) {
            errorsLabel.setText("fill the email blank correct");
            emailTextField.setStyle("-fx-border-color: red;");
        } else if (captcha.isExpired()) {
            errorsLabel.setText("captcha is expired");
            captchaTextField.setStyle("-fx-border-color: red;");
        } else if (!captchaTextField.getText().equalsIgnoreCase(captcha.getCode())) {
            errorsLabel.setText("Captcha mismatched.");
            captchaTextField.setStyle("-fx-border-color: red;");
        } else {
                Statement stmt;
                PreparedStatement pstmt;
                ResultSet rs;
                String sql;
                try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
                    if (!isWallet){
                        for (int i = 0; i < basketCommodities.size(); i++) {
                            int id = basketCommodities.get(i).getCommodityId();
                            int number = basketCommodities.get(i).getNumber();
                            sql = "SELECT * FROM AllCommodities WHERE commodityId = " + id;
                            stmt = connection.createStatement();
                            rs = stmt.executeQuery(sql);
                            while (rs.next()) {
                                int lastNum = rs.getInt("Number");
                                number = lastNum - number;
                            }
                            sql = "UPDATE AllCommodities SET Number = " + number + " WHERE commodityId = " + id;
                            pstmt = connection.prepareStatement(sql);
                            pstmt.executeUpdate();

                            //update or insert price in sellersChart table
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM");
                            String date = formatter.format(LocalDate.now());
                            sql = "SELECT * FROM sellersChart WHERE date = '" + date + "' AND userName = '" + basketCommodities.get(i).getSellerId() + "'";
                            rs = stmt.executeQuery(sql);
                            String dateTest = "";
                            String price;
                            String lastPrice;
                            while (rs.next()) {
                                dateTest = rs.getString("date");
                                if (dateTest.equals(date)) {
                                    //update cost
                                    if (user instanceof Seller) {
                                        lastPrice = rs.getString("cost");
                                    }else{
                                        lastPrice = rs.getString("income");
                                    }
                                    double p = Double.parseDouble(basketCommodities.get(i).getPrice()) + Double.parseDouble(lastPrice);
                                    price = String.valueOf(p);
                                    if (user instanceof Seller) {
                                        sql = "UPDATE sellersChart SET cost = '" + price + "' WHERE date = '" + date
                                                + "' AND userName = '" + user.getUsername() + "';";
                                    } else  {
                                        sql = "UPDATE sellersChart SET income = '" + price + "' WHERE date = '" + date
                                                + "' AND userName = '" + basketCommodities.get(i).getSellerId() + "'";
                                    }
                                    pstmt = connection.prepareStatement(sql);
                                    pstmt.executeUpdate();
                                    break;
                                }
                            }
                            if (!dateTest.equals(date)) {
                                //insert cost
                                if (user instanceof Seller) {
                                    sql = "INSERT INTO sellersChart (userName, cost, date) VALUES ('" + user.getUsername()
                                            + "', '" + basketCommodities.get(i).getPrice() + "', '" + date + "')";
                                } else {
                                    sql = "INSERT INTO sellersChart (userName, income, date) VALUES ('" + basketCommodities.get(i).getSellerId()
                                            + "', '" + basketCommodities.get(i).getPrice() + "', '" + date + "')";
                                }
                                stmt.executeUpdate(sql);
                            }
                            sql = "DELETE FROM Baskets WHERE basketId = " + basketCommodities.get(i).getBasketId();
                            pstmt = connection.prepareStatement(sql);
                            pstmt.executeUpdate();

                            LocalDateTime localDateTime = LocalDateTime.now();
                            String date1 = localDateTime.toString();
                            sql = "INSERT INTO Purchases (commodityId, userId, date, user, discountCode, Type, Number, PriceOfOne, Brand) VALUES ("
                                    + basketCommodities.get(i).getCommodityId() + ", '" + user.getUsername() + "', '"
                                    + date + "', '" + user.toString() + "', '" + discountCode + "', '" + basketCommodities.get(i).getType()
                                    + "', " + basketCommodities.get(i).getNumber() + ", '" + basketCommodities.get(i).getPrice()
                                    + "', '" + basketCommodities.get(i).getBrand() + "')";
                            stmt.executeUpdate(sql);


                            logPurchase(basketCommodities.get(i), connection);
                        }


                }else {
                        double newPrice = price + user.getCharge();
                        user.setCharge(newPrice);
                         sql = "UPDATE " + table + " SET charge = ? WHERE username = ?";
                         pstmt = connection.prepareStatement(sql);
                         pstmt.setInt(1, (int) newPrice);
                         pstmt.setString(2,user.getUsername());
                         pstmt.executeUpdate();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }

                info1.setVisible(false);
                info2.setVisible(false);
                info3.setVisible(false);
                info4.setVisible(false);
                info5.setVisible(false);
                info6.setVisible(false);
                info7.setVisible(false);
                captchaPane.setVisible(false);
                payButton.setVisible(false);
                cash.setVisible(false);
                BankIcon.setVisible(false);
                errorsLabel.setVisible(false);
                cardNumberTextField.setVisible(false);
                cvv2TextField.setVisible(false);
                monthDateTextField.setVisible(false);
                yearDateTextField.setVisible(false);
                captchaTextField.setVisible(false);
                cardPassTextField.setVisible(false);
                emailTextField.setVisible(false);
                paymentNotify.setVisible(true);
                backToHomeButton.setVisible(true);

        }
    }

    private void logPurchase(Commodity commodity, Connection connection)  {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String sql = "select storageId,userName from allcommodities where commodityId = '" + commodity.getCommodityId() + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                int storageId = resultSet.getInt("storageId");
                String sellerId = resultSet.getString("userName");
                StorageLog.logPurchase(storageId, commodity.getNumber(),
                        Double.parseDouble(commodity.getPrice()) * commodity.getNumber(),
                        sellerId, user.getUsername(), userType, connection);
            } else {

                    throw new Exception("log error");

            }
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }

    public void setBackToHomeButtonOnAction(ActionEvent event) throws IOException {
        new Login(user).loginToHome((Node) event.getSource());
    }


    public void setUser(User user) {
        if (user == null) {
            throw new NullPointerException("User is null");
        }
        this.user = user;
        if (user instanceof Admin){
            userType = "admin";
            table = "Admins";
        } else if (user instanceof Seller) {
            userType = "seller";
            table = "Sellers";
        } else if (user instanceof Customer) {
            userType = "customer";
            table = "Customers";
        }
    }
    public void setCommoditiesPortalPage(ArrayList<Commodity> commodities){
        this.basketCommodities.addAll(commodities);
    }
    public void setDiscountCode(String discountCode){
        this.discountCode = discountCode;
    }
    public void setPricePortalPage(double price){
        this.price = price;
        cash.setText(String.valueOf(price) + "$");
    }
}

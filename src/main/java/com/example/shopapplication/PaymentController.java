package com.example.shopapplication;

import com.example.shopapplication.regex.MyRegex;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.IntBinaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaymentController implements Initializable {
    @FXML
    private Button payBtn;
    @FXML
    private TextArea address;
    @FXML
    private TextField telephone;
    @FXML
    private TextField discountCode;
    @FXML
    private Label priceLabel;
    @FXML
    private Button submit;
    @FXML
    private Label errorLabel;
    @FXML
    private Button edit;
    private User user;
    private String discount = null;

    private double price;
    private double currentPrice;
    private ArrayList<Commodity> commodities = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Pattern numberPattern = Pattern.compile(MyRegex.numberRegex);
        telephone.textProperty().addListener((observableValue, oldValue, newValue) -> {
            Matcher matcher = numberPattern.matcher(newValue);
            if (!matcher.matches()) {
                telephone.setText(oldValue);
            }
        });
    }
    public void setEditButtonOnAction(){
        submit.setVisible(true);
        edit.setVisible(false);
        address.setEditable(true);
        telephone.setEditable(true);
        discountCode.setEditable(true);
    }

    public void setSubmitOnAction(){
        if (address.getText().length() == 0 ){
            payBtn.setVisible(false);
            errorLabel.setText("write your address");
            address.setStyle("-fx-border-color: red;");
        } else if (telephone.getText().length() != 11) {
            payBtn.setVisible(false);
            errorLabel.setText("wrong number phone");
            telephone.setStyle("-fx-border-color: red;");
        }else if (discountCode.getText().length() !=0){
            try(Connection connection = new DatabaseConnectionJDBC().getConnection()){
                double percent;
                String code;
                String sql = "SELECT * FROM discountCodes";
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                boolean isCorrect = false;
                outerLoop:while(rs.next()){
                    code = rs.getString("code");
                    if (code.equals(discountCode.getText())){
                        String sql2 = "SELECT * FROM Purchases WHERE userId = '" + user.getUsername()
                                + "' AND user = '" + user.toString() + "'";
                        rs = stmt.executeQuery(sql2);
                        while (rs.next()){
                            String code1 = rs.getString("discountCode");
                            if (code1.equals(code)){
                                isCorrect = false;
                                break outerLoop;
                            }
                        }
                        discount = discountCode.getText();
                        rs = stmt.executeQuery(sql);
                        percent = rs.getInt("percent");
                        isCorrect = true;
                        percent = 100.0 - percent;
                        currentPrice = (percent/100.0) * price;
                        priceLabel.setText(String.valueOf(currentPrice));
                        address.setEditable(false);
                        telephone.setEditable(false);
                        discountCode.setEditable(false);
                        submit.setVisible(false);
                        edit.setVisible(true);
                        payBtn.setVisible(true);
                        break;
                    }
                }
                if (!isCorrect){
                    payBtn.setVisible(false);
                    submit.setVisible(true);
                    edit.setVisible(false);
                    errorLabel.setText("Wrong discount code!");
                    discountCode.setStyle("-fx-border-color: red;");
                }
            }catch (SQLException | ClassNotFoundException e){
                throw new RuntimeException(e);
            }
        }else{
            currentPrice = price;
            discountCode.setStyle("-fx-border-color: none;");
            address.setStyle("-fx-border-color: none;");
            telephone.setStyle("-fx-border-color: none;");
            errorLabel.setText("");
            address.setEditable(false);
            telephone.setEditable(false);
            discountCode.setEditable(false);
            edit.setVisible(true);
            submit.setVisible(false);
            payBtn.setVisible(true);
        }
    }
    public void backToHome(ActionEvent event) throws IOException {
        new Login(user).loginToHome((Node) event.getSource());
    }
    public void backToBasket(ActionEvent event){
        Node node = (Node) event.getSource();
        FXMLLoader loader = new FXMLLoader(Login.class.getResource("basket.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BasketController basketController = loader.getController();
        basketController.setUser(user);
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
    }
    public void setPayBtnOnAction(ActionEvent event){
        Node node = (Node) event.getSource();
        FXMLLoader loader = new FXMLLoader(Login.class.getResource("portalPage.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PortalPageController portalPageController = loader.getController();
        portalPageController.setUser(user);
        portalPageController.setDiscountCode(discount);
        portalPageController.setPricePortalPage(currentPrice);
        portalPageController.isWallet = false;
        portalPageController.setCommoditiesPortalPage(commodities);
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
    }
    public void setPricePaymentPage(double price){
        this.price = price;
        priceLabel.setText(String.valueOf(price));
    }
    public void setUser(User user){
        if (user == null){
            throw new NullPointerException("User is null");
        }
        this.user = user;
    }
    public void setCommoditiesPaymentPage(ArrayList<Commodity> commodities){
        this.commodities.addAll(commodities);
    }

}

package com.example.shopapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.*;

public class HomeController implements Initializable {
    private Stage stage;
    @FXML
    private ListView<String> groupingList;
    private String[] commodity = {"All Commodities","Grocery" , "Break fast","Protein","Dairy","Fruit and Vegetables","Snacks"};
    @FXML
    private Button loginbutton;
    @FXML
    private AnchorPane anchorPane00;
    @FXML
   private AnchorPane anchorPane10;
    @FXML
    private AnchorPane anchorPane20;
    @FXML
    private AnchorPane anchorPane30;
    @FXML
    private AnchorPane anchorPane40;
    @FXML
    private AnchorPane anchorPane50;
    @FXML
    private AnchorPane anchorPane60;
    @FXML
    private AnchorPane anchorPane01;
    @FXML
    private AnchorPane anchorPane11;
    @FXML
    private AnchorPane anchorPane21;
    @FXML
    private AnchorPane anchorPane31;
    @FXML
    private AnchorPane anchorPane41;
    @FXML
    private AnchorPane anchorPane51;
    @FXML
    private AnchorPane anchorPane61;
    @FXML
    private Label title00;
    @FXML
    private Label title10;
    @FXML
    private Label title20;
    @FXML
    private Label title30;
    @FXML
    private Label title40;
    @FXML
    private Label title50;
    @FXML
    private Label title60;
    @FXML
    private Label title01;
    @FXML
    private Label title11;
    @FXML
    private Label title21;
    @FXML
    private Label title31;
    @FXML
    private Label title41;
    @FXML
    private Label title51;
    @FXML
    private Label title61;
    @FXML
    private Label price00;
    @FXML
    private Label price10;
    @FXML
    private Label price20;
    @FXML
    private Label price30;
    @FXML
    private Label price40;
    @FXML
    private Label price50;
    @FXML
    private Label price60;
    @FXML
    private Label price01;
    @FXML
    private Label price11;
    @FXML
    private Label price21;
    @FXML
    private Label price31;
    @FXML
    private Label price41;
    @FXML
    private Label price51;
    @FXML
    private Label price61;
    @FXML
    private Label ratio00 ;
    @FXML
    private Label ratio10;
    @FXML
   private Label ratio20;
    @FXML
    private Label ratio30;
    @FXML
    private Label ratio40;
    @FXML
    private Label ratio50;
    @FXML
    private Label ratio60;
    @FXML
    private Label ratio01;
    @FXML
    private Label ratio11;
    @FXML
    private Label ratio21;
    @FXML
    private Label ratio31;
    @FXML
    private Label ratio41;
    @FXML
    private Label ratio51;
    @FXML
    private Label ratio61;
    @FXML
    private TextField search;
//    @FXML
//    private ImageView imageView00;
//    @FXML
//    private ImageView imageView10;
//    @FXML
//    private ImageView imageView20;
//    @FXML
//    private ImageView imageView30;
//    @FXML
//    private ImageView imageView40;
//    @FXML
//    private ImageView imageView50;
//    @FXML
//    private ImageView imageView60;
//    @FXML
//    private ImageView imageView01;
//    @FXML
//    private ImageView imageView11;
//    @FXML
//    private ImageView imageView21;
//    @FXML
//    private ImageView imageView31;
//    @FXML
//    private ImageView imageView41;
//    @FXML
//    private ImageView imageView51;
//    @FXML
//    private ImageView imageView61;

    @FXML
    private Label number00 ;
    @FXML
    private Label number10;
    @FXML
    private Label number20;
    @FXML
    private Label number30;
    @FXML
    private Label number40;
    @FXML
    private Label number50;
    @FXML
    private Label number60;
    @FXML
    private Label number01;
    @FXML
    private Label number11;
    @FXML
    private Label number21;
    @FXML
    private Label number31;
    @FXML
    private Label number41;
    @FXML
    private Label number51;
    @FXML
    private Label number61;
    @FXML
    private GridPane gridPane;
    @FXML
    private ChoiceBox<String> choiceFilter;
    @FXML
    private Label showGroupLabel;
    @FXML
    private Button goToPreviousPageButton;
    @FXML
    private Button goToNextPageButton;
    @FXML
    private Label page;
    @FXML
    private ChoiceBox<String> brandFilter;
    @FXML
    private Label brandName;
    @FXML
    private CheckBox isAuction;
    @FXML
    private Button placeAuction;
    @FXML
    private Button productRegistration;
    @FXML
    private Label typeInfo;
    @FXML
    private Button manageCommodities;
    private String choiceBoxOption = "Filter";
    private String groupListItem  = "All Commodities";
    private String brandListItem;
    private String orderBy;
    private boolean isLowToHigh;
    private User user;
    private ObservableList<String> choiceBoxOptions =
            FXCollections.observableArrayList("Clear filters","Cheapest to most expensive", "Most expensive to cheapest", "Based on points");
//     private AnchorPane[] anchorPanes = new AnchorPane[14];
//    private Label[] number = new Label[14];
//    private Label[] title = new Label[14];
//    private Label[] price = new Label[14];
//    private Label[] ratio = new Label[14];

    ArrayList<Commodity> commodities = new ArrayList<>();
    int arraySizeCounter = 0;

    private Connection connection = null;

    public static void toHome(Node node) throws IOException {
        FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("home.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    public void setUser(User user){
        if (user == null){
            throw new NullPointerException("User is null");
        }
        this.user = user;

        if (user instanceof Seller){
            placeAuction.setVisible(true);
            productRegistration.setVisible(true);
            manageCommodities.setVisible(true);

            typeInfo.setText("you are a seller!");
        }
    }
    private void hideAnchorPanes(){
        anchorPane00.setVisible(false);
        anchorPane10.setVisible(false);
        anchorPane20.setVisible(false);
        anchorPane30.setVisible(false);
        anchorPane40.setVisible(false);
        anchorPane50.setVisible(false);
        anchorPane60.setVisible(false);
        anchorPane01.setVisible(false);
        anchorPane11.setVisible(false);
        anchorPane21.setVisible(false);
        anchorPane31.setVisible(false);
        anchorPane41.setVisible(false);
        anchorPane51.setVisible(false);
        anchorPane61.setVisible(false);
    }
    private void showAnchorPanes(int page){
        hideAnchorPanes();
        page = (page*14) - 14;
        if (page < commodities.size()){
            anchorPane00.setVisible(true);
//            imageView00.setImage(images.get(page));
            number00.setText("Number: " + commodities.get(page).number);
            ratio00.setText(commodities.get(page).getRatio());
            price00.setText(commodities.get(page).getPrice());
            title00.setText(commodities.get(page).getTitle());
            page++;
        } else return;
        if (page < commodities.size()){
            anchorPane10.setVisible(true);
//            imageView10.setImage(images.get(page));
            number10.setText("Number: " + commodities.get(page).number);
            ratio10.setText(commodities.get(page).getRatio());
            price10.setText(commodities.get(page).getPrice());
            title10.setText(commodities.get(page).getTitle());
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane20.setVisible(true);
//            imageView20.setImage(images.get(page));
            number20.setText("Number: " + commodities.get(page).number);
            ratio20.setText(commodities.get(page).getRatio());
            price20.setText(commodities.get(page).getPrice());
            title20.setText(commodities.get(page).getTitle());
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane30.setVisible(true);
//            imageView30.setImage(images.get(page));
            number30.setText("Number: " + commodities.get(page).number);
            ratio30.setText(commodities.get(page).getRatio());
            price30.setText(commodities.get(page).getPrice());
            title30.setText(commodities.get(page).getTitle());
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane40.setVisible(true);
//            imageView40.setImage(images.get(page));
            number40.setText("Number: " + commodities.get(page).number);
            ratio40.setText(commodities.get(page).getRatio());
            price40.setText(commodities.get(page).getPrice());
            title40.setText(commodities.get(page).getTitle());
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane50.setVisible(true);
//            imageView50.setImage(images.get(page));
            number50.setText("Number: " + commodities.get(page).number);
            ratio50.setText(commodities.get(page).getRatio());
            price50.setText(commodities.get(page).getPrice());
            title50.setText(commodities.get(page).getTitle());
            page++;
        } else return;
        if (page < commodities.size()){
            anchorPane60.setVisible(true);
//            imageView60.setImage(images.get(page));
            number60.setText("Number: " + commodities.get(page).number);
            ratio60.setText(commodities.get(page).getRatio());
            price60.setText(commodities.get(page).getPrice());
            title60.setText(commodities.get(page).getTitle());
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane01.setVisible(true);
//            imageView01.setImage(images.get(page));
            number01.setText("Number: " + commodities.get(page).number);
            ratio01.setText(commodities.get(page).getRatio());
            price01.setText(commodities.get(page).getPrice());
            title01.setText(commodities.get(page).getTitle());
            page++;
        } else return;
        if (page < commodities.size()){
            anchorPane11.setVisible(true);
//            imageView11.setImage(images.get(page));
            number11.setText("Number: " + commodities.get(page).number);
            ratio11.setText(commodities.get(page).getRatio());
            price11.setText(commodities.get(page).getPrice());
            title11.setText(commodities.get(page).getTitle());
            page++;
        } else return;
        if (page < commodities.size()){
            anchorPane21.setVisible(true);
//            imageView21.setImage(images.get(page));
            number21.setText("Number: " + commodities.get(page).number);
            ratio21.setText(commodities.get(page).getRatio());
            price21.setText(commodities.get(page).getPrice());
            title21.setText(commodities.get(page).getTitle());
            page++;
        } else return;
        if (page < commodities.size()){
            anchorPane31.setVisible(true);
//            imageView31.setImage(images.get(page));
            number31.setText("Number: " + commodities.get(page).number);
            ratio31.setText(commodities.get(page).getRatio());
            price31.setText(commodities.get(page).getPrice());
            title31.setText(commodities.get(page).getTitle());
            page++;
        } else return;
        if (page < commodities.size()){
            anchorPane41.setVisible(true);
//            imageView41.setImage(images.get(page));
            number41.setText("Number: " + commodities.get(page).number);
            ratio41.setText(commodities.get(page).getRatio());
            price41.setText(commodities.get(page).getPrice());
            title41.setText(commodities.get(page).getTitle());
            page++;
        } else return;
        if (page < commodities.size()){
            anchorPane51.setVisible(true);
//            imageView51.setImage(images.get(page));
            number51.setText("Number: " + commodities.get(page).number);
            ratio51.setText(commodities.get(page).getRatio());
            price51.setText(commodities.get(page).getPrice());
            title51.setText(commodities.get(page).getTitle());
            page++;
        } else return;
        if (page < commodities.size()){
            anchorPane61.setVisible(true);
//            imageView61.setImage(images.get(page));
            number61.setText("Number: " + commodities.get(page).number);
            ratio61.setText(commodities.get(page).getRatio());
            price61.setText(commodities.get(page).getPrice());
            title61.setText(commodities.get(page).getTitle());
            page++;
        }
        return;

    }
    private void selectCommoditiesBySearch(String group,String orderBy,boolean isLowToHigh, String brand,String searchedItem){
        hideAnchorPanes();
        page.setText("1");
        int count = 0;
        String sql;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/database.db";
            Connection conn = DriverManager.getConnection(url);
            if (brand.equals("Brands") || brand.equals("All brands")) {
                if (isLowToHigh == false) {
                    if (!group.equals("AllCommodities"))
                    sql = "SELECT * FROM AllCommodities WHERE groupp = " + "'"+ group + "'"+" ORDER BY " + orderBy + " desc";
                    else sql = sql = "SELECT * FROM AllCommodities ORDER BY " + orderBy + " desc";
                } else {
                    if (!group.equals("AllCommodities"))
                    sql = "SELECT * FROM AllCommodities WHERE groupp = " + "'"+ group + "'"+" ORDER BY " + orderBy + " ASC";
                    else sql = "SELECT * FROM AllCommodities ORDER BY " + orderBy + " ASC";
                }
            }else {
                if (isLowToHigh == false) {
                    if (!group.equals("AllCommodities"))
                    sql ="SELECT * FROM AllCommodities WHERE Brand = " + "'"+ brand +"'" +" AND groupp = " + "'" + group + "'" + " ORDER BY " + orderBy + " desc";
                    else sql = "SELECT * FROM AllCommodities WHERE Brand = " + "'" + brand + "'" + " ORDER BY " + orderBy  + " desc";
                }else {
                    if (!group.equals("AllCommodities"))
                    sql ="SELECT * FROM AllCommodities WHERE Brand = " + "'"+ brand +"'" +" AND groupp = " + "'" + group + "'" + " ORDER BY " + orderBy + " ASC";
                    else sql = "SELECT * FROM AllCommodities WHERE Brand = " + "'" + brand + "'" + " ORDER BY " + orderBy  + " ASC";
                }
            }
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            commodities.clear();
            while (rs.next()) {
                String type1 = rs.getString("Type");
                String brand1 = rs.getString("Brand");
                if (type1.indexOf(searchedItem) != -1 || brand1.indexOf(searchedItem) != -1){
                    int number1 = rs.getInt("Number");
                    if (number1 > 0) {
                        String price1 = rs.getString("Price");
                        String ratio1 = rs.getString("Ratio");
                        String title1 = rs.getString("Title");
                        commodities.add(new Commodity(type1,brand1,price1,ratio1,title1,number1));

                        String date = rs.getString("Date");
                        System.out.println("Type = " + type1 + ", Brand = " + brand1 + ", Price = " + price1 + " Ratio = " + ratio1 + " Title = " + title1 + " Num = " + number1 + " Date = " + date);


                    }
//                     title[count].setText(title1);
//                     if (number1 > 0)
//                         anchorPanes[count].setVisible(true);
//                     number[count].setText("Number: " + String.valueOf(number1));
//                     ratio[count].setText(ratio1);
//                     price[count].setText(price1);
//                     switch (count) {
//                         case 0:
//                             anchorPane00 = anchorPanes[count];
//                             number00 = number[count];
//                             title00 = title[count];
//                             ratio00 = ratio[count];
//                             price00 = price[count];
//                             break;
//                         case 1:
//                             anchorPane10 = anchorPanes[count];
//                             number10 = number[count];
//                             title10 = title[count];
//                             ratio10 = ratio[count];
//                             price10 = price[count];
//                             break;
//                         case 2:
//                             anchorPane20 = anchorPanes[count];
//                             number20 = number[count];
//                             title20 = title[count];
//                             ratio20 = ratio[count];
//                             price20 = price[count];
//                             break;
//                         case 3:
//                             anchorPane30 = anchorPanes[count];
//                             number30 = number[count];
//                             title30 = title[count];
//                             ratio30 = ratio[count];
//                             price30 = price[count];
//                             break;
//                         case 4:
//                             anchorPane40 = anchorPanes[count];
//                             number40 = number[count];
//                             title40 = title[count];
//                             ratio40 = ratio[count];
//                             price40 = price[count];
//                             break;
//                         case 5:
//                             anchorPane50 = anchorPanes[count];
//                             number50 = number[count];
//                             title50 = title[count];
//                             ratio50 = ratio[count];
//                             price50 = price[count];
//                             break;
//                         case 6:
//                             anchorPane60 = anchorPanes[count];
//                             number60 = number[count];
//                             title60 = title[count];
//                             ratio60 = ratio[count];
//                             price60 = price[count];
//                             break;
//                         case 7:
//                             anchorPane01 = anchorPanes[count];
//                             number01 = number[count];
//                             title01 = title[count];
//                             ratio01 = ratio[count];
//                             price01 = price[count];
//                             break;
//                         case 8:
//                             anchorPane11 = anchorPanes[count];
//                             number11 = number[count];
//                             title11 = title[count];
//                             ratio11 = ratio[count];
//                             price11 = price[count];
//                             break;
//                         case 9:
//                             anchorPane21 = anchorPanes[count];
//                             number21 = number[count];
//                             title21 = title[count];
//                             ratio21 = ratio[count];
//                             price21 = price[count];
//                             break;
//                         case 10:
//                             anchorPane31 = anchorPanes[count];
//                             number31 = number[count];
//                             title31 = title[count];
//                             ratio31 = ratio[count];
//                             price31 = price[count];
//                             break;
//                         case 11:
//                             anchorPane41 = anchorPanes[count];
//                             number41 = number[count];
//                             title41 = title[count];
//                             ratio41 = ratio[count];
//                             price41 = price[count];
//                             break;
//                         case 12:
//                             anchorPane51 = anchorPanes[count];
//                             number51 = number[count];
//                             title51 = title[count];
//                             ratio51 = ratio[count];
//                             price51 = price[count];
//                             break;
//                         case 13:
//                             anchorPane61 = anchorPanes[count];
//                             number61 = number[count];
//                             title61 = title[count];
//                             ratio61 = ratio[count];
//                             price61 = price[count];
//                             break;
//                     }
//                     if (number1 > 0)
                }
            }
            showAnchorPanes(1);
            checkToVisibleNextButton();
            checkToVisiblePreviousButton();
//            ObservableList<String> observableList = FXCollections.observableArrayList(brands);
//            brandFilter.setItems(observableList);
            System.out.println("Test1 passed");
//            brandFilter.getItems().add(0,"All brands");
            System.out.println("Test2 passed");

            rs.close();
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Test3 passed");
        }
    }
    public void select(){
        if (choiceFilter.getValue().equals("Filters") || choiceFilter.getValue().equals("Clear filters")) selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
        else selectCommoditiesByChoiceFilter(groupListItem,choiceFilter.getValue());
    }

    private void selectCommodities(String group,String orderBy,boolean isLowToHigh,String brand) {
        search.setText(null);
        hideAnchorPanes();
        page.setText("1");
        int count = 0;
        String sql;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/database.db";
            Connection conn = DriverManager.getConnection(url);
            conn = new DatabaseConnectionJDBC().getConnection();
            if (brand.equals("Brands") || brand.equals("All brands")) {
                if (isLowToHigh == false) {
                    if (!group.equals("AllCommodities"))
                    sql = "SELECT * FROM AllCommodities WHERE groupp = " + "'"+ group + "'"+" ORDER BY " + orderBy + " desc";
                    else sql = "SELECT * FROM AllCommodities ORDER BY " + orderBy + " desc";
                } else {
                    if (!group.equals("AllCommodities"))
                    sql = "SELECT * FROM AllCommodities WHERE groupp = " + "'"+ group + "'"+" ORDER BY " + orderBy + " ASC";
                    else sql = "SELECT * FROM AllCommodities ORDER BY " + orderBy + " ASC";
                }
            }else {
                if (isLowToHigh == false) {
                    if (!group.equals("AllCommodities"))
                    sql = "SELECT * FROM AllCommodities WHERE Brand = " + "'"+ brand +"'" +" AND groupp = " + "'" + group + "'" + " ORDER BY " + orderBy + " desc";
                    else sql = "SELECT * FROM AllCommodities WHERE Brand = " + "'" + brand + "'" + " ORDER BY " + orderBy  + " desc";
                }else {
                    if (!group.equals("AllCommodities"))
                    sql = "SELECT * FROM AllCommodities WHERE Brand = " + "'"+ brand +"'" +" AND groupp = " + "'" + group + "'" + " ORDER BY " + orderBy + " ASC";
                    else sql = "SELECT * FROM AllCommodities WHERE Brand = " + "'" + brand + "'" + " ORDER BY " + orderBy  + " ASC";
                }
            }
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            commodities.clear();
            if (!isAuction.isSelected()) {
                while (rs.next()) {
                    int number1 = rs.getInt("Number");
                    if (number1 > 0) {
                        String type1 = rs.getString("Type");
                        String brand1 = rs.getString("Brand");
                        String price1 = rs.getString("Price");
                        String ratio1 = rs.getString("Ratio");
                        String title1 = rs.getString("Title");
                        commodities.add(new Commodity(type1,brand1,price1,ratio1,title1,number1));

                        String date = rs.getString("Date");
                        System.out.println("Type = " + type1 + ", Brand = " + brand1 + ", Price = " + price1 + " Ratio = " + ratio1 + " Title = " + title1 + " Num = " + number1 + " Date = " + date);


                    }
                }
            }else{
                while (rs.next()) {
                    String isAuction = rs.getString("isAuction");
                    if (isAuction.equals("true")){
                        int number1 = rs.getInt("Number");
                        if (number1 > 0) {
                            String type1 = rs.getString("Type");
                            String brand1 = rs.getString("Brand");
                            String price1 = rs.getString("Price");
                            String ratio1 = rs.getString("Ratio");
                            String title1 = rs.getString("Title");
                            commodities.add(new Commodity(type1,brand1,price1,ratio1,title1,number1));

                            String date = rs.getString("Date");
                            System.out.println("Type = " + type1 + ", Brand = " + brand1 + ", Price = " + price1 + " Ratio = " + ratio1 + " Title = " + title1 + " Num = " + number1 + " Date = " + date);


                        }
                }
                }
            }

            showAnchorPanes(1);
            checkToVisibleNextButton();
            checkToVisiblePreviousButton();
//            ObservableList<String> observableList = FXCollections.observableArrayList(brands);
//            brandFilter.setItems(observableList);
            System.out.println("Test1 passed");
//            brandFilter.getItems().add(0,"All brands");
            System.out.println("Test2 passed");

            rs.close();
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Test3 passed");
        }
    }
//    private void insertImageToDataBase() throws SQLException, IOException {
//        // Establish a connection to the SQLite database
//        Connection connection = DriverManager.getConnection("jdbc:sqlite:src/database.db");
//
//        // Create a PreparedStatement for inserting the image into the database
//        PreparedStatement statement = connection.prepareStatement("INSERT INTO images (name, data) VALUES (?, ?)");
//
//        // Read the image file into a byte array
//        File file = new File("/path/to/image.jpg");
//        FileInputStream inputStream = new FileInputStream(file);
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        byte[] buffer = new byte[1024];
//        int bytesRead;
//        while ((bytesRead = inputStream.read(buffer)) != -1) {
//            outputStream.write(buffer, 0, bytesRead);
//        }
//        byte[] imageBytes = outputStream.toByteArray();
//
//        // Set the parameters of the PreparedStatement and execute it
//        statement.setString(1, "image.jpg");
//        statement.setBytes(2, imageBytes);
//        statement.executeUpdate();
//
//        // Close the input/output streams and the database connection
//        inputStream.cancel();
//        outputStream.cancel();
//        statement.cancel();
//        connection.cancel();
//    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        setIDs();
        brandFilter.setValue("Brands");
        brandFilter.getSelectionModel().selectFirst();
        brandListItem = "Brands";
        groupListItem = "AllCommodities";
        orderBy = "Date";
        isLowToHigh = false;
        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
        ArrayList<String> brands1 = new ArrayList<>();
        for (int i = 0; i < commodities.size(); i++) brands1.add(commodities.get(i).getBrand());
        ObservableList<String> observableList = FXCollections.observableArrayList(brands1);
        brandFilter.setItems(observableList);
        brandFilter.getItems().add(0,"All brands");
//        brandListItem = "cheetoz";
//        selectCommoditiesByChoiceFilter("All Commodities","Filters");
        groupingList.getItems().addAll(commodity);
        groupingList.setStyle("-fx-font-family: Century Gothic; -fx-font-size: 13px;");
        groupingList.getSelectionModel().selectFirst();
        groupingList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> listView) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                            setBackground(null);
                        } else {
                            setText(item);
                            setAlignment(Pos.CENTER);
                            if (getIndex() % 2 == 0){
                                setBackground(new Background(new BackgroundFill(Color.rgb(178,235,249), null, null)));
                            }else {
                                setBackground(new Background(new BackgroundFill(Color.rgb(144,209,242),null,null)));
                            }
                        }
                    }
                };
            }
        });

        groupingList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                search.setText(null);
                String selectedItem = groupingList.getSelectionModel().getSelectedItem();
                showGroupLabel.setText("Group: " + selectedItem);
                choiceBoxOption = choiceFilter.getValue();
                ArrayList<String> brands2 = new ArrayList<>();
                for (int i = 0; i < commodities.size(); i++) brands2.add(commodities.get(i).getBrand());
                ObservableList<String> observableList1 = FXCollections.observableArrayList(brands2);
                brandFilter.setItems(observableList1);
                brandFilter.getItems().add(0,"All brands");
                brandFilter.getSelectionModel().selectFirst();
                switch (selectedItem){
                    case "All Commodities" :
                        groupListItem = "AllCommodities";
                        switch (choiceBoxOption) {
                            case "Clear filters", "Filters":
                                orderBy = "Date";
                                isLowToHigh = false;
                                selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                                break;
                            case "Cheapest to most expensive" :
                                orderBy = "Price";
                                isLowToHigh = true;
                                selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                                break;
                            case "Most expensive to cheapest" :
                                orderBy = "Price";
                                isLowToHigh = false;
                                selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                                break;
                            case "Based on points":
                                orderBy = "Ratio";
                                isLowToHigh = false;
                                selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                                break;
                        }
                        break;
                            case "Grocery" :
                                groupListItem = "GroceryCommodities";
                                switch (choiceBoxOption) {
                                    case "Clear filters", "Filters":
                                        orderBy = "Date";
                                        isLowToHigh =false;
                                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                                         break;
                                    case "Cheapest to most expensive" :
                                        orderBy = "Price";
                                        isLowToHigh = true;
                                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                                        break;
                                    case "Most expensive to cheapest" :
                                        orderBy = "Price";
                                        isLowToHigh = false;
                                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                                        break;
                                    case "Based on points":
                                        orderBy = "Ratio";
                                        isLowToHigh = false;
                                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                                        break;
                                }
                                break;
                            case "Break fast" :
                                groupListItem = "BreakFastCommodities";
                                switch (choiceBoxOption) {
                                    case "Clear filters", "Filters":
                                        orderBy = "Date";
                                        isLowToHigh = false;
                                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                                        break;
                                    case "Cheapest to most expensive" :
                                        orderBy = "Price";
                                        isLowToHigh = true;
                                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                                        break;
                                    case "Most expensive to cheapest" :
                                        orderBy = "Price";
                                        isLowToHigh =false;
                                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                                        break;
                                    case "Based on points":
                                        orderBy = "Ratio";
                                        isLowToHigh = false;
                                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                                        break;
                                }
                                break;
                            case  "Protein" :
                                groupListItem = "ProteinCommodities";
                                switch (choiceBoxOption) {
                                    case "Clear filters", "Filters":
                                        orderBy = "Date";
                                        isLowToHigh =false;
                                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                                        break;
                                    case "Cheapest to most expensive" :
                                        orderBy = "Price";
                                        isLowToHigh =true;
                                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                                        break;
                                    case "Most expensive to cheapest" :
                                        orderBy = "Price";
                                        isLowToHigh = false;
                                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                                        break;
                                    case "Based on points":
                                        orderBy = "Ratio";
                                        isLowToHigh = false;
                                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                                        break;
                                }
                                break;
                            case "Dairy" :
                                groupListItem = "DairyCommodities";
                                switch (choiceBoxOption) {
                                    case "Clear filters", "Filters":
                                        orderBy = "Date";
                                        isLowToHigh = false;
                                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                                        break;
                                    case "Cheapest to most expensive" :
                                        orderBy = "Price";
                                        isLowToHigh = true;
                                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                                        break;
                                    case "Most expensive to cheapest" :
                                        orderBy = "Price";
                                        isLowToHigh = false;
                                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                                        break;
                                    case "Based on points":
                                        orderBy = "Ratio";
                                        isLowToHigh = false;
                                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                                        break;
                                }
                                break;
                            case "Fruit and Vegetables" :
                                groupListItem = "FruitAndVegetablesCommodities";
                                switch (choiceBoxOption) {
                                    case "Clear filters", "Filters":
                                        orderBy = "Date";
                                        isLowToHigh = false;
                                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                                        break;
                                    case "Cheapest to most expensive" :
                                        orderBy = "Price";
                                        isLowToHigh = true;
                                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                                        break;
                                    case "Most expensive to cheapest" :
                                        orderBy = "Price";
                                        isLowToHigh = false;
                                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                                        break;
                                    case "Based on points":
                                        orderBy = "Ratio";
                                        isLowToHigh = false;
                                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                                        break;
                                }
                                break;
                            case "Snacks" :
                                groupListItem = "SnackCommodities";
                                switch (choiceBoxOption) {
                                    case "Clear filters", "Filters":
                                        orderBy = "Date";
                                        isLowToHigh = false;
                                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                                        break;
                                    case "Cheapest to most expensive" :
                                        orderBy = "Price";
                                        isLowToHigh = true;
                                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                                        break;
                                    case "Most expensive to cheapest" :
                                        orderBy = "Price";
                                        isLowToHigh = false;
                                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                                        break;
                                    case "Based on points":
                                        orderBy = "Ratio";
                                        isLowToHigh = false;
                                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                                        break;
                                }
                                break;
                        }
                        ArrayList<String> brands3 = new ArrayList<>();
                for (int i = 0; i < commodities.size() ; i++) brands3.add(commodities.get(i).getBrand());
                ObservableList<String> observableList2 = FXCollections.observableArrayList(brands3);
                brandFilter.setItems(observableList2);
                brandFilter.getItems().add(0,"All brands");
                brandFilter.getSelectionModel().selectFirst();
                System.out.println("Selected item: " + selectedItem);
            }
        });


        choiceFilter.setItems(choiceBoxOptions);
        choiceFilter.setValue("Filters");
        choiceFilter.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            groupListItem = groupingList.getSelectionModel().getSelectedItem();
            switch (groupListItem){
                case "All Commodities":
                    groupListItem = "AllCommodities";
                    break;
                case "Grocery":
                    groupListItem = "GroceryCommodities";
                    break;
                case "Break fast":
                    groupListItem = "BreakFastCommodities";
                    break;
                case "Protein":
                    groupListItem = "ProteinCommodities";
                    break;
                case "Dairy":
                    groupListItem = "DairyCommodities";
                    break;
                case "Fruit and Vegetables":
                    groupListItem = "FruitAndVegetablesCommodities";
                    break;
                case "Snacks":
                    groupListItem = "SnackCommodities";
            }
            selectCommoditiesByChoiceFilter(groupListItem,newValue);
        });
        brandFilter.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            groupListItem = groupingList.getSelectionModel().getSelectedItem();
            switch (groupListItem){
                case "All Commodities":
                    groupListItem = "AllCommodities";
                    break;
                case "Grocery":
                    groupListItem = "GroceryCommodities";
                    break;
                case "Break fast":
                    groupListItem = "BreakFastCommodities";
                    break;
                case "Protein":
                    groupListItem = "ProteinCommodities";
                    break;
                case "Dairy":
                    groupListItem = "DairyCommodities";
                    break;
                case "Fruit and Vegetables":
                    groupListItem = "FruitAndVegetablesCommodities";
                    break;
                case "Snacks":
                    groupListItem = "SnackCommodities";
            }
            brandListItem = newValue;
            brandName.setText("Brand: " + newValue);
            selectCommoditiesByChoiceFilter(groupListItem,choiceFilter.getValue());

        });
        checkToVisibleNextButton();
        checkToVisiblePreviousButton();

    }
    private void selectCommoditiesByChoiceFilter(String groupListItem,String newValue){
        switch (groupListItem){
            case  "All Commodities":
                groupListItem = "AllCommodities";
                switch (newValue) {
                    case "Clear filters", "Filters":
                        orderBy = "Date";
                        isLowToHigh = false;
                        if (search.getText() == null)
                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                        else {
                            groupListItem = "AllCommodities";
                            selectCommoditiesBySearch(groupListItem,orderBy,isLowToHigh,brandListItem,search.getText());
                        }
                        break;
                    case "Cheapest to most expensive" :
                        orderBy = "Price";
                        isLowToHigh = true;
                        if (search.getText() == null)
                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                        else {
                            groupListItem = "AllCommodities";
                            selectCommoditiesBySearch(groupListItem,orderBy,isLowToHigh,brandListItem,search.getText());
                        }
                        break;
                    case "Most expensive to cheapest" :
                        orderBy = "Price";
                        isLowToHigh = false;
                        if (search.getText()==null)
                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                        else {
                            groupListItem = "AllCommodities";
                            selectCommoditiesBySearch(groupListItem,orderBy,isLowToHigh,brandListItem,search.getText());
                        }
                        break;
                    case "Based on points":
                        orderBy = "Ratio";
                        isLowToHigh = false;
                        if (search.getText() == null)
                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                        else{
                            groupListItem = "AllCommodities";
                            selectCommoditiesBySearch(groupListItem,orderBy,isLowToHigh,brandListItem,search.getText());
                        }
                        break;
                }
                break;
            case "Grocery" :
                groupListItem = "GroceryCommodities";
                switch (newValue) {
                    case "Clear filters", "Filters":
                        orderBy = "Date";
                        isLowToHigh = false;
                        if (search.getText()==null)
                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                        else {
                            groupListItem = "AllCommodities";
                            selectCommoditiesBySearch(groupListItem,orderBy,isLowToHigh,brandListItem,search.getText());
                        }
                        break;
                    case "Cheapest to most expensive":
                        orderBy = "Price";
                        isLowToHigh = true;
                        if (search.getText()==null)
                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                        else {
                            groupListItem = "AllCommodities";
                            selectCommoditiesBySearch(groupListItem,orderBy,isLowToHigh,brandListItem,search.getText());
                        }
                    case "Most expensive to cheapest" :
                        orderBy = "Price";
                        isLowToHigh = false;
                        if (search.getText() == null)
                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                        else {
                            groupListItem = "AllCommodities";
                            selectCommoditiesBySearch(groupListItem,orderBy,isLowToHigh,brandListItem,search.getText());
                        }
                        break;
                    case "Based on points":
                        orderBy = "Ratio";
                        isLowToHigh = false;
                        if (search.getText() == null)
                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                        else{
                            groupListItem = "AllCommodities";
                            selectCommoditiesBySearch(groupListItem,orderBy,isLowToHigh,brandListItem,search.getText());
                        }
                        break;
                }
                break;
            case "Break fast" :
                groupListItem = "BreakFastCommodities";
                switch (newValue) {
                    case "Clear filters", "Filters":
                        orderBy = "Date";
                        isLowToHigh = false;
                        if (search.getText() == null)
                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                        else {
                            groupListItem = "AllCommodities";
                            selectCommoditiesBySearch(groupListItem,orderBy,isLowToHigh,brandListItem,search.getText());
                        }
                        break;
                    case "Cheapest to most expensive" :
                        orderBy = "Price";
                        isLowToHigh = true;
                        if (search.getText() == null)
                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                        else {
                            groupListItem = "AllCommodities";
                            selectCommoditiesBySearch(groupListItem,orderBy,isLowToHigh,brandListItem,search.getText());
                        }
                        break;
                    case "Most expensive to cheapest" :
                        orderBy = "Price";
                        isLowToHigh = false;
                        if (search.getText() == null)
                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                        else {
                            groupListItem = "AllCommodities";
                            selectCommoditiesBySearch(groupListItem,orderBy,isLowToHigh,brandListItem,search.getText());
                        }
                        break;
                    case "Based on points":
                        orderBy = "Ratio";
                        isLowToHigh = false;
                        if (search.getText() == null)
                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                        else {
                            groupListItem = "AllCommodities";
                            selectCommoditiesBySearch(groupListItem,orderBy,isLowToHigh,brandListItem,search.getText());
                        }
                        break;
                }
                break;
            case  "Protein" :
                groupListItem = "ProteinCommodities";
                switch (newValue) {
                    case "Clear filters", "Filters":
                        orderBy = "Date";
                        isLowToHigh = false;
                        if (search.getText() == null)
                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                        else{
                            groupListItem = "AllCommodities";
                            selectCommoditiesBySearch(groupListItem,orderBy,isLowToHigh,brandListItem,search.getText());
                        }
                        break;
                    case "Cheapest to most expensive" :
                        orderBy = "Price";
                        isLowToHigh = true;
                        if (search.getText() == null)
                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                        else{
                            groupListItem = "AllCommodities";
                            selectCommoditiesBySearch(groupListItem,orderBy,isLowToHigh,brandListItem,search.getText());
                        }
                        break;
                    case "Most expensive to cheapest" :
                        orderBy = "Price";
                        isLowToHigh = false;
                        if (search.getText() == null)
                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                        else{
                            groupListItem = "AllCommodities";
                            selectCommoditiesBySearch(groupListItem,orderBy,isLowToHigh,brandListItem,search.getText());
                        }
                        break;
                    case "Based on points":
                        orderBy = "Ratio";
                        isLowToHigh = false;
                        if (search.getText() == null)
                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                        else{
                            groupListItem = "AllCommodities";
                            selectCommoditiesBySearch(groupListItem,orderBy,isLowToHigh,brandListItem,search.getText());
                        }
                        break;
                }
                break;
            case "Dairy" :
                groupListItem = "DairyCommodities";
                switch (newValue) {
                    case "Clear filters", "Filters":
                        orderBy = "Date";
                        isLowToHigh =false;
                        if (search.getText() == null)
                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                        else {
                            groupListItem = "AllCommodities";
                            selectCommoditiesBySearch(groupListItem,orderBy,isLowToHigh,brandListItem,search.getText());
                        }
                        break;
                    case "Cheapest to most expensive" :
                        orderBy = "Price";
                        isLowToHigh = true;
                        if (search.getText() == null)
                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                        else{
                            groupListItem = "AllCommodities";
                            selectCommoditiesBySearch(groupListItem,orderBy,isLowToHigh,brandListItem,search.getText());
                        }
                        break;
                    case "Most expensive to cheapest" :
                        orderBy = "Price";
                        isLowToHigh = false;
                        if (search.getText() ==null)
                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                        else{
                            groupListItem ="AllCommodities";
                            selectCommoditiesBySearch(groupListItem,orderBy,isLowToHigh,brandListItem,search.getText());
                        }
                        break;
                    case "Based on points":
                        orderBy= "Ratio";
                        isLowToHigh = false;
                        if (search.getText() == null)
                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                        else{
                            groupListItem = "AllCommodities";
                            selectCommoditiesBySearch(groupListItem,orderBy,isLowToHigh,brandListItem,search.getText());
                        }
                        break;
                }
                break;
            case "Fruit and Vegetables" :
                groupListItem = "FruitAndVegetablesCommodities";
                switch (newValue) {
                    case "Clear filters", "Filters":
                        orderBy = "Date";
                        isLowToHigh = false;
                        if (search.getText() == null)
                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                        else {
                            groupListItem = "AllCommodities";
                            selectCommoditiesBySearch(groupListItem,orderBy,isLowToHigh,brandListItem,search.getText());
                        }
                        break;
                    case "Cheapest to most expensive" :
                        orderBy = "Price";
                        isLowToHigh = true;
                        if (search.getText() == null)
                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                        else {
                            groupListItem = "AllCommodities";
                            selectCommoditiesBySearch(groupListItem,orderBy,isLowToHigh,brandListItem,search.getText());
                        }
                        break;
                    case "Most expensive to cheapest" :
                        orderBy = "Price";
                        isLowToHigh = false;
                        if (search.getText() == null)
                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                        else{
                            groupListItem = "AllCommodities";
                            selectCommoditiesBySearch(groupListItem,orderBy,isLowToHigh,brandListItem,search.getText());
                        }
                        break;
                    case "Based on points":
                        orderBy = "Ratio";
                        isLowToHigh = false;
                        if(search.getText() == null)
                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                        else{
                            groupListItem  ="AllCommodities";
                            selectCommoditiesBySearch(groupListItem,orderBy,isLowToHigh,brandListItem,search.getText());
                        }
                        break;
                }
                break;
            case "Snacks" :
                groupListItem = "SnackCommodities";
                switch (newValue) {
                    case "Clear filters", "Filters":
                        orderBy = "Date";
                        isLowToHigh = false;
                        if (search.getText() == null)
                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                        else {
                            groupListItem = "AllCommodities";
                            selectCommoditiesBySearch(groupListItem,orderBy,isLowToHigh,brandListItem,search.getText());
                        }
                        break;
                    case "Cheapest to most expensive" :
                        orderBy = "Price";
                        isLowToHigh = true;
                        if (search.getText() == null)
                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                        else{
                            groupListItem = "AllCommodities";
                            selectCommoditiesBySearch(groupListItem,orderBy,isLowToHigh,brandListItem,search.getText());
                        }
                        break;
                    case "Most expensive to cheapest" :
                        orderBy = "Price";
                        isLowToHigh = false;
                        if (search.getText() == null)
                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                        else {
                            groupListItem  = "AllCommodities";
                            selectCommoditiesBySearch(groupListItem,orderBy,isLowToHigh,brandListItem,search.getText());
                        }
                        break;
                    case "Based on points":
                        orderBy = "Ratio";
                        isLowToHigh = false;
                        if (search.getText() == null)
                        selectCommodities(groupListItem,orderBy,isLowToHigh,brandListItem);
                        else {
                            groupListItem = "AllCommodities";
                            selectCommoditiesBySearch(groupListItem,orderBy,isLowToHigh,brandListItem,search.getText());
                        }
                        break;
                }
                break;
        }
    }
    private void checkToVisibleNextButton(){
        int pageNum = Integer.parseInt(page.getText());
        pageNum++;
        pageNum = (pageNum * 14) - 14;
        if (pageNum >= commodities.size()){
            pageNum = (pageNum + 13)/14;
            page.setText(String.valueOf(pageNum));
            goToNextPageButton.setVisible(false);
        }else goToNextPageButton.setVisible(true);
    }
    private void checkToVisiblePreviousButton(){
        int pageNum = Integer.parseInt(page.getText());
        if (pageNum == 1) goToPreviousPageButton.setVisible(false);
        else goToPreviousPageButton.setVisible(true);
    }
    private void switchScene(ActionEvent event , String sceneName)  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneName + ".fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void switchToLoginScene(ActionEvent event) {
            switchScene(event,"login-scene");
    }
    @FXML
    private void goToNextPageButtonOnAction(ActionEvent event){
        goToPreviousPageButton.setVisible(true);
        int pageNum = Integer.parseInt(page.getText());
        pageNum++;
        showAnchorPanes(pageNum);
        page.setText(String.valueOf(pageNum));
        checkToVisibleNextButton();
    }
    @FXML
    private void goToPreviousPageButtonOnAction(){
        int pageNume = Integer.parseInt(page.getText());
        pageNume--;
        showAnchorPanes(pageNume);
        page.setText(String.valueOf(pageNume));
        checkToVisiblePreviousButton();
        checkToVisibleNextButton();
    }
    public void search(){
//        brandListItem = "All brands";
        String searchItem = search.getText();
        searchItem.toLowerCase();
        groupListItem = "AllCommodities";
        selectCommoditiesBySearch(groupListItem,orderBy,isLowToHigh,brandListItem,searchItem);
        ArrayList<String> brands4 = new ArrayList<>();
        for (int i = 0; i < commodities.size() ; i++) brands4.add(commodities.get(i).getBrand());
        ObservableList<String> observableList2 = FXCollections.observableArrayList(brands4);
        brandFilter.setItems(observableList2);
        brandFilter.getItems().add(0,"All brands");
        brandFilter.getSelectionModel().selectFirst();
    }


    private int getIndexOfAnchorPane(int row, int col) {
        int pageNumber = Integer.parseInt(page.getText());
        return (row * 7 + col) + (pageNumber-1) * 14;
    }

    public void toProduct(int row, int col) {
        int index = getIndexOfAnchorPane(row, col);
        System.out.println("HomeController.toProduct");
        System.out.println("index = " + index);
        System.out.println("row = " + row);
        System.out.println("col = " + col);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("production-page.fxml"));
            Parent root = loader.load();

            ProductionController controller = loader.getController();
            controller.setAll(commodities.get(index), user);

            Stage stage = (Stage) loginbutton.getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(int row, int col) {
        int index = getIndexOfAnchorPane(row, col);
        System.out.println("HomeController.add");
        System.out.println("index = " + index);
        System.out.println("row = " + row);
        System.out.println("col = " + col);
    }

    public void toProduct00() {
        toProduct(0, 0);
    }

    public void toProduct10() {
        toProduct(0, 1);
    }

    public void toProduct20() {
        toProduct(0, 2);
    }

    public void toProduct30() {
        toProduct(0, 3);
    }

    public void toProduct40() {
        toProduct(0, 4);
    }

    public void toProduct50() {
        toProduct(0, 5);
    }

    public void toProduct60() {
        toProduct(0, 6);
    }

    public void toProduct01() {
        toProduct(1, 0);
    }

    public void toProduct11() {
        toProduct(1, 1);
    }

    public void toProduct21() {
        toProduct(1, 2);
    }

    public void toProduct31() {
        toProduct(1, 3);
    }

    public void toProduct41() {
        toProduct(1, 4);
    }

    public void toProduct51() {
        toProduct(1, 5);
    }

    public void toProduct61() {
        toProduct(1, 6);
    }


    public void add00() {
        add(0, 0);
    }

    public void add10() {
        add(0, 1);
    }

    public void add20() {
        add(0, 2);
    }

    public void add30() {
        add(0, 3);
    }

    public void add40() {
        add(0, 4);
    }

    public void add50() {
        add(0, 5);
    }

    public void add60() {
        add(0, 6);
    }

    public void add01() {
        add(1, 0);
    }

    public void add11() {
        add(1, 1);
    }

    public void add21() {
        add(1, 2);
    }

    public void add31() {
        add(1, 3);
    }

    public void add41() {
        add(1, 4);
    }

    public void add51() {
        add(1, 5);
    }

    public void add61() {
        add(1, 6);
    }

    public void goToProductRegistrationPage(ActionEvent event){
//        switchScene(event,"ProductRegistrationPage");
        FXMLLoader loader = new FXMLLoader(getClass().getResource( "ProductRegistrationPage.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
//        stage.show();
        stage.setX(50);
        ProductRegistrationController productRegistrationController = loader.getController();
        productRegistrationController.setUser(user);
    }
}

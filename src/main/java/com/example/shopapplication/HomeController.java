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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private String choiceBoxOption = "Filter";
    String groupListItem  = "All Commodities";
    String brandListItem;
    private ObservableList<String> choiceBoxOptions =
            FXCollections.observableArrayList("Clear filters","Cheapest to most expensive", "Most expensive to cheapest", "Based on points");
//     private AnchorPane[] anchorPanes = new AnchorPane[14];
//    private Label[] number = new Label[14];
//    private Label[] title = new Label[14];
//    private Label[] price = new Label[14];
//    private Label[] ratio = new Label[14];

    ArrayList<String> types = new ArrayList<String>();
//    ArrayList<Image> images = new ArrayList<Image>();
    ArrayList<String> brands = new ArrayList<String>();
    ArrayList<String> prices = new ArrayList<String>();
    ArrayList<String> rates = new ArrayList<String>();
    ArrayList<String> titles = new ArrayList<String>();
    ArrayList<String> numbers = new ArrayList<String>();
    ArrayList<String> dates = new ArrayList<String >();
    int arraySizeCounter = 0;

    private Connection connection = null;
//    private void setIDs(){
//        anchorPanes[0] = anchorPane00;
//        anchorPanes[1] = anchorPane10;
//        anchorPanes[2] = anchorPane20;
//        anchorPanes[3] = anchorPane30;
//        anchorPanes[4] = anchorPane40;
//        anchorPanes[5] = anchorPane50;
//        anchorPanes[6] = anchorPane60;
//        anchorPanes[7] = anchorPane01;
//        anchorPanes[8] = anchorPane11;
//        anchorPanes[9] = anchorPane21;
//        anchorPanes[10] = anchorPane31;
//        anchorPanes[11] = anchorPane41;
//        anchorPanes[12] = anchorPane51;
//        anchorPanes[13] = anchorPane61;
//
//        title[0] = title00;
//        title[1] = title10 ;
//        title[2] = title20 ;
//        title[3] = title30 ;
//        title[4] = title40 ;
//        title[5] = title50 ;
//        title[6] = title60 ;
//        title[7] =title01;
//        title[8] = title11;
//        title[9] = title21;
//        title[10] = title31;
//        title[11] = title41;
//        title[12] = title51;
//        title[13] = title61;
//
//        ratio[0] = ratio00;
//        ratio[1] = ratio10;
//        ratio[2] = ratio20;
//        ratio[3] = ratio30;
//        ratio[4] = ratio40;
//        ratio[5] = ratio50;
//        ratio[6] = ratio60;
//        ratio[7] =ratio01 ;
//        ratio[8] = ratio11 ;
//        ratio[9] = ratio21 ;
//        ratio[10] = ratio31 ;
//        ratio[11] = ratio41 ;
//        ratio[12] = ratio51 ;
//        ratio[13] = ratio61 ;
//
//        price[0] = price00 ;
//        price[1] = price10 ;
//        price[2] = price20 ;
//        price[3] = price30 ;
//        price[4] = price40 ;
//        price[5] = price50 ;
//        price[6] = price60 ;
//        price[7] =price01 ;
//        price[8] = price11 ;
//        price[9] = price21 ;
//        price[10] = price31;
//        price[11] = price41;
//        price[12] = price51;
//        price[13] = price61;
//
//        number[0] = number00 ;
//       number[1] = number10;
//       number[2] = number20;
//       number[3] = number30;
//       number[4] = number40;
//       number[5] = number50;
//       number[6] = number60;
//       number[7] = number01;
//       number[8] = number11;
//       number[9] = number21;
//       number[10] = number31;
//       number[11] = number41;
//       number[12] = number51;
//       number[13] = number61;
//    }
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
        if (page < titles.size()){
            anchorPane00.setVisible(true);
//            imageView00.setImage(images.get(page));
            number00.setText("Number: " + numbers.get(page));
            ratio00.setText(rates.get(page));
            price00.setText(prices.get(page));
            title00.setText(titles.get(page));
            page++;
        } else return;
        if (page < titles.size()){
            anchorPane10.setVisible(true);
//            imageView10.setImage(images.get(page));
            number10.setText("Number: " + numbers.get(page));
            ratio10.setText(rates.get(page));
            price10.setText(prices.get(page));
            title10.setText(titles.get(page));
            page++;
        }else return;
        if (page < titles.size()){
            anchorPane20.setVisible(true);
//            imageView20.setImage(images.get(page));
            number20.setText("Number: " + numbers.get(page));
            ratio20.setText(rates.get(page));
            price20.setText(prices.get(page));
            title20.setText(titles.get(page));
            page++;
        }else return;
        if (page < titles.size()){
            anchorPane30.setVisible(true);
//            imageView30.setImage(images.get(page));
            number30.setText("Number: " + numbers.get(page));
            ratio30.setText(rates.get(page));
            price30.setText(prices.get(page));
            title30.setText(titles.get(page));
            page++;
        }else return;
        if (page < titles.size()){
            anchorPane40.setVisible(true);
//            imageView40.setImage(images.get(page));
            number40.setText("Number: " + numbers.get(page));
            ratio40.setText(rates.get(page));
            price40.setText(prices.get(page));
            title40.setText(titles.get(page));
            page++;
        }else return;
        if (page < titles.size()){
            anchorPane50.setVisible(true);
//            imageView50.setImage(images.get(page));
            number50.setText("Number: " + numbers.get(page));
            ratio50.setText(rates.get(page));
            price50.setText(prices.get(page));
            title50.setText(titles.get(page));
            page++;
        } else return;
        if (page < titles.size()){
            anchorPane60.setVisible(true);
//            imageView60.setImage(images.get(page));
            number60.setText("Number: " + numbers.get(page));
            ratio60.setText(rates.get(page));
            price60.setText(prices.get(page));
            title60.setText(titles.get(page));
            page++;
        }else return;
        if (page < titles.size()){
            anchorPane01.setVisible(true);
//            imageView01.setImage(images.get(page));
            number01.setText("Number: " + numbers.get(page));
            ratio01.setText(rates.get(page));
            price01.setText(prices.get(page));
            title01.setText(titles.get(page));
            page++;
        } else return;
        if (page < titles.size()){
            anchorPane11.setVisible(true);
//            imageView11.setImage(images.get(page));
            number11.setText("Number: " + numbers.get(page));
            ratio11.setText(rates.get(page));
            price11.setText(prices.get(page));
            title11.setText(titles.get(page));
            page++;
        } else return;
        if (page < titles.size()){
            anchorPane21.setVisible(true);
//            imageView21.setImage(images.get(page));
            number21.setText("Number: " + numbers.get(page));
            ratio21.setText(rates.get(page));
            price21.setText(prices.get(page));
            title21.setText(titles.get(page));
            page++;
        } else return;
        if (page < titles.size()){
            anchorPane31.setVisible(true);
//            imageView31.setImage(images.get(page));
            number31.setText("Number: " + numbers.get(page));
            ratio31.setText(rates.get(page));
            price31.setText(prices.get(page));
            title31.setText(titles.get(page));
            page++;
        } else return;
        if (page < titles.size()){
            anchorPane41.setVisible(true);
//            imageView41.setImage(images.get(page));
            number41.setText("Number: " + numbers.get(page));
            ratio41.setText(rates.get(page));
            price41.setText(prices.get(page));
            title41.setText(titles.get(page));
            page++;
        } else return;
        if (page < titles.size()){
            anchorPane51.setVisible(true);
//            imageView51.setImage(images.get(page));
            number51.setText("Number: " + numbers.get(page));
            ratio51.setText(rates.get(page));
            price51.setText(prices.get(page));
            title51.setText(titles.get(page));
            page++;
        } else return;
        if (page < titles.size()){
            anchorPane61.setVisible(true);
//            imageView61.setImage(images.get(page));
            number61.setText("Number: " + numbers.get(page));
            ratio61.setText(rates.get(page));
            price61.setText(prices.get(page));
            title61.setText(titles.get(page));
            page++;
        }
        return;

    }
    private void selectCommodities(String group,String orderBy,boolean isLowToHigh,String brand) {
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
                    sql = "SELECT * FROM " + group + " ORDER BY " + orderBy + " desc";
                } else {
                    sql = "SELECT * FROM " + group + " ORDER BY " + orderBy + " ASC";
                }
            }else {
                if (isLowToHigh == false) {
                    sql = "SELECT * FROM " + group + " WHERE Brand = " + "'"+ brand +"'" + " ORDER BY " + orderBy + " desc";
                }else {
                    sql = "SELECT * FROM " + group + " WHERE Brand = " +"'"+ brand +"'" + " ORDER BY " + orderBy + " ASC";
                }
            }
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    String number1 = rs.getString("Number");
                    if (Integer.parseInt(number1) > 0) {
                        if (count == numbers.size()) numbers.add(number1);
                        else numbers.set(count, number1);
                        String type1 = rs.getString("Type");
                        if (count == types.size()) types.add(type1);
                        else types.set(count, type1);
                        String brand1 = rs.getString("Brand");
                        if (count == brands.size()) brands.add(brand1);
                        else brands.set(count, brand1);
                        String price1 = rs.getString("Price");
                        if (count == prices.size()) prices.add(price1);
                        else prices.set(count, price1);
                        String ratio1 = rs.getString("Ratio");
                        if (count == rates.size()) rates.add(ratio1);
                        else rates.set(count, ratio1);
                        String title1 = rs.getString("Title");
                        if (count == titles.size()) titles.add(title1);
                        else titles.set(count, title1);
//                        Blob blob = rs.getBlob("image");
//                        InputStream inputStream = blob.getBinaryStream();
//                        Image image1 = new Image(inputStream);
//                        if (count == images.size()) images.add(image1);
//                        else images.set(count,image1);
                        String date = rs.getString("Date");
                        if (count == dates.size()) {
                            dates.add(date);
                            arraySizeCounter++;
                        } else dates.set(count, date);
                        count++;
                        System.out.println("Type = " + type1 + ", Brand = " + brand1 + ", Price = " + price1 + " Ratio = " + ratio1 + " Title = " + title1 + " Num = " + number1 + " Date = " + date);

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
            for (int i = arraySizeCounter - 1; i >= count  ; i--) {
                types.remove(i);
                brands.remove(i);
                prices.remove(i);
                rates.remove(i);
                titles.remove(i);
                numbers.remove(i);
                dates.remove(i);
                arraySizeCounter--;
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
//        inputStream.close();
//        outputStream.close();
//        statement.close();
//        connection.close();
//    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        setIDs();
        brandFilter.setValue("Brands");
        brandFilter.getSelectionModel().selectFirst();
        brandListItem = "Brands";
        selectCommodities("AllCommodities","Date",false,brandListItem);
        ObservableList<String> observableList = FXCollections.observableArrayList(brands);
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
                String selectedItem = groupingList.getSelectionModel().getSelectedItem();
                showGroupLabel.setText("Group: " + selectedItem);
                choiceBoxOption = choiceFilter.getValue();
                ObservableList<String> observableList1 = FXCollections.observableArrayList(brands);
                brandFilter.setItems(observableList1);
                brandFilter.getItems().add(0,"All brands");
                brandFilter.getSelectionModel().selectFirst();
                switch (selectedItem){
                    case "All Commodities" :
                        switch (choiceBoxOption) {
                            case "Clear filters", "Filters":
                                selectCommodities("AllCommodities","Date",false,brandListItem);
                                break;
                            case "Cheapest to most expensive" :
                                selectCommodities("AllCommodities","Price",true,brandListItem);
                                break;
                            case "Most expensive to cheapest" :
                                selectCommodities("AllCommodities","Price",false,brandListItem);
                                break;
                            case "Based on points":
                                selectCommodities("AllCommodities","Ratio",false,brandListItem);
                                break;
                        }
                        break;
                            case "Grocery" :
                                switch (choiceBoxOption) {
                                    case "Clear filters", "Filters":
                                        selectCommodities("GroceryCommodities","Date",false,brandListItem);
                                         break;
                                    case "Cheapest to most expensive" :
                                            selectCommodities("GroceryCommodities","Price",true,brandListItem);
                                            break;
                                    case "Most expensive to cheapest" :
                                        selectCommodities("GroceryCommodities","Price",false,brandListItem);
                                        break;
                                    case "Based on points":
                                        selectCommodities("GroceryCommodities","Ratio",false,brandListItem);
                                        break;
                                }
                                break;
                            case "Break fast" :
                                switch (choiceBoxOption) {
                                    case "Clear filters", "Filters":
                                        selectCommodities("BreakFastCommodities","Date",false,brandListItem);
                                        break;
                                    case "Cheapest to most expensive" :
                                        selectCommodities("BreakFastCommodities","Price",true,brandListItem);
                                        break;
                                    case "Most expensive to cheapest" :
                                        selectCommodities("BreakFastCommodities","Price",false,brandListItem);
                                        break;
                                    case "Based on points":
                                        selectCommodities("BreakFastCommodities","Ratio",false,brandListItem);
                                        break;
                                }
                                break;
                            case  "Protein" :
                                switch (choiceBoxOption) {
                                    case "Clear filters", "Filters":
                                        selectCommodities("ProteinCommodities","Date",false,brandListItem);
                                        break;
                                    case "Cheapest to most expensive" :
                                        selectCommodities("ProteinCommodities","Price",true,brandListItem);
                                        break;
                                    case "Most expensive to cheapest" :
                                        selectCommodities("ProteinCommodities","Price",false,brandListItem);
                                        break;
                                    case "Based on points":
                                        selectCommodities("ProteinCommodities","Ratio",false,brandListItem);
                                        break;
                                }
                                break;
                            case "Dairy" :
                                switch (choiceBoxOption) {
                                    case "Clear filters", "Filters":
                                        selectCommodities("DairyCommodities","Date",false,brandListItem);
                                        break;
                                    case "Cheapest to most expensive" :
                                        selectCommodities("DairyCommodities","Price",true,brandListItem);
                                        break;
                                    case "Most expensive to cheapest" :
                                        selectCommodities("DairyCommodities","Price",false,brandListItem);
                                        break;
                                    case "Based on points":
                                        selectCommodities("DairyCommodities","Ratio",false,brandListItem);
                                        break;
                                }
                                break;
                            case "Fruit and Vegetables" :
                                switch (choiceBoxOption) {
                                    case "Clear filters", "Filters":
                                        selectCommodities("FruitAndVegetablesCommodities","Date",false,brandListItem);
                                        break;
                                    case "Cheapest to most expensive" :
                                        selectCommodities("FruitAndVegetablesCommodities","Price",true,brandListItem);
                                        break;
                                    case "Most expensive to cheapest" :
                                        selectCommodities("FruitAndVegetablesCommodities","Price",false,brandListItem);
                                        break;
                                    case "Based on points":
                                        selectCommodities("FruitAndVegetablesCommodities","Ratio",false,brandListItem);
                                        break;
                                }
                                break;
                            case "Snacks" :
                                switch (choiceBoxOption) {
                                    case "Clear filters", "Filters":
                                        selectCommodities("SnackCommodities","Date",false,brandListItem);
                                        break;
                                    case "Cheapest to most expensive" :
                                        selectCommodities("SnackCommodities","Price",true,brandListItem);
                                        break;
                                    case "Most expensive to cheapest" :
                                        selectCommodities("SnackCommodities","Price",false,brandListItem);
                                        break;
                                    case "Based on points":
                                        selectCommodities("SnackCommodities","Ratio",false,brandListItem);
                                        break;
                                }
                                break;
                        }
                ObservableList<String> observableList2 = FXCollections.observableArrayList(brands);
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
            selectCommoditiesByChoiceFilter(groupListItem,newValue);
        });
        brandFilter.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            groupListItem = groupingList.getSelectionModel().getSelectedItem();
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
                switch (newValue) {
                    case "Clear filters", "Filters":
                        selectCommodities("AllCommodities","Date",false,brandListItem);
                        break;
                    case "Cheapest to most expensive" :
                        selectCommodities("AllCommodities","Price",true,brandListItem);
                        break;
                    case "Most expensive to cheapest" :
                        selectCommodities("AllCommodities","Price",false,brandListItem);
                        break;
                    case "Based on points":
                        selectCommodities("AllCommodities","Ratio",false,brandListItem);
                        break;
                }
                break;
            case "Grocery" :
                switch (newValue) {
                    case "Clear filters", "Filters":
                        selectCommodities("GroceryCommodities","Date",false,brandListItem);
                        break;
                    case "Cheapest to most expensive":
                        selectCommodities("GroceryCommodities","Price",true,brandListItem);
                    case "Most expensive to cheapest" :
                        selectCommodities("GroceryCommodities","Price",false,brandListItem);
                        break;
                    case "Based on points":
                        selectCommodities("GroceryCommodities","Ratio",false,brandListItem);
                        break;
                }
                break;
            case "Break fast" :
                switch (newValue) {
                    case "Clear filters", "Filters":
                        selectCommodities("BreakFastCommodities","Date",false,brandListItem);
                        break;
                    case "Cheapest to most expensive" :
                        selectCommodities("BreakFastCommodities","Price",true,brandListItem);
                        break;
                    case "Most expensive to cheapest" :
                        selectCommodities("BreakFastCommodities","Price",false,brandListItem);
                        break;
                    case "Based on points":
                        selectCommodities("BreakFastCommodities","Ratio",false,brandListItem);
                        break;
                }
                break;
            case  "Protein" :
                switch (newValue) {
                    case "Clear filters", "Filters":
                        selectCommodities("ProteinCommodities","Date",false,brandListItem);
                        break;
                    case "Cheapest to most expensive" :
                        selectCommodities("ProteinCommodities","Price",true,brandListItem);
                        break;
                    case "Most expensive to cheapest" :
                        selectCommodities("ProteinCommodities","Price",false,brandListItem);
                        break;
                    case "Based on points":
                        selectCommodities("ProteinCommodities","Ratio",false,brandListItem);
                        break;
                }
                break;
            case "Dairy" :
                switch (newValue) {
                    case "Clear filters", "Filters":
                        selectCommodities("DairyCommodities","Date",false,brandListItem);
                        break;
                    case "Cheapest to most expensive" :
                        selectCommodities("DairyCommodities","Price",true,brandListItem);
                        break;
                    case "Most expensive to cheapest" :
                        selectCommodities("DairyCommodities","Price",false,brandListItem);
                        break;
                    case "Based on points":
                        selectCommodities("DairyCommodities","Ratio",false,brandListItem);
                        break;
                }
                break;
            case "Fruit and Vegetables" :
                switch (newValue) {
                    case "Clear filters", "Filters":
                        selectCommodities("FruitAndVegetablesCommodities","Date",false,brandListItem);
                        break;
                    case "Cheapest to most expensive" :
                        selectCommodities("FruitAndVegetablesCommodities","Price",true,brandListItem);
                        break;
                    case "Most expensive to cheapest" :
                        selectCommodities("FruitAndVegetablesCommodities","Price",false,brandListItem);
                        break;
                    case "Based on points":
                        selectCommodities("FruitAndVegetablesCommodities","Ratio",false,brandListItem);
                        break;
                }
                break;
            case "Snacks" :
                switch (newValue) {
                    case "Clear filters", "Filters":
                        selectCommodities("SnackCommodities","Date",false,brandListItem);
                        break;
                    case "Cheapest to most expensive" :
                        selectCommodities("SnackCommodities","Price",true,brandListItem);
                        break;
                    case "Most expensive to cheapest" :
                        selectCommodities("SnackCommodities","Price",false,brandListItem);
                        break;
                    case "Based on points":
                        selectCommodities("SnackCommodities","Ratio",false,brandListItem);
                        break;
                }
                break;
        }
    }
    private void checkToVisibleNextButton(){
        int pageNum = Integer.parseInt(page.getText());
        pageNum++;
        pageNum = (pageNum * 14) - 14;
        if (pageNum >= titles.size()){
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
}

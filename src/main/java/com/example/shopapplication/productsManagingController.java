package com.example.shopapplication;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.w3c.dom.Text;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.plaf.nimbus.State;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;

public class productsManagingController implements Initializable {
    @FXML
    private Button goToNextPageButton;
    @FXML
    private Button goToPreviousPageButton;
    @FXML
    private Label page;
    @FXML
    private Label typeInfo;
    @FXML
    private AnchorPane anchorPane00;
    @FXML
    private AnchorPane  anchorPane10;
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
    private Label errorForChangePageLabel;
    @FXML
    private Button auction00;
    @FXML
    private Button auction10;
    @FXML
    private Button auction20;
    @FXML
    private Button auction30;
    @FXML
    private Button auction40;
    @FXML
    private Button auction50;
    @FXML
    private Button auction60;
    @FXML
    private Button auction01;
    @FXML
    private Button auction11;
    @FXML
    private Button auction21;
    @FXML
    private Button auction31;
    @FXML
    private Button auction41;
    @FXML
    private Button auction51;
    @FXML
    private Button auction61;

    @FXML
    private AnchorPane options00;
    @FXML
    private AnchorPane options10;
    @FXML
    private AnchorPane options20;
    @FXML
    private AnchorPane options30;
    @FXML
    private AnchorPane options40;
    @FXML
    private AnchorPane options50;
    @FXML
    private AnchorPane options60;
    @FXML
    private AnchorPane options01;
    @FXML
    private AnchorPane options11;
    @FXML
    private AnchorPane options21;
    @FXML
    private AnchorPane options31;
    @FXML
    private AnchorPane options41;
    @FXML
    private AnchorPane options51;
    @FXML
    private AnchorPane options61;


    @FXML
    private Spinner<Integer> day00;
    @FXML
    private Spinner<Integer> day10;
    @FXML
    private Spinner<Integer> day20;
    @FXML
    private Spinner<Integer> day30;
    @FXML
    private Spinner<Integer> day40;
    @FXML
    private Spinner<Integer> day50;
    @FXML
    private Spinner<Integer> day60;
    @FXML
    private Spinner<Integer> day01;
    @FXML
    private Spinner<Integer> day11;
    @FXML
    private Spinner<Integer> day21;
    @FXML
    private Spinner<Integer> day31;
    @FXML
    private Spinner<Integer> day41;
    @FXML
    private Spinner<Integer> day51;
    @FXML
    private Spinner<Integer> day61;


    private SpinnerValueFactory<Integer> dayValueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10);
    private SpinnerValueFactory<Integer> dayValueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10);
    private SpinnerValueFactory<Integer> dayValueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10);
    private SpinnerValueFactory<Integer> dayValueFactory4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10);
    private SpinnerValueFactory<Integer> dayValueFactory5 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10);
    private SpinnerValueFactory<Integer> dayValueFactory6 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10);
    private SpinnerValueFactory<Integer> dayValueFactory7 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10);
    private SpinnerValueFactory<Integer> dayValueFactory8 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10);
    private SpinnerValueFactory<Integer> dayValueFactory9 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10);
    private SpinnerValueFactory<Integer> dayValueFactory10 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10);
    private SpinnerValueFactory<Integer> dayValueFactory11 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10);
    private SpinnerValueFactory<Integer> dayValueFactory12 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10);
    private SpinnerValueFactory<Integer> dayValueFactory13 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10);
    private SpinnerValueFactory<Integer> dayValueFactory14 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10);

    @FXML
    private Spinner<Integer> hour00;
    @FXML
    private Spinner<Integer> hour10;
    @FXML
    private Spinner<Integer> hour20;
    @FXML
    private Spinner<Integer> hour30;
    @FXML
    private Spinner<Integer> hour40;
    @FXML
    private Spinner<Integer> hour50;
    @FXML
    private Spinner<Integer> hour60;
    @FXML
    private Spinner<Integer> hour01;
    @FXML
    private Spinner<Integer> hour11;
    @FXML
    private Spinner<Integer> hour21;
    @FXML
    private Spinner<Integer> hour31;
    @FXML
    private Spinner<Integer> hour41;
    @FXML
    private Spinner<Integer> hour51;
    @FXML
    private Spinner<Integer> hour61;

    private SpinnerValueFactory<Integer> hourValueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,24);
    private SpinnerValueFactory<Integer> hourValueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,24);
    private SpinnerValueFactory<Integer> hourValueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,24);
    private SpinnerValueFactory<Integer> hourValueFactory4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,24);
    private SpinnerValueFactory<Integer> hourValueFactory5 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,24);
    private SpinnerValueFactory<Integer> hourValueFactory6 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,24);
    private SpinnerValueFactory<Integer> hourValueFactory7 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,24);
    private SpinnerValueFactory<Integer> hourValueFactory8 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,24);
    private SpinnerValueFactory<Integer> hourValueFactory9 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,24);
    private SpinnerValueFactory<Integer> hourValueFactory10 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,24);
    private SpinnerValueFactory<Integer> hourValueFactory11 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,24);
    private SpinnerValueFactory<Integer> hourValueFactory12 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,24);
    private SpinnerValueFactory<Integer> hourValueFactory13 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,24);
    private SpinnerValueFactory<Integer> hourValueFactory14 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,24);


    @FXML
    private Spinner<Integer> minute00;
    @FXML
    private Spinner<Integer> minute10;
    @FXML
    private Spinner<Integer> minute20;
    @FXML
    private Spinner<Integer> minute30;
    @FXML
    private Spinner<Integer> minute40;
    @FXML
    private Spinner<Integer> minute50;
    @FXML
    private Spinner<Integer> minute60;
    @FXML
    private Spinner<Integer> minute01;
    @FXML
    private Spinner<Integer> minute11;
    @FXML
    private Spinner<Integer> minute21;
    @FXML
    private Spinner<Integer> minute31;
    @FXML
    private Spinner<Integer> minute41;
    @FXML
    private Spinner<Integer> minute51;
    @FXML
    private Spinner<Integer> minute61;

    private SpinnerValueFactory<Integer> minuteValueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,60);
    private SpinnerValueFactory<Integer> minuteValueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,60);
    private SpinnerValueFactory<Integer> minuteValueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,60);
    private SpinnerValueFactory<Integer> minuteValueFactory4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,60);
    private SpinnerValueFactory<Integer> minuteValueFactory5 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,60);
    private SpinnerValueFactory<Integer> minuteValueFactory6 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,60);
    private SpinnerValueFactory<Integer> minuteValueFactory7 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,60);
    private SpinnerValueFactory<Integer> minuteValueFactory8 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,60);
    private SpinnerValueFactory<Integer> minuteValueFactory9 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,60);
    private SpinnerValueFactory<Integer> minuteValueFactory10 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,60);
    private SpinnerValueFactory<Integer> minuteValueFactory11 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,60);
    private SpinnerValueFactory<Integer> minuteValueFactory12 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,60);
    private SpinnerValueFactory<Integer> minuteValueFactory13 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,60);
    private SpinnerValueFactory<Integer> minuteValueFactory14  = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,60);

    @FXML
    private Button remove00;
    @FXML
    private Button remove10;
    @FXML
    private Button remove20;
    @FXML
    private Button remove30;
    @FXML
    private Button remove40;
    @FXML
    private Button remove50;
    @FXML
    private Button remove60;
    @FXML
    private Button remove01;
    @FXML
    private Button remove11;
    @FXML
    private Button remove21;
    @FXML
    private Button remove31;
    @FXML
    private Button remove41;
    @FXML
    private Button remove51;
    @FXML
    private Button remove61;

    @FXML
    private Button record00;
    @FXML
    private Button record10;
    @FXML
    private Button record20;
    @FXML
    private Button record30;
    @FXML
    private Button record40;
    @FXML
    private Button record50;
    @FXML
    private Button record60;
    @FXML
    private Button record01;
    @FXML
    private Button record11;
    @FXML
    private Button record21;
    @FXML
    private Button record31;
    @FXML
    private Button record41;
    @FXML
    private Button record51;
    @FXML
    private Button record61;

    @FXML
    private TextField basePrice00;
    @FXML
    private TextField basePrice10;
    @FXML
    private TextField basePrice20;
    @FXML
    private TextField basePrice30;
    @FXML
    private TextField basePrice40;
    @FXML
    private TextField basePrice50;
    @FXML
    private TextField basePrice60;
    @FXML
    private TextField basePrice01;
    @FXML
    private TextField basePrice11;
    @FXML
    private TextField basePrice21;
    @FXML
    private TextField basePrice31;
    @FXML
    private TextField basePrice41;
    @FXML
    private TextField basePrice51;
    @FXML
    private TextField basePrice61;

    @FXML
    private ImageView cancel00;
    @FXML
    private ImageView cancel10;
    @FXML
    private ImageView cancel20;
    @FXML
    private ImageView cancel30;
    @FXML
    private ImageView cancel40;
    @FXML
    private ImageView cancel50;
    @FXML
    private ImageView cancel60;
    @FXML
    private ImageView cancel01;
    @FXML
    private ImageView cancel11;
    @FXML
    private ImageView cancel21;
    @FXML
    private ImageView cancel31;
    @FXML
    private ImageView cancel41;
    @FXML
    private ImageView cancel51;
    @FXML
    private ImageView cancel61;

    @FXML
    private ImageView image00;
    @FXML
    private ImageView image10;
    @FXML
    private ImageView image20;
    @FXML
    private ImageView image30;
    @FXML
    private ImageView image40;
    @FXML
    private ImageView image50;
    @FXML
    private ImageView image60;
    @FXML
    private ImageView image01;
    @FXML
    private ImageView image11;
    @FXML
    private ImageView image21;
    @FXML
    private ImageView image31;
    @FXML
    private ImageView image41;
    @FXML
    private ImageView image51;
    @FXML
    private ImageView image61;

    @FXML
    private Spinner<Integer> number00,number10,number20,number30,number40,number50
            ,number60,number01,number11,number21,number31,number41,number51,number61;
    @FXML
    private TextField price00,price10,price20,price30,price40,price50,price60,price01,price11,price21,price31,price41,price51,price61;
    private SpinnerValueFactory<Integer> numberValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
    private SpinnerValueFactory<Integer> numberValueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
    private SpinnerValueFactory<Integer> numberValueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
    private SpinnerValueFactory<Integer> numberValueFactory4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
    private SpinnerValueFactory<Integer> numberValueFactory5 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
    private SpinnerValueFactory<Integer> numberValueFactory6 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
    private SpinnerValueFactory<Integer> numberValueFactory7 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
    private SpinnerValueFactory<Integer> numberValueFactory8 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
    private SpinnerValueFactory<Integer> numberValueFactory9 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
    private SpinnerValueFactory<Integer> numberValueFactory10 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
    private SpinnerValueFactory<Integer> numberValueFactory11 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
    private SpinnerValueFactory<Integer> numberValueFactory12 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
    private SpinnerValueFactory<Integer> numberValueFactory13 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
    private SpinnerValueFactory<Integer> numberValueFactory14 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
    Connection connection;
    Statement stmt;
    ResultSet rs;
    private User user;
    private User user2;
    private ArrayList<Commodity> commodities = new ArrayList<>();

    private void hideOptions(){
        options00.setVisible(false);
        options10.setVisible(false);
        options20.setVisible(false);
        options30.setVisible(false);
        options40.setVisible(false);
        options50.setVisible(false);
        options60.setVisible(false);
        options01.setVisible(false);
        options11.setVisible(false);
        options21.setVisible(false);
        options31.setVisible(false);
        options41.setVisible(false);
        options51.setVisible(false);
        options61.setVisible(false);

    }
    private void showOptions(){
        options00.setVisible(true);
        options10.setVisible(true);
        options20.setVisible(true);
        options30.setVisible(true);
        options40.setVisible(true);
        options50.setVisible(true);
        options60.setVisible(true);
        options01.setVisible(true);
        options11.setVisible(true);
        options21.setVisible(true);
        options31.setVisible(true);
        options41.setVisible(true);
        options51.setVisible(true);
        options61.setVisible(true);
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
        page = page*14 - 14;
        if (page < commodities.size()){
            anchorPane00.setVisible(true);
            image00.setImage(commodities.get(page).getImage());
            title00.setText(commodities.get(page).getTitle());
            price00.setText(commodities.get(page).getPrice());
            numberValueFactory.setValue(commodities.get(page).getNumber());
            if (commodities.get(page).isAuction != 0) {
                remove00.setVisible(true);
                image00.setVisible(true);
                number00.setVisible(false);
                price00.setVisible(false);
                record00.setVisible(false);
            }else {
                remove00.setVisible(false);
                image00.setVisible(true);
                number00.setVisible(true);
                price00.setVisible(true);
                record00.setVisible(true);
            }
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane10.setVisible(true);
            image10.setImage(commodities.get(page).getImage());
            title10.setText(commodities.get(page).getTitle());
            price10.setText(commodities.get(page).getPrice());
            numberValueFactory2.setValue(commodities.get(page).getNumber());
            if (commodities.get(page).isAuction != 0) {
                remove10.setVisible(true);
                image10.setVisible(true);
                number10.setVisible(false);
                price10.setVisible(false);
                record10.setVisible(false);
            }else {
                remove10.setVisible(false);
                image10.setVisible(true);
                number10.setVisible(true);
                price10.setVisible(true);
                record10.setVisible(true);
            }
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane20.setVisible(true);
            image20.setImage(commodities.get(page).getImage());
            title20.setText(commodities.get(page).getTitle());
            price20.setText(commodities.get(page).getPrice());
            numberValueFactory3.setValue(commodities.get(page).getNumber());
            if (commodities.get(page).isAuction != 0) {
                remove20.setVisible(true);
                image20.setVisible(true);
                number20.setVisible(false);
                price20.setVisible(false);
                record20.setVisible(false);
            }else {
                remove20.setVisible(false);
                image20.setVisible(true);
                number20.setVisible(true);
                price20.setVisible(true);
                record20.setVisible(true);
            }
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane30.setVisible(true);
            image30.setImage(commodities.get(page).getImage());
            title30.setText(commodities.get(page).getTitle());
            price30.setText(commodities.get(page).getPrice());
            numberValueFactory4.setValue(commodities.get(page).getNumber());
            if (commodities.get(page).isAuction != 0) {
                remove30.setVisible(true);
                image30.setVisible(true);
                number30.setVisible(false);
                price30.setVisible(false);
                record30.setVisible(false);
            }else {
                remove30.setVisible(false);
                image30.setVisible(true);
                number30.setVisible(true);
                price30.setVisible(true);
                record30.setVisible(true);
            }
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane40.setVisible(true);
            image40.setImage(commodities.get(page).getImage());
            title40.setText(commodities.get(page).getTitle());
            price40.setText(commodities.get(page).getPrice());
            numberValueFactory5.setValue(commodities.get(page).getNumber());
            if (commodities.get(page).isAuction != 0) {
                remove40.setVisible(true);
                image40.setVisible(true);
                number40.setVisible(false);
                price40.setVisible(false);
                record40.setVisible(false);
            }else {
                remove40.setVisible(false);
                image40.setVisible(true);
                number40.setVisible(true);
                price40.setVisible(true);
                record40.setVisible(true);
            }
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane50.setVisible(true);
            image50.setImage(commodities.get(page).getImage());
            title50.setText(commodities.get(page).getTitle());
            price50.setText(commodities.get(page).getPrice());
            numberValueFactory6.setValue(commodities.get(page).getNumber());
            if (commodities.get(page).isAuction != 0) {
                remove50.setVisible(true);
                image50.setVisible(true);
                number50.setVisible(false);
                price50.setVisible(false);
                record50.setVisible(false);
            }else {
                remove50.setVisible(false);
                image50.setVisible(true);
                number50.setVisible(true);
                price50.setVisible(true);
                record50.setVisible(true);
            }

            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane60.setVisible(true);
            image60.setImage(commodities.get(page).getImage());
            title60.setText(commodities.get(page).getTitle());
            price60.setText(commodities.get(page).getPrice());
            numberValueFactory7.setValue(commodities.get(page).getNumber());
            if (commodities.get(page).isAuction != 0) {
                remove60.setVisible(true);
                image60.setVisible(true);
                number60.setVisible(false);
                price60.setVisible(false);
                record60.setVisible(false);
            }else {
                remove60.setVisible(false);
                image60.setVisible(true);
                number60.setVisible(true);
                price60.setVisible(true);
                record60.setVisible(true);
            }
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane01.setVisible(true);
            image01.setImage(commodities.get(page).getImage());
            title01.setText(commodities.get(page).getTitle());
            price01.setText(commodities.get(page).getPrice());
            numberValueFactory8.setValue(commodities.get(page).getNumber());
            if (commodities.get(page).isAuction != 0) {
                remove01.setVisible(true);
                image01.setVisible(true);
                number01.setVisible(false);
                price01.setVisible(false);
                record01.setVisible(false);
            }else {
                remove01.setVisible(false);
                image01.setVisible(true);
                number01.setVisible(true);
                price01.setVisible(true);
                record01.setVisible(true);
            }
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane11.setVisible(true);
            image11.setImage(commodities.get(page).getImage());
            title11.setText(commodities.get(page).getTitle());
            price11.setText(commodities.get(page).getPrice());
            numberValueFactory9.setValue(commodities.get(page).getNumber());
            if (commodities.get(page).isAuction != 0) {
                remove11.setVisible(true);
                image11.setVisible(true);
                number11.setVisible(false);
                price11.setVisible(false);
                record11.setVisible(false);
            }else {
                remove11.setVisible(false);
                image11.setVisible(true);
                number11.setVisible(true);
                price11.setVisible(true);
                record11.setVisible(true);
            }
            page++;
        }else return;
        if (page < commodities.size()){
            anchorPane21.setVisible(true);
            image21.setImage(commodities.get(page).getImage());
            title21.setText(commodities.get(page).getTitle());
            price21.setText(commodities.get(page).getPrice());
            numberValueFactory10.setValue(commodities.get(page).getNumber());
            if (commodities.get(page).isAuction != 0) {
                remove21.setVisible(true);
                image21.setVisible(true);
                number21.setVisible(false);
                price21.setVisible(false);
                record21.setVisible(false);
            }else {
                remove21.setVisible(false);
                image21.setVisible(true);
                number21.setVisible(true);
                price21.setVisible(true);
                record21.setVisible(true);
            }
            page++;
        }else  return;
        if (page < commodities.size()){
            anchorPane31.setVisible(true);
            image31.setImage(commodities.get(page).getImage());
            title31.setText(commodities.get(page).getTitle());
            price31.setText(commodities.get(page).getPrice());
            numberValueFactory11.setValue(commodities.get(page).getNumber());
            if (commodities.get(page).isAuction != 0) {
                remove31.setVisible(true);
                image31.setVisible(true);
                number31.setVisible(false);
                price31.setVisible(false);
                record31.setVisible(false);
            }else {
                remove31.setVisible(false);
                image31.setVisible(true);
                number31.setVisible(true);
                price31.setVisible(true);
                record31.setVisible(true);
            }
            page++;
        }else  return;
        if (page < commodities.size()){
            anchorPane41.setVisible(true);
            image41.setImage(commodities.get(page).getImage());
            title41.setText(commodities.get(page).getTitle());
            price41.setText(commodities.get(page).getPrice());
            numberValueFactory12.setValue(commodities.get(page).getNumber());
            if (commodities.get(page).isAuction != 0) {
                remove41.setVisible(true);
                image41.setVisible(true);
                number41.setVisible(false);
                price41.setVisible(false);
                record41.setVisible(false);
            }else {
                remove41.setVisible(false);
                image41.setVisible(true);
                number41.setVisible(true);
                price41.setVisible(true);
                record41.setVisible(true);
            }
            page++;
        }else  return;
        if (page < commodities.size()){
            anchorPane51.setVisible(true);
            image51.setImage(commodities.get(page).getImage());
            title51.setText(commodities.get(page).getTitle());
            price51.setText(commodities.get(page).getPrice());
            numberValueFactory13.setValue(commodities.get(page).getNumber());
            if (commodities.get(page).isAuction != 0) {
                remove51.setVisible(true);
                image51.setVisible(true);
                number51.setVisible(false);
                price51.setVisible(false);
                record51.setVisible(false);
            }else {
                remove51.setVisible(false);
                image51.setVisible(true);
                number51.setVisible(true);
                price51.setVisible(true);
                record51.setVisible(true);
            }
            page++;
        }else  return;
        if (page < commodities.size()){
            anchorPane61.setVisible(true);
            image61.setImage(commodities.get(page).getImage());
            title61.setText(commodities.get(page).getTitle());
            price61.setText(commodities.get(page).getPrice());
            numberValueFactory14.setValue(commodities.get(page).getNumber());
            if (commodities.get(page).isAuction != 0) {
                remove61.setVisible(true);
                image61.setVisible(true);
                number61.setVisible(false);
                price61.setVisible(false);
                record61.setVisible(false);
            }else {
                remove61.setVisible(false);
                image61.setVisible(true);
                number61.setVisible(true);
                price61.setVisible(true);
                record61.setVisible(true);
            }
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
        goToPreviousPageButton.setVisible(pageNum != 1);
    }

    private boolean isAllowedToChangePage(){
        if (options00.isVisible() || options10.isVisible() || options20.isVisible() || options30.isVisible() || options40.isVisible()
                ||options40.isVisible() || options50.isVisible() || options60.isVisible() || options01.isVisible() || options11.isVisible()
                || options21.isVisible() || options31.isVisible() || options41.isVisible() || options51.isVisible() || options61.isVisible())
        {
            return false;
        }
        return true;
    }

    @FXML
    private void goToNextPageButtonOnAction(ActionEvent event){
        if (isAllowedToChangePage()) {
            errorForChangePageLabel.setVisible(false);
            goToPreviousPageButton.setVisible(true);
            int pageNum = Integer.parseInt(page.getText());
            pageNum++;
            showAnchorPanes(pageNum);
            page.setText(String.valueOf(pageNum));
            checkToVisibleNextButton();
        }else {
            errorForChangePageLabel.setVisible(true);
        }
    }
    @FXML
    private void goToPreviousPageButtonOnAction(){
        if (isAllowedToChangePage()) {
            errorForChangePageLabel.setVisible(false);
            int pageNume = Integer.parseInt(page.getText());
            pageNume--;
            showAnchorPanes(pageNume);
            page.setText(String.valueOf(pageNume));
            checkToVisiblePreviousButton();
            checkToVisibleNextButton();
        }else {
            errorForChangePageLabel.setVisible(true);
        }
    }

    public void setUser(User user) {
        if (user == null) {
            throw new NullPointerException("User is null");
        }
        this.user = user;
        typeInfo.setText(user.getUsername());
        selectCommodities();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hideOptions();
        try {
            DatabaseConnectionJDBC databaseConnectionJDBC = new DatabaseConnectionJDBC();
            connection = databaseConnectionJDBC.getConnection();
            stmt = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        setValueFactories();
        numbersListeners();
    }
    private void setValueFactories(){
        number00.setValueFactory(numberValueFactory);
        number10.setValueFactory(numberValueFactory2);
        number20.setValueFactory(numberValueFactory3);
        number30.setValueFactory(numberValueFactory4);
        number40.setValueFactory(numberValueFactory5);
        number50.setValueFactory(numberValueFactory6);
        number60.setValueFactory(numberValueFactory7);
        number01.setValueFactory(numberValueFactory8);
        number11.setValueFactory(numberValueFactory9);
        number21.setValueFactory(numberValueFactory10);
        number31.setValueFactory(numberValueFactory11);
        number41.setValueFactory(numberValueFactory12);
        number51.setValueFactory(numberValueFactory13);
        number61.setValueFactory(numberValueFactory14);

        day00.setValueFactory(dayValueFactory1);
        day10.setValueFactory(dayValueFactory2);
        day20.setValueFactory(dayValueFactory3);
        day30.setValueFactory(dayValueFactory4);
        day40.setValueFactory(dayValueFactory5);
        day50.setValueFactory(dayValueFactory6);
        day60.setValueFactory(dayValueFactory7);
        day01.setValueFactory(dayValueFactory8);
        day11.setValueFactory(dayValueFactory9);
        day21.setValueFactory(dayValueFactory10);
        day31.setValueFactory(dayValueFactory11);
        day41.setValueFactory(dayValueFactory12);
        day51.setValueFactory(dayValueFactory13);
        day61.setValueFactory(dayValueFactory14);

        hour00.setValueFactory(hourValueFactory1);
        hour10.setValueFactory(hourValueFactory2);
        hour20.setValueFactory(hourValueFactory3);
        hour30.setValueFactory(hourValueFactory4);
        hour40.setValueFactory(hourValueFactory5);
        hour50.setValueFactory(hourValueFactory6);
        hour60.setValueFactory(hourValueFactory7);
        hour01.setValueFactory(hourValueFactory8);
        hour11.setValueFactory(hourValueFactory9);
        hour21.setValueFactory(hourValueFactory10);
        hour31.setValueFactory(hourValueFactory11);
        hour41.setValueFactory(hourValueFactory12);
        hour51.setValueFactory(hourValueFactory13);
        hour61.setValueFactory(hourValueFactory14);

        minute00.setValueFactory(minuteValueFactory1);
        minute10.setValueFactory(minuteValueFactory2);
        minute20.setValueFactory(minuteValueFactory3);
        minute30.setValueFactory(minuteValueFactory4);
        minute40.setValueFactory(minuteValueFactory5);
        minute50.setValueFactory(minuteValueFactory6);
        minute60.setValueFactory(minuteValueFactory7);
        minute01.setValueFactory(minuteValueFactory8);
        minute11.setValueFactory(minuteValueFactory9);
        minute21.setValueFactory(minuteValueFactory10);
        minute31.setValueFactory(minuteValueFactory11);
        minute41.setValueFactory(minuteValueFactory12);
        minute51.setValueFactory(minuteValueFactory13);
        minute61.setValueFactory(minuteValueFactory14);
    }

    public void selectCommodities(){

        System.out.println(user);

        String sql = "SELECT * FROM AllCommodities WHERE userName = " + "'" + user.getUsername() + "'";
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        commodities.clear();
        while (true){
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            int number1;
            String type1;
            String brand1;
            String price1;
            String ratio1;
            String title1;
            int commodityId;
            int isAuction;
            Image image;
            try {
                number1 = rs.getInt("Number");
                type1 = rs.getString("Type");
                brand1 = rs.getString("Brand");
                price1 = rs.getString("Price");
                ratio1 = rs.getString("Ratio");
                title1 = rs.getString("Title");
                commodityId = rs.getInt("commodityId");
                isAuction = rs.getInt("isAuction");
//                byte[] imageData = rs.getBytes("image");
//                ByteArrayInputStream inputStream = new ByteArrayInputStream(imageData);
//                 image = new Image(inputStream);
                String imageName = rs.getString("imageName");
                 image = new Image(imageName);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            commodities.add(new Commodity(type1,brand1,price1,ratio1,title1,number1,commodityId,isAuction,image));


        }

        showAnchorPanes(1);
        checkToVisibleNextButton();
        checkToVisiblePreviousButton();
        try {
            rs.close();
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }


    }
    private void numbersListeners(){
        number00.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14;
            int id  = commodities.get(page).getCommodityId();
            String sql2 = "UPDATE AllCommodities SET Number = ? WHERE commodityId = ?";
            PreparedStatement pstmt = null;
            try {
                pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1,numberValueFactory.getValue());
                pstmt.setInt(2,id);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        number10.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14+1;
            int id  = commodities.get(page).getCommodityId();
            String sql2 = "UPDATE AllCommodities SET Number = ? WHERE commodityId = ?";
            try {
                PreparedStatement pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, numberValueFactory2.getValue());
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException();
            }
        });
        number20.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14+2;
            int id  = commodities.get(page).getCommodityId();
            String sql2 = "UPDATE AllCommodities SET Number = ? WHERE commodityId = ?";
            try {
                PreparedStatement pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, numberValueFactory3.getValue());
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException();
            }
        });
        number30.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14+3;
            int id  = commodities.get(page).getCommodityId();
            String sql2 = "UPDATE AllCommodities SET Number = ? WHERE commodityId = ?";
            try {
                PreparedStatement pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, numberValueFactory4.getValue());
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException();
            }
        });
        number40.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14+4;
            int id  = commodities.get(page).getCommodityId();
            String sql2 = "UPDATE AllCommodities SET Number = ? WHERE commodityId = ?";
            try {
                PreparedStatement pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, numberValueFactory5.getValue());
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException();
            }
        });
        number50.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14+5;
            int id  = commodities.get(page).getCommodityId();
            String sql2 = "UPDATE AllCommodities SET Number = ? WHERE commodityId = ?";
            try {
                PreparedStatement pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, numberValueFactory6.getValue());
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException();
            }
        });
        number60.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14+6;
            int id  = commodities.get(page).getCommodityId();
            String sql2 = "UPDATE AllCommodities SET Number = ? WHERE commodityId = ?";
            try {
                PreparedStatement pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, numberValueFactory7.getValue());
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException();
            }
        });
        number01.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14+7;
            int id  = commodities.get(page).getCommodityId();
            String sql2 = "UPDATE AllCommodities SET Number = ? WHERE commodityId = ?";
            try {
                PreparedStatement pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, numberValueFactory8.getValue());
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException();
            }
        });
        number11.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14+8;
            int id  = commodities.get(page).getCommodityId();
            String sql2 = "UPDATE AllCommodities SET Number = ? WHERE commodityId = ?";
            try {
                PreparedStatement pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, numberValueFactory9.getValue());
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException();
            }
        });
        number21.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14+9;
            int id  = commodities.get(page).getCommodityId();
            String sql2 = "UPDATE AllCommodities SET Number = ? WHERE commodityId = ?";
            try {
                PreparedStatement pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, numberValueFactory10.getValue());
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException();
            }
        });
        number31.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14+10;
            int id  = commodities.get(page).getCommodityId();
            String sql2 = "UPDATE AllCommodities SET Number = ? WHERE commodityId = ?";
            try {
                PreparedStatement pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, numberValueFactory11.getValue());
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException();
            }
        });
        number41.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14+11;
            int id  = commodities.get(page).getCommodityId();
            String sql2 = "UPDATE AllCommodities SET Number = ? WHERE commodityId = ?";
            try {
                PreparedStatement pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, numberValueFactory12.getValue());
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException();
            }
        });
        number51.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14+12;
            int id  = commodities.get(page).getCommodityId();
            String sql2 = "UPDATE AllCommodities SET Number = ? WHERE commodityId = ?";
            try {
                PreparedStatement pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, numberValueFactory13.getValue());
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException();
            }
        });
        number61.valueProperty().addListener((observableValue, integer, t1) -> {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14+13;
            int id  = commodities.get(page).getCommodityId();
            String sql2 = "UPDATE AllCommodities SET Number = ? WHERE commodityId = ?";
            try {
                PreparedStatement pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, numberValueFactory14.getValue());
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException();
            }
        });
    }

    public void setDelete00OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14;
//        String sql = "DELETE FROM AllCommodities WHERE Type = " + "'"+commodities.get(page).getType() + "'";
        String sql = "DELETE FROM AllCommodities WHERE commodityId = " + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);
        sql = "DELETE FROM Auction WHERE commodityId = " + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);
        selectCommodities();
    }
    public void setDelete10OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 1;
        String sql = "DELETE FROM AllCommodities WHERE commodityId = " + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);
        sql = "DELETE FROM Auction WHERE commodityId = " + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);
        selectCommodities();
    }
    public void setDelete20OnAction() throws SQLException{
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 2;
        String sql = "DELETE FROM AllCommodities WHERE commodityId = " + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);
        sql = "DELETE FROM Auction WHERE commodityId = " + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);
        selectCommodities();
    }
    public void setDelete30OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14+3;
        String sql = "DELETE FROM AllCommodities WHERE commodityId = " + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);
        sql = "DELETE FROM Auction WHERE commodityId = " + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);
        selectCommodities();
    }
    public void setDelete40OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 4;
        String sql = "DELETE FROM AllCommodities WHERE commodityId = " + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);
        sql = "DELETE FROM Auction WHERE commodityId = " + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);
        selectCommodities();
    }
    public void setDelete50OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 5;
        String sql = "DELETE FROM AllCommodities WHERE commodityId = " +commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);
        sql = "DELETE FROM Auction WHERE commodityId = " + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);
        selectCommodities();
    }
    public void setDelete60OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 6;
        String sql = "DELETE FROM AllCommodities WHERE commodityId = " + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);
        sql = "DELETE FROM Auction WHERE commodityId = " + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);
        selectCommodities();
    }
    public void setDelete01OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 7;
        String sql = "DELETE FROM AllCommodities WHERE commodityId = " + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);
        sql = "DELETE FROM Auction WHERE commodityId = " + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);
        selectCommodities();
    }
    public void setDelete11OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 8;
        String sql = "DELETE FROM AllCommodities WHERE commodityId = " + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);
        sql = "DELETE FROM Auction WHERE commodityId = " + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);
        selectCommodities();
    }
    public void setDelete21OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 9;
        String sql = "DELETE FROM AllCommodities WHERE commodityId = " + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);
        sql = "DELETE FROM Auction WHERE commodityId = " + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);
        selectCommodities();
    }
    public void setDelete31OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 10;
        String sql = "DELETE FROM AllCommodities WHERE commodityId = " + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);
        sql = "DELETE FROM Auction WHERE commodityId = " + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);
        selectCommodities();
    }
    public void setDelete41OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 11;
        String sql = "DELETE FROM AllCommodities WHERE commodityId = " + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);
        sql = "DELETE FROM Auction WHERE commodityId = " + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);
        selectCommodities();
    }
    public void setDelete51OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 12;
        String sql = "DELETE FROM AllCommodities WHERE commodityId = " + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);
        sql = "DELETE FROM Auction WHERE commodityId = " + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);
        selectCommodities();
    }
    public void setDelete61OnAction() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14 + 13;
        String sql = "DELETE FROM AllCommodities WHERE commodityId = " + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);
        sql = "DELETE FROM Auction WHERE commodityId = " + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);
        selectCommodities();
    }

    public void setBackToHomeButtonOnAction(ActionEvent event) throws IOException {
        new Login(user).loginToHome((Node) event.getSource());
    }

    public void setRecordButtonOnAction(ActionEvent event) throws IOException {
//        int page = Integer.parseInt(this.page.getText());
//        page = (page*14) - 14;
//
//        int id = commodities.get(page).getCommodityId();
//        String sql = "UPDATE AllCommodities SET Number = "+ numberValueFactory.getValue() + " WHERE commodityId = " + id +";";
//               stmt.executeUpdate(sql);

//            String sql2 = "UPDATE AllCommodities SET Price = '" + price00.getText() + "' WHERE commodityId = " + id + ";";
//            stmt.executeUpdate(sql2);

//        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
//        PreparedStatement pstmt = connection.prepareStatement(sql2);
//        pstmt.setString(1, price00.getText());
//        pstmt.setInt(2, id);
//        pstmt.executeUpdate();

        if (isAllowedToChangePage()) {
            errorForChangePageLabel.setVisible(false);
            new Login(user).loginToHome((Node) event.getSource());
        }
        else {
            errorForChangePageLabel.setVisible(true);
        }
    }
    public void setPrice00OnTextChanged() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14;

        int id = commodities.get(page).getCommodityId();
        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql2);
        pstmt.setString(1, price00.getText());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
    public void setPrice10OnTextChanged() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14+1;

        int id = commodities.get(page).getCommodityId();
        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql2);
        pstmt.setString(1, price10.getText());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
    public void setPrice20OnTextChanged() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14+2;

        int id = commodities.get(page).getCommodityId();
        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql2);
        pstmt.setString(1, price20.getText());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
    public void setPrice30OnTextChanged() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14+3;

        int id = commodities.get(page).getCommodityId();
        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql2);
        pstmt.setString(1, price30.getText());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
    public void setPrice40OnTextChanged() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14+4;

        int id = commodities.get(page).getCommodityId();
        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql2);
        pstmt.setString(1, price40.getText());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
    public void setPrice50OnTextChanged() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14+5;

        int id = commodities.get(page).getCommodityId();
        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql2);
        pstmt.setString(1, price50.getText());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
    public void setPrice60OnTextChanged() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14+6;

        int id = commodities.get(page).getCommodityId();
        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql2);
        pstmt.setString(1, price60.getText());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
    public void setPrice01OnTextChanged() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14+7;

        int id = commodities.get(page).getCommodityId();
        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql2);
        pstmt.setString(1, price01.getText());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
    public void setPrice11OnTextChanged() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14+8;

        int id = commodities.get(page).getCommodityId();
        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql2);
        pstmt.setString(1, price11.getText());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
    public void setPrice21OnTextChanged() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14+9;

        int id = commodities.get(page).getCommodityId();
        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql2);
        pstmt.setString(1, price21.getText());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
    public void setPrice31OnTextChanged() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14+10;

        int id = commodities.get(page).getCommodityId();
        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql2);
        pstmt.setString(1, price31.getText());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
    public void setPrice41OnTextChanged() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14+11;

        int id = commodities.get(page).getCommodityId();
        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql2);
        pstmt.setString(1, price41.getText());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
    public void setPrice51OnTextChanged() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14+12;

        int id = commodities.get(page).getCommodityId();
        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql2);
        pstmt.setString(1, price51.getText());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }
    public void setPrice61OnTextChanged() throws SQLException {
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) - 14+13;

        int id = commodities.get(page).getCommodityId();
        String sql2 = "UPDATE AllCommodities SET Price = ? WHERE commodityId = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql2);
        pstmt.setString(1, price61.getText());
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }

    public void setAuction00OnAction() {
        image00.setVisible(false);
        options00.setVisible(true);
        auction00.setVisible(false);
    }
    public void setAuction10OnAction() {
        image10.setVisible(false);
        options10.setVisible(true);
        auction10.setVisible(false);
    }
    public void setAuction20OnAction() {
        image20.setVisible(false);
        options20.setVisible(true);
        auction20.setVisible(false);
    }
    public void setAuction30OnAction() {
        image30.setVisible(false);
        options30.setVisible(true);
        auction30.setVisible(false);
    }
    public void setAuction40OnAction() {
        image40.setVisible(false);
        options40.setVisible(true);
        auction40.setVisible(false);
    }
    public void setAuction50OnAction() {
        image50.setVisible(false);
        options50.setVisible(true);
        auction50.setVisible(false);
    }
    public void setAuction60OnAction() {
        image60.setVisible(false);
        options60.setVisible(true);
        auction60.setVisible(false);
    }
    public void setAuction01OnAction() {
        image01.setVisible(false);
        options01.setVisible(true);
        auction01.setVisible(false);
    }
    public void setAuction11OnAction() {
        image11.setVisible(false);
        options11.setVisible(true);
        auction11.setVisible(false);
    }
    public void setAuction21OnAction() {
        image21.setVisible(false);
        options21.setVisible(true);
        auction21.setVisible(false);
    }
    public void setAuction31OnAction() {
        image31.setVisible(false);
        options31.setVisible(true);
        auction31.setVisible(false);
    }
    public void setAuction41OnAction() {
        image41.setVisible(false);
        options41.setVisible(true);
        auction41.setVisible(false);
    }
    public void setAuction51OnAction() {
        image51.setVisible(false);
        options51.setVisible(true);
        auction51.setVisible(false);
    }
    public void setAuction61OnAction() {
        image61.setVisible(false);
        options61.setVisible(true);
        auction61.setVisible(false);
    }


    public void setBasePrice00OnAction(){}
    public void setBasePrice10OnAction(){}
    public void setBasePrice20OnAction(){}
    public void setBasePrice30OnAction(){}
    public void setBasePrice40OnAction(){}
    public void setBasePrice50OnAction(){}
    public void setBasePrice60OnAction(){}
    public void setBasePrice01OnAction(){}
    public void setBasePrice11OnAction(){}
    public void setBasePrice21OnAction(){}
    public void setBasePrice31OnAction(){}
    public void setBasePrice41OnAction(){}
    public void setBasePrice51OnAction(){}
    public void setBasePrice61OnAction(){}

    public void setCancel00OnAction(){
        options00.setVisible(false);
        remove00.setVisible(false);
        auction00.setVisible(true);
        image00.setVisible(true);
    }
    public void setCancel10OnAction(){
        options10.setVisible(false);
        remove10.setVisible(false);
        auction10.setVisible(true);
        image10.setVisible(true);
    }
    public void setCancel20OnAction(){
        options20.setVisible(false);
        remove20.setVisible(false);
        auction20.setVisible(true);
        image20.setVisible(true);
    }
    public void setCancel30OnAction(){
        options30.setVisible(false);
        remove30.setVisible(false);
        auction30.setVisible(true);
        image30.setVisible(true);
    }
    public void setCancel40OnAction(){
        options40.setVisible(false);
        remove40.setVisible(false);
        auction40.setVisible(true);
        image40.setVisible(true);
    }
    public void setCancel50OnAction(){
        options50.setVisible(false);
        remove50.setVisible(false);
        auction50.setVisible(true);
        image50.setVisible(true);
    }
    public void setCancel60OnAction(){
        options60.setVisible(false);
        remove60.setVisible(false);
        auction60.setVisible(true);
        image60.setVisible(true);
    }
    public void setCancel01OnAction(){
        options01.setVisible(false);
        remove01.setVisible(false);
        auction01.setVisible(true);
        image01.setVisible(true);
    }
    public void setCancel11OnAction(){
        options11.setVisible(false);
        remove11.setVisible(false);
        auction11.setVisible(true);
        image11.setVisible(true);
    }
    public void setCancel21OnAction(){
        options21.setVisible(false);
        remove21.setVisible(false);
        auction21.setVisible(true);
        image21.setVisible(true);
    }
    public void setCancel31OnAction(){
        options31.setVisible(false);
        remove31.setVisible(false);
        auction31.setVisible(true);
        image31.setVisible(true);
    }
    public void setCancel41OnAction(){
        options41.setVisible(false);
        remove41.setVisible(false);
        auction41.setVisible(true);
        image41.setVisible(true);
    }
    public void setCancel51OnAction(){
        options51.setVisible(false);
        remove51.setVisible(false);
        auction51.setVisible(true);
        image51.setVisible(true);
    }
    public void setCancel61OnAction(){
        options61.setVisible(false);
        remove61.setVisible(false);
        auction61.setVisible(true);
        image61.setVisible(true);
    }

    public void setRecord00OnAction() throws SQLException {
        if (basePrice00.getText().length() == 0){
            basePrice00.setStyle("-fx-border-color: red;");
        }else {
                int page = Integer.parseInt(this.page.getText());
                page = (page*14) - 14;
                Statement statement = connection.createStatement();
                long days = dayValueFactory1.getValue(); //days left
                long hours = hourValueFactory1.getValue(); //hours left
                long minutes = minuteValueFactory1.getValue(); //minutes left

                LocalDateTime localDateTime = LocalDateTime.now();
                localDateTime = localDateTime.plusDays(days);
                localDateTime = localDateTime.plusHours(hours);
                localDateTime = localDateTime.plusMinutes(minutes);
                String endTime = localDateTime.toString();

                int commodityId = commodities.get(page).getCommodityId();
                int number = numberValueFactory.getValue();

                String basePrice = basePrice00.getText();
                String sql = "INSERT INTO Auction (sellerUserName, commodityId, basePrice, number, date) VALUES ('"
                        + user.getUsername() + "', " + commodityId + ", '" + basePrice + "', " + number + ", '" + endTime + "')";

                statement.executeUpdate(sql);

                sql = "SELECT * FROM Auction WHERE rowid = (SELECT MAX(rowid) FROM Auction)";
                try {
                    rs = stmt.executeQuery(sql);
                }catch (SQLException e){
                    throw new RuntimeException(e);
                }
                int auctionId = 0;
                while(rs.next()){
                    auctionId = rs.getInt("auctionId");
                }
                System.out.println("AuctionId= " + auctionId);
                sql = "UPDATE AllCommodities SET isAuction = ? WHERE commodityId = ?";
                PreparedStatement pstmt = null;
                try {
                    pstmt = connection.prepareStatement(sql);
                    pstmt.setInt(1,auctionId);
                    pstmt.setInt(2,commodityId);
                    pstmt.executeUpdate();
                }catch (SQLException e){
                    throw new RuntimeException(e);
                }
            try {
                Sound.auctionIsCompleted();
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }

            auction00.setVisible(false);
                remove00.setVisible(true);
                options00.setVisible(false);
                image00.setVisible(true);
                number00.setVisible(false);
                price00.setVisible(false);

        }
    }
    public void setRecord10OnAction() throws SQLException {
        if (basePrice10.getText().length() == 0){  /////////////////////////////////////////////////////////////////
            basePrice10.setStyle("-fx-border-color: red;");///////////////////////////////////////////////////////
        }else {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14 + 1;//////////////////////////////////////////////////////////////////////////////////
            Statement statement = connection.createStatement();
            long days = dayValueFactory2.getValue(); //days left     /////////////////////////////////////////////////////
            long hours = hourValueFactory2.getValue(); //hours left   /////////////////////////////////////////////////////
            long minutes = minuteValueFactory2.getValue(); //minutes left  /////////////////////////////////////////////////

            LocalDateTime localDateTime = LocalDateTime.now();
            localDateTime = localDateTime.plusDays(days);
            localDateTime = localDateTime.plusHours(hours);
            localDateTime = localDateTime.plusMinutes(minutes);
            String endTime = localDateTime.toString();

            int commodityId = commodities.get(page).getCommodityId();
            int number = numberValueFactory2.getValue(); //////////////////////////////////////////////////////////

            String basePrice = basePrice10.getText();/////////////////////////////////////////////////////////////
            String sql = "INSERT INTO Auction (sellerUserName, commodityId, basePrice, number, date) VALUES ('"
                    + user.getUsername() + "', " + commodityId + ", '" + basePrice + "', " + number + ", '" + endTime + "')";

            statement.executeUpdate(sql);

            sql = "SELECT * FROM Auction WHERE rowid = (SELECT MAX(rowid) FROM Auction)";
            try {
                rs = stmt.executeQuery(sql);
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
            int auctionId = 0;
            while(rs.next()){
                auctionId = rs.getInt("auctionId");
            }
            System.out.println("AuctionId= " + auctionId);
            sql = "UPDATE AllCommodities SET isAuction = ? WHERE commodityId = ?";
            PreparedStatement pstmt = null;
            try {
                pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1,auctionId);
                pstmt.setInt(2,commodityId);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
            try {
                Sound.auctionIsCompleted();
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }


            auction10.setVisible(false);////////////////////////////////////////////////////////////
            remove10.setVisible(true);////////////////////////////////////////////////////////////
            options10.setVisible(false);/////////////////////////////////////////////////////////////
            image10.setVisible(true);//////////////////////////////////////////////////////////////
            number10.setVisible(false);////////////////////////////////////////////////////////////
            price10.setVisible(false);///////////////////////////////////////////////////////////////////////

        }
    }
    public void setRecord20OnAction() throws SQLException {
        if (basePrice20.getText().length() == 0){  /////////////////////////////////////////////////////////////////
            basePrice20.setStyle("-fx-border-color: red;");///////////////////////////////////////////////////////
        }else {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14 + 2;//////////////////////////////////////////////////////////////////////////////////
            Statement statement = connection.createStatement();
            long days = dayValueFactory3.getValue(); //days left     /////////////////////////////////////////////////////
            long hours = hourValueFactory3.getValue(); //hours left   /////////////////////////////////////////////////////
            long minutes = minuteValueFactory3.getValue(); //minutes left  /////////////////////////////////////////////////

            LocalDateTime localDateTime = LocalDateTime.now();
            localDateTime = localDateTime.plusDays(days);
            localDateTime = localDateTime.plusHours(hours);
            localDateTime = localDateTime.plusMinutes(minutes);
            String endTime = localDateTime.toString();

            int commodityId = commodities.get(page).getCommodityId();
            int number = numberValueFactory3.getValue(); //////////////////////////////////////////////////////////

            String basePrice = basePrice20.getText();/////////////////////////////////////////////////////////////
            String sql = "INSERT INTO Auction (sellerUserName, commodityId, basePrice, number, date) VALUES ('"
                    + user.getUsername() + "', " + commodityId + ", '" + basePrice + "', " + number + ", '" + endTime + "')";

            statement.executeUpdate(sql);

            sql = "SELECT * FROM Auction WHERE rowid = (SELECT MAX(rowid) FROM Auction)";
            try {
                rs = stmt.executeQuery(sql);
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
            int auctionId = 0;
            while(rs.next()){
                auctionId = rs.getInt("auctionId");
            }
            System.out.println("AuctionId= " + auctionId);
            sql = "UPDATE AllCommodities SET isAuction = ? WHERE commodityId = ?";
            PreparedStatement pstmt = null;
            try {
                pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1,auctionId);
                pstmt.setInt(2,commodityId);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }

            try {
                Sound.auctionIsCompleted();
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }


            auction20.setVisible(false);////////////////////////////////////////////////////////////
            remove20.setVisible(true);////////////////////////////////////////////////////////////
            options20.setVisible(false);/////////////////////////////////////////////////////////////
            image20.setVisible(true);//////////////////////////////////////////////////////////////
            number20.setVisible(false);////////////////////////////////////////////////////////////
            price20.setVisible(false);///////////////////////////////////////////////////////////////////////

        }
    }
    public void setRecord30OnAction() throws SQLException {
        if (basePrice30.getText().length() == 0){  /////////////////////////////////////////////////////////////////
            basePrice30.setStyle("-fx-border-color: red;");///////////////////////////////////////////////////////
        }else {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14 + 3;//////////////////////////////////////////////////////////////////////////////////
            Statement statement = connection.createStatement();
            long days = dayValueFactory4.getValue(); //days left     /////////////////////////////////////////////////////
            long hours = hourValueFactory4.getValue(); //hours left   /////////////////////////////////////////////////////
            long minutes = minuteValueFactory4.getValue(); //minutes left  /////////////////////////////////////////////////

            LocalDateTime localDateTime = LocalDateTime.now();
            localDateTime = localDateTime.plusDays(days);
            localDateTime = localDateTime.plusHours(hours);
            localDateTime = localDateTime.plusMinutes(minutes);
            String endTime = localDateTime.toString();

            int commodityId = commodities.get(page).getCommodityId();
            int number = numberValueFactory4.getValue(); //////////////////////////////////////////////////////////

            String basePrice = basePrice30.getText();/////////////////////////////////////////////////////////////
            String sql = "INSERT INTO Auction (sellerUserName, commodityId, basePrice, number, date) VALUES ('"
                    + user.getUsername() + "', " + commodityId + ", '" + basePrice + "', " + number + ", '" + endTime + "')";

            statement.executeUpdate(sql);

            sql = "SELECT * FROM Auction WHERE rowid = (SELECT MAX(rowid) FROM Auction)";
            try {
                rs = stmt.executeQuery(sql);
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
            int auctionId = 0;
            while(rs.next()){
                auctionId = rs.getInt("auctionId");
            }
            System.out.println("AuctionId= " + auctionId);
            sql = "UPDATE AllCommodities SET isAuction = ? WHERE commodityId = ?";
            PreparedStatement pstmt = null;
            try {
                pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1,auctionId);
                pstmt.setInt(2,commodityId);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }

            try {
                Sound.auctionIsCompleted();
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }


            auction30.setVisible(false);////////////////////////////////////////////////////////////
            remove30.setVisible(true);////////////////////////////////////////////////////////////
            options30.setVisible(false);/////////////////////////////////////////////////////////////
            image30.setVisible(true);//////////////////////////////////////////////////////////////
            number30.setVisible(false);////////////////////////////////////////////////////////////
            price30.setVisible(false);///////////////////////////////////////////////////////////////////////

        }
    }
    public void setRecord40OnAction() throws SQLException {
        if (basePrice40.getText().length() == 0){  /////////////////////////////////////////////////////////////////
            basePrice40.setStyle("-fx-border-color: red;");///////////////////////////////////////////////////////
        }else {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14 + 4;//////////////////////////////////////////////////////////////////////////////////
            Statement statement = connection.createStatement();
            long days = dayValueFactory5.getValue(); //days left     /////////////////////////////////////////////////////
            long hours = hourValueFactory5.getValue(); //hours left   /////////////////////////////////////////////////////
            long minutes = minuteValueFactory5.getValue(); //minutes left  /////////////////////////////////////////////////

            LocalDateTime localDateTime = LocalDateTime.now();
            localDateTime = localDateTime.plusDays(days);
            localDateTime = localDateTime.plusHours(hours);
            localDateTime = localDateTime.plusMinutes(minutes);
            String endTime = localDateTime.toString();

            int commodityId = commodities.get(page).getCommodityId();
            int number = numberValueFactory5.getValue(); //////////////////////////////////////////////////////////

            String basePrice = basePrice40.getText();/////////////////////////////////////////////////////////////
            String sql = "INSERT INTO Auction (sellerUserName, commodityId, basePrice, number, date) VALUES ('"
                    + user.getUsername() + "', " + commodityId + ", '" + basePrice + "', " + number + ", '" + endTime + "')";

            statement.executeUpdate(sql);

            sql = "SELECT * FROM Auction WHERE rowid = (SELECT MAX(rowid) FROM Auction)";
            try {
                rs = stmt.executeQuery(sql);
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
            int auctionId = 0;
            while(rs.next()){
                auctionId = rs.getInt("auctionId");
            }
            System.out.println("AuctionId= " + auctionId);
            sql = "UPDATE AllCommodities SET isAuction = ? WHERE commodityId = ?";
            PreparedStatement pstmt = null;
            try {
                pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1,auctionId);
                pstmt.setInt(2,commodityId);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }

            try {
                Sound.auctionIsCompleted();
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }


            auction40.setVisible(false);////////////////////////////////////////////////////////////
            remove40.setVisible(true);////////////////////////////////////////////////////////////
            options40.setVisible(false);/////////////////////////////////////////////////////////////
            image40.setVisible(true);//////////////////////////////////////////////////////////////
            number40.setVisible(false);////////////////////////////////////////////////////////////
            price40.setVisible(false);///////////////////////////////////////////////////////////////////////

        }
    }
    public void setRecord50OnAction() throws SQLException {
        if (basePrice50.getText().length() == 0){  /////////////////////////////////////////////////////////////////
            basePrice50.setStyle("-fx-border-color: red;");///////////////////////////////////////////////////////
        }else {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14 + 5;//////////////////////////////////////////////////////////////////////////////////
            Statement statement = connection.createStatement();
            long days = dayValueFactory6.getValue(); //days left     /////////////////////////////////////////////////////
            long hours = hourValueFactory6.getValue(); //hours left   /////////////////////////////////////////////////////
            long minutes = minuteValueFactory6.getValue(); //minutes left  /////////////////////////////////////////////////

            LocalDateTime localDateTime = LocalDateTime.now();
            localDateTime = localDateTime.plusDays(days);
            localDateTime = localDateTime.plusHours(hours);
            localDateTime = localDateTime.plusMinutes(minutes);
            String endTime = localDateTime.toString();

            int commodityId = commodities.get(page).getCommodityId();
            int number = numberValueFactory6.getValue(); //////////////////////////////////////////////////////////

            String basePrice = basePrice50.getText();/////////////////////////////////////////////////////////////
            String sql = "INSERT INTO Auction (sellerUserName, commodityId, basePrice, number, date) VALUES ('"
                    + user.getUsername() + "', " + commodityId + ", '" + basePrice + "', " + number + ", '" + endTime + "')";

            statement.executeUpdate(sql);

            sql = "SELECT * FROM Auction WHERE rowid = (SELECT MAX(rowid) FROM Auction)";
            try {
                rs = stmt.executeQuery(sql);
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
            int auctionId = 0;
            while(rs.next()){
                auctionId = rs.getInt("auctionId");
            }
            System.out.println("AuctionId= " + auctionId);
            sql = "UPDATE AllCommodities SET isAuction = ? WHERE commodityId = ?";
            PreparedStatement pstmt = null;
            try {
                pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1,auctionId);
                pstmt.setInt(2,commodityId);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }

            try {
                Sound.auctionIsCompleted();
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }


            auction50.setVisible(false);////////////////////////////////////////////////////////////
            remove50.setVisible(true);////////////////////////////////////////////////////////////
            options50.setVisible(false);/////////////////////////////////////////////////////////////
            image50.setVisible(true);//////////////////////////////////////////////////////////////
            number50.setVisible(false);////////////////////////////////////////////////////////////
            price50.setVisible(false);///////////////////////////////////////////////////////////////////////

        }
    }
    public void setRecord60OnAction() throws SQLException {
        if (basePrice60.getText().length() == 0){  /////////////////////////////////////////////////////////////////
            basePrice60.setStyle("-fx-border-color: red;");///////////////////////////////////////////////////////
        }else {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14 + 6;//////////////////////////////////////////////////////////////////////////////////
            Statement statement = connection.createStatement();
            long days = dayValueFactory7.getValue(); //days left     /////////////////////////////////////////////////////
            long hours = hourValueFactory7.getValue(); //hours left   /////////////////////////////////////////////////////
            long minutes = minuteValueFactory7.getValue(); //minutes left  /////////////////////////////////////////////////

            LocalDateTime localDateTime = LocalDateTime.now();
            localDateTime = localDateTime.plusDays(days);
            localDateTime = localDateTime.plusHours(hours);
            localDateTime = localDateTime.plusMinutes(minutes);
            String endTime = localDateTime.toString();

            int commodityId = commodities.get(page).getCommodityId();
            int number = numberValueFactory7.getValue(); //////////////////////////////////////////////////////////

            String basePrice = basePrice60.getText();/////////////////////////////////////////////////////////////
            String sql = "INSERT INTO Auction (sellerUserName, commodityId, basePrice, number, date) VALUES ('"
                    + user.getUsername() + "', " + commodityId + ", '" + basePrice + "', " + number + ", '" + endTime + "')";

            statement.executeUpdate(sql);

            sql = "SELECT * FROM Auction WHERE rowid = (SELECT MAX(rowid) FROM Auction)";
            try {
                rs = stmt.executeQuery(sql);
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
            int auctionId = 0;
            while(rs.next()){
                auctionId = rs.getInt("auctionId");
            }
            System.out.println("AuctionId= " + auctionId);
            sql = "UPDATE AllCommodities SET isAuction = ? WHERE commodityId = ?";
            PreparedStatement pstmt = null;
            try {
                pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1,auctionId);
                pstmt.setInt(2,commodityId);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }

            try {
                Sound.auctionIsCompleted();
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }


            auction60.setVisible(false);////////////////////////////////////////////////////////////
            remove60.setVisible(true);////////////////////////////////////////////////////////////
            options60.setVisible(false);/////////////////////////////////////////////////////////////
            image60.setVisible(true);//////////////////////////////////////////////////////////////
            number60.setVisible(false);////////////////////////////////////////////////////////////
            price60.setVisible(false);///////////////////////////////////////////////////////////////////////

        }
    }
    public void setRecord01OnAction() throws SQLException {
        if (basePrice01.getText().length() == 0){  /////////////////////////////////////////////////////////////////
            basePrice01.setStyle("-fx-border-color: red;");///////////////////////////////////////////////////////
        }else {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14 + 7;//////////////////////////////////////////////////////////////////////////////////
            Statement statement = connection.createStatement();
            long days = dayValueFactory8.getValue(); //days left     /////////////////////////////////////////////////////
            long hours = hourValueFactory8.getValue(); //hours left   /////////////////////////////////////////////////////
            long minutes = minuteValueFactory8.getValue(); //minutes left  /////////////////////////////////////////////////

            LocalDateTime localDateTime = LocalDateTime.now();
            localDateTime = localDateTime.plusDays(days);
            localDateTime = localDateTime.plusHours(hours);
            localDateTime = localDateTime.plusMinutes(minutes);
            String endTime = localDateTime.toString();

            int commodityId = commodities.get(page).getCommodityId();
            int number = numberValueFactory8.getValue(); //////////////////////////////////////////////////////////

            String basePrice = basePrice01.getText();/////////////////////////////////////////////////////////////
            String sql = "INSERT INTO Auction (sellerUserName, commodityId, basePrice, number, date) VALUES ('"
                    + user.getUsername() + "', " + commodityId + ", '" + basePrice + "', " + number + ", '" + endTime + "')";

            statement.executeUpdate(sql);

            sql = "SELECT * FROM Auction WHERE rowid = (SELECT MAX(rowid) FROM Auction)";
            try {
                rs = stmt.executeQuery(sql);
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
            int auctionId = 0;
            while(rs.next()){
                auctionId = rs.getInt("auctionId");
            }
            System.out.println("AuctionId= " + auctionId);
            sql = "UPDATE AllCommodities SET isAuction = ? WHERE commodityId = ?";
            PreparedStatement pstmt = null;
            try {
                pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1,auctionId);
                pstmt.setInt(2,commodityId);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }

            try {
                Sound.auctionIsCompleted();
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }


            auction01.setVisible(false);////////////////////////////////////////////////////////////
            remove01.setVisible(true);////////////////////////////////////////////////////////////
            options01.setVisible(false);/////////////////////////////////////////////////////////////
            image01.setVisible(true);//////////////////////////////////////////////////////////////
            number01.setVisible(false);////////////////////////////////////////////////////////////
            price01.setVisible(false);///////////////////////////////////////////////////////////////////////

        }
    }
    public void setRecord11OnAction() throws SQLException {
        if (basePrice11.getText().length() == 0){  /////////////////////////////////////////////////////////////////
            basePrice11.setStyle("-fx-border-color: red;");///////////////////////////////////////////////////////
        }else {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14 + 8;//////////////////////////////////////////////////////////////////////////////////
            Statement statement = connection.createStatement();
            long days = dayValueFactory9.getValue(); //days left     /////////////////////////////////////////////////////
            long hours = hourValueFactory9.getValue(); //hours left   /////////////////////////////////////////////////////
            long minutes = minuteValueFactory9.getValue(); //minutes left  /////////////////////////////////////////////////

            LocalDateTime localDateTime = LocalDateTime.now();
            localDateTime = localDateTime.plusDays(days);
            localDateTime = localDateTime.plusHours(hours);
            localDateTime = localDateTime.plusMinutes(minutes);
            String endTime = localDateTime.toString();

            int commodityId = commodities.get(page).getCommodityId();
            int number = numberValueFactory9.getValue(); //////////////////////////////////////////////////////////

            String basePrice = basePrice11.getText();/////////////////////////////////////////////////////////////
            String sql = "INSERT INTO Auction (sellerUserName, commodityId, basePrice, number, date) VALUES ('"
                    + user.getUsername() + "', " + commodityId + ", '" + basePrice + "', " + number + ", '" + endTime + "')";

            statement.executeUpdate(sql);

            sql = "SELECT * FROM Auction WHERE rowid = (SELECT MAX(rowid) FROM Auction)";
            try {
                rs = stmt.executeQuery(sql);
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
            int auctionId = 0;
            while(rs.next()){
                auctionId = rs.getInt("auctionId");
            }
            System.out.println("AuctionId= " + auctionId);
            sql = "UPDATE AllCommodities SET isAuction = ? WHERE commodityId = ?";
            PreparedStatement pstmt = null;
            try {
                pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1,auctionId);
                pstmt.setInt(2,commodityId);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }

            try {
                Sound.auctionIsCompleted();
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }


            auction11.setVisible(false);////////////////////////////////////////////////////////////
            remove11.setVisible(true);////////////////////////////////////////////////////////////
            options11.setVisible(false);/////////////////////////////////////////////////////////////
            image11.setVisible(true);//////////////////////////////////////////////////////////////
            number11.setVisible(false);////////////////////////////////////////////////////////////
            price11.setVisible(false);///////////////////////////////////////////////////////////////////////

        }
    }
    public void setRecord21OnAction() throws SQLException {
        if (basePrice21.getText().length() == 0){  /////////////////////////////////////////////////////////////////
            basePrice21.setStyle("-fx-border-color: red;");///////////////////////////////////////////////////////
        }else {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14 + 9;//////////////////////////////////////////////////////////////////////////////////
            Statement statement = connection.createStatement();
            long days = dayValueFactory10.getValue(); //days left     /////////////////////////////////////////////////////
            long hours = hourValueFactory10.getValue(); //hours left   /////////////////////////////////////////////////////
            long minutes = minuteValueFactory10.getValue(); //minutes left  /////////////////////////////////////////////////

            LocalDateTime localDateTime = LocalDateTime.now();
            localDateTime = localDateTime.plusDays(days);
            localDateTime = localDateTime.plusHours(hours);
            localDateTime = localDateTime.plusMinutes(minutes);
            String endTime = localDateTime.toString();

            int commodityId = commodities.get(page).getCommodityId();
            int number = numberValueFactory10.getValue(); //////////////////////////////////////////////////////////

            String basePrice = basePrice21.getText();/////////////////////////////////////////////////////////////
            String sql = "INSERT INTO Auction (sellerUserName, commodityId, basePrice, number, date) VALUES ('"
                    + user.getUsername() + "', " + commodityId + ", '" + basePrice + "', " + number + ", '" + endTime + "')";

            statement.executeUpdate(sql);

            sql = "SELECT * FROM Auction WHERE rowid = (SELECT MAX(rowid) FROM Auction)";
            try {
                rs = stmt.executeQuery(sql);
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
            int auctionId = 0;
            while(rs.next()){
                auctionId = rs.getInt("auctionId");
            }
            System.out.println("AuctionId= " + auctionId);
            sql = "UPDATE AllCommodities SET isAuction = ? WHERE commodityId = ?";
            PreparedStatement pstmt = null;
            try {
                pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1,auctionId);
                pstmt.setInt(2,commodityId);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }

            try {
                Sound.auctionIsCompleted();
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }


            auction21.setVisible(false);////////////////////////////////////////////////////////////
            remove21.setVisible(true);////////////////////////////////////////////////////////////
            options21.setVisible(false);/////////////////////////////////////////////////////////////
            image21.setVisible(true);//////////////////////////////////////////////////////////////
            number21.setVisible(false);////////////////////////////////////////////////////////////
            price21.setVisible(false);///////////////////////////////////////////////////////////////////////

        }
    }
    public void setRecord31OnAction() throws SQLException {
        if (basePrice31.getText().length() == 0){  /////////////////////////////////////////////////////////////////
            basePrice31.setStyle("-fx-border-color: red;");///////////////////////////////////////////////////////
        }else {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14 + 10;//////////////////////////////////////////////////////////////////////////////////
            Statement statement = connection.createStatement();
            long days = dayValueFactory11.getValue(); //days left     /////////////////////////////////////////////////////
            long hours = hourValueFactory11.getValue(); //hours left   /////////////////////////////////////////////////////
            long minutes = minuteValueFactory11.getValue(); //minutes left  /////////////////////////////////////////////////

            LocalDateTime localDateTime = LocalDateTime.now();
            localDateTime = localDateTime.plusDays(days);
            localDateTime = localDateTime.plusHours(hours);
            localDateTime = localDateTime.plusMinutes(minutes);
            String endTime = localDateTime.toString();

            int commodityId = commodities.get(page).getCommodityId();
            int number = numberValueFactory11.getValue(); //////////////////////////////////////////////////////////

            String basePrice = basePrice31.getText();/////////////////////////////////////////////////////////////
            String sql = "INSERT INTO Auction (sellerUserName, commodityId, basePrice, number, date) VALUES ('"
                    + user.getUsername() + "', " + commodityId + ", '" + basePrice + "', " + number + ", '" + endTime + "')";

            statement.executeUpdate(sql);

            sql = "SELECT * FROM Auction WHERE rowid = (SELECT MAX(rowid) FROM Auction)";
            try {
                rs = stmt.executeQuery(sql);
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
            int auctionId = 0;
            while(rs.next()){
                auctionId = rs.getInt("auctionId");
            }
            System.out.println("AuctionId= " + auctionId);
            sql = "UPDATE AllCommodities SET isAuction = ? WHERE commodityId = ?";
            PreparedStatement pstmt = null;
            try {
                pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1,auctionId);
                pstmt.setInt(2,commodityId);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }

            try {
                Sound.auctionIsCompleted();
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }


            auction31.setVisible(false);////////////////////////////////////////////////////////////
            remove31.setVisible(true);////////////////////////////////////////////////////////////
            options31.setVisible(false);/////////////////////////////////////////////////////////////
            image31.setVisible(true);//////////////////////////////////////////////////////////////
            number31.setVisible(false);////////////////////////////////////////////////////////////
            price31.setVisible(false);///////////////////////////////////////////////////////////////////////

        }
    }
    public void setRecord41OnAction() throws SQLException {
        if (basePrice41.getText().length() == 0){  /////////////////////////////////////////////////////////////////
            basePrice41.setStyle("-fx-border-color: red;");///////////////////////////////////////////////////////
        }else {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14 + 11;//////////////////////////////////////////////////////////////////////////////////
            Statement statement = connection.createStatement();
            long days = dayValueFactory12.getValue(); //days left     /////////////////////////////////////////////////////
            long hours = hourValueFactory12.getValue(); //hours left   /////////////////////////////////////////////////////
            long minutes = minuteValueFactory12.getValue(); //minutes left  /////////////////////////////////////////////////

            LocalDateTime localDateTime = LocalDateTime.now();
            localDateTime = localDateTime.plusDays(days);
            localDateTime = localDateTime.plusHours(hours);
            localDateTime = localDateTime.plusMinutes(minutes);
            String endTime = localDateTime.toString();

            int commodityId = commodities.get(page).getCommodityId();
            int number = numberValueFactory12.getValue(); //////////////////////////////////////////////////////////

            String basePrice = basePrice41.getText();/////////////////////////////////////////////////////////////
            String sql = "INSERT INTO Auction (sellerUserName, commodityId, basePrice, number, date) VALUES ('"
                    + user.getUsername() + "', " + commodityId + ", '" + basePrice + "', " + number + ", '" + endTime + "')";

            statement.executeUpdate(sql);

            sql = "SELECT * FROM Auction WHERE rowid = (SELECT MAX(rowid) FROM Auction)";
            try {
                rs = stmt.executeQuery(sql);
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
            int auctionId = 0;
            while(rs.next()){
                auctionId = rs.getInt("auctionId");
            }
            System.out.println("AuctionId= " + auctionId);
            sql = "UPDATE AllCommodities SET isAuction = ? WHERE commodityId = ?";
            PreparedStatement pstmt = null;
            try {
                pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1,auctionId);
                pstmt.setInt(2,commodityId);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }

            try {
                Sound.auctionIsCompleted();
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }


            auction41.setVisible(false);////////////////////////////////////////////////////////////
            remove41.setVisible(true);////////////////////////////////////////////////////////////
            options41.setVisible(false);/////////////////////////////////////////////////////////////
            image41.setVisible(true);//////////////////////////////////////////////////////////////
            number41.setVisible(false);////////////////////////////////////////////////////////////
            price41.setVisible(false);///////////////////////////////////////////////////////////////////////

        }
    }
    public void setRecord51OnAction() throws SQLException {
        if (basePrice51.getText().length() == 0){  /////////////////////////////////////////////////////////////////
            basePrice51.setStyle("-fx-border-color: red;");///////////////////////////////////////////////////////
        }else {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14 + 12;//////////////////////////////////////////////////////////////////////////////////
            Statement statement = connection.createStatement();
            long days = dayValueFactory13.getValue(); //days left     /////////////////////////////////////////////////////
            long hours = hourValueFactory13.getValue(); //hours left   /////////////////////////////////////////////////////
            long minutes = minuteValueFactory13.getValue(); //minutes left  /////////////////////////////////////////////////

            LocalDateTime localDateTime = LocalDateTime.now();
            localDateTime = localDateTime.plusDays(days);
            localDateTime = localDateTime.plusHours(hours);
            localDateTime = localDateTime.plusMinutes(minutes);
            String endTime = localDateTime.toString();

            int commodityId = commodities.get(page).getCommodityId();
            int number = numberValueFactory13.getValue(); //////////////////////////////////////////////////////////

            String basePrice = basePrice51.getText();/////////////////////////////////////////////////////////////
            String sql = "INSERT INTO Auction (sellerUserName, commodityId, basePrice, number, date) VALUES ('"
                    + user.getUsername() + "', " + commodityId + ", '" + basePrice + "', " + number + ", '" + endTime + "')";

            statement.executeUpdate(sql);

            sql = "SELECT * FROM Auction WHERE rowid = (SELECT MAX(rowid) FROM Auction)";
            try {
                rs = stmt.executeQuery(sql);
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
            int auctionId = 0;
            while(rs.next()){
                auctionId = rs.getInt("auctionId");
            }
            System.out.println("AuctionId= " + auctionId);
            sql = "UPDATE AllCommodities SET isAuction = ? WHERE commodityId = ?";
            PreparedStatement pstmt = null;
            try {
                pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1,auctionId);
                pstmt.setInt(2,commodityId);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }

            try {
                Sound.auctionIsCompleted();
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }

            auction51.setVisible(false);////////////////////////////////////////////////////////////
            remove51.setVisible(true);////////////////////////////////////////////////////////////
            options51.setVisible(false);/////////////////////////////////////////////////////////////
            image51.setVisible(true);//////////////////////////////////////////////////////////////
            number51.setVisible(false);////////////////////////////////////////////////////////////
            price51.setVisible(false);///////////////////////////////////////////////////////////////////////

        }
    }
    public void setRecord61OnAction() throws SQLException {
        if (basePrice61.getText().length() == 0){  /////////////////////////////////////////////////////////////////
            basePrice61.setStyle("-fx-border-color: red;");///////////////////////////////////////////////////////
        }else {
            int page = Integer.parseInt(this.page.getText());
            page = (page*14) - 14 + 13;//////////////////////////////////////////////////////////////////////////////////
            Statement statement = connection.createStatement();
            long days = dayValueFactory14.getValue(); //days left     /////////////////////////////////////////////////////
            long hours = hourValueFactory14.getValue(); //hours left   /////////////////////////////////////////////////////
            long minutes = minuteValueFactory14.getValue(); //minutes left  /////////////////////////////////////////////////

            LocalDateTime localDateTime = LocalDateTime.now();
            localDateTime = localDateTime.plusDays(days);
            localDateTime = localDateTime.plusHours(hours);
            localDateTime = localDateTime.plusMinutes(minutes);
            String endTime = localDateTime.toString();

            int commodityId = commodities.get(page).getCommodityId();
            int number = numberValueFactory14.getValue(); //////////////////////////////////////////////////////////

            String basePrice = basePrice61.getText();/////////////////////////////////////////////////////////////
            String sql = "INSERT INTO Auction (sellerUserName, commodityId, basePrice, number, date) VALUES ('"
                    + user.getUsername() + "', " + commodityId + ", '" + basePrice + "', " + number + ", '" + endTime + "')";

            statement.executeUpdate(sql);

            sql = "SELECT * FROM Auction WHERE rowid = (SELECT MAX(rowid) FROM Auction)";
            try {
                rs = stmt.executeQuery(sql);
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
            int auctionId = 0;
            while(rs.next()){
                auctionId = rs.getInt("auctionId");
            }
            System.out.println("AuctionId= " + auctionId);
            sql = "UPDATE AllCommodities SET isAuction = ? WHERE commodityId = ?";
            PreparedStatement pstmt = null;
            try {
                pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1,auctionId);
                pstmt.setInt(2,commodityId);
                pstmt.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }

            try {
                Sound.auctionIsCompleted();
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }

            auction61.setVisible(false);////////////////////////////////////////////////////////////
            remove61.setVisible(true);////////////////////////////////////////////////////////////
            options61.setVisible(false);/////////////////////////////////////////////////////////////
            image61.setVisible(true);//////////////////////////////////////////////////////////////
            number61.setVisible(false);////////////////////////////////////////////////////////////
            price61.setVisible(false);///////////////////////////////////////////////////////////////////////

        }
    }

    public void setRemove00OnAction() throws SQLException {
        remove00.setVisible(false);
        auction00.setVisible(true);
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) -14;

        String sql = "UPDATE AllCommodities SET isAuction = ? WHERE isAuction = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,0);
            pstmt.setInt(2,commodities.get(page).isAuction);
            pstmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        sql = "DELETE FROM Auction WHERE commodityId = "  + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);

        auction00.setVisible(true);
        number00.setVisible(true);
        price00.setVisible(true);

    }
    public void setRemove10OnAction() throws SQLException {
        remove10.setVisible(false);
        auction10.setVisible(true);
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) -14 +1 ;

        String sql = "UPDATE AllCommodities SET isAuction = ? WHERE isAuction = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,0);
            pstmt.setInt(2,commodities.get(page).isAuction);
            pstmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        sql = "DELETE FROM Auction WHERE commodityId = "  + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);

        auction10.setVisible(true);
        number10.setVisible(true);
        price10.setVisible(true);
    }
    public void setRemove20OnAction() throws SQLException {
        remove20.setVisible(false);
        auction20.setVisible(true);
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) -14 +2 ;

        String sql = "UPDATE AllCommodities SET isAuction = ? WHERE isAuction = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,0);
            pstmt.setInt(2,commodities.get(page).isAuction);
            pstmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        sql = "DELETE FROM Auction WHERE commodityId = "  + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);

        auction20.setVisible(true);
        number20.setVisible(true);
        price20.setVisible(true);
    }
    public void setRemove30OnAction() throws SQLException {
        remove30.setVisible(false);
        auction30.setVisible(true);
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) -14 + 3;

        String sql = "UPDATE AllCommodities SET isAuction = ? WHERE isAuction = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,0);
            pstmt.setInt(2,commodities.get(page).isAuction);
            pstmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        sql = "DELETE FROM Auction WHERE commodityId = "  + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);

        auction30.setVisible(true);
        number30.setVisible(true);
        price30.setVisible(true);
    }
    public void setRemove40OnAction() throws SQLException {
        remove40.setVisible(false);
        auction40.setVisible(true);
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) -14 + 4;

        String sql = "UPDATE AllCommodities SET isAuction = ? WHERE isAuction = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,0);
            pstmt.setInt(2,commodities.get(page).isAuction);
            pstmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        sql = "DELETE FROM Auction WHERE commodityId = "  + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);

        auction40.setVisible(true);
        number40.setVisible(true);
        price40.setVisible(true);
    }
    public void setRemove50OnAction() throws SQLException {
        remove50.setVisible(false);
        auction50.setVisible(true);
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) -14+5;

        String sql = "UPDATE AllCommodities SET isAuction = ? WHERE isAuction = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,0);
            pstmt.setInt(2,commodities.get(page).isAuction);
            pstmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        sql = "DELETE FROM Auction WHERE commodityId = "  + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);

        auction50.setVisible(true);
        number50.setVisible(true);
        price50.setVisible(true);
    }
    public void setRemove60OnAction() throws SQLException {
        remove60.setVisible(false);
        auction60.setVisible(true);
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) -14+6;

        String sql = "UPDATE AllCommodities SET isAuction = ? WHERE isAuction = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,0);
            pstmt.setInt(2,commodities.get(page).isAuction);
            pstmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        sql = "DELETE FROM Auction WHERE commodityId = "  + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);

        auction60.setVisible(true);
        number60.setVisible(true);
        price60.setVisible(true);
    }
    public void setRemove01OnAction() throws SQLException {
        remove01.setVisible(false);
        auction01.setVisible(true);
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) -14+7;

        String sql = "UPDATE AllCommodities SET isAuction = ? WHERE isAuction = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,0);
            pstmt.setInt(2,commodities.get(page).isAuction);
            pstmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        sql = "DELETE FROM Auction WHERE commodityId = "  + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);

        auction01.setVisible(true);
        number01.setVisible(true);
        price01.setVisible(true);
    }
    public void setRemove11OnAction() throws SQLException {
        remove11.setVisible(false);
        auction11.setVisible(true);
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) -14+7;

        String sql = "UPDATE AllCommodities SET isAuction = ? WHERE isAuction = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,0);
            pstmt.setInt(2,commodities.get(page).isAuction);
            pstmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        sql = "DELETE FROM Auction WHERE commodityId = "  + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);

        auction11.setVisible(true);
        number11.setVisible(true);
        price11.setVisible(true);
    }
    public void setRemove21OnAction() throws SQLException {
        remove21.setVisible(false);
        auction21.setVisible(true);
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) -14+7;

        String sql = "UPDATE AllCommodities SET isAuction = ? WHERE isAuction = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,0);
            pstmt.setInt(2,commodities.get(page).isAuction);
            pstmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        sql = "DELETE FROM Auction WHERE commodityId = "  + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);

        auction21.setVisible(true);
        number21.setVisible(true);
        price21.setVisible(true);
    }
    public void setRemove31OnAction() throws SQLException {
        remove31.setVisible(false);
        auction31.setVisible(true);
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) -14+7;

        String sql = "UPDATE AllCommodities SET isAuction = ? WHERE isAuction = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,0);
            pstmt.setInt(2,commodities.get(page).isAuction);
            pstmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        sql = "DELETE FROM Auction WHERE commodityId = "  + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);

        auction31.setVisible(true);
        number31.setVisible(true);
        price31.setVisible(true);
    }
    public void setRemove41OnAction() throws SQLException {
        remove41.setVisible(false);
        auction41.setVisible(true);
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) -14+7;

        String sql = "UPDATE AllCommodities SET isAuction = ? WHERE isAuction = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,0);
            pstmt.setInt(2,commodities.get(page).isAuction);
            pstmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        sql = "DELETE FROM Auction WHERE commodityId = "  + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);

        auction41.setVisible(true);
        number41.setVisible(true);
        price41.setVisible(true);
    }
    public void setRemove51OnAction() throws SQLException {
        remove51.setVisible(false);
        auction51.setVisible(true);
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) -14+7;

        String sql = "UPDATE AllCommodities SET isAuction = ? WHERE isAuction = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,0);
            pstmt.setInt(2,commodities.get(page).isAuction);
            pstmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        sql = "DELETE FROM Auction WHERE commodityId = "  + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);

        auction51.setVisible(true);
        number51.setVisible(true);
        price51.setVisible(true);
    }
    public void setRemove61OnAction() throws SQLException {
        remove61.setVisible(false);
        auction61.setVisible(true);
        int page = Integer.parseInt(this.page.getText());
        page = (page*14) -14+7;

        String sql = "UPDATE AllCommodities SET isAuction = ? WHERE isAuction = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,0);
            pstmt.setInt(2,commodities.get(page).isAuction);
            pstmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        sql = "DELETE FROM Auction WHERE commodityId = "  + commodities.get(page).getCommodityId();
        stmt.executeUpdate(sql);

        auction61.setVisible(true);
        number61.setVisible(true);
        price61.setVisible(true);
    }

}

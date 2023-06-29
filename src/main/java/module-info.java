module com.example.shopproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
//    requires java.mail;
    requires org.simplejavamail;
    requires org.simplejavamail.core;
    requires jakarta.mail;


    opens com.example.shopapplication to javafx.fxml;
    exports com.example.shopapplication;
    exports com.example.shopapplication.GroceryCommodities;
    opens com.example.shopapplication.GroceryCommodities to javafx.fxml;
//    exports com.example.shopapplication;
//    opens com.example.shopapplication to javafx.fxml;
}
module com.example.shopapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires sqlite.jdbc;

    opens com.example.shopapplication to javafx.fxml;
    exports com.example.shopapplication;
    exports com.example.shopapplication.exceptions;
    opens com.example.shopapplication.exceptions to javafx.fxml;
    exports com.example.shopapplication.regex;
    opens com.example.shopapplication.regex to javafx.fxml;
}
package com.example.shopapplication;

import com.example.shopapplication.exceptions.UsernameAlreadyExistsException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login implements Verifiable {
    private final User user;

    public Login(User user) {
        if (user == null) {
            try {
                throw new NullPointerException();
            } catch (NullPointerException e) {
                System.err.println(e);
                e.printStackTrace();
            }
        }
        this.user = user;
    }

    @Override
    public boolean verify() throws UsernameAlreadyExistsException { // todo how to make it private?
        Connection connection;
        String username = user.getUsername();
        String password = user.getPassword();
        String table = null;

        if (user instanceof Customer)
            table = "Customers";
        else if (user instanceof Seller)
            table = "Sellers";
        else if (user instanceof Admin)
            table = "Admins";

        try {
            connection = new com.example.shopapplication.DatabaseConnectionJDBC().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            String sql = "SELECT * FROM " + table + " WHERE username='" + username + "' AND password='" + password + "'";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                if (resultSet.getString("username").equals(username)) {
                    AppWorkflow.username = username;
//                    connection.close();
                    return true;
                } else {
//                    connection.close();
                    return false;
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e);
            e.printStackTrace();
        }

        return false;
    }
    public boolean validateCustomerLogin(String username, String password) {
        try {
            return verify();
        } catch (UsernameAlreadyExistsException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validateSellerLogin(String username, String password) {
        try {
            return verify();
        } catch (UsernameAlreadyExistsException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validateAdminLogin(String username, String password) {
        try {
            return verify();
        } catch (UsernameAlreadyExistsException e) {
            throw new RuntimeException(e);
        }
    }

    public void loginToHome(Node node) throws IOException { // todo
        FXMLLoader loader = new FXMLLoader(Login.class.getResource("Home.fxml"));
        Parent root = loader.load();
        HomeController homeController = loader.getController();
        homeController.setUser(user);
        Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));

    }

    public static User getCompleteUser(User usernameUser) { // temp method
        String table;
        if (usernameUser instanceof Customer) {
            table = "Customers";
        } else if (usernameUser instanceof Seller) {
            table = "Sellers";
        } else if (usernameUser instanceof Admin) {
            table = "Admins";
        } else
            return null;

        try (Connection connection = new DatabaseConnectionJDBC().getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            String sql = "SELECT * FROM " + table + " WHERE username='" + usernameUser.getUsername() + "' AND password='" + usernameUser.getPassword() + "'";
            resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                // create obj
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String email = resultSet.getString("email");

                switch (table) {
                    case "Customers":
                        return new Customer(username, password, firstname, lastname, email);
                    case "Sellers":
                        String company = resultSet.getString("company");
                        return new Seller(username, password, firstname, lastname, email, company);
                    case "Admins":
                        return new Admin(username, password, firstname, lastname, email);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}

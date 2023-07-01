package com.example.shopapplication;

import com.example.shopapplication.exceptions.*;
import org.w3c.dom.ls.LSOutput;

import javax.mail.internet.AddressException;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import javax.mail.internet.AddressException;


public class SignUp implements Validatable, Verifiable {
    private final User user;
    private boolean step1 = false;
    private boolean step2 = false;
    private boolean step3 = false;
    private boolean step4 = false;

    public SignUp(User user) {
        if (user == null) {
            try {
                throw new NullPointerException();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.user = user;
    }

    @Override
    public boolean validate() throws IllegalUsernameException, IllegalPasswordException,
            IllegalFirstnameException, IllegalLastnameException, AddressException {
        Validator validator = new Validator();
        boolean validation = validator.validateUserSignUp(user);
        if (validation) {
            step1 = true;
        }
        return validator.validateUserSignUp(user);
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean verify() throws UsernameAlreadyExistsException {
        /*checks if username already exists*/
        boolean verification = false; // don't let user sign up by default
        System.out.println("SignUp.verify");
        System.out.println("step1 = " + step1);
        if (step1) {
            Connection connection;
            String username = user.getUsername();
            String table = null;

            if (user instanceof Customer) {
                table = "Customers";
            } else if (user instanceof Seller) {
                table = "Sellers";
            }

            try {
                connection = new DatabaseConnectionJDBC().getConnection();
                Statement statement = connection.createStatement();
                String sql = "SELECT * FROM " + table + " WHERE username='" + username + "'";
                ResultSet resultSet = statement.executeQuery(sql);

                if (resultSet.next()) {
                    // if username is unique set true
                    String str = resultSet.getString("username");
                    if (str.equals(username)) {
                        System.out.println("oh yes");
                        throw new UsernameAlreadyExistsException();
                    }
                } else {
                    verification = true;
                }
                connection.close();
            } catch (SQLException | ClassNotFoundException e) {
                System.err.println(e);
                e.printStackTrace();
            }
        }

        if (verification) {
            step2 = true;
        }
        System.out.println("verification = " + verification);
        return verification;
    }

    private boolean requestForAdminPermission() {
        boolean permission = false;
        // todo socket server
        return permission;
    }

    public boolean signUp() throws SignUpException, SQLException, ClassNotFoundException { // add to appropriate table

        if (!step1 || !step2)
            throw new SignUpException();

        try (Connection connection = new DatabaseConnectionJDBC().getConnection();
             Statement statement = connection.createStatement()) {

            if (user instanceof Customer) { // add customer directly to customers
                String sql = "INSERT INTO Customers (username, password, firstname, lastname, email)" +
                        " VALUES ('" + user.getUsername() + "', '" + user.getPassword() + "', '" +
                        user.getFirstname() + "', '" + user.getLastname() + "', '" + user.getEmail() + "')";
//            statement.execute(sql);
                System.out.println("statement.executeUpdate(sql) = " + statement.executeUpdate(sql));
                connection.close();
                return true;

            } else if (user instanceof Seller) { // add seller to waiting list
                Seller seller = (Seller) user;
                // todo admin section
                String sql = "INSERT INTO waitingSellers (username,password,firstname,lastname,email,company)" +
                        " VALUES ('" + seller.getUsername() + "', '" + seller.getPassword() + "', '" +
                        seller.getFirstname() + "', '" + seller.getLastname() + "', '" + seller.getEmail() + "', '" +
                        seller.getCompany() + "')";
//            statement.execute(sql);
                System.out.println("statement.executeUpdate(sql) = " + statement.executeUpdate(sql));
                connection.close();
                return true;
            }

        } catch (SQLException e) {
            System.err.println(e);
            e.printStackTrace();
        }

        return false;
    }
//    public boolean validateCustomerSignUp(String username, String password) throws UsernameAlreadyExistsException,
//            IllegalPasswordException, IllegalUsernameException { // check if username exists already
//        Customer customer = new Customer(username, password);
//        return validate(customer) && verify("Customers", customer);
//    }
//
//    public boolean validateSellerSignUp(String username, String password) throws IllegalUsernameException,
//            IllegalPasswordException, NotReceivedAdminPermissionException, UsernameAlreadyExistsException {
//        Customer customer = new Customer(username, password);
//        return  validate(customer) && verify("Sellers", customer) && requestForAdminPermission();

//    }

//    private boolean validatePassword(String password) throws IllegalPasswordException {
//        Pattern pattern = Pattern.compile(MyRegEx.passwordRegex);
//        Matcher matcher = pattern.matcher(password);
//        if (!matcher.find())
//            throw new IllegalPasswordException();
//        else
//            return true;
//
//    }
//
//    private boolean validateUsername(String username) throws IllegalUsernameException {
//        Pattern pattern = Pattern.compile(MyRegEx.usernameRegex);
//        Matcher matcher = pattern.matcher(username);
//        if (!matcher.find())
//            throw new IllegalUsernameException();
//        else
//            return true;
//    }
//
//    private boolean validateEmail(String email) throws AddressException {
//        InternetAddress internetAddress = new InternetAddress(email);
//        internetAddress.validate();
//        return true;
//    }
//
//    private boolean validateName(String name) throws IllegalNameException {
//        Pattern pattern = Pattern.compile(MyRegEx.usernameRegex);
//        Matcher matcher = pattern.matcher(name);
//        if (!matcher.find())
//            throw new IllegalNameException();
//        else
//            return true;
//    }
}
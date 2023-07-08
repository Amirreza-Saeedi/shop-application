package com.example.shopapplication;

public class Person {
    private User user;
    private String userType;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private double charge;
    private String phone;
    private String company;

    public User getUser() {
        return user;
    }

    public String getUserType() {
        return userType;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public double getCharge() {
        return charge;
    }

    public String getPhone() {
        return phone;
    }

    public String getCompany() {
        return company;
    }


    public Person(User user){
        this.user = user;
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.charge = user.getCharge();
        if (user instanceof Seller){
            userType = "seller";
            this.company = ((Seller) user).getCompany();
        } else if (user instanceof Customer) {
            userType = "customer";
        } else if (user instanceof Admin) {
            userType =  "admin";
        }
    }
}

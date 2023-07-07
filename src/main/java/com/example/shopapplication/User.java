package com.example.shopapplication;

public abstract class User {
    private String seller = "seller";
    private String customer = "customer";
    private String admin = "admin";
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private double charge;

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    private String phone;
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User(String username, String password, String firstname, String lastname, String email, String phone) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
    }
    public String toString(){
        if (this instanceof Customer){
            return "customer";
        }
        if (this instanceof Seller){
            return "seller";
        }
        if (this instanceof Admin){
            return "admin";
        }
        return null;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String username) {
        this(username, null);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return firstname + " " + lastname;
    }
    public String getName(){
        return firstname;
    }
}

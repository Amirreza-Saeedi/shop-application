package com.example.shopapplication;

public final class MyRegEx {
    public final static String username = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
    public final static String password = "";
}

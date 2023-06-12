package com.example.shopapplication.regex;

public final class MyRegEx {
    public final static String usernameRegex    = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
    public final static String passwordRegex    = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,16}$";
    public final static String nameRegex        = "^(?=.*[A-Za-z])[A-Za-z]{1,20}$";
//    public final static String emailRegex       = null;
}

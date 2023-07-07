package com.example.shopapplication.regex;

public final class MyRegex {
    public final static String usernameRegex    = "^(?=.{4,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
    public final static String passwordRegex    = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,16}$";
    public final static String nameRegex        = "^(?=.*[A-Za-z])[A-Za-z]{1,20}$";
    public final static String emailRegex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
    public final static String numberRegex = "\\d*";
    public final static String doubleRegex = "^(-?)(0|([1-9][0-9]*))(\\.[0-9]+)?$";
    public final static String commentRegex = "^[^\\n]{0,200}$";
    public final static String percentRegex = "^([1-9]|[1-9][0-9]|100)$";
//    public final static String emailRegex       = null;
}

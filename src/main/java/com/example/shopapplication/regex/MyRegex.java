package com.example.shopapplication.regex;

public final class MyRegex {
    public final static String usernameRegex    = "^(?=.{4,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
    public final static String passwordRegex    = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,16}$";
    public final static String nameRegex        = "^(?=.*[A-Za-z])[A-Za-z]{1,20}$"; // todo edit
    public final static String managerRegex        = "^(?=.*[A-Za-z])[A-Za-z]{0,20}$";
    public final static String storageNameRegex        = "^(?=.*[A-Za-z0-9])[A-Za-z0-9]{0,20}$";
    public final static String emailRegex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
    public final static String numberRegex = "\\d*";
    public final static String phoneRegex = "\\d*{0,20}";
    public final static String doubleRegex = "^(-?)(0|([1-9][0-9]*))(\\.[0-9]+)?$";
    public final static String commentRegex = "^[^\\n]{0,200}$";
//    public final static String emailRegex       = null;
}

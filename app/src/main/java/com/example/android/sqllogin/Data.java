package com.example.android.sqllogin;

public class Data {

    private String email;
    private String password;

    public Data(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

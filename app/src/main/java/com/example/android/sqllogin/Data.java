package com.example.android.sqllogin;

public class Data {

    private String email;
    private String password;
    private String plant_no;


    public Data(String email, String password, String plant_no) {
        this.email = email;
        this.password = password;
        this.plant_no = plant_no;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPlant_no() {
        return plant_no;
    }
}

package com.example.android.sqllogin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "http://10.0.2.2/webapp/";

    @GET("data.json.php")
    Call<List<Data>> getData();
}

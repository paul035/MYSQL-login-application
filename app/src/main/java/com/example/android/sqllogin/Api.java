package com.example.android.sqllogin;

import org.w3c.dom.Text;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    String BASE_URL = "http://10.0.2.2/webapp/";

    @GET("data.json.php")
    Call<List<Data>> getData();

    @GET("data.plant_no.json.php")
    Call<List<PlantDetail>> getPlantDetail();

}

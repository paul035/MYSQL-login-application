package com.example.android.sqllogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SuccessActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        listView = (ListView) findViewById(R.id.list_item);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Api api = retrofit.create(Api.class);

        Call <List <Data>> call = api.getData();

        call.enqueue(new Callback <List <Data>>() {
            @Override
            public void onResponse(Call <List <Data>> call, Response <List <Data>> response) {
                List <Data> datas = response.body();

                String [] dataValue = new String[datas.size()];

                for(int i=0; i<datas.size(); i++){
                    dataValue[i] = datas.get(i).getEmail();
                }
                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext()
                , android.R.layout.simple_list_item_1, dataValue));

            }

            @Override
            public void onFailure(Call <List <Data>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}

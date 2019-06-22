package com.example.android.sqllogin;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.IntegerRes;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.highsoft.highcharts.common.hichartsclasses.HIChart;
import com.highsoft.highcharts.common.hichartsclasses.HIColumn;
import com.highsoft.highcharts.common.hichartsclasses.HICondition;
import com.highsoft.highcharts.common.hichartsclasses.HILabel;
import com.highsoft.highcharts.common.hichartsclasses.HILegend;
import com.highsoft.highcharts.common.hichartsclasses.HILine;
import com.highsoft.highcharts.common.hichartsclasses.HIOptions;
import com.highsoft.highcharts.common.hichartsclasses.HIPlotOptions;
import com.highsoft.highcharts.common.hichartsclasses.HIResponsive;
import com.highsoft.highcharts.common.hichartsclasses.HIRules;
import com.highsoft.highcharts.common.hichartsclasses.HISeries;
import com.highsoft.highcharts.common.hichartsclasses.HITitle;
import com.highsoft.highcharts.common.hichartsclasses.HIXAxis;
import com.highsoft.highcharts.common.hichartsclasses.HIYAxis;
import com.highsoft.highcharts.core.HIChartView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.nio.file.attribute.GroupPrincipal;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SuccessActivity extends AppCompatActivity {

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    public String plant_no;

    private TextView data11;
    private TextView data22;
    private TextView data33;
    private TextView data44;
    private TextView data55;

    private String data1 = "0";
    private String data2 = "0";
    private String data3 = "0";
    private String data4 = "0";
    private String data5 = "0";

    private HIChartView chartView;
    private HIOptions options;
    private HITitle title;
    private HIYAxis yaxis;
    private HILegend legend;
    private HIPlotOptions plotoptions;
    private HILine line1;
    private HIResponsive responsive;
    private HIRules rules1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);



        Intent intent = getIntent();
        plant_no = intent.getStringExtra("plant_no");
        // Log.d("plantno", String.valueOf(plant_no));

/*
        data11 = (TextView) findViewById(R.id.data1);
        data22 = (TextView) findViewById(R.id.data2);
        data33 = (TextView) findViewById(R.id.data3);
        data44 = (TextView) findViewById(R.id.data4);
        data55 = (TextView) findViewById(R.id.data5);
*/

        chartView = findViewById(R.id.hc);

        options = new HIOptions();

        title = new HITitle();

        yaxis = new HIYAxis();

        legend = new HILegend();

        plotoptions = new HIPlotOptions();

        line1 = new HILine();

        responsive = new HIResponsive();

        rules1 = new HIRules();

        /*
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<PlantDetail>> call = api.getPlantDetail();

        call.enqueue(new Callback<List <PlantDetail>>() {
            @Override
            public void onResponse(Call <List <PlantDetail>> call, Response<List <PlantDetail>> response) {
                final List <PlantDetail> datas = response.body();

                String[] dataValue = new String[datas.size()];

                for (int i = 0; i < datas.size(); i++) {
                    dataValue[i] = datas.get(i).getData1();
                }
                data1.setText(dataValue[0]);
            }

            @Override
            public void onFailure(Call <List <PlantDetail>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });*/
        checkDetail();
        //DrawGraph(data1, data2, data3, data4, data5);
    }

    public void checkDetail() {
        new AsyncRetrieve().execute(plant_no);
    }

    public void DrawGraph(String a, String b, String c, String d, String e) {

        title.setText("Plant Data");
        options.setTitle(title);


        yaxis.setTitle(new HITitle());
        yaxis.getTitle().setText("Plant Detail");
        options.setYAxis(new ArrayList <>(Collections.singletonList(yaxis)));

        options.setYAxis(new ArrayList <HIYAxis>() {{
            add(yaxis);
        }});


        legend.setLayout("vertical");
        legend.setAlign("right");
        legend.setVerticalAlign("middle");
        options.setLegend(legend);


        plotoptions.setSeries(new HISeries());
        plotoptions.getSeries().setLabel(new HILabel());
        plotoptions.getSeries().getLabel().setConnectorAllowed(false);
        plotoptions.getSeries().setPointStart(1);
        options.setPlotOptions(plotoptions);


        line1.setName("Data");
        line1.setData(new ArrayList <>(Arrays.asList(Integer.parseInt(a), Integer.parseInt(b), Integer.parseInt(c), Integer.parseInt(d), Integer.parseInt(e))));


        rules1.setCondition(new HICondition());
        rules1.getCondition().setMaxWidth(1000);
        HashMap <String, HashMap> chartLegend = new HashMap <>();
        HashMap <String, String> legendOptions = new HashMap <>();
        legendOptions.put("layout", "horizontal");
        legendOptions.put("align", "center");
        legendOptions.put("verticalAlign", "bottom");
        chartLegend.put("legend", legendOptions);
        rules1.setChartOptions(chartLegend);
        responsive.setRules(new ArrayList <>(Collections.singletonList(rules1)));
        options.setResponsive(responsive);

        options.setSeries(new ArrayList <>(Arrays.asList(line1)));

        chartView.setOptions(options);
    }

    private class AsyncRetrieve extends AsyncTask <String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(SuccessActivity.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

                //this method will be running on UI thread
                pdLoading.setMessage("\tPlant details are loading...");
                pdLoading.setCancelable(false);
                pdLoading.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                url = new URL("http://10.0.2.2/webapp/data.plant_no.json.php");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "exception";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("plant_no", plant_no);
                String query = builder.build().getEncodedQuery();

                // Log.d("Plant Detail", query);
                // query contains the plantNumber

                // Open connection for sending data
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

                Log.d("OS", String.valueOf(os));

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception";
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    //  Log.d("Data", String.valueOf(result));
                    // result contain the plant details each and every datas

                    // Pass data to onPostExecute method

                    return (result.toString());


                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            //this method will be running on UI thread
             pdLoading.dismiss();

            if (result != null) {
                    /*String data1="";
                    String data2="";
                    String data3="";
                    String data4="";
                    String data5="";*/

                try {
                    JSONArray jArray = new JSONArray(result);
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);
                        PlantDetail plantDetail = new PlantDetail();
                        data1 = json_data.getString("data1");
                        data2 = json_data.getString("data2");
                        data3 = json_data.getString("data3");
                        data4 = json_data.getString("data4");
                        data5 = json_data.getString("data5");


                       /*data11.setText(data1);
                        data22.setText(data2);
                        data33.setText(data3);
                        data44.setText(data4);
                        data55.setText(data5);*/

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //Intent intent = new Intent(SuccessActivity.this, PlantActivity.class);
                //startActivity(intent);
                // Log.d("Data: ", result);

               // DrawGraph(data1, data2, data3, data4, data5);

                Intent intent = new Intent(SuccessActivity.this, GraphActivity.class);
                intent.putExtra("data1", data1);
                intent.putExtra("data2", data2);
                intent.putExtra("data3", data3);
                intent.putExtra("data4", data4);
                intent.putExtra("data5", data5);
                SuccessActivity.this.finish();
                startActivity(intent);

            }

        }

    }
}

package com.example.android.sqllogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.highsoft.highcharts.common.hichartsclasses.HIChart;
import com.highsoft.highcharts.common.hichartsclasses.HIColumn;
import com.highsoft.highcharts.common.hichartsclasses.HICondition;
import com.highsoft.highcharts.common.hichartsclasses.HICrosshair;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class GraphActivity extends AppCompatActivity {

    private HIChartView chartView;
    private HIOptions options;
    private HITitle title;
    private HIYAxis yaxis;
    private HILegend legend;
    private HIPlotOptions plotoptions;
    private HILine line1;
    private HIResponsive responsive;
    private HIRules rules1;

    private TextView todayGen;
    private TextView totalGen;
    private TextView acPower;
    private TextView dcPower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Intent intent = getIntent();
        String newData1 = intent.getStringExtra("data1");
        String newData2 = intent.getStringExtra("data2");
        String newData3 = intent.getStringExtra("data3");
        String newData4 = intent.getStringExtra("data4");
        String newData5 = intent.getStringExtra("data5");

        todayGen = (TextView) findViewById(R.id.textView3);
        todayGen.setText(newData1 + " kW");

        totalGen = (TextView) findViewById(R.id.textView5);
        totalGen.setText(newData2 + " kW");

        acPower = (TextView) findViewById(R.id.textView7);
        acPower.setText(newData3 + " kW");

        dcPower = (TextView) findViewById(R.id.textView9);
        dcPower.setText(newData4 + " kW");


        chartView = findViewById(R.id.hc);

        options = new HIOptions();

        title = new HITitle();

        yaxis = new HIYAxis();

        legend = new HILegend();

        plotoptions = new HIPlotOptions();

        line1 = new HILine();

        responsive = new HIResponsive();

        rules1 = new HIRules();

        DrawNewGraph(newData1, newData2, newData3, newData4, newData5);

        DrawBarGraph(newData1, newData2, newData3, newData4, newData5);


    }

    private void DrawNewGraph(String a, String b, String c, String d, String e) {
        title.setText("Plant: Line Chart Detail");
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


    private void DrawBarGraph(String a, String b, String c, String d, String e) {

        HIChartView chartView = findViewById(R.id.hc1);

        HIOptions options = new HIOptions();

        HIChart chart = new HIChart();
        chart.setType("column");
        options.setChart(chart);

        HITitle title = new HITitle();
        title.setText("Plant: Bar Chart Detail");
        options.setTitle(title);


        HIXAxis xAxis = new HIXAxis();
        String[] categoriesList = new String[]{"Data1", "Data2", "Data3", "Data4", "Data5"};
        xAxis.setCategories(new ArrayList <>(Arrays.asList(categoriesList)));
        xAxis.setCrosshair(new HICrosshair());
        options.setXAxis(new ArrayList <HIXAxis>() {{
            add(xAxis);
        }});

        HIYAxis yAxis = new HIYAxis();
        yAxis.setMin(0);
        yAxis.setTitle(new HITitle());
        yAxis.getTitle().setText("Plant Detail");
        options.setYAxis(new ArrayList <HIYAxis>() {{
            add(yAxis);
        }});

        HIPlotOptions plotOptions = new HIPlotOptions();
        plotOptions.setColumn(new HIColumn());
        plotOptions.getColumn().setPointPadding(0.2);
        plotOptions.getColumn().setBorderWidth(0);
        options.setPlotOptions(plotOptions);

        HIColumn series1 = new HIColumn();
        series1.setName("Values");
        Number[] series1_data = new Number[]{Integer.parseInt(a), Integer.parseInt(b), Integer.parseInt(c), Integer.parseInt(d), Integer.parseInt(e)};
        series1.setData(new ArrayList <>(Arrays.asList(series1_data)));

        options.setSeries(new ArrayList <>(Arrays.asList(series1)));
        chartView.setOptions(options);

    }

}

package com.example.myapplication;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    TimeAsXAxisLabelFormatter f0, f1, f2, f3, f4, f5;

    String [] TimerArray = new String[5];


    private GraphView graphTemperature, graphLightLevel;

    private LineGraphSeries<DataPoint> seriesTemp;
    private LineGraphSeries<DataPoint> seriesTemp2;
    private int graph2LastXValue = 5;
    private int graph1LastXValue = 5;


    Calendar calendar = Calendar.getInstance();
    Date d1;
    Date d2;
    Date d3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        d1 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        d2 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        d3 = calendar.getTime();

        graphTemperature = findViewById(R.id.graphTemperature);
        graphLightLevel = findViewById(R.id.graphLightLevel);


        graphTemperature.getViewport().setMinY(20);
        graphTemperature.getViewport().setMaxY(40);
        graphTemperature.getViewport().setYAxisBoundsManual(true);


        graphLightLevel.getViewport().setMinY(0);
        graphLightLevel.getViewport().setMaxY(30);
        graphLightLevel.getViewport().setYAxisBoundsManual(true);

        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graphTemperature);

        staticLabelsFormatter.setHorizontalLabels(TimerArray);
        graphTemperature.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        graphTemperature.getGridLabelRenderer().setNumHorizontalLabels(5); // only 4 because of the space
//        graphTemperature.getGridLabelRenderer().setHorizontalLabelsVisible(false);

        getDataFromThingSpeak();

        //   setupBlinkyTimer();
//        getHistoricalData("2020-03-01");
        startMQTT();
//        setupBlinkyTimer();


        setupBlinkyTimer();
    }

    MQTTHelper mqttHelper;

    private void startMQTT() {
        mqttHelper = new MQTTHelper(getApplicationContext());
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {

            }

            @Override
            public void connectionLost(Throwable throwable) {
            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                Log.d("Mqtt-check", topic);
                Log.e("Mqtt-check", mqttMessage.toString());
                Log.d("MQTT", mqttMessage.toString());
                System.out.println("?????");
//                if (topic.equals("sensor/Temperature")) {
//                    Log.d("TOPIC", topic);
//                    double temp = Double.valueOf(mqttMessage.toString());
//
//
//                    seriesTemp.appendData(new DataPoint(graph1LastXValue, temp), true, 5);
//                    showDataOnGraph(seriesTemp, graphTemperature);
//                    graph1LastXValue++;
//
//
//                } else if (topic.equals("sensor/LightLevel")) {
//                    Log.d("TOPIC", topic);
//                    double temp = Double.valueOf(mqttMessage.toString());
//
//
//                    seriesTemp2.appendData(new DataPoint(graph2LastXValue, temp), true, 5);
//                    showDataOnGraph(seriesTemp2, graphLightLevel);
//                    graph2LastXValue++;
//
//
//                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }

        });
    }

    private void showDataOnGraph(LineGraphSeries<DataPoint> series, GraphView graph) {
        if (graph.getSeries().size() > 0) {
            graph.getSeries().remove(0);
        }
        graph.addSeries(series);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
    }

    private void getDataFromThingSpeak() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();

        // need to hide API
        String apiURL = "https://api.thingspeak.com/channels/1002590/feeds.json?results=5";

        Request request = builder.url(apiURL).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {


            }

            @Override
            public void onResponse(Response response) throws IOException {

                String jsonString = response.body().string();

                try {
                    JSONObject jsonData = new JSONObject(jsonString);
                    JSONArray jsonArray = jsonData.getJSONArray("feeds");
                    double temp0 = jsonArray.getJSONObject(0).getDouble("field1");
                    double temp1 = jsonArray.getJSONObject(1).getDouble("field1");
                    double temp2 = jsonArray.getJSONObject(2).getDouble("field1");
                    double temp3 = jsonArray.getJSONObject(3).getDouble("field1");
                    double temp4 = jsonArray.getJSONObject(4).getDouble("field1");

                    // get time for 5 points
                    String time0 = jsonArray.getJSONObject(0).getString("created_at");
                    String time1 = jsonArray.getJSONObject(1).getString("created_at");
                    String time2 = jsonArray.getJSONObject(2).getString("created_at");
                    String time3 = jsonArray.getJSONObject(3).getString("created_at");
                    String time4 = jsonArray.getJSONObject(4).getString("created_at");

                    time0 = getTimeFormat(time0);

                    time1 = getTimeFormat(time1);

                    time2 = getTimeFormat(time2);

                    time3 = getTimeFormat(time3);

                    time4 = getTimeFormat(time4);


                    TimerArray[0] = time0;
                    TimerArray[1] = time1;
                    TimerArray[2] = time2;
                    TimerArray[3] = time3;
                    TimerArray[4] = time4;


                    // Ok -> need to change format day time
                    JSONObject getChannelInfor = jsonData.getJSONObject("channel");
                    Log.d("Created: ", getChannelInfor.getString("created_at"));
                    Log.d("Updated: ", getChannelInfor.getString("updated_at"));


                    double tem0 = jsonArray.getJSONObject(0).getDouble("field2");
                    double tem1 = jsonArray.getJSONObject(1).getDouble("field2");
                    double tem2 = jsonArray.getJSONObject(2).getDouble("field2");
                    double tem3 = jsonArray.getJSONObject(3).getDouble("field2");
                    double tem4 = jsonArray.getJSONObject(4).getDouble("field2");



                    seriesTemp = new LineGraphSeries<>(new DataPoint[]
                            {
                                    new DataPoint(0, temp0),
                                    new DataPoint(1, temp1),
                                    new DataPoint(2, temp2),

                                    new DataPoint(3, temp3),
                                    new DataPoint(4, temp4)
                            });

                    seriesTemp2 = new LineGraphSeries<>(new DataPoint[]
                            {
                                    new DataPoint(0, tem0),
                                    new DataPoint(1, tem1),
                                    new DataPoint(2, tem2),

                                    new DataPoint(3, tem3),
                                    new DataPoint(4, tem4)
                            });
                    showDataOnGraph(seriesTemp, graphTemperature);
                    showDataOnGraph(seriesTemp2, graphLightLevel);

                } catch (Exception e) {
                }


            }
        });
    }

    private void setupBlinkyTimer() {
        Timer mTimer = new Timer();
        TimerTask mTask = new TimerTask() {
            @Override
            public void run() {
                getDataFromThingSpeak();
            }
        };
        mTimer.schedule(mTask, 2000, 5000);
    }

    private String getTimeFormat(String rawTime) {
        // simple version will optimize in future : :v
        String time = "";
        int flag = 0;
        for (int i = 0; i < rawTime.length(); i++) {
            if (rawTime.charAt(i) <= 'Z' && rawTime.charAt(i) >= 'A' && flag == 1) {
                flag = 0;
            }

            if (flag == 1) {
                time = time + rawTime.charAt(i);
            }
            // Because the format is  2020-02-26T15:11:20Z
            if (rawTime.charAt(i) <= 'Z' && rawTime.charAt(i) >= 'A') {
                flag = 1;
            }
        }

        // Convert  Ha Noi time  UTC+7
        // To simple I will convert to hours to int;
        // From second back to hh:mm:ss
        // String h = time.charAt(0) + time.charAt(1);

        String h = time.charAt(0) + "" +time.charAt(1);
        int hours = Integer.parseInt(h);
        hours = hours + 7;

        // set specific number to test   hours = 24;

        if (hours >= 24) {
            hours = hours - 24;
        }
        // I have no idea
        time = time.substring(0, 0) + time.substring(2);
        // System.out.println(time);

        h = Integer.toString(hours);

        if (h.length() <= 1) {
            time = "0" + h + time;
        } else {
            time = h + time;
        }

        return time;
    }

    //dataFormat = 2020-03-01
    void getHistoricalData(String dateFormat) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();

        //https://api.thingspeak.com/channels/1002590/feeds.json?start=2020-03-01%2000:00:00&end=2020-03-01%2005:35:05

        String apiURL = "https://api.thingspeak.com/channels/1002590/feeds.json?start=" + dateFormat + "%2000:00:00&end=" + dateFormat +"%2000:00:00";

        //String apiURL = "https://api.thingspeak.com/channels/1002590/feeds.json?start=" + dateFormat + "%2000:00:00&end=" + dateFormat +"%2005:35:05";

        Request request = builder.url(apiURL).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {


            }

            @Override
            public void onResponse(Response response) throws IOException {

                String jsonString = response.body().string();

                try {
                    JSONObject jsonData = new JSONObject(jsonString);
                    JSONArray jsonArray = jsonData.getJSONArray("feeds");

                    System.out.println(jsonArray); // ok


                } catch (Exception e) {
                }


            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }

    String[] listChoice1;
    int choiceLed1 = 0;
    String[] listChoice2;
    int choiceLed2 = 0;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // declare
        AlertDialog.Builder builder;
        AlertDialog mDialog;
        switch (id)
        {
            case R.id.btn_led1:
                listChoice1 = new String[] {"OFF", "ON"};
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Choose");
                builder.setSingleChoiceItems(listChoice1, choiceLed1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        // set value to MQTT
                        changeLEDState1(i);


                        // set change value
                        choiceLed1 = i;
                        dialog.dismiss();

                    }
                });

                // Create button Cancel
                // Position is the button order (left to right) was POSITIVE - NEUTRAL - NEGATIVE.
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                // display
                mDialog = builder.create();
                mDialog.show();
                return true;
            case R.id.btn_led2:
                listChoice2 = new String[] {"OFF", "ON"};
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Choose");
                builder.setSingleChoiceItems(listChoice2, choiceLed2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        // set value to MQTT
                        changeLEDState2(i);

                        // set change value
                        choiceLed2 = i;
                        dialog.dismiss();

                    }
                });

                // Create button Cancel
                // Position is the button order (left to right) was POSITIVE - NEUTRAL - NEGATIVE.
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                // display
                mDialog = builder.create();
                mDialog.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void changeLEDState1(int i) {
        if (i == 0) {
            publishControlLED("LEDTOFF");

        }

        if (i == 1) {
            publishControlLED("LEDTON");
        }

    }

    private void changeLEDState2(int i) {
        if (i == 0) {
            publishControlLED("LEDLOFF");
        }

        if (i == 1) {
            publishControlLED("LEDLON");
        }

    }

    private void publishControlLED(String signal){
        MqttMessage message = new MqttMessage();
        message.setPayload(signal.getBytes());
        try {
            mqttHelper.mqttAndroidClient.publish(mqttHelper.subscriptionTopic1, message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}


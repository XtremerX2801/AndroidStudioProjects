package com.example.vdho1.graphmonitoring;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity{

    GraphView graphTemperature, graphLightLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        graphTemperature = findViewById(R.id.graphTemperature);
        graphLightLevel = findViewById((R.id.graphLightLevel));

        setupBlinkyTimer();

        startMQTT();
    }

    private void showDataOnGraph(LineGraphSeries<DataPoint> series, GraphView graph) {
        if (graph.getSeries().size() > 0) {
            graph.getSeries().remove(0);
        }
        graph.addSeries(series);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
    }

    private void getDataFromThingSpeak(){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        String apiURL = "https://api.thingspeak.com/channels/1005142/feeds.json?api_key=M2KSJMKX1JR48H00&results=5";
        Request request = builder.url(apiURL).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String jsonString = response.body().string();
                try{
                    JSONObject jsonData = new JSONObject(jsonString);
                    JSONArray jsonArray = jsonData.getJSONArray("feeds");
                    double temp0 = jsonArray.getJSONObject(0).getDouble("field1");
                    double temp1 = jsonArray.getJSONObject(1).getDouble("field1");
                    double temp2 = jsonArray.getJSONObject(2).getDouble("field1");
                    double temp3 = jsonArray.getJSONObject(3).getDouble("field1");
                    double temp4 = jsonArray.getJSONObject(4).getDouble("field1");

                    LineGraphSeries<DataPoint> seriesTemp = new LineGraphSeries<>(new DataPoint[]
                            {   new DataPoint(0, temp0),
                                    new DataPoint(1, temp1),
                                    new DataPoint(2, temp2),
                                    new DataPoint(3, temp3),
                                    new DataPoint(4, temp4)
                            });

                    showDataOnGraph(seriesTemp, graphTemperature);

                    double temp5 = jsonArray.getJSONObject(0).getDouble("field2");
                    double temp6 = jsonArray.getJSONObject(1).getDouble("field2");
                    double temp7 = jsonArray.getJSONObject(2).getDouble("field2");
                    double temp8 = jsonArray.getJSONObject(3).getDouble("field2");
                    double temp9 = jsonArray.getJSONObject(4).getDouble("field2");

                    LineGraphSeries<DataPoint> seriesTemp1 = new LineGraphSeries<>(new DataPoint[]
                            {   new DataPoint(0, temp5),
                                    new DataPoint(1, temp6),
                                    new DataPoint(2, temp7),
                                    new DataPoint(3, temp8),
                                    new DataPoint(4, temp9)
                            });

                    showDataOnGraph(seriesTemp1, graphLightLevel);

                }catch (Exception e){}
            }
        });
    }

    private void setupBlinkyTimer(){
        Timer mTimer = new Timer();
        TimerTask mTask = new TimerTask() {
            @Override
            public void run() {
                getDataFromThingSpeak();
//                sendDataToMQTT("2", "Sensor");
            }
        };
        mTimer.schedule(mTask, 2000, 5000);
    }

    MQTTHelper mqttHelper;
    private void startMQTT(){
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
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            }
        });
    }

//    private void sendDataToMQTT(String ID, String value){
//        MqttMessage msg = new MqttMessage();
//        msg.setId(1234);
//        msg.setQos(0);
//        msg.setRetained(true);
//
//        if (ID.equals("1")) {
//            value = "Temperature";
//        } else if (ID.equals("2")) {
//            value = "Light Level";
//        }
//        String data = ID + ":" + value;
//
//        byte[] b = data.getBytes(Charset.forName("UTF-8"));
//        msg.setPayload(b);
//
//        try {
//            mqttHelper.mqttAndroidClient.publish("sensor/RP3", msg);
//
//        }catch (MqttException e){
//        }
//    }

}


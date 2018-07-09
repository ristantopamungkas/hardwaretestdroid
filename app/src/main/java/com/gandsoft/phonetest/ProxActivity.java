package com.gandsoft.phonetest;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ProxActivity extends AppCompatActivity {
    private static final String TAG = "ProxActivity";
    TextView ProximityReading,tvPassed;

    SensorManager mySensorManager;
    Sensor myProximitySensor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prox);
        ProximityReading = (TextView)findViewById(R.id.proximityReading);
        tvPassed = (TextView)findViewById(R.id.tvPassed);

        mySensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        myProximitySensor = mySensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if (myProximitySensor == null){
        }
        else{
            mySensorManager.registerListener(proximitySensorEventListener,
                    myProximitySensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    SensorEventListener proximitySensorEventListener = new SensorEventListener(){
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event){
            if(event.sensor.getType()== Sensor.TYPE_PROXIMITY){
                Log.d(TAG, "onSensorChanged: "+String.valueOf(event.values[0]));
                if(String.valueOf(event.values[0]).equals("0.0")){
                    ProximityReading.setText("Proximity sensor covered");
                }else{
                    ProximityReading.setText("Proximity sensor uncovered");
                }
            }
        }
    };
    @Override
    public void onBackPressed() {
        finish();
    }
}
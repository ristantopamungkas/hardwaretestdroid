package com.gandsoft.phonetest.ActivityClass;
import android.content.Context;
import android.hardware.*;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import com.gandsoft.phonetest.R;
import com.gandsoft.phonetest.ReportHelper;

public class PedoActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView count,tvPassed;
    boolean activityRunning;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedo);
        count = (TextView) findViewById(R.id.tvPedoCounter);
        tvPassed = (TextView) findViewById(R.id.tvPassed);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        activityRunning = true;

        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
            ReportHelper.writeToFile("<br><font color='green'>Pedometer worked</font><br>");
        } else {
            Toast.makeText(this, "Count sensor not available!", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (activityRunning) {
            count.setText(Html.fromHtml(String.valueOf(event.values[0])));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}

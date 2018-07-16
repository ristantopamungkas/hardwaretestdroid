package com.gandsoft.phonetest.ActivityClass;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.Manifest;
import com.google.android.gms.*;
import com.gandsoft.phonetest.R;

public class GpsActivity extends AppCompatActivity {

    Button btnShowLocation;


    GpsTracker gps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);

        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                gps = new GpsTracker(GpsActivity.this);


                if (gps.canGetLocation()) {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                } else {


                    gps.showSettingsAlert();
                }

            }
        });

    }

}




package com.gandsoft.phonetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button bPhoneInformation, bWifiTest, bCamTest, bBtoothTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bPhoneInformation = (Button)findViewById(R.id.bPhoneInformation);
        bWifiTest = (Button)findViewById(R.id.bWifiTest);
        bCamTest = (Button)findViewById(R.id.bCamTest);
        bBtoothTest = (Button)findViewById(R.id.bBtoothTest);

        bPhoneInformation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PhoneInformationActivity.class));
            }
        });
        bWifiTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, WifiActivity.class));
            }
        });
        bCamTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CamActivity.class));
            }
        });
        bBtoothTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BtoothActivity.class));
            }
        });


    }
}
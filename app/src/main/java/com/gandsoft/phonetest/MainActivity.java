package com.gandsoft.phonetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gandsoft.phonetest.ActivityClass.BrightnessActivity;
import com.gandsoft.phonetest.ActivityClass.BtoothActivity;
import com.gandsoft.phonetest.ActivityClass.ButtonActivity;
import com.gandsoft.phonetest.ActivityClass.CamActivity;
import com.gandsoft.phonetest.ActivityClass.MicActivity;
import com.gandsoft.phonetest.ActivityClass.PedoActivity;
import com.gandsoft.phonetest.ActivityClass.PhoneInformationActivity;
import com.gandsoft.phonetest.ActivityClass.ProxActivity;
import com.gandsoft.phonetest.ActivityClass.WifiActivity;

public class MainActivity extends AppCompatActivity {
    private Button bPhoneInformation, bWifiTest, bCamTest, bBtoothTest,bButtonTest,bBrightnessTest,bProxTest,bPedoTest,bMicTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bPhoneInformation = (Button)findViewById(R.id.bPhoneInformation);
        bWifiTest = (Button)findViewById(R.id.bWifiTest);
        bCamTest = (Button)findViewById(R.id.bCamTest);
        bBtoothTest = (Button)findViewById(R.id.bBtoothTest);
        bButtonTest = (Button)findViewById(R.id.bButtonTest);
        bBrightnessTest = (Button)findViewById(R.id.bBrightnessTest);
        bProxTest = (Button)findViewById(R.id.bProxTest);
        bPedoTest = (Button)findViewById(R.id.bPedoTest);
        bMicTest = (Button)findViewById(R.id.bMicTest);

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

        bButtonTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ButtonActivity.class));
            }
        });

        bBrightnessTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BrightnessActivity.class));
            }
        });

        bProxTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ProxActivity.class));
            }
        });

        bPedoTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PedoActivity.class));
            }
        });

        bMicTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MicActivity.class));
            }
        });

    }
}
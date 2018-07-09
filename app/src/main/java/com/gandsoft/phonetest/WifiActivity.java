package com.gandsoft.phonetest;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class WifiActivity extends AppCompatActivity {
    WifiManager wifi;
    List<ScanResult> wifiList;
    TextView txtWifiReport,tvPassed;

    @SuppressLint("WifiManagerLeak")
    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*Request Permission Again only at Marshmallow*/
        requestPermissions(new String[]{Manifest.permission.CHANGE_WIFI_STATE}, 1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);

        tvPassed = (TextView)findViewById(R.id.tvPassed);
        txtWifiReport = (TextView)findViewById(R.id.txtWifiReport);
        wifi = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        startTest();
    }

    private void startTest(){
        if (!wifi.isWifiEnabled()) {
            wifi.setWifiEnabled(true);
            SystemClock.sleep(3000);
        }

        if(wifi.setWifiEnabled(true)){
            txtWifiReport.append(Html.fromHtml("Wifi Activated<br>"));
            tvPassed.setVisibility(View.VISIBLE);
        }
        else{
            txtWifiReport.append(Html.fromHtml("Wifi Failed<br>"));
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

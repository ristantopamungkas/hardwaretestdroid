package com.gandsoft.phonetest.ActivityClass;

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

import com.gandsoft.phonetest.R;

import java.util.List;
import com.gandsoft.phonetest.ReportHelper;

public class WifiActivity extends AppCompatActivity {
    WifiManager wifi;
    List<ScanResult> wifiList;
    TextView txtWifiReport,tvPassed;
    String report = "<font color='grey'>Wifi not tested</font>";

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
            report = "<font color='green'>Wifi can be activated</font><br>";
            ReportHelper.writeToFile(report);
        }
        else{
            txtWifiReport.append(Html.fromHtml("Wifi Failed<br>"));
            report = "<font color='red'>Wifi cannot be activated</font><br>";
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

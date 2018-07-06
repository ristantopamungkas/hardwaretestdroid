package com.gandsoft.phonetest;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
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
        /*Request Permission*/
        requestPermissions(new String[]{Manifest.permission.ACCESS_WIFI_STATE}, 999);
        requestPermissions(new String[]{Manifest.permission.CHANGE_WIFI_STATE}, 999);
        requestPermissions(new String[]{Manifest.permission.CHANGE_WIFI_MULTICAST_STATE}, 999);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);

        tvPassed = (TextView)findViewById(R.id.tvPassed);
        txtWifiReport = (TextView)findViewById(R.id.txtWifiReport);

        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        registerReceiver(mWifiScanReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        startTest();
    }

    private void startTest(){
        if (!wifi.isWifiEnabled()) {
            wifi.setWifiEnabled(true);
        }
        txtWifiReport.append(Html.fromHtml("Wifi Activated<br>"));
        wifi.startScan();
    }

    private final BroadcastReceiver mWifiScanReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent intent) {
            if (intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
                wifiList = wifi.getScanResults();
                if (wifiList.size() >= 1) {
                    txtWifiReport.append(Html.fromHtml(wifiList.size() + " conection scanned.<br>"));
                    tvPassed.setVisibility(View.VISIBLE);
                }else{
                    txtWifiReport.append(Html.fromHtml("Can't Found"));
                }
            }
        }
    };

    @Override
    public void onBackPressed() {
        finish();
    }
}

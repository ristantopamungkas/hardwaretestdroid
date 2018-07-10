package com.gandsoft.phonetest.ActivityClass;

import android.Manifest;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gandsoft.phonetest.R;
import com.gandsoft.phonetest.ReportHelper;

public class BtoothActivity extends AppCompatActivity {

    TextView txtReport,tvPassed;
    Button btnNext;
    BluetoothAdapter btAdapter;
    String report;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestPermissions(new String[]{Manifest.permission.BLUETOOTH}, 1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btooth);

        tvPassed = (TextView)findViewById(R.id.tvPassed);
        txtReport = (TextView)findViewById(R.id.txtBtReport);

        btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!btAdapter.isEnabled()) {
            btAdapter.enable();
            SystemClock.sleep(3000);
        }
        if(btAdapter.isEnabled()){
            txtReport.append(Html.fromHtml("Bluetooth activated<br>"));
            tvPassed.setVisibility(View.VISIBLE);
            report = "<br><font color='green'>Bluetooth can be activated</font><br>";
            ReportHelper.writeToFile(report);
        }

        else{
            txtReport.append(Html.fromHtml("Bluetooth cant activate<br>"));
        }
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
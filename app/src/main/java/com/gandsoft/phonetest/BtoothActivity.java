package com.gandsoft.phonetest;

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

public class BtoothActivity extends AppCompatActivity {

    TextView txtReport,tvPassed;
    Button btnNext;
    BluetoothAdapter btAdapter;
    String report = "- <strong>Bluetooth:</strong> <font color='#cc0000'></font><br>\n";

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
        txtReport.append(Html.fromHtml("Bluetooth activated<br>"));
        btAdapter.startDiscovery();

        final BroadcastReceiver bReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    txtReport.append(Html.fromHtml("Bluetooth device found"));
                    tvPassed.setVisibility(View.VISIBLE);
                    btAdapter.cancelDiscovery();
                    btAdapter.disable();
                } else {
                    txtReport.append(Html.fromHtml("Can't Found"));
                }
            }
        };
        registerReceiver(bReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
package com.gandsoft.phonetest;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class PhoneInformationActivity extends AppCompatActivity{

    private Context context;
    private TextView tvPhoneInfo;
    private TelephonyManager mTelephonyManager;

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_information);

        tvPhoneInfo = (TextView) findViewById(R.id.tvPhoneInfo);

        requestPermissions( new String[]{Manifest.permission.READ_PHONE_STATE},1);

        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        String deviceid = mTelephonyManager.getDeviceId();
        String provider_number = mTelephonyManager.getNetworkOperatorName();

        String serial = Build.SERIAL;
        String model = Build.MODEL;
        String id = Build.ID;
        String brand = Build.BRAND;
        String sdk = Build.VERSION.SDK;
        String version_code = Build.VERSION.RELEASE;
        String board =  Build.BOARD;

        tvPhoneInfo.setText(
            "IMEI : " + deviceid
            + "\n\nProvider : " + provider_number
            + "\n\nSerial : " + serial
            + "\n\nBrand :" + brand
            + "\n\nModel : " + model
            + "\n\nFirmware : " + id
            + "\n\nVersion Code : " + version_code + ",SDK " + sdk
            + "\n\nBoard : " + board
        );


    }

}

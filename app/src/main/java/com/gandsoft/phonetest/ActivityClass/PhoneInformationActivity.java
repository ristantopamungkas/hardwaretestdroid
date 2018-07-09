package com.gandsoft.phonetest.ActivityClass;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import com.gandsoft.phonetest.R;

public class PhoneInformationActivity extends AppCompatActivity{
    private TextView tvPhoneInfo;
    private TelephonyManager mTelephonyManager;

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_information);

        tvPhoneInfo = (TextView) findViewById(R.id.tvPhoneInfo);

        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

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

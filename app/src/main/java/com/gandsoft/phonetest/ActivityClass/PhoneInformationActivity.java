package com.gandsoft.phonetest.ActivityClass;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.widget.TextView;

import com.gandsoft.phonetest.R;
import com.gandsoft.phonetest.ReportHelper;

public class PhoneInformationActivity extends AppCompatActivity {
    private TextView tvPhoneInfo;
    private TelephonyManager mTelephonyManager;

    public static String reportType = "phonetest";
    public TextView txtReport;
    public String uri = "";
    public static String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/phonetest_report.txt";

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_information);

        tvPhoneInfo = (TextView) findViewById(R.id.tvPhoneInfo);

        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
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

        txtReport = (TextView)findViewById(R.id.txtReport);

        if (reportType == "phonetest") {
            uri = Environment.getExternalStorageDirectory().getAbsolutePath() + "/phonetest_report.txt";
        } else if (reportType == "stress") {
            uri = Environment.getExternalStorageDirectory().getAbsolutePath() + "/stress_report.txt";
        }

        txtReport.setText(Html.fromHtml(ReportHelper.readFromFile(uri)));
    }
}

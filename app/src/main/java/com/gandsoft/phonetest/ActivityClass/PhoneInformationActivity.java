package com.gandsoft.phonetest.ActivityClass;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
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

    @SuppressLint("SetTextI18n")
    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_information);

        tvPhoneInfo = (TextView) findViewById(R.id.tvPhoneInfo);

        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);


/*        DisplayMetrics dm = new DisplayMetrics();
        dm.getWindowManager().getDefaultDisplay().getMetrics(dm);
        double x = Math.pow(mWidthPixels / dm.xdpi, 2);
        float mHeightPixels;
        double y = Math.pow(mHeightPixels / dm.ydpi, 2);
        screenInches = Math.sqrt(x + y);
        rounded = df2.format(screenInches);
        densityDpi = (int) (dm.density * 160f);*/
        String Hardware = Build.HARDWARE;
        if(Hardware.equals("qcom")){
            Hardware = "Qualcomm";
        }else{
            Hardware = "Mediatek";
        }

        int Datastate = mTelephonyManager.getDataState();
        String DatastateName = null;
        if(Datastate==2){
            DatastateName="Connected";
        }
        else if(Datastate==0){
            DatastateName="Not Connected";
        }

        tvPhoneInfo.setText(
                "Device Model : "+ Build.MANUFACTURER  + " " +  Build.MODEL
                + "\n Android Version  : " + Build.VERSION.RELEASE
                + "\n Current Security Patch : " + Build.VERSION.SECURITY_PATCH
                + "\n Board : " + Hardware + " " + Build.BOARD
                + "\n Serial Number : " + Build.SERIAL
                + "\n Kernel Number : " + Build.USER + "@" + Build.HOST + "#" +Build.BOOTLOADER
                + "\n IMEI Number : " + mTelephonyManager.getDeviceId()
                + "\n IMEI SV : " + mTelephonyManager.getDeviceSoftwareVersion()
                + "\n Operator Name : " + mTelephonyManager.getNetworkOperatorName()
                + "\n Mobile Network : " + DatastateName
        );
/*        String deviceid = mTelephonyManager.getDeviceId();
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
        );*/

/*        txtReport = (TextView)findViewById(R.id.txtReport);

        if (reportType == "phonetest") {
            uri = Environment.getExternalStorageDirectory().getAbsolutePath() + "/phonetest_report.txt";
        } else if (reportType == "stress") {
            uri = Environment.getExternalStorageDirectory().getAbsolutePath() + "/stress_report.txt";
        }

        txtReport.setText(Html.fromHtml(ReportHelper.readFromFile(uri)));*/
    }

    @Override
    protected void onStop() {
        super.onStop();
        ReportHelper.removeFile();
    }

}

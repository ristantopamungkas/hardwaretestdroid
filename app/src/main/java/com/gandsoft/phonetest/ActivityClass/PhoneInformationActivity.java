package com.gandsoft.phonetest.ActivityClass;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.widget.TextView;

import com.gandsoft.phonetest.R;
import com.gandsoft.phonetest.ReportHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

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


    private static final String TAG ="PhoneInformationActivity";

    @SuppressLint({"LongLogTag", "MissingPermission"})
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_information);
        tvPhoneInfo = (TextView) findViewById(R.id.tvPhoneInfo);
        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        String rootstate="no";
        String[] paths = {
                "/system/app/Superuser.apk",
                "/sbin/su",
                "/system/bin/su",
                "/system/xbin/su",
                "/data/local/xbin/su",
                "/data/local/bin/su",
                "/system/sd/xbin/su",
                "/system/bin/failsafe/su",
                "/data/local/su",
                "/su/bin/su"
        };
        for (String path : paths) {
            if (new File(path).exists()){
                rootstate="yes";
            }
        }

        String [] AndroidNameList= {
                "","Petit Four", "Cupcake", "Donut", "Eclair",
                "Eclair","Eclair", "Froyo", "Gingerbread", "Gingerbread",
                "Honeycomb","Honeycomb","Honeycomb", "Ice Cream Sandwich","Ice Cream Sandwich",
                "Jellybean","Jellybean","Jellybean", "Kitkat","Kitkat",
                "Lolipop","Lolipop", "Marshmello","Nougat","Nougat",
                "Oreo","Oreo","Android P"};

        String androidname = AndroidNameList[Build.VERSION.SDK_INT-1];

        String Hardware = Build.HARDWARE;
        if(Hardware.equals("qcom")){
            Hardware = "Qualcomm";
        }else{
            Hardware = "Mediatek";
        }

        int datastate_int = mTelephonyManager.getDataState();
        String datastate = null;
        if(datastate_int==2){
            datastate="Connected";
        }
        else if(datastate_int==0){
            datastate="Not Connected";
        }
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int heightPixels = metrics.heightPixels;
        int widthPixels = metrics.widthPixels;
        int densityDpi = metrics.densityDpi;
        float xdpi = metrics.xdpi;
        float ydpi = metrics.ydpi;
        float x = (float) Math.pow(xdpi,2);
        float y = (float) Math.pow(ydpi,2);
        float screensize = (float) Math.sqrt(x+y)/100;

        String line = null;
        try {
            Process p = Runtime.getRuntime().exec("uname -r");
            InputStream is = null;
            if (p.waitFor() == 0) {
                is = p.getInputStream();
            } else {
                is = p.getErrorStream();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            line = br.readLine();
            Log.i("Kernel Version", line);
            br.close();
        } catch (Exception ex) {}

        String lay = null;
        try {
            Process p = Runtime.getRuntime().exec("cat /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
            InputStream is = null;
            if (p.waitFor() == 0) {
                is = p.getInputStream();
            } else {
                is = p.getErrorStream();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            lay = br.readLine();

            Log.i("CPU info", lay);
            br.close();
        } catch (Exception ex) {}

        tvPhoneInfo.setText(
            "Device Model : "+ Build.MANUFACTURER  + " " +  Build.MODEL
            + "\n Android Version  : " + androidname + ", " +Build.VERSION.RELEASE
            + "\n Current Security Patch : " + Build.VERSION.SECURITY_PATCH
            + "\n Board : " + Hardware + " " + Build.BOARD +" e" +String.format("%.2f", Float.valueOf(lay)/1000000) + " GHz"
            + "\n Serial Number : " + Build.SERIAL
            + "\n Kernel Version : " + line
            + "\n Builder : " + Build.USER + "@" + Build.HOST
            + "\n Bootloader Version : " +Build.BOOTLOADER
            + "\n IMEI Number : " + mTelephonyManager.getDeviceId()
            + "\n IMEI SV : " + mTelephonyManager.getDeviceSoftwareVersion()
            + "\n Operator Name : " + mTelephonyManager.getNetworkOperatorName()
            + "\n Mobile Network : " + datastate
            + "\n Root Permission : " + rootstate
            + "\n Screen Resolution : " + heightPixels + "x"+ widthPixels + "pixels"
            + "\n Screen Size : " + String.format("%.2f", screensize) + "inch"
        );
// pixels, dpi

// deprecated
    }

    @Override
    protected void onStop() {
        super.onStop();
        ReportHelper.removeFile();
    }

}

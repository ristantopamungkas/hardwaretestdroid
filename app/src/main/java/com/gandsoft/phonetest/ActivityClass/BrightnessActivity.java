package com.gandsoft.phonetest.ActivityClass;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.gandsoft.phonetest.R;
import com.gandsoft.phonetest.ReportHelper;

import java.util.Random;


public class BrightnessActivity extends Activity {//UI objects//
    private static final String TAG = "MainActivity";
    private SeekBar seekBar;
    private int brightness;
    private ContentResolver cResolver;
    private Window window;
    private TextView tvPassedMax,tvPassedMin,tvPassed;

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brightness);

        tvPassed = (TextView)findViewById(R.id.tvPassed);
        tvPassedMax = (TextView)findViewById(R.id.tvPassedMax);
        tvPassedMin = (TextView)findViewById(R.id.tvPassedMin);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        cResolver = getContentResolver();
        window = getWindow();
        seekBar.setMax(245);
        final int minval =10;

        Context context = getApplicationContext();
        boolean settingsCanWrite = Settings.System.canWrite(context);
        if (!android.provider.Settings.System.canWrite(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            intent.setData(Uri.parse("package:com.gandsoft.phonetest"));
            startActivity(intent);
        }

        try {
            brightness = System.getInt(cResolver, System.SCREEN_BRIGHTNESS);
        } catch (SettingNotFoundException e) {
            e.printStackTrace();
        }

        seekBar.setProgress(brightness);
        seekBar.setOnSeekBarChangeListener(
            new OnSeekBarChangeListener(){
                int a=0,b=0;

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {}

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {}

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                    progress = progress +minval;

                    Settings.System.putInt(cResolver, System.SCREEN_BRIGHTNESS,progress);
                    Log.d(TAG, "onProgressChanged: "+ progress);



                    if (progress ==255){
                        tvPassedMax.setVisibility(View.VISIBLE);
                        a=1;
                    }
                    else if(progress == 10){
                        tvPassedMin.setVisibility(View.VISIBLE);
                        b=1;
                    }
                    else if(a>0 && b>0){
                        tvPassed.setVisibility(View.VISIBLE);
                        ReportHelper.writeToFile("<br><font color='green'>Brightness control worked</font><br>");
                    }

                }
            }
        );
    }

}


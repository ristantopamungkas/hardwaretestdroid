package com.gandsoft.phonetest;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


public class BrightnessActivity extends Activity {//UI objects//
    private SeekBar seekBar;
    private int brightness;
    private ContentResolver cResolver;
    private Window window;
    private static final String TAG = "MainActivity";
    public TextView tvPassedMax,tvPassedMin;
    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
//        requestPermissions(new String[]{Manifest.permission.WRITE_SETTINGS}, 1);
  //      requestPermissions(new String[]{Manifest.permission.WRITE_SECURE_SETTINGS}, 1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brightness);
        tvPassedMax = (TextView)findViewById(R.id.tvPassedMax);
        tvPassedMin = (TextView)findViewById(R.id.tvPassedMin);


        Context context = getApplicationContext();
        boolean settingsCanWrite = Settings.System.canWrite(context);
        if (!android.provider.Settings.System.canWrite(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            intent.setData(Uri.parse("package:com.gandsoft.phonetest"));
            startActivity(intent);
        }

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        cResolver = getContentResolver();
        window = getWindow();
        final int minval =10;
        seekBar.setMax(245);
        try {
            brightness = System.getInt(cResolver, System.SCREEN_BRIGHTNESS);
        } catch (SettingNotFoundException e) {
            e.printStackTrace();
        }

        seekBar.setProgress(brightness);

        seekBar.setOnSeekBarChangeListener(
            new OnSeekBarChangeListener(){
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
                    }
                    else if(progress == 10){
                        tvPassedMin.setVisibility(View.GONE);
                    }
                }
            }
        );
    }

}


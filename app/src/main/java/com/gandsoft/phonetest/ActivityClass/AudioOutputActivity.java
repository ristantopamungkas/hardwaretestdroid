package com.gandsoft.phonetest.ActivityClass;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gandsoft.phonetest.R;

public class AudioOutputActivity extends AppCompatActivity {
    TextView tvPassed,tvReport;
    Button bTest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_output);
        tvPassed = (TextView)findViewById(R.id.tvPassed);
        tvReport = (TextView)findViewById(R.id.tvReport);
        bTest = (Button)findViewById(R.id.bTest);

        AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        if(audioManager.isWiredHeadsetOn()==true){
            tvPassed.setVisibility(View.VISIBLE);
            bTest.setVisibility(View.GONE);
            tvReport.setText("Wired audio device is connected");
        }else {
            tvReport.setText("Wired audio device is not connected");
        }
        Log.i("WiredHeadsetOn = ", audioManager.isWiredHeadsetOn()+"");
    }
    public void recreate(View v){
        super.onRestart();
        Intent i = new Intent(this, AudioOutputActivity.class);  //your class
        startActivity(i);
        finish();
    }

}

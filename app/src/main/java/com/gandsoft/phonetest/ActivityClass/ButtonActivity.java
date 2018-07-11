package com.gandsoft.phonetest.ActivityClass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

import com.gandsoft.phonetest.R;
import com.gandsoft.phonetest.ReportHelper;

public class ButtonActivity extends AppCompatActivity {
    private static final String TAG = "ButtonActivity";
    TextView tvPassedVolUp,tvPassedVolDown;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        tvPassedVolUp = (TextView)findViewById(R.id.tvPassedVolUp);
        tvPassedVolDown = (TextView)findViewById(R.id.tvPassedVolDown);
    }
    int a=0, b=0;
    @Override
    public boolean onKeyDown( int keyCode, KeyEvent event ) {

        if(keyCode==KeyEvent.KEYCODE_VOLUME_UP){
            a=1;
            Toast.makeText(this, "Volume up", Toast.LENGTH_SHORT).show();
            tvPassedVolUp.setVisibility(View.VISIBLE);
            return true;
        }
        else if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN){
            b=1;
            Toast.makeText(this, "Volume down", Toast.LENGTH_SHORT).show();
            tvPassedVolDown.setVisibility(View.VISIBLE);
            return true;
        }
        else if(a>0 && b>0){
            ReportHelper.writeToFile("<br><font color='green'>Volume button worked</font><br>");
        }

        /*
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                a=1;
                Toast.makeText(this, "Volume up", Toast.LENGTH_SHORT).show();
                tvPassedVolUp.setVisibility(View.VISIBLE);
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                b=1;
                Toast.makeText(this, "Volume down", Toast.LENGTH_SHORT).show();
                tvPassedVolDown.setVisibility(View.VISIBLE);
                return true;
        }
        */
        return super.onKeyDown( keyCode, event );
    }

}

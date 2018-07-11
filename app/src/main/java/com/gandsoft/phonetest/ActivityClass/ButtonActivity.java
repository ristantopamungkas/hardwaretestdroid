package com.gandsoft.phonetest.ActivityClass;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gandsoft.phonetest.R;
import com.gandsoft.phonetest.ReportHelper;

public class ButtonActivity extends AppCompatActivity {
    private static final String TAG = "ButtonActivity";
    TextView tvPassedVolUp,tvPassedVolDown;
    ConstraintLayout L1;
    ImageView image;
    Button bBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        tvPassedVolUp = (TextView)findViewById(R.id.tvPassedVolUp);
        tvPassedVolDown = (TextView)findViewById(R.id.tvPassedVolDown);
        bBack = (Button)findViewById(R.id.bBack);
    }
    int a=0, b=0,c=0;
    @Override
    public boolean onKeyDown( int keyCode, KeyEvent event ) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            a = 1;
            Toast.makeText(this, "Volume up", Toast.LENGTH_SHORT).show();
            tvPassedVolUp.setVisibility(View.VISIBLE);
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            b = 1;
            Toast.makeText(this, "Volume down", Toast.LENGTH_SHORT).show();
            tvPassedVolDown.setVisibility(View.VISIBLE);
            Log.d(TAG,"woi "+c);
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_CAMERA) {
            c = 1;
            Log.d(TAG,"woi "+c);
            Toast.makeText(this, "Screenshot", Toast.LENGTH_SHORT).show();
            return true;
        }else if (keyCode == KeyEvent.KEYCODE_BACK) {
            c = 1;
            Log.d(TAG,"woi "+c);
            Toast.makeText(this, "Screenshot", Toast.LENGTH_SHORT).show();
            return true;
        } else if (a > 0 && b > 0) {
            ReportHelper.writeToFile("<br><font color='green'>Volume button worked</font><br>");
        }
        return super.onKeyDown(keyCode, event);
    }
   public void back(){
        onDestroy();
   }
}

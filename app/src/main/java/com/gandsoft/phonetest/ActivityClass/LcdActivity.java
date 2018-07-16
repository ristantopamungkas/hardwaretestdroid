package com.gandsoft.phonetest.ActivityClass;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.gandsoft.phonetest.R;

import java.util.HashMap;
import java.util.Random;

public class LcdActivity extends Activity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lcd);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY );
        final int a=10000;
        final int b=10000;
        final int[] c = { 0 };
        final HashMap<Integer,View> map= new HashMap<>();
        final ConstraintLayout rl= (ConstraintLayout)findViewById(R.id.rel);
        rl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getActionMasked()==MotionEvent.ACTION_DOWN){
                    Paint paint = null;
                    int newPointerIndex = event.getActionIndex();
                    int newPointerId = event.getPointerId(newPointerIndex);
                    View newView = new View(LcdActivity.this);
                    Random rnd = new Random();
                    int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                    newView.setBackgroundColor(color);
                    c[0] = c[0] +1;
                    if(c[0]>5){
                        finish();
                    }
                    float x = event.getX();
                    float y = event.getY();
                    newView.setLayoutParams(new LinearLayout.LayoutParams(a,b));
                    newView.setX(x-(a/2));
                    newView.setY(y-(b/2));

                    map.put(newPointerId,newView);
                    rl.addView(newView);
                }
                return true;
            }
        });
    }
}

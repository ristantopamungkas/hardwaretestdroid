package com.gandsoft.phonetest.ActivityClass;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.Touch;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.gandsoft.phonetest.R;

import java.util.HashMap;


public class TouchscreenActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touchscreen);

        final HashMap<Integer,View> map= new HashMap<>();
        final ConstraintLayout rl= (ConstraintLayout)findViewById(R.id.rel);
        rl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getActionMasked()==MotionEvent.ACTION_DOWN){
                    int newPointerIndex = event.getActionIndex();
                    int newPointerId = event.getPointerId(newPointerIndex);
                    View newView = new View(TouchscreenActivity.this);
                    newView.setBackgroundColor(Color.BLACK);
                    float x = event.getX();
                    float y = event.getY();
                    newView.setLayoutParams(new LinearLayout.LayoutParams(150,150));
                    newView.setX(x-75);
                    newView.setY(y-75);
                    map.put(newPointerId,newView);
                    rl.addView(newView);
                }
                else if(event.getActionMasked()==MotionEvent.ACTION_POINTER_DOWN){
                    int newPointerIndex = event.getActionIndex();
                    int newPointerId = event.getPointerId(newPointerIndex);
                    View newView = new View(TouchscreenActivity.this);
                    newView.setBackgroundColor(Color.BLACK);
                    float x = event.getX(newPointerIndex);
                    float y = event.getY(newPointerIndex);
                    newView.setLayoutParams(new LinearLayout.LayoutParams(150,150));
                    newView.setX(x-75);
                    newView.setY(y-75);
                    map.put(newPointerId,newView);
                    rl.addView(newView);
                }else if(event.getActionMasked()==MotionEvent.ACTION_UP){
                    int newPointerIndex = event.getActionIndex();
                    int newPointerId = event.getPointerId(newPointerIndex);
                    View newView = new View(TouchscreenActivity.this);
                    newView.setBackgroundColor(Color.BLACK);
                    float x = event.getX();
                    float y = event.getY();
                    newView.setLayoutParams(new LinearLayout.LayoutParams(150,150));
                    newView.setX(x-75);
                    newView.setY(y-75);
                    map.put(newPointerId,newView);
                    rl.addView(newView);
                }else if(event.getActionMasked()==MotionEvent.ACTION_POINTER_UP){
                    int newPointerIndex = event.getActionIndex();
                    int newPointerId = event.getPointerId(newPointerIndex);
                    View newView = new View(TouchscreenActivity.this);
                    newView.setBackgroundColor(Color.BLACK);
                    float x = event.getX();
                    float y = event.getY();
                    newView.setLayoutParams(new LinearLayout.LayoutParams(150,150));
                    newView.setX(x-75);
                    newView.setY(y-75);
                    map.put(newPointerId,newView);
                    rl.addView(newView);
                }else if(event.getActionMasked()==MotionEvent.ACTION_MOVE){
                    int newPointerIndex = event.getActionIndex();
                    int newPointerId = event.getPointerId(newPointerIndex);
                    View newView = new View(TouchscreenActivity.this);
                    newView.setBackgroundColor(Color.BLACK);
                    newView.setLayoutParams(new LinearLayout.LayoutParams(150,150));
                    int count = event.getPointerCount();
                    map.put(newPointerId,newView);
                    rl.addView(newView);
                    for (int i =0;i<count;i++){
                        int id=event.getPointerId(i);
                        float x = event.getX(i);
                        float y = event.getY(i);
                        View nv=map.get(id);
                        nv.setX(x-75);
                        nv.setY(y-75);
                    }

                }
                return true;
            }
        });
    }

}

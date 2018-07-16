package com.gandsoft.phonetest;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.GridLayout;
import android.widget.Toast;
import java.lang.reflect.Array;


public class Touchscreen2Activity extends AppCompatActivity {

    private static  final  int DEFAULT_SQUARE_SIZE = 55;//55
    private static  final  int MINIMAL_SQUARE_SIZE = 37;//37
    private View[][] circles;
    private int counter;
    private Drawable green = createBackground(-16711936);
    private int squareXY;
    private boolean toastShownFlag;
    private int total;
    private Drawable white = createBackground(-0);
    private boolean[][] flags;

    public static class CircleView extends View{
        public int i,j;


        public CircleView(Context context){
            super(context);
        }
        public CircleView(Context context, int i, int j){
            super(context);
            this.i=i;
            this.j=j;
        }
        public CircleView(Context context, AttributeSet attrs){
            super(context,attrs);
        }
        public CircleView(Context context, AttributeSet attrs, int defStyleAttr){
            super(context,attrs,defStyleAttr);
        }
        public CircleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){
            super(context,attrs,defStyleAttr,defStyleRes);
        }

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touchscreen2);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.screen_test_grid_layout);
        size.y = size.y;
        int square = calculateSquare(size.y,size.x, DEFAULT_SQUARE_SIZE);
        if (square<MINIMAL_SQUARE_SIZE){
            square=DEFAULT_SQUARE_SIZE;
        }
        this.squareXY = square;
        int rows = size.y /this.squareXY;
        int cells = size.x /this.squareXY;
        this.flags = (boolean[][])Array.newInstance(Boolean.TYPE, new int[]{rows,cells});
        this.total = rows*cells;
        this.counter = 0;
        gridLayout.setRowCount(rows);
        gridLayout.setColumnCount(cells);
        this.circles = (View[][])Array.newInstance(View.class, new int[]{rows,cells});

        for (int i=0 ; i<rows ; i++){
            for (int j=0 ; j<cells ; j++){
                this.circles[i][j]= new CircleView(getApplicationContext(),i,j);
                View view = this.circles[i][j];
                view.setLayoutParams(new LayoutParams(this.squareXY,this.squareXY));
                view.setBackground(this.white);
                view.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        CircleView view = (CircleView) v;
                        view.setBackground(Touchscreen2Activity.this.green);
                        Touchscreen2Activity.this.checkOnSuccess(view.i,view.j);
                    }
                });
                gridLayout.addView(view);
            }
        }
    }

    public boolean dispatchTouchEvent(MotionEvent e){
        if (2 == e.getAction()){
            checkOnSuccess(((int) e.getY())/this.squareXY,((int)e.getX())/this.squareXY);
        }
        return super.dispatchTouchEvent(e);
    }
    public boolean checkOnSuccess(int i, int j){
        try {
            this.circles[i][j].setBackground(this.green);
            if(!this.flags[i][j]){
                this.flags[i][j]=true;
                this.counter++;
            }
            if(this.counter == this.total){
                if(!this.toastShownFlag){
                    Toast.makeText(getApplicationContext(),"TEST PASSED",Toast.LENGTH_LONG).show();
                    this.toastShownFlag=true;
                }
                return true;
            }
        }catch (IndexOutOfBoundsException e){}
        return false;
    }

    @NonNull
    private ShapeDrawable createBackground(int green){
        ShapeDrawable drawable = new ShapeDrawable(new OvalShape());
        drawable.getPaint().setColor(green);
        return drawable;
    }

    private int calculateSquare(int screenHeight, int screenWidth, int basedOn){
        int i = basedOn;
        while(i!=0){
            if(screenHeight%i == 0 && screenWidth % i ==0){
                return i;
            }
            i--;
        }
        throw new RuntimeException("Cannot Calculate!!");
    }
}

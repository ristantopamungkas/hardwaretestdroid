package com.gandsoft.phonetest;

import java.io.IOException;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CamActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    public TextView tvPassed;
    public Camera camera;
    public SurfaceView surfaceView;
    public SurfaceHolder surfaceHolder;
    public Button btnRear;
    public Button btnFront;
  //  public Button btnNext;

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam);

        btnRear = (Button) findViewById(R.id.btnRearCam);
        btnFront = (Button) findViewById(R.id.btnFrontCam);
        tvPassed = (TextView) findViewById(R.id.tvPassed);

        getWindow().setFormat(PixelFormat.UNKNOWN);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceview);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    }

    public void rearCamera(View v) {
        btnRear.setVisibility(View.GONE);
        btnFront.setVisibility(View.VISIBLE);

        camera = Camera.open(0);
        camera.setDisplayOrientation(90);

        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void frontCamera(View v) {
        camera.stopPreview();
        camera.release();
        camera = Camera.open(1);
        camera.setDisplayOrientation(90);

        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
            btnFront.setVisibility(View.GONE);
            tvPassed.setVisibility(View.VISIBLE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }

    @Override
    public void surfaceCreated(SurfaceHolder holder) { }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) { }

    @Override
    public void onBackPressed() {
        camera.release();
        finish();
    }
}
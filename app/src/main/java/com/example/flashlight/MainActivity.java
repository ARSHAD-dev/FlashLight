package com.example.flashlight;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
/*
created by arshad.....
 */

public class MainActivity extends AppCompatActivity {
    ImageButton imageButton;
    private boolean flashlightStateChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        imageButton = (ImageButton) findViewById(R.id.imageButton3);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (!flashlightStateChanged) {
                    try {
                        String cameraId = cameraManager.getCameraIdList()[0];
                        cameraManager.setTorchMode(cameraId, true);
                        imageButton.setImageResource(R.drawable.on);
                        flashlightStateChanged = true;
                    } catch (CameraAccessException e) {
                        System.out.println(e);
                    }
                } else {
                    try {
                        String cameraId = cameraManager.getCameraIdList()[0];
                        cameraManager.setTorchMode(cameraId, false);
                        imageButton.setImageResource(R.drawable.off);
                        flashlightStateChanged = false;
                    } catch (CameraAccessException e) {
                        System.out.println(e);
                    }
                }

            }
        });



    }
}
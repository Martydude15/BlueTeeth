package com.example.marta.blueteeth;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Student_Activity_Step1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_step1);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Ask for it
            ActivityCompat
                .requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }
    }
    // Makes it so that the button goes to the camera screen
    public void cameraClick(View view) {
        //Gives intent for button to move after picture is taken.
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent intent = new Intent(Student_Activity_Step1.this, Student_Step_2.class);
        intent.putExtra("studentpic", data);
        startActivity(intent);
    }
}
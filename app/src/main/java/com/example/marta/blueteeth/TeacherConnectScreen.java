package com.example.marta.blueteeth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TeacherConnectScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_connect_screen);
    }

    public void testMethod(View view) {
        Intent switchPage = new Intent(TeacherConnectScreen.this, TeacherPhoto.class);
        startActivity(switchPage);
    }
}
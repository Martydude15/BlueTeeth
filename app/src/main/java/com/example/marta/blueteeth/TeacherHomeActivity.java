package com.example.marta.blueteeth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TeacherHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_password);
    }

    public void testMethod(View view) {
        Intent switchPage = new Intent(TeacherHomeActivity.this, TeacherConnectScreen.class);
        startActivity(switchPage);
    }
}

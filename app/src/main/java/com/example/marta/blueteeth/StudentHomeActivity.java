package com.example.marta.blueteeth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StudentHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        getActionBar().hide();
    }

    public void testMethod(View view) {
        Intent switchPage = new Intent(StudentHomeActivity.this, StudentConnectScreen.class);
        startActivity(switchPage);
    }

}

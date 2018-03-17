package com.example.marta.blueteeth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Student_Confirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__confirmation);
    }

    public void testMethod(View view) {
        Intent switchPage = new Intent(Student_Confirmation.this, StudentHomeActivity.class);
        startActivity(switchPage);
    }
}

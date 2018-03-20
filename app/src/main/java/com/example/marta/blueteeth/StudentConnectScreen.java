package com.example.marta.blueteeth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StudentConnectScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_connect_screen);
    }

    /**
     *
     * @param view
     * Gives intent to switch pages
     */
    public void testMethod(View view) {
        // Sets intent switchPage to go from StudentConnectScreen to Student_Activity_Step1
        Intent switchPage = new Intent(StudentConnectScreen.this, Student_Activity_Step1.class);
        // Activates page switch
        startActivity(switchPage);
    }

}

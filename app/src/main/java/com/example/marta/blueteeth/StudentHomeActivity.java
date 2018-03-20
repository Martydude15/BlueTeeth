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
    }

    /**
     *
     * @param view
     * Gives intent to switch pages
     */
    public void testMethod(View view) {
        // Sets intent switchPage to go from StudentHomeActivity to StudentConnectScreen
        Intent switchPage = new Intent(StudentHomeActivity.this, StudentConnectScreen.class);
        // Activates page switch
        startActivity(switchPage);
    }
}

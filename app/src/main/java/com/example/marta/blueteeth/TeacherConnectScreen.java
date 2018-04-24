package com.example.marta.blueteeth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.marta.domain.Bluetooth;

public class TeacherConnectScreen extends AppCompatActivity {

    Bluetooth bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_connect_screen);
        bt = new Bluetooth(this);
        bt.on();
        bt.discover();
    }

    /**
     *
     * @param view
     * Gives intent to switch pages
     */
    public void testMethod(View view) {
        // Sets intent switchPage to go from TeacherConnectScreen activity to TeacherPhoto activity
        Intent switchPage = new Intent(TeacherConnectScreen.this, MainActivity.class);
        // Activates page switch
        startActivity(switchPage);
    }
}

package com.example.marta.blueteeth;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.marta.domain.Bluetooth;
import com.example.marta.domain.Student;

public class StudentConnectScreen extends AppCompatActivity {

    public BluetoothAdapter btAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_connect_screen);
        Bluetooth bluetooth = new Bluetooth(BluetoothAdapter.getDefaultAdapter(), this);
        bluetooth.on();
        Student student = getIntent().getParcelableExtra("user");
        try {
            bluetooth.discoverable(student.getFirstName());
        } catch (Exception e) {
            new DialogBox(e.toString(), this);
        }
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

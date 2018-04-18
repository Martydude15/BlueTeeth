package com.example.marta.blueteeth;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class StudentConnectScreen extends AppCompatActivity {

    public BluetoothAdapter btAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_connect_screen);
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (btAdapter != null) {
            if (!btAdapter.isEnabled())
            {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                Log.d("Blueteeth", "Turning bluetooth on.");
                startActivity(enableBtIntent);
            } else
            {
                new DialogBox("Bluetooth is already on.", StudentConnectScreen.this);
            }
        } else {
            new DialogBox("Bluetooth is not available on this device.", StudentConnectScreen.this);
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

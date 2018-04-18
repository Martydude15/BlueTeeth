package com.example.marta.blueteeth;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.marta.domain.Bluetooth;

public class TeacherConnectScreen extends AppCompatActivity {

    public BluetoothAdapter btAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_connect_screen);
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (btAdapter != null) {
            if (!btAdapter.isEnabled())
            {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 0);
            } else
            {
                new DialogBox("Bluetooth is already on.", TeacherConnectScreen.this);
            }
        } else {
            new DialogBox("Bluetooth is not available on this device.", TeacherConnectScreen.this);
        }
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

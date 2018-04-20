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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_connect_screen);
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        on(btAdapter);
        discoverable("TEST", btAdapter);
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

    public void on(BluetoothAdapter btAdapter) {
        if (btAdapter != null) {
            if (!btAdapter.isEnabled())
            {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                Log.d("Blueteeth", "Turning bluetooth on.");
                startActivity(enableBtIntent);
            } else
            {
                new DialogBox("Bluetooth is already on.", this);
            }
        } else {
            new DialogBox("Bluetooth is not available on this device.", this);
        }
    }

    public void discoverable(String name, BluetoothAdapter btAdapter) {
        if (btAdapter != null) {
            btAdapter.setName(name);
            Intent discover = new Intent(btAdapter.ACTION_REQUEST_DISCOVERABLE);
            discover.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(discover);
        }
    }
}

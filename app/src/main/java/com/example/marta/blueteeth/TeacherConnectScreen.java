package com.example.marta.blueteeth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.example.marta.domain.Bluetooth;

public class TeacherConnectScreen extends AppCompatActivity {

    private final static int REQUEST_ENABLE_BT = 1;
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_connect_screen);
        registerReceiver(broadcastReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        on(btAdapter);
        discover(btAdapter);
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

    public void on(BluetoothAdapter btAdapter) {
        if (btAdapter != null) {
            if (!btAdapter.isEnabled())
            {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                Log.d("Blueteeth", "Turning bluetooth on.");
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            } else
            {
                new DialogBox("Bluetooth is already on.", this);
            }
        } else {
            new DialogBox("Bluetooth is not available on this device.", this);
        }
    }

    public void discover(BluetoothAdapter btAdapter) {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if(BluetoothDevice.ACTION_FOUND.equals(action)) {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    new DialogBox(device.getName() + "\t" + device.getAddress(), context);
                }
            }
        };
        if (btAdapter.isDiscovering()) {
            new DialogBox("Already discovering.", this);
            btAdapter.cancelDiscovery();
        } else {
            new DialogBox("Starting discovery.", this);
            btAdapter.startDiscovery();
        }
    }
}

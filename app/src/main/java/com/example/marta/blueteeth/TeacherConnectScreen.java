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
import android.widget.ArrayAdapter;
import android.widget.Toast;

import static android.bluetooth.BluetoothDevice.ACTION_FOUND;

public class TeacherConnectScreen extends AppCompatActivity {

    private final static int REQUEST_ENABLE_BT = 1;
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_connect_screen);
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
        if (btAdapter.isDiscovering()) {
            btAdapter.cancelDiscovery();
            btAdapter.startDiscovery();
            IntentFilter discoverDevice = new IntentFilter(ACTION_FOUND);
            registerReceiver(broadcastReceiver, discoverDevice);
        } else {
            btAdapter.startDiscovery();
            IntentFilter discoverDevice = new IntentFilter(ACTION_FOUND);
            registerReceiver(broadcastReceiver, discoverDevice);
        }
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if(ACTION_FOUND.equals(action)) {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    Toast.makeText(context, "Showing Unpaired Device: " +
                            device.getName() + "\t" + device.getAddress(), Toast.LENGTH_LONG).show();
                }
            }
        };
    }
}

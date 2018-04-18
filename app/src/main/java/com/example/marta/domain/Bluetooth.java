package com.example.marta.domain;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;
import java.util.UUID;

public class Bluetooth extends AppCompatActivity implements Runnable {

    private static

    final UUID uuid = UUID.fromString("67d338c8-42a1-11e8-842f-0ed5f89f718b");
    public BluetoothServerSocket btServer;
    public BluetoothServerSocket btServer2;

    public void off(BluetoothAdapter btAdapter) {
        btAdapter.disable();
        Log.d("Blueteeth", "Turning bluetooth off.");
    }

    public Intent discoverable(BluetoothAdapter btAdapter) {
        Intent discover = new Intent(btAdapter.ACTION_REQUEST_DISCOVERABLE);
        discover.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        return discover;
    }


    public void acceptConnect(BluetoothAdapter btAdapter) {
        btServer = null;
        try {
            btServer = btAdapter.listenUsingRfcommWithServiceRecord("BLUETEETH", uuid);
        } catch (Exception e) { }
        btServer2 = btServer;
    }

    public void run() {
        BluetoothSocket sock = null;
        while (true) {
            try {
                sock = btServer2.accept();
            } catch (IOException ioe) {
                break;
            }
        }
    }
}

package com.example.marta.domain;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.marta.blueteeth.DialogBox;
import com.example.marta.blueteeth.TeacherConnectScreen;

import java.io.IOException;
import java.util.UUID;

public class Bluetooth implements Runnable {

    final UUID uuid = UUID.fromString("67d338c8-42a1-11e8-842f-0ed5f89f718b");
    private Context context;
    private BluetoothAdapter btAdapter;
    public BluetoothServerSocket btServer;
    public BluetoothServerSocket btServer2;

    public Bluetooth(BluetoothAdapter btAdapter, Context context) {
        this.context = context;
        this.btAdapter = btAdapter;
    }

    public void on() {
        if (btAdapter != null) {
            if (!btAdapter.isEnabled())
            {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                Log.d("Blueteeth", "Turning bluetooth on.");
                context.startActivity(enableBtIntent);
            } else
            {
                new DialogBox("Bluetooth is already on.", context);
            }
        } else {
            new DialogBox("Bluetooth is not available on this device.", context);
        }
    }

    public void off() {
        btAdapter.disable();
        Log.d("Blueteeth", "Turning bluetooth off.");
    }

    public void discoverable(Context context) {
        Intent discover = new Intent(btAdapter.ACTION_REQUEST_DISCOVERABLE);
        discover.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        context.startActivity(discover);
    }


    public void acceptConnect() {
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

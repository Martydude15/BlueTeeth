package com.example.marta.domain;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.marta.blueteeth.DialogBox;

import java.io.IOException;
import java.util.UUID;

public class Bluetooth extends AppCompatActivity implements Runnable {

    final UUID uuid = UUID.fromString("67d338c8-42a1-11e8-842f-0ed5f89f718b");
    public BluetoothAdapter btAdapter;
    public BluetoothServerSocket btServer;
    public BluetoothServerSocket btServer2;

    public Bluetooth(BluetoothAdapter btAdapter) {
        this.btAdapter = btAdapter.getDefaultAdapter();
    }

    public void on(View v) {
        Intent enableBtIntent;
        if (!btAdapter.isEnabled())
        {
            enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 0);
        } else
        {
            new DialogBox("Bluetooth is already on.", v.getContext());
        }
    }

    public void off(View v) {
        btAdapter.disable();
        new DialogBox("Bluetooth is off.", v.getContext());
    }

    public void discoverable(View v) {
        Intent discover = new Intent(btAdapter.ACTION_REQUEST_DISCOVERABLE);
        discover.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivityForResult(discover, 1);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            acceptConnect();
            run();
        }
    }
}

package com.example.marta.domain;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.example.marta.blueteeth.DialogBox;
import java.util.UUID;

public class Bluetooth  {

    final UUID uuid = UUID.fromString("67d338c8-42a1-11e8-842f-0ed5f89f718b");
    private Context context;
    private BluetoothAdapter btAdapter;
    public BroadcastReceiver broadcastReceiver;

    public Bluetooth(BluetoothAdapter btAdapter, Context context) {
        this.context = context;
        this.btAdapter = btAdapter;
        context.registerReceiver(broadcastReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
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

    public void discoverable(String name) {
        btAdapter.setName(name);
        Intent discover = new Intent(btAdapter.ACTION_REQUEST_DISCOVERABLE);
        discover.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        context.startActivity(discover);
    }

    public void discover() {
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
    }

}

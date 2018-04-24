package com.example.marta.domain;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bluetooth  {

//    final UUID uuid = UUID.fromString("67d338c8-42a1-11e8-842f-0ed5f89f718b");
    private Context context;
    private Activity activity;

    private final static int REQUEST_ENABLE_BT = 1;
    private BluetoothAdapter btAdapter;
    private List<BluetoothDevice> devices = new ArrayList<>();

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context1, Intent intent) {
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String name = device.getName();
                String address = device.getAddress();
                if (name != null) {
                    devices.add(device);
                }
            }
        }
    };

    public Bluetooth(Context context) {
        this.context = context;
        this.activity = (Activity) context;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            BluetoothManager btManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
            this.btAdapter = btManager.getAdapter();
        } else {
            this.btAdapter = BluetoothAdapter.getDefaultAdapter();
        }
    }

    public void on() {
        if (btAdapter != null) {
            if (!btAdapter.isEnabled())
            {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                this.activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            } else
            {
                Toast.makeText(this.context,"Bluetooth is already on.", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this.context,"Bluetooth is not available on this device.", Toast.LENGTH_LONG).show();
        }
    }

    public void off() {
        this.btAdapter.disable();
    }

    public void discoverable(String name) {
        if (btAdapter != null) {
            checkBtPermissions();
            btAdapter.setName(name);
            Intent discover = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discover.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            this.activity.startActivity(discover);
            Toast.makeText(this.context, "Device is discoverable.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this.context, "Had an error here.", Toast.LENGTH_LONG).show();
        }
    }


    public void discover() {
        if (btAdapter.isDiscovering()) {
            btAdapter.cancelDiscovery();
            checkBtPermissions();
            btAdapter.startDiscovery();
            Toast.makeText(this.context,"Starting discovery.", Toast.LENGTH_LONG).show();
            IntentFilter discoverDevice = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            this.context.registerReceiver(broadcastReceiver, discoverDevice);
        } else {
            checkBtPermissions();
            btAdapter.startDiscovery();
            Toast.makeText(this.context,"Starting discovery.", Toast.LENGTH_LONG).show();
            IntentFilter discoverDevice = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            this.context.registerReceiver(broadcastReceiver, discoverDevice);
        }
    }

    public void showDevices() {
        for (BluetoothDevice device: devices) {
            String name = device.getName();
            String address = device.getAddress();
            Toast.makeText(context, "Showing Unpaired Device: " +
                        name + " " + address, Toast.LENGTH_LONG).show();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkBtPermissions() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            int permissionCheck = this.context.checkSelfPermission("Manifest.permission.ACCESS_FINE_LOCATION");
            permissionCheck += this.context.checkSelfPermission("Manifest.permission.ACCESS_FINE_LOCATION");
            if (permissionCheck != 0) {
                this.activity.requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1001);
            }
        }
    }

}

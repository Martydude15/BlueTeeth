package com.example.marta.domain;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
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

public class Bluetooth  {

    private Context context;
    private Activity activity;

    private final static int REQUEST_ENABLE_BT = 1;
    private BluetoothAdapter btAdapter;
    private List<BluetoothDevice> devices = new ArrayList<>();

    /**
     *  Catches all of the devices that are currently discoverable.
     */
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
                    Toast.makeText(context, "Showing Unpaired Device: " +
                            name + " " + address, Toast.LENGTH_LONG).show();
                }
            }
        }
    };

    /**
     *
     * @param context
     *      Takes in the activity to apply the action to it.
     */
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

    /**
     * Turns the bluetooth on
     */
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

    /**
     * Turns the bluetooth off
     */
    public void off() {
        this.btAdapter.disable();
    }

    /**
     * Makes the device discoverable
     * @param name
     *      Sets the name of the device to be discovered
     */
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

    /**
     * Makes the device discover all discoverable bluetooth devices in range.
     */
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

    /**
     * Turns the broadcast receiver off.
     */
    public void unregister() {
        if (btAdapter.isDiscovering()) {
            btAdapter.cancelDiscovery();
        }
        this.activity.unregisterReceiver(broadcastReceiver);
    }

    /**
     * @return
     *      Just returns whether the device is discovering or not.
     */
    public Boolean discovering() {
        return btAdapter.isDiscovering();
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

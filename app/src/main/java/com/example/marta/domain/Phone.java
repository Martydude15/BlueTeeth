package com.example.marta.domain;

/**
 * Created by Blake on 3/13/2018.
 */

public class Phone {
    private String macAddress;
    boolean cameraOn, bluetoothOn, locat_On;

    Phone(String macAddress){
        this.macAddress = macAddress;
    }
    /////////   GETTERS   ///////////

    private String getMacAddress(){
        return macAddress;
    }
    private boolean isCameraOn(){
        return false;
    }
    private boolean isBluetoothOn(){
        return false;
    }
    private boolean isLoc_On(){
        return false;
    }
    /////// SETTERS //////////////

    void setCameraOn(boolean cameraOn){
        this.cameraOn = cameraOn;
    }
    void setBluetoothOn(boolean bluetoothOm){
        this.bluetoothOn = bluetoothOn;
    }
    void setLocat_On(boolean locat_On){
        this.locat_On = locat_On;
    }
    void setMacAddress(String macAddress){
        this.macAddress = macAddress;
    }
}

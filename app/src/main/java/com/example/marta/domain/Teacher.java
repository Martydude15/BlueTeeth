package com.example.marta.domain;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Blake on 3/9/2018.
 */

public class Teacher extends Person implements Parcelable{

    private String passCode;

    Teacher(String firstName, String middleName, String lastName, String jagNumber, String passCode) {
        super(firstName, middleName, lastName, jagNumber);
        setPassCode(passCode);
    }

    Teacher (Parcel parcel) {
        super(parcel);
        this.passCode = parcel.readString();
    }

    Teacher() {
        super();
        this.passCode = "";
    }

    public static final Creator<Teacher> CREATOR = new Creator<Teacher>() {
        @Override
        public Teacher createFromParcel(Parcel in) {
            return new Teacher(in);
        }

        @Override
        public Teacher[] newArray(int size) {
            return new Teacher[size];
        }
    };

    public String getPassCode() {
        /**
         * @TODO: Need to add code here to take care of empty string. Should not allow.
         */
        if (passCode.equals("")) {
            System.out.println("Password is empty");
        }
        return passCode;
    }
    private void setPassCode(String passCode){
       this.passCode = passCode;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(passCode);
    }
}
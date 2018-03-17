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
        this.passCode = passCode;
    }

    /**
     *
     * @param parcel
     *      This is to help with passing around between activities
     */
    private Teacher(Parcel parcel) {
        super(parcel);
        this.passCode = parcel.readString();
    }

    /**
     * This just takes and creates a new object when passed between the activities.
     */
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

    /**
     *
     * @return
     *      the password.
     */
    public String getPassCode() {
        /**
         * @TODO: Need to add code here to take care of empty string. Should not allow.
         */
        if (passCode.equals("")) {
            throw new IllegalArgumentException();
        }
        return passCode;
    }
    private void setPassCode(String passCode){
       this.passCode = passCode;
    }

    /**
     *
     * @return
     *      The hashcode for checking when unpacking.
     */
    @Override
    public int describeContents() {
        return hashCode();
    }

    /**
     *
     * @param dest
     *      target view to send object to.
     * @param flags
     *      for flags, if flags needed to be set.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(passCode);
    }
}
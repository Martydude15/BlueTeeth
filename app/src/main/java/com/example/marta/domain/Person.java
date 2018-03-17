package com.example.marta.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Blake on 3/9/2018.
 */

public class Person implements Parcelable{
    private String jagNumber;
    private String firstName, middleName, lastName;

    Person(String firstName, String middleName, String lastName, String jagNumber){
           this.firstName = firstName;
           this.middleName = middleName;
           this.lastName = lastName;
           this.jagNumber = jagNumber;
    }

    /**
     *
     * @param parcel
     *      This is to help with passing around between activities
     */
    Person (Parcel parcel) {
        this.firstName = parcel.readString();
        this.middleName = parcel.readString();
        this.lastName = parcel.readString();
        this.jagNumber = parcel.readString();
    }

    public String getFirstName() {
            return firstName;
        }
    public String getMiddleName(){
            return middleName;
        }
    public String getLastName(){
            return lastName;
        }
    public String getJagNumber(){
            return jagNumber;
        }

    /**
     * This just takes and creates a new object when passed between the activities.
     */
    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

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
        dest.writeString(firstName);
        dest.writeString(middleName);
        dest.writeString(lastName);
        dest.writeString(jagNumber);
    }
}

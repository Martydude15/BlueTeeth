package com.example.marta.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Blake on 3/9/2018.
 */

public class Person implements Parcelable{
    private String jagNumber;
    private String firstName, middleName, lastName;

    public Person(String firstName, String middleName, String lastName, String jagNumber){
           this.firstName = firstName;
           this.middleName = middleName;
           this.lastName = lastName;
           this.jagNumber = jagNumber;
    }

    Person() {
        this.firstName = "";
        this.middleName = "";
        this.lastName = "";
        this.jagNumber = "";
    }

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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private void setJagNumber(String jagNumber) {
        this.jagNumber = jagNumber;
    }

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

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(middleName);
        dest.writeString(lastName);
        dest.writeString(jagNumber);
    }
}

package com.example.marta.domain;

import android.os.Parcelable;

import org.json.JSONObject;

/**
 * Created by Blake on 3/9/2018.
 */

public class Student extends Person implements Parcelable {

    public Student(String firstName, String middleName, String lastName, String jagNumber)
   {
       super(firstName,middleName,lastName,jagNumber);
   }

    public String toString() {
        return getFirstName() + " " + getMiddleName() + " " + getLastName();
    }

}

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

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("class", "Student");
            jsonObject.put("firstName", getFirstName());
            jsonObject.put("middleName", getMiddleName());
            jsonObject.put("lastName", getLastName());
            jsonObject.put("jagNumber", getJagNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public String toString() {
        return getFirstName() + " " + getMiddleName() + " " + getLastName();
    }

}

package com.example.marta.domain;

import android.os.Parcelable;

import org.json.JSONObject;

/**
 * Created by Blake on 3/9/2018.
 */

public class Student extends Person implements Parcelable {

    Student(String firstName, String middleName, String lastName, String jagNumber)
   {
       super(firstName,middleName,lastName,jagNumber);
   }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("class", "Student");
            jsonObject.put("middleName", getFirstName());
            jsonObject.put("lastName", getMiddleName());
            jsonObject.put("jagNumber", getLastName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}

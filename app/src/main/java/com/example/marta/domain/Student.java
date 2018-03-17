package com.example.marta.domain;

import android.os.Parcelable;

/**
 * Created by Blake on 3/9/2018.
 */

public class Student extends Person implements Parcelable {

    Student(String firstName, String middleName, String lastName, String jagNumber)
   {
       super(firstName,middleName,lastName,jagNumber);
   }

}

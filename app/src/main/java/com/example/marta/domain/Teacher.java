package com.example.marta.domain;

/**
 * Created by Blake on 3/9/2018.
 */

public class Teacher extends Person {

    private String passCode;

    Teacher(String firstName, String middleName, String lastName, String jagNumber, String passCode) {
        super(firstName, middleName, lastName, jagNumber);

        this.passCode = passCode;
        /**
         * @TODO: Need to add code here to take care of empty string. Should not allow.
         */
//        if (passCode.equals("")) {
//            throw new IllegalArgumentException();
//        }
    }

    public String getPassCode() {
        return passCode;
    }
    private void setPassCode(String passCode){
       this.passCode = passCode;
    }
}
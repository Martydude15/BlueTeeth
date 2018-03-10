package com.example.marta.blueteeth.person;

/**
 * Created by Blake on 3/9/2018.
 */

public class Teacher extends Person {

    private String passCode;

    Teacher(String firstName, String middleName, String lastName, String jagNumber, String passCode) {
        super(firstName, middleName, lastName, jagNumber);

        this.passCode = passCode;
    }

    public String getPassCode() {
        return passCode;
    }
    private void setPassCode(String passCode){
       this.passCode = passCode;
    }
}
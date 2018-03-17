package com.example.marta.domain;

/**
 * Created by Blake on 3/9/2018.
 */

public class Person {
    private String jagNumber;
    private String firstName, middleName, lastName;

    public Person(String firstName, String middleName, String lastName, String jagNumber){
           this.firstName = firstName;
           this.middleName = middleName;
           this.lastName = lastName;
           this.jagNumber = jagNumber;
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

}

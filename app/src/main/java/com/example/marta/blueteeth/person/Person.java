package com.example.marta.blueteeth.person;

/**
 * Created by Blake on 3/9/2018.
 */

public class Person {
    private String jagNumber;
    private String firstName, middleName, lastName;

    Person(){

    }

    Person (String firstName, String middleName,String lastName,String jagNumber){
           this.firstName = firstName;
           this.middleName = middleName;
           this.lastName = lastName;
           this.jagNumber = jagNumber;
    }
    ///////     GETTERS     /////////
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

    ///////     SETTERS     /////////
        public void setFirstName(String firstName){
            this.firstName = firstName;
        }
        public void setMiddleName(String middleName){
            this.middleName = middleName;
        }
        public void setLastName(String lastName){
            this.lastName = lastName;
        }
        public void setJagNumber(String jagNumber){
            this.jagNumber = jagNumber;
        }



}

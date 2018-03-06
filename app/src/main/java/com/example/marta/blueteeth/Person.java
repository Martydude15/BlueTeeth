package com.example.marta.blueteeth;

class Person {

    private final String jagNumber;
    private final Boolean aTeacher;

    Person(String jagNumber, Boolean aTeacher) {
        this.jagNumber = jagNumber;
        this.aTeacher = aTeacher;
    }

    Person() {
        jagNumber = "55";
        aTeacher = false;
    }

    Boolean isTeacher() {
        return this.aTeacher;
    }

    String getJagNumber() {
        return this.jagNumber;
    }
}

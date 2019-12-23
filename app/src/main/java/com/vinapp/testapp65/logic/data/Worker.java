package com.vinapp.testapp65.logic.data;

public class Worker {

    private String firstName;
    private String lastName;
    private String birthday;
    private Specialty specialty;
    private int age;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public int getAge() {
        return age;
    }
}

package com.vinapp.testapp65.logic.data;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Worker {

    private String firstName;
    private String lastName;
    private String birthday;
    private Specialty specialty;
    private int age;
    private String avatarUrl;

    public void setFirstName(String firstName) {
        this.firstName = formatString(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = formatString(lastName);
    }

    public void setBirthday(String birthday) {
        birthday = birthday.replace('-', '.');
        if (birthday.isEmpty() || birthday.equals("null") || birthday == null) {
            this.birthday = "-";
        } else {
            String wrongPattern = "yyyy.MM.dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(wrongPattern);
            simpleDateFormat.setLenient(false);
            try {
                Log.e("DATE", birthday);
                if (simpleDateFormat.parse(birthday) != null) {
                    Log.e("DATE----", birthday);
                    String correctPattern = "dd.MM.yyyy";
                    Date date = simpleDateFormat.parse(birthday);
                    simpleDateFormat.applyPattern(correctPattern);
                    birthday = simpleDateFormat.format(date);
                }
            } catch (Exception exc) {
                Log.e("EXC", exc.toString());
            }
            this.birthday = birthday;
        }
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    private String formatString(String string) {
        string = string.trim();
        string = string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
        return string;
    }
}

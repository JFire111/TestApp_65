package com.vinapp.testapp65.logic.data;

import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Worker {

    private String firstName;
    private String lastName;
    private String birthday;
    private Specialty specialty;
    private Integer age;
    private String avatarUrl;
    private BitmapDrawable avatar;

    public void setFirstName(String firstName) {
        this.firstName = formatNameString(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = formatNameString(lastName);
    }

    public void setBirthday(String birthday) {
        birthday = birthday.replace('-', '.');
        String wrongPattern = "yyyy.MM.dd";
        String correctPattern = "dd.MM.yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(wrongPattern);
        simpleDateFormat.setLenient(false);
        try {
            Log.e("DATE", birthday);
            if (simpleDateFormat.parse(birthday) != null) {
                Log.e("DATE----", birthday);
                Date date = simpleDateFormat.parse(birthday);
                simpleDateFormat.applyPattern(correctPattern);
                birthday = simpleDateFormat.format(date);
            }
        } catch (Exception exc) {
            try {
                simpleDateFormat.applyPattern(correctPattern);
                simpleDateFormat.parse(birthday);
            } catch (Exception e) {
                birthday = "-";
            }
        }
        this.birthday = birthday;
        calculateAge(birthday);
    }


    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    private void calculateAge(String birthday) {
        Integer age;
        String dateFormat = "dd.MM.yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        SimpleDateFormat month = new SimpleDateFormat("MM");
        try {
            Date currentDate = new Date();
            Date birthdayDate = simpleDateFormat.parse(birthday);
            age = Integer.valueOf(year.format(currentDate)) - Integer.valueOf(year.format(birthdayDate));
            if (Integer.valueOf(month.format(currentDate)) < Integer.valueOf(month.format(birthdayDate))) {
                age = age - 1;
            }
            if (Integer.valueOf(month.format(currentDate)) == Integer.valueOf(month.format(birthdayDate))) {
                SimpleDateFormat day = new SimpleDateFormat("dd");
                if (Integer.valueOf(day.format(currentDate)) < Integer.valueOf(day.format(birthdayDate))) {
                    age = age - 1;
                }
            }
        } catch (Exception exc) {
            age = null;
        }
        this.age = age;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setAvatar(BitmapDrawable avatar) {
        this.avatar = avatar;
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

    public Integer getAge() {

        return age;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public BitmapDrawable getAvatar() {
        return avatar;
    }

    private String formatNameString(String string) {
        string = string.trim();
        string = string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
        return string;
    }
}

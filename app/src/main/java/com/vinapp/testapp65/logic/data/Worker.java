package com.vinapp.testapp65.logic.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Worker {

    private String firstName;
    private String lastName;
    private String birthday;
    private Specialty specialty;
    private Integer age;
    private String avatarUrl;

    public void setFirstName(String firstName) {
        this.firstName = formatNameString(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = formatNameString(lastName);
    }

    //Устанавливаем дату рождения в виде строки(день.месяц.год)
    public void setBirthday(String birthday) {
        birthday = birthday.replace('-', '.');
        String wrongPattern = "yyyy.MM.dd";
        String correctPattern = "dd.MM.yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(wrongPattern);
        simpleDateFormat.setLenient(false);
        try {
            //Если полученая на входе строка совпадает с неправильным форматом(год.месяц.день),
            //то приводим ее к правильному формату(день.месяц.год).
            if (simpleDateFormat.parse(birthday) != null) {
                Date date = simpleDateFormat.parse(birthday);
                simpleDateFormat.applyPattern(correctPattern);
                birthday = simpleDateFormat.format(date);
            }
        } catch (Exception exc) {
            try {
                //Если при сравнивании вылетает исключение, то сравниваем строку с правильным форматом
                //Если снова вылетит исключение значит строка не подходит ни под один из форматов, по этому устанавливаем birthday = "-"
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

    //Вычисляем возраст
    private void calculateAge(String birthday) {
        Integer age;
        String dateFormat = "dd.MM.yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        SimpleDateFormat month = new SimpleDateFormat("MM");
        try {
            Date currentDate = new Date();
            Date birthdayDate = simpleDateFormat.parse(birthday);
            //Вычисляем количество лет между текущим годом и годом рождения
            age = Integer.valueOf(year.format(currentDate)) - Integer.valueOf(year.format(birthdayDate));
            //Если текущий месяц меньше месяца рождения, то вычитаем от возраста один год
            if (Integer.valueOf(month.format(currentDate)) < Integer.valueOf(month.format(birthdayDate))) {
                age = age - 1;
            }
            if (Integer.valueOf(month.format(currentDate)) == Integer.valueOf(month.format(birthdayDate))) {
                SimpleDateFormat day = new SimpleDateFormat("dd");
                //Если текущий день меньше месяца дня рождения, то вычитаем от возраста один год
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

    //Форматируем строку, для отображения строки(имени) с заглавной буквы
    private String formatNameString(String string) {
        string = string.trim();
        string = string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
        return string;
    }
}

package com.vinapp.testapp65.logic.data;

public class Specialty {

    private int id;
    private String name;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = formatString(name);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //Форматируем строку, для отображения строки(названия специальности) с заглавной буквы
    private String formatString(String string) {
        string = string.trim();
        string = string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
        return string;
    }
}

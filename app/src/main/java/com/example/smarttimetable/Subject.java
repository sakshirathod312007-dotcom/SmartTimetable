package com.example.smarttimetable;

public class Subject {

    String name;
    int hours;

    public Subject(String name, int hours) {
        this.name = name;
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public int getHours() {
        return hours;
    }
}
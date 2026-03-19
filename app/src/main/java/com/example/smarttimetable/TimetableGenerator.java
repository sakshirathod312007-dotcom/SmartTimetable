package com.example.smarttimetable;

import java.util.Random;

public class TimetableGenerator {

    String[] subjects = {
            "Maths",
            "DBMS",
            "Java",
            "CN",
            "Python",
            "AI"
    };

    String[][] timetable = new String[5][6];

    public String[][] generateTimetable(){

        Random random = new Random();

        for(int i=0;i<5;i++){

            for(int j=0;j<6;j++){

                int index = random.nextInt(subjects.length);

                timetable[i][j] = subjects[index];
            }
        }

        return timetable;
    }
}
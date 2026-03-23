package com.example.smarttimetable;

import java.util.*;

public class TimetableGenerator {

    public String[][] generate(List<Subject> subjects) {

        String[][] timetable = new String[6][6];

        List<String> pool = new ArrayList<>();

        // create pool
        for (Subject s : subjects) {
            for (int i = 0; i < s.getHours(); i++) {
                pool.add(s.getName());
            }
        }

        Collections.shuffle(pool);

        int index = 0;

        for (int i = 0; i < 6; i++) {

            Set<String> usedToday = new HashSet<>();

            for (int j = 0; j < 6; j++) {

                if (j == 2) {
                    timetable[i][j] = "LUNCH";
                    continue;
                }

                if (index >= pool.size()) {
                    index = 0; // 🔥 restart to avoid FREE
                }

                String subject = pool.get(index);

                int attempts = 0;
                while (usedToday.contains(subject) && attempts < 10) {
                    index++;
                    if (index >= pool.size()) index = 0;
                    subject = pool.get(index);
                    attempts++;
                }

                timetable[i][j] = subject;
                usedToday.add(subject);
                index++;
            }
        }

        return timetable;
    }
}
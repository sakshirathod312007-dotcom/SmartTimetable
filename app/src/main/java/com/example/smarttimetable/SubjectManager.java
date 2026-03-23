package com.example.smarttimetable;

import java.util.ArrayList;
import java.util.List;

public class SubjectManager {

    public static List<Subject> getSubjects(String sem) {

        List<Subject> list = new ArrayList<>();

        // ✅ SEM 1
        if (sem.equals("1")) {
            list.add(new Subject("BMS", 6));
            list.add(new Subject("ENG", 5));
            list.add(new Subject("BSC", 6));
            list.add(new Subject("ICT", 3));
            list.add(new Subject("WPC", 4));
            list.add(new Subject("YAM", 2));
            list.add(new Subject("EGP", 8));
        }

        // ✅ SEM 2
        else if (sem.equals("2")) {
            list.add(new Subject("AMS", 4));
            list.add(new Subject("BEE", 8));
            list.add(new Subject("PIC", 9));
            list.add(new Subject("BLP", 4));
            list.add(new Subject("PCO", 2));
            list.add(new Subject("SFS", 2));
            list.add(new Subject("WPD", 6));
        }

        // ✅ SEM 3
        else if (sem.equals("3")) {
            list.add(new Subject("DMS", 8));
            list.add(new Subject("DTE", 5));
            list.add(new Subject("DSP", 7));
            list.add(new Subject("SML", 8));
            list.add(new Subject("EIC", 1));
            list.add(new Subject("DST", 4));
        }

        // ✅ SEM 4
        else if (sem.equals("4")) {
            list.add(new Subject("EES", 3));
            list.add(new Subject("JPR", 8));
            list.add(new Subject("DCN", 7));
            list.add(new Subject("MML", 6));
            list.add(new Subject("MIC", 5));
            list.add(new Subject("UID", 5));
        }

        // ✅ SEM 5 (Elective: CCD used)
        else if (sem.equals("5")) {
            list.add(new Subject("OSY", 7));
            list.add(new Subject("AMA", 8));
            list.add(new Subject("ENDS", 3));
            list.add(new Subject("SPI", 1));
            list.add(new Subject("CCD", 6)); // elective
        }

        // ✅ SEM 6 (Elective: DWM used)
        else if (sem.equals("6")) {
            list.add(new Subject("MAN", 3));
            list.add(new Subject("BDA", 7));
            list.add(new Subject("PIP", 3));
            list.add(new Subject("MAD", 6));
            list.add(new Subject("NMA", 6));
            list.add(new Subject("CPE", 2));
            list.add(new Subject("DWM", 5)); // elective
        }

        return list;
    }
}
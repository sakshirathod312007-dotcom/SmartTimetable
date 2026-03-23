package com.example.smarttimetable;

public class SubjectManager {

    public static String[] getSubjects(String sem){

        switch(sem){

            case "1":
                return new String[]{"BMS","ENG","BSC","ICT","WPC","YAM","EGP"};

            case "2":
                return new String[]{"AMS","BEE","PIC","BLP","PCO","SFS","WPD"};

            case "3":
                return new String[]{"DMS","DTE","DSP","SML","EIC","DST"};

            case "4":
                return new String[]{"EES","JPR","DCN","MML","MIC","UID"};

            case "5":
                return new String[]{"OSY","AMA","ENDS","SPI","ADM"}; // elective

            case "6":
                return new String[]{"MAN","BDA","PIP","MAD","NMA","CPE"};

            default:
                return new String[]{"SUB1","SUB2","SUB3"};
        }
    }

    public static String[] getHeavy(String sem){
        switch(sem){
            case "1": return new String[]{"BMS","BSC"};
            case "2": return new String[]{"AMS","BEE","PIC"};
            case "3": return new String[]{"DMS","SML"};
            case "4": return new String[]{"MML","JPR"};
            case "5": return new String[]{"OSY","AMA"};
            case "6": return new String[]{"BDA","MAD"};
            default: return new String[]{};
        }
    }

    public static String[] getPractical(String sem){
        switch(sem){
            case "1": return new String[]{"WPC","EGP"};
            case "2": return new String[]{"PIC","BEE","WPD"};
            case "3": return new String[]{"DSP","DMS","SML"};
            case "4": return new String[]{"JPR","DCN","UID"};
            case "5": return new String[]{"AMA","ADM","SPI"};
            case "6": return new String[]{"MAD","NMA","CPE"};
            default: return new String[]{};
        }
    }

    public static String[] getLight(String sem){
        switch(sem){
            case "1": return new String[]{"YAM"};
            case "2": return new String[]{"SFS","PCO"};
            case "3": return new String[]{"EIC"};
            case "4": return new String[]{"EES"};
            case "5": return new String[]{"ENDS"};
            case "6": return new String[]{"MAN"};
            default: return new String[]{};
        }
    }
}
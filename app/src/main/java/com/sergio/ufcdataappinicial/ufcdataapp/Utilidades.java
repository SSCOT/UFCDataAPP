package com.sergio.ufcdataappinicial.ufcdataapp;

public class Utilidades {
    public Utilidades() {
    }

    public static String changeDate(String date) {
        String newDate;
        if (date.length() > 9)
            newDate = date.substring(0,10);
        else
            newDate = date;
        return newDate;
    }
}

package com.sergio.ufcdataappinicial.ufcdataapp;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utilidades {
    public Utilidades() {
    }

    public static String changeDate (String date) {
        String newDate = null;
        if (date != null) {
            if (date.length() > 9)
                newDate = date.substring(0, 10);
            else
                newDate = date;
        }
        return newDate;
    }

    public static void messageWithOk (Context context, View view, String message){
        Snackbar snck = Snackbar.make(view, message , Snackbar.LENGTH_INDEFINITE)
                .setActionTextColor(context.getResources().getColor(R.color.white))
                .setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
        snck.getView().setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
        snck.show();
    }

    public static int getFechaActualInt() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.US);
        Date date = new Date();
        String fecha = dateFormat.format(date);
        return Integer.parseInt(fecha);
    }
}

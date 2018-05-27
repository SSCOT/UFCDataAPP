package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Alarms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento.Evento;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Notifications.EventNotification;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;


public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Recuperamos en bytes porque hay problemas en el paso de objetos directamente
        byte[] eventoBytes = intent.getByteArrayExtra("evento");
        // Transformamos los bytes en evento
        Evento evento = byteToObject(eventoBytes);
        new EventNotification(context).execute(evento);
    }

    private Evento byteToObject(byte[] eventoBytes) {
        Evento evento = null;
        ByteArrayInputStream bis = new ByteArrayInputStream(eventoBytes);
        ObjectInput in = null;
        try {
            in = new ObjectInputStream(bis);
            evento = (Evento) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
        }
        return evento;
    }
}

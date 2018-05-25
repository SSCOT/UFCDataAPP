package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Alarms;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento.Evento;
import com.sergio.ufcdataappinicial.ufcdataapp.R;

import java.io.Serializable;

public class AlarmReceiver  extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("*+++", "onReceive: LLAMADA A ALARMA RECIBIDA");
        Bundle event = intent.getExtras();

        Intent emptyIntent = new Intent();
        PendingIntent pi = PendingIntent.getActivity(context, 0, emptyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification mNotification = null;

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "DEFAULT_CHANNEL");
        Bitmap large = BitmapFactory.decodeFile(event.getString("img1"));
        Bitmap notSoLarge = BitmapFactory.decodeResource(context.getResources(), R.drawable.ufc_logo_white);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            CharSequence name = "my_channel";
            String Description = "This is my channel and ";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel("33", name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }

        builder.setContentTitle(event.getString("titulo"))
                .setContentText(event.getString("subtitulo"))
                .setContentInfo("INFO?")
                .setTicker("¡¡¿INFO?!!")
                .setSmallIcon(R.drawable.ufc_logo_white)
                .setLargeIcon(notSoLarge)
                .setContentIntent(pi);

        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();

        bigPictureStyle.bigPicture(notSoLarge)
                .bigLargeIcon(notSoLarge)
                .setBigContentTitle(event.getString("titulo"))
                .setSummaryText(event.getString("subtitulo"));

        builder.setStyle(bigPictureStyle);
        mNotification = builder.build();
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify (1, mNotification);
    }

}

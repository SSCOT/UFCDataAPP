package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Alarms;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
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
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Activities.MainActivity;
import com.sergio.ufcdataappinicial.ufcdataapp.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AlarmReceiver  extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        /*Log.d("*+++", "onReceive: LLAMADA A ALARMA RECIBIDA");
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
        manager.notify (1, mNotification);*/

        Bundle event = intent.getExtras();
        int NOTIFICATION_ID = 1;
        String CHANNEL_ID = "my_channel_01";

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            CharSequence name = "my_channel";
            String Description = "This is my channel and ";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }
        
        /*Bitmap myBitmap = null;
        URL url = null;
        try {
            url = new URL("http://imagec.ufc.com/http%253A%252F%252Fmedia.ufc.tv%252F%252F197%252F025087_197_UFCcom_Features_1.jpg?-mw500-mh500-tc1");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            myBitmap = BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        // Picasso.get().load("http://imagec.ufc.com/http%253A%252F%252Fmedia.ufc.tv%252F%252F197%252F025087_197_UFCcom_Features_1.jpg?-mw500-mh500-tc1").placeholder(R.drawable.reach_);
        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_logo_ufc_round)
                .setContentTitle(event.getString("titulo"))
                .setContentText(event.getString("subtitulo"))
                .setStyle(bigPictureStyle);

        Bitmap large = BitmapFactory.decodeResource(context.getResources(), R.drawable.ufc_logo_white);
        Bitmap notSoLarge = BitmapFactory.decodeResource(context.getResources(), R.drawable.ufc_logo_peq);

        bigPictureStyle.bigPicture(large)
                .bigLargeIcon(notSoLarge)
                .setBigContentTitle(event.getString("titulo"))
                .setSummaryText(event.getString("subtitulo"));


        Intent resultIntent = new Intent(context, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(resultPendingIntent);

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

}

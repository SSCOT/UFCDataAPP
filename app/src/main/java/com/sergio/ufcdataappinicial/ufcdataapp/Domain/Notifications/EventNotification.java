package com.sergio.ufcdataappinicial.ufcdataapp.Domain.Notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;

import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Evento.Evento;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Activities.EventActivity;
import com.sergio.ufcdataappinicial.ufcdataapp.Domain.Activities.MainActivity;
import com.sergio.ufcdataappinicial.ufcdataapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class EventNotification extends AsyncTask<Evento, Void, Bitmap> {
    Context context;
    private String titulo, subtitulo, urlImagen;

    Evento evento;

    public EventNotification(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected Bitmap doInBackground(Evento... params) {
        evento = params[0];
        titulo = evento.getTitulo();
        subtitulo = evento.getSubtitulo();
        urlImagen = evento.getImgPrincipal();

        try {
            return getBitmapFromUrl(urlImagen);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap result) {

        super.onPostExecute(result);
        try {
            int NOTIFICATION_ID = evento.getId();
            String CHANNEL_ID = "Events_channel";
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            // Creamos el canal
            createChannel(CHANNEL_ID, notificationManager);
            // Configuramos el intent al que iremos al pulsar
            PendingIntent resultPendingIntent = configurePendingIntent();
            // Configuramos la notificación
            NotificationCompat.Builder builder = configureNotification(result, CHANNEL_ID, resultPendingIntent);

            // Lanzamos la notificación
            notificationManager.notify(NOTIFICATION_ID, builder.build());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PendingIntent configurePendingIntent() {
        Intent resultIntent = new Intent(context, EventActivity.class);
        resultIntent.putExtra("evento", evento);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private NotificationCompat.Builder configureNotification(Bitmap result, String CHANNEL_ID, PendingIntent pendingIntent) {
        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_logo_ufc_round)
                .setLargeIcon(result)
                .setContentTitle(titulo)
                .setContentText(subtitulo)
                .setStyle(bigPictureStyle)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        Bitmap imagenPequenia = BitmapFactory.decodeResource(context.getResources(), R.drawable.ufc_logo_peq);
        bigPictureStyle.bigPicture(result)
                .bigLargeIcon(imagenPequenia)
                .setBigContentTitle(titulo)
                .setSummaryText(subtitulo + " (Tomorrow)");
        return builder;
    }

    private Bitmap getBitmapFromUrl(String param) throws IOException {
        InputStream in;
        URL url = new URL(param);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.connect();
        in = connection.getInputStream();
        Bitmap myBitmap = BitmapFactory.decodeStream(in);
        return myBitmap;
    }

    private void createChannel(String CHANNEL_ID, NotificationManager notificationManager) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence name = "Events_channel_name";
            String Description = "Channel about all event notifications";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }
    }
}

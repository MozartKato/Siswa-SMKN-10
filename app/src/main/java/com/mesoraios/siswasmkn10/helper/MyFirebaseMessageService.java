package com.mesoraios.siswasmkn10.helper;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.mesoraios.siswasmkn10.R;
import com.mesoraios.siswasmkn10.activities.SPPDetailActivity;

public class MyFirebaseMessageService extends FirebaseMessagingService {

    private static final String CHANNEL_ID = "my_notification_channel_id";
    private static final String CHANNEL_NAME = "My Notification Channel";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        if (message.getData() != null) {
            String title = message.getData().get("title");
            String body = message.getData().get("body");
            sendNotification(title, body);
        }
    }

    private void sendNotification(String title, String body) {
        // Intent untuk membuka aktivitas tertentu
        Intent intent = new Intent(this, SPPDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // PendingIntent untuk notifikasi
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        // Uri untuk suara notifikasi
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        // Membuat NotificationManager
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Membuat Notification Channel (hanya untuk API 26+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("Notification channel for my app");
            notificationManager.createNotificationChannel(channel);
        }

        // Membuat notifikasi
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.computer)
                        .setContentTitle(title)
                        .setContentText(body)
                        .setAutoCancel(true)
                        .setSound(soundUri)
                        .setContentIntent(pendingIntent);

        // Menampilkan notifikasi
        notificationManager.notify((int) System.currentTimeMillis(), notificationBuilder.build());
    }
}
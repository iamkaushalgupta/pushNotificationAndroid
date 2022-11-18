package com.example.pushnotificationimplementation;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService  extends FirebaseMessagingService {

    @Override
    public  void onMessageReceived(@NonNull RemoteMessage remoteMessage){
        super.onMessageReceived(remoteMessage);
        createNotificationChannel();
        getFireBaseMessage(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());



    }



    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "weather";
            String description = "Nothing";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("abc", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public  void getFireBaseMessage(String title, String msg){
        //creating Notification Builder
        Log.d("ABC","getFireBaseMessage"+"title: "+title+"msg"+msg);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"abc")
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle(title)
                .setContentText(msg)
                .setAutoCancel(true);


        //Creating Notification Builder
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);

        manager.notify(101,builder.build());


    }
}

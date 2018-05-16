package in.co.massacre.firebasetest;


import android.app.Notification;
import android.app.NotificationChannel;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("Message Recieved:",remoteMessage.getNotification().getBody());

    }
}

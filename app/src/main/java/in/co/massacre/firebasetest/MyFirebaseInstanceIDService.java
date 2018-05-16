package in.co.massacre.firebasetest;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    public static String SHRD_PREF="token_share_pref";
    public static String token="token";
    final private String TAG="MyFirebaseInstance";
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        SharedPreferences.Editor editor=this.getSharedPreferences(SHRD_PREF,MODE_PRIVATE).edit();
        editor.putString(token,refreshedToken);
        editor.apply();
        editor.commit();

        sendRegistrationToServer(refreshedToken);
    }
    public boolean sendRegistrationToServer(String token) {
        //TODO implemement sendRegistrationToServer for sending token to server


        return false;
    }
}

package com.strobertchs.finalproject.messaging;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.strobertchs.finalproject.model.SavedUsers;
import com.strobertchs.finalproject.model.User;
import com.strobertchs.finalproject.utils.ViewUtils;

/**
 * Created by jenny on 2018-01-26.
 */

public class CafeteriaFirebaseInstanceIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }
    // [END refresh_token]

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    public static void sendRegistrationToServer(String token) {
        User user = SavedUsers.getCurrentUser();
        if(user!=null && user.getEmail()!=null && !user.getEmail().equals("")) {
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference userTokensRef = database.getReference("UserTokens");
            userTokensRef.child(ViewUtils.encodeEmailAddress(user.getEmail())).setValue(token);
        }
    }
}

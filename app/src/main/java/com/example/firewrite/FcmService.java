package com.example.firewrite;

import android.util.Log;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.Map;

/**
 * Created by ishaan on 18/3/18.
 */

public class FcmService extends FirebaseMessagingService {

    private static final String TAG = "FcmService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        final Map<String, String> dataMap = remoteMessage.getData();
        if (dataMap.size() > 0) {
            Log.d(TAG, "Message data payload: " + dataMap);

            Gson gson = new Gson();
            JsonElement jsonElement = gson.toJsonTree(dataMap);
            NotifData notifData = gson.fromJson(jsonElement, NotifData.class);
            sendDeliveryReceipt(notifData);
        }
    }

    private void sendDeliveryReceipt(NotifData notifData) {
        FirebaseDatabase.getInstance().getReference("messages/groups")
                .child("0")
                .child(notifData.getMessageId())
                .child("deliveryReceipts")
                .child("UID_1")
                .setValue(System.currentTimeMillis());
    }

}

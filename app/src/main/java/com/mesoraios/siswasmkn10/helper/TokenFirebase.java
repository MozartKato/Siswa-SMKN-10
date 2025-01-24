package com.mesoraios.siswasmkn10.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;

public class TokenFirebase extends FirebaseMessagingService {

    private static final String TAG = "TokenFirebase";

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);

        // Log token untuk debugging
        Log.d(TAG, "FCM Token: " + token);

        // Simpan token ke SharedPreferences
        saveTokenToPreferences(token);
    }

    private void saveTokenToPreferences(String token) {
        SharedPreferences sp = getSharedPreferences("firebase", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("token", token);
        editor.apply();
        Log.d(TAG, "Token saved to SharedPreferences.");
    }
}

package com.example.firewrite;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;

/**
 * Created by ishaan on 18/3/18.
 */

public class FireWriteApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        if (BuildConfig.DEBUG) {
            FirebaseDatabase.getInstance().setLogLevel(Logger.Level.DEBUG);
        }
    }
}

package com.ryanheise.audioservice;

import android.content.Context;

import androidx.annotation.NonNull;
import android.content.Intent;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;

public class AudioServiceActivity extends FlutterActivity {
    @Override
    public FlutterEngine provideFlutterEngine(@NonNull Context context) {
        return AudioServicePlugin.getFlutterEngine(context);
    }

    @Override 
    protected void onDestroy() { 
        super.onDestroy(); 
        // Stop the audio service when the activity is destroyed 
        if (AudioService.instance != null) { 
            AudioService.instance.stop(); 
            AudioService.instance.onDestroy(); 
        }  
    }  

    @Override
    protected void onNewIntent(Intent intent) { 
        super.onNewIntent(intent);     
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
           cleverTapDefaultInstance?.pushNotificationClickedEvent(intent!!.extras)
        }
        setIntent(intent);
    }
}

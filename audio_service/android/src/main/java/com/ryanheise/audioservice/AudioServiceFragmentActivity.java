package com.ryanheise.audioservice;

import android.content.Context;

import android.os.Bundle;
import androidx.annotation.NonNull;
import android.content.Intent;
import androidx.annotation.Nullable;
import io.flutter.embedding.android.FlutterFragmentActivity;
import io.flutter.embedding.engine.FlutterEngine;

public class AudioServiceFragmentActivity extends FlutterFragmentActivity {
    @Override
    public FlutterEngine provideFlutterEngine(@NonNull Context context) {
        return AudioServicePlugin.getFlutterEngine(context);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AudioServicePlugin.getFlutterEngine(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getCachedEngineId() {
        AudioServicePlugin.getFlutterEngine(this);
        return AudioServicePlugin.getFlutterEngineId();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // We can access 'instance' here because we are in the same package!
        if (AudioService.instance != null) {
            AudioService.instance.stop();
            AudioService.instance.onDestroy();
        }
    }
    
    // The engine is created and managed by AudioServicePlugin,
    // it should not be destroyed with the activity.
    @Override
    public boolean shouldDestroyEngineWithHost() {
        return false;
    }

    // Make this protected method public
    @Override
    public String getInitialRoute() {
        return super.getInitialRoute();
    }

    // Make this protected method public
    @Override
    public boolean shouldHandleDeeplinking() {
        return super.shouldHandleDeeplinking();
    }
}

package com.example.testgsmservices;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

/**
 * Created by jflorezgaleano on 13/10/2017.
 */

public class InitSmsListener {

    private Activity activity;
    private BroadcastReceiver receiver;

    public InitSmsListener(Activity activity, BroadcastReceiver receiver) {
        this.activity = activity;
        this.receiver = receiver;
    }




    public void executeAPI() {

        try {
            SmsRetrieverClient client = SmsRetriever.getClient(activity);

                Task<Void> task = client.startSmsRetriever();
                task.addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("InitListener", "SMS Retriever starts");;
                    }
                });
                task.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("InitListener", "Error starting SMS Retriever");
                        FirebaseCrashlytics.getInstance().recordException(e);
                        //throw new RuntimeException("Test Crash");

                    }
                });


        } catch (Exception e) {
            Log.d("InitListener", "Exception: " + e.getMessage());
        }
    }

}

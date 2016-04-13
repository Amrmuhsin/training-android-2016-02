package com.artivisi.app.android.pembayaran.service;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by endymuhardin on 4/13/16.
 */
public class GcmHandlerService extends GcmListenerService {
    private static final String TAG = "GcmReceiver";
    @Override
    public void onMessageReceived(String from, Bundle data) {
        Log.d(TAG, "From : "+from);
        Log.d(TAG, "Action : "+data.getString("action"));
        Log.d(TAG, "Content : "+data.getString("content"));
    }
}

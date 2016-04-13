package com.artivisi.app.android.pembayaran.service;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by endymuhardin on 4/13/16.
 */
public class InstanceIDUpdateListenerService extends InstanceIDListenerService {

    private static final String TAG = "PembayaranIDLS";

    @Override
    public void onTokenRefresh() {
        // Fetch updated Instance ID token and notify our app's server of any changes (if applicable).
        Intent intent = new Intent(this, RegistrasiGcmService.class);
        startService(intent);
        Log.d(TAG, "Refreshing GCM Token");
    }
}
package com.artivisi.app.android.pembayaran;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by endymuhardin on 4/12/16.
 */
public class AplikasiPembayaran extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}

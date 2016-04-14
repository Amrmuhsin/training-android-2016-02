package com.artivisi.app.android.pembayaran.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.artivisi.app.android.pembayaran.R;
import com.artivisi.app.android.pembayaran.dao.ProdukDao;
import com.artivisi.app.android.pembayaran.dto.Page;
import com.artivisi.app.android.pembayaran.dto.Produk;
import com.artivisi.app.android.pembayaran.restclient.PembayaranClient;

import java.util.List;

/**
 * Created by endymuhardin on 4/14/16.
 */
public class UpdateDataProdukService extends IntentService {

    private static final String TAG = "PRODUKUPDATE";

    public UpdateDataProdukService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        updateDataProduk();
        tampilkanNotifikasi();
    }

    private void updateDataProduk() {
        PembayaranClient pembayaranClient = new PembayaranClient();

        Log.d(TAG, "Mengambil data produk");
        List<Produk> dataProduk = pembayaranClient.ambilDataProduk();
        ProdukDao pd = new ProdukDao(this);

        Log.d(TAG, "Menyimpan data produk ke database");
        for(Produk p : dataProduk){
            pd.insertProduk(p);
        }
    }

    private void tampilkanNotifikasi(){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_menu_share)
                .setContentTitle("Produk Baru")
                .setContentText("Ada produk baru di aplikasi");
        mBuilder.setAutoCancel(true);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        int idNotifikasiProduk = 10;
        mNotificationManager.notify(idNotifikasiProduk, mBuilder.build());
    }
}

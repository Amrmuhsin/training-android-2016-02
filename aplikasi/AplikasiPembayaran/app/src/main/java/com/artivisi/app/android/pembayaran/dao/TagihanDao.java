package com.artivisi.app.android.pembayaran.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.artivisi.app.android.pembayaran.domain.Tagihan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by endymuhardin on 4/12/16.
 */
public class TagihanDao {

    private Context context;

    public TagihanDao(Context ctx){
        this.context = ctx;
    }

    public void insertTagihan(Tagihan t){
        PembayaranDbHelper dbHelper = new PembayaranDbHelper(context);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(PembayaranDbHelper.TabelTagihan.COLUMN_NAME_PRODUK, t.getNamaProduk());
        cv.put(PembayaranDbHelper.TabelTagihan.COLUMN_NAME_NOMER_PELANGGAN, t.getNomerPelanggan());
        cv.put(PembayaranDbHelper.TabelTagihan.COLUMN_NAME_NAMA_PELANGGAN, t.getNamaPelanggan());
        cv.put(PembayaranDbHelper.TabelTagihan.COLUMN_NAME_BULAN_TAGIHAN, t.getBulanTagihan().getTime());
        cv.put(PembayaranDbHelper.TabelTagihan.COLUMN_NAME_JATUH_TEMPO, t.getJatuhTempo().getTime());
        cv.put(PembayaranDbHelper.TabelTagihan.COLUMN_NAME_NILAI, t.getNilai().doubleValue());

        db.insert(PembayaranDbHelper.TabelTagihan.TABLE_NAME, null, cv);
    }

    public List<Tagihan> semuaTagihan(){
        List<Tagihan> dataTagihan = new ArrayList<>();

        PembayaranDbHelper dbHelper = new PembayaranDbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] daftarKolom = {
                PembayaranDbHelper.TabelTagihan.COLUMN_NAME_PRODUK,
                PembayaranDbHelper.TabelTagihan.COLUMN_NAME_NOMER_PELANGGAN,
                PembayaranDbHelper.TabelTagihan.COLUMN_NAME_NAMA_PELANGGAN,
                PembayaranDbHelper.TabelTagihan.COLUMN_NAME_BULAN_TAGIHAN,
                PembayaranDbHelper.TabelTagihan.COLUMN_NAME_JATUH_TEMPO,
                PembayaranDbHelper.TabelTagihan.COLUMN_NAME_NILAI
        };

        String urutan = PembayaranDbHelper.TabelTagihan.COLUMN_NAME_JATUH_TEMPO
                + " ASC";

        Cursor c = db.query(PembayaranDbHelper.TabelTagihan.TABLE_NAME,
                daftarKolom, null, null, null, null, urutan);

        if(c.moveToFirst()){
            while(c.moveToNext()){
                Tagihan t = new Tagihan();
                t.setNamaProduk(c.getString(0));
                t.setNomerPelanggan(c.getString(1));
                t.setNamaPelanggan(c.getString(2));
                t.setBulanTagihan(new Date(c.getLong(3)));
                t.setJatuhTempo(new Date(c.getLong(4)));
                t.setNilai(new BigDecimal(c.getDouble(5)));
                dataTagihan.add(t);
            }
        }

        c.close();

        return dataTagihan;
    }
}

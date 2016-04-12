package com.artivisi.app.android.pembayaran.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.artivisi.app.android.pembayaran.domain.Tagihan;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by endymuhardin on 4/12/16.
 */
public class TagihanDao {

    private static final String TAG = "DB_TAG";

    private Context context;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public TagihanDao(Context ctx){
        this.context = ctx;
    }

    public void insertTagihan(Tagihan t){
        PembayaranDbHelper dbHelper = new PembayaranDbHelper(context);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(SkemaDatabasePembayaran.TabelTagihan.COLUMN_NAME_PRODUK, t.getNamaProduk());
        cv.put(SkemaDatabasePembayaran.TabelTagihan.COLUMN_NAME_NOMER_PELANGGAN, t.getNomerPelanggan());
        cv.put(SkemaDatabasePembayaran.TabelTagihan.COLUMN_NAME_NAMA_PELANGGAN, t.getNamaPelanggan());
        cv.put(SkemaDatabasePembayaran.TabelTagihan.COLUMN_NAME_BULAN_TAGIHAN, formatter.format(t.getBulanTagihan()));
        cv.put(SkemaDatabasePembayaran.TabelTagihan.COLUMN_NAME_JATUH_TEMPO, formatter.format(t.getJatuhTempo()));
        cv.put(SkemaDatabasePembayaran.TabelTagihan.COLUMN_NAME_NILAI, t.getNilai().doubleValue());

        db.insert(SkemaDatabasePembayaran.TabelTagihan.TABLE_NAME, null, cv);
    }

    public List<Tagihan> semuaTagihan(){
        List<Tagihan> dataTagihan = new ArrayList<>();

        PembayaranDbHelper dbHelper = new PembayaranDbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] daftarKolom = {
                SkemaDatabasePembayaran.TabelTagihan.COLUMN_NAME_PRODUK,
                SkemaDatabasePembayaran.TabelTagihan.COLUMN_NAME_NOMER_PELANGGAN,
                SkemaDatabasePembayaran.TabelTagihan.COLUMN_NAME_NAMA_PELANGGAN,
                SkemaDatabasePembayaran.TabelTagihan.COLUMN_NAME_BULAN_TAGIHAN,
                SkemaDatabasePembayaran.TabelTagihan.COLUMN_NAME_JATUH_TEMPO,
                SkemaDatabasePembayaran.TabelTagihan.COLUMN_NAME_NILAI
        };

        String urutan = SkemaDatabasePembayaran.TabelTagihan.COLUMN_NAME_JATUH_TEMPO
                + " ASC";

        Cursor c = db.query(SkemaDatabasePembayaran.TabelTagihan.TABLE_NAME,
                daftarKolom, null, null, null, null, urutan);

        if(c.moveToFirst()){
            while(c.moveToNext()){
                Tagihan t = new Tagihan();
                t.setNamaProduk(c.getString(0));
                t.setNomerPelanggan(c.getString(1));
                t.setNamaPelanggan(c.getString(2));
                try {
                    t.setBulanTagihan(formatter.parse(c.getString(3)));
                    t.setJatuhTempo(formatter.parse(c.getString(4)));
                } catch (ParseException e) {
                    Log.w(TAG, e.getMessage());
                }
                t.setNilai(new BigDecimal(c.getDouble(5)));
                dataTagihan.add(t);
            }
        }

        c.close();

        return dataTagihan;
    }
}

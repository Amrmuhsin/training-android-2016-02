package com.artivisi.app.android.pembayaran.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.artivisi.app.android.pembayaran.dto.Produk;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by endymuhardin on 4/14/16.
 */
public class ProdukDao {
    private Context context;

    public ProdukDao(Context context) {
        this.context = context;
    }

    public void insertProduk(Produk produk) {
        PembayaranDbHelper dbHelper = new PembayaranDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PembayaranDbHelper.DbProduk.COLUMN_ID_PRODUK, produk.getId());
        values.put(PembayaranDbHelper.DbProduk.COLUMN_NAME_KODE_PRODUK, produk.getKode());
        values.put(PembayaranDbHelper.DbProduk.COLUMN_NAME_NAMA_PRODUK, produk.getNama());

        db.insertWithOnConflict(PembayaranDbHelper.DbProduk.TABLE_NAME,
                null,
                values,
                SQLiteDatabase.CONFLICT_REPLACE);

        db.close();
    }

    public List<Produk> getAllProduk() {
        PembayaranDbHelper dbHelper = new PembayaranDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query(PembayaranDbHelper.DbProduk.TABLE_NAME,
                new String[]{PembayaranDbHelper.DbProduk.COLUMN_ID_PRODUK,
                        PembayaranDbHelper.DbProduk.COLUMN_NAME_KODE_PRODUK,
                        PembayaranDbHelper.DbProduk.COLUMN_NAME_NAMA_PRODUK}, null, null, null, null, null);

        List<Produk> listData = new ArrayList<>();

        if(c.moveToFirst()) {
            while(c.moveToNext()) {
                Produk p = new Produk();
                p.setId(c.getString(c.getColumnIndex(PembayaranDbHelper.DbProduk.COLUMN_ID_PRODUK)));
                p.setKode(c.getString(c.getColumnIndex(PembayaranDbHelper.DbProduk.COLUMN_NAME_KODE_PRODUK)));
                p.setNama(c.getString(c.getColumnIndex(PembayaranDbHelper.DbProduk.COLUMN_NAME_NAMA_PRODUK)));

                listData.add(p);
            }

        }
        c.close();
        db.close();
        return listData;
    }
}

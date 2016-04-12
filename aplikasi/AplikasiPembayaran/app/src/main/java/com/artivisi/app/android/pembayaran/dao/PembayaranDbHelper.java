package com.artivisi.app.android.pembayaran.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by endymuhardin on 4/12/16.
 */
public class PembayaranDbHelper extends SQLiteOpenHelper {


    // create tabel tagihan
    private static final String SQL_CREATE_TAGIHAN
            = "create table "+ SkemaDatabasePembayaran.TabelTagihan.TABLE_NAME +" (" +
            SkemaDatabasePembayaran.TabelTagihan._ID + "integer primary key, " +
            SkemaDatabasePembayaran.TabelTagihan.COLUMN_NAME_PRODUK + " TEXT, " +
            SkemaDatabasePembayaran.TabelTagihan.COLUMN_NAME_NOMER_PELANGGAN + " TEXT, " +
            SkemaDatabasePembayaran.TabelTagihan.COLUMN_NAME_NAMA_PELANGGAN + " TEXT, " +
            SkemaDatabasePembayaran.TabelTagihan.COLUMN_NAME_BULAN_TAGIHAN + " INTEGER, " +
            SkemaDatabasePembayaran.TabelTagihan.COLUMN_NAME_JATUH_TEMPO + " INTEGER, " +
            SkemaDatabasePembayaran.TabelTagihan.COLUMN_NAME_NILAI + " REAL " +
            ")";

    // drop table tagihan
    private static final String SQL_DROP_TAGIHAN
            = "drop table if exists "+SkemaDatabasePembayaran.TabelTagihan.TABLE_NAME;

    public PembayaranDbHelper(Context context) {
        super(context,
                SkemaDatabasePembayaran.DATABASE_NAME,
                null, SkemaDatabasePembayaran.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TAGIHAN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_TAGIHAN);
        onCreate(db);
    }
}

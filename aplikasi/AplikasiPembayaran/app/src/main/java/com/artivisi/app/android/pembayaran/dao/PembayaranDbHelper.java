package com.artivisi.app.android.pembayaran.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by endymuhardin on 4/12/16.
 */
public final class PembayaranDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Pembayaran.db";
    public static final Integer DATABASE_VERSION = 1;

    // create tabel tagihan
    private static final String SQL_CREATE_TAGIHAN
            = "create table "+ TabelTagihan.TABLE_NAME +" (" +
            TabelTagihan._ID + "integer primary key, " +
            TabelTagihan.COLUMN_NAME_PRODUK + " TEXT, " +
            TabelTagihan.COLUMN_NAME_NOMER_PELANGGAN + " TEXT, " +
            TabelTagihan.COLUMN_NAME_NAMA_PELANGGAN + " TEXT, " +
            TabelTagihan.COLUMN_NAME_BULAN_TAGIHAN + " INTEGER, " +
            TabelTagihan.COLUMN_NAME_JATUH_TEMPO + " INTEGER, " +
            TabelTagihan.COLUMN_NAME_NILAI + " REAL " +
            ")";

    // drop table tagihan
    private static final String SQL_DROP_TAGIHAN
            = "drop table if exists "+TabelTagihan.TABLE_NAME;

    public PembayaranDbHelper(Context context) {
        super(context,
                DATABASE_NAME,
                null, DATABASE_VERSION);
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

    // Skema tabel tagihan
    public abstract class TabelTagihan implements BaseColumns {
        public static final String TABLE_NAME = "tagihan";
        public static final String COLUMN_NAME_PRODUK = "produk";
        public static final String COLUMN_NAME_NOMER_PELANGGAN = "no_pelanggan";
        public static final String COLUMN_NAME_NAMA_PELANGGAN = "nama_pelanggan";
        public static final String COLUMN_NAME_BULAN_TAGIHAN = "blth";
        public static final String COLUMN_NAME_JATUH_TEMPO = "jt";
        public static final String COLUMN_NAME_NILAI = "nilai";
    }
}

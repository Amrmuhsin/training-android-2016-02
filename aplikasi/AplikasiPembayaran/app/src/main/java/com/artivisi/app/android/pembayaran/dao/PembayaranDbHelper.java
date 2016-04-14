package com.artivisi.app.android.pembayaran.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.artivisi.app.android.pembayaran.dto.Produk;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by endymuhardin on 4/12/16.
 */
public final class PembayaranDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Pembayaran.db";
    public static final Integer DATABASE_VERSION = 1;
    SQLiteDatabase db;

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

    // create tabel tagihan
    private static final String SQL_CREATE_PRODUK
            = "create table "+ DbProduk.TABLE_NAME +" (" +
            DbProduk.COLUMN_ID_PRODUK + " TEXT primary key, " +
            DbProduk.COLUMN_NAME_KODE_PRODUK + " TEXT, " +
            DbProduk.COLUMN_NAME_NAMA_PRODUK + " TEXT, " +
            ")";

    // drop table tagihan
    private static final String SQL_DROP_TAGIHAN
            = "drop table if exists "+TabelTagihan.TABLE_NAME;

    private static final String SQL_DROP_PRODUK
            = "drop table if exists "+ DbProduk.TABLE_NAME;

    public PembayaranDbHelper(Context context) {
        super(context,
                DATABASE_NAME,
                null, DATABASE_VERSION);
    }

    public PembayaranDbHelper open() throws SQLException {
        db = getWritableDatabase();
        return this;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TAGIHAN);
        db.execSQL(SQL_CREATE_PRODUK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_TAGIHAN);
        db.execSQL(SQL_DROP_PRODUK);
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

    // Skema tabel tagihan
    public abstract class DbProduk implements BaseColumns {
        public static final String TABLE_NAME = "produk";
        public static final String COLUMN_ID_PRODUK = "id_produk";
        public static final String COLUMN_NAME_NAMA_PRODUK = "nama_produk";
        public static final String COLUMN_NAME_KODE_PRODUK = "kode_produk";
    }

}

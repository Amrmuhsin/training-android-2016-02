package com.artivisi.app.android.pembayaran.dao;

import android.provider.BaseColumns;

/**
 * Created by endymuhardin on 4/12/16.
 */
public final class SkemaDatabasePembayaran {

    public static final String DATABASE_NAME = "Pembayaran.db";
    public static final Integer DATABASE_VERSION = 1;




    // dilarang menginstankan
    // tidak bisa new SkemaDatabasePembayaran();
    private SkemaDatabasePembayaran(){}

    // Skema tabel tagihan
    public abstract class TabelTagihan implements BaseColumns{
        public static final String TABLE_NAME = "tagihan";
        public static final String COLUMN_NAME_PRODUK = "produk";
        public static final String COLUMN_NAME_NOMER_PELANGGAN = "no_pelanggan";
        public static final String COLUMN_NAME_NAMA_PELANGGAN = "nama_pelanggan";
        public static final String COLUMN_NAME_BULAN_TAGIHAN = "blth";
        public static final String COLUMN_NAME_JATUH_TEMPO = "jt";
        public static final String COLUMN_NAME_NILAI = "nilai";
    }
}

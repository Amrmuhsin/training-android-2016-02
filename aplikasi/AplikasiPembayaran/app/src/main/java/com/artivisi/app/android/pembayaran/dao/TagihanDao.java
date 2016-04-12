package com.artivisi.app.android.pembayaran.dao;

import com.artivisi.app.android.pembayaran.domain.Tagihan;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by endymuhardin on 4/12/16.
 */
public class TagihanDao {
    public List<Tagihan> semuaTagihan(){
        List<Tagihan> dataTagihan = new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Tagihan t1 = new Tagihan();
        t1.setNamaProduk("PLN Pascabayar");
        t1.setNomerPelanggan("1234567890");
        t1.setNamaPelanggan("Endy Muhardin");
        t1.setNilai(new BigDecimal("100000.00"));

        try {
            t1.setBulanTagihan(formatter.parse("2016-01-01"));
            t1.setJatuhTempo(formatter.parse("2016-01-20"));
        } catch (Exception err){
            err.printStackTrace();
        }
        dataTagihan.add(t1);

        Tagihan t2 = new Tagihan();
        t2.setNamaProduk("PLN Pascabayar");
        t2.setNomerPelanggan("0987654321");
        t2.setNamaPelanggan("Jimmy Rengga");
        t2.setNilai(new BigDecimal("90000.00"));

        try {
            t2.setBulanTagihan(formatter.parse("2016-01-01"));
            t2.setJatuhTempo(formatter.parse("2016-01-20"));
        } catch (Exception err){
            err.printStackTrace();
        }
        dataTagihan.add(t2);

        Tagihan t3 = new Tagihan();
        t3.setNamaProduk("Telkom");
        t3.setNomerPelanggan("1234567890");
        t3.setNamaPelanggan("Endy Muhardin");
        t3.setNilai(new BigDecimal("1000000.00"));

        try {
            t3.setBulanTagihan(formatter.parse("2016-01-01"));
            t3.setJatuhTempo(formatter.parse("2016-01-20"));
        } catch (Exception err){
            err.printStackTrace();
        }
        dataTagihan.add(t3);


        return dataTagihan;
    }
}

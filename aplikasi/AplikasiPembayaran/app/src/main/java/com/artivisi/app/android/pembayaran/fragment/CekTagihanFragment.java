package com.artivisi.app.android.pembayaran.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.artivisi.app.android.pembayaran.PembayaranConstants;
import com.artivisi.app.android.pembayaran.R;
import com.artivisi.app.android.pembayaran.adapter.ProdukAdapter;
import com.artivisi.app.android.pembayaran.dao.ProdukDao;
import com.artivisi.app.android.pembayaran.dto.Produk;

import java.util.List;


/**
 * Created by endymuhardin on 4/11/16.
 */
public class CekTagihanFragment extends Fragment {

    private static final String TAG = "CEKTAGIHAN";
    private Spinner spProduk;
    private BroadcastReceiver broadcastReceiver;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                updateSpinner();
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(getContext())
                .registerReceiver(
                        broadcastReceiver,
                        new IntentFilter(PembayaranConstants.INTENT_UPDATE_PRODUK)
                );
    }

    @Override
    public void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getContext())
                .unregisterReceiver(broadcastReceiver);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fr_cek_tagihan, container, false);
        spProduk = (Spinner) fragmentView.findViewById(R.id.spProduk);


        final EditText txtNomer = (EditText) fragmentView.findViewById(R.id.etNoPelanggan);

        Button btTagihan = (Button) fragmentView.findViewById(R.id.btnTagihan);
        btTagihan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Produk produkDipilih = (Produk) spProduk.getSelectedItem();
                String nomerPelanggan = txtNomer.getText().toString();

                Log.d(TAG, "Produk : "+produkDipilih.getKode());
                Log.d(TAG, "Nomer : "+nomerPelanggan);

                FragmentTransaction ft =
                        CekTagihanFragment.this.getActivity()
                                .getSupportFragmentManager()
                        .beginTransaction();

                ft.replace(R.id.fragment_sebelum_login, new HasilTagihanFragment());
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return fragmentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateSpinner();
    }

    private void updateSpinner(){
        List<Produk> dataProduk = new ProdukDao(getContext()).getAllProduk();
        spProduk.setAdapter(new ProdukAdapter(getContext(),
                android.R.layout.simple_spinner_item, dataProduk));
    }
}

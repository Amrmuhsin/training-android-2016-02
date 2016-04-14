package com.artivisi.app.android.pembayaran.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.artivisi.app.android.pembayaran.R;
import com.artivisi.app.android.pembayaran.activity.SebelumLoginActivity;
import com.artivisi.app.android.pembayaran.domain.Tagihan;

/**
 * Created by endymuhardin on 4/11/16.
 */
public class HasilTagihanFragment extends Fragment {
    private Tagihan tagihan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle data) {

        View fragmentView = inflater.inflate(R.layout.fr_hasil_tagihan, container, false);

        if(getActivity().getClass().isAssignableFrom(SebelumLoginActivity.class)){
            Button btnBayar = (Button) fragmentView.findViewById(R.id.btnBayar);
            btnBayar.setText("Registrasi");
        }

        TextView tvTotal = (TextView) fragmentView.findViewById(R.id.tvTotal);
        TextView tvNamaPelanggan = (TextView) fragmentView.findViewById(R.id.tvNamaPelanggan);
        TextView tvNoPelanggan = (TextView) fragmentView.findViewById(R.id.tvNoPelanggan);
        TextView tvTagihanAnda = (TextView) fragmentView.findViewById(R.id.tvTagihanAnda);

        tvTotal.setText(getArguments().getString("nilai"));
        tvNamaPelanggan.setText(getArguments().getString("nama"));
        tvNoPelanggan.setText(getArguments().getString("nomer"));
        tvTagihanAnda.setText("Tagihan "+getArguments().getString("produk")+" Anda");

        Button btnBack = (Button) fragmentView.findViewById(R.id.btnBackHasilTagihan);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = HasilTagihanFragment.this.getActivity()
                        .getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                ft.replace(R.id.fragment_sebelum_login, new CekTagihanFragment());
                fm.popBackStackImmediate();
                ft.commit();
            }
        });

        return fragmentView;
    }
}

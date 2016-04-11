package com.artivisi.app.android.pembayaran.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.artivisi.app.android.pembayaran.R;


/**
 * Created by endymuhardin on 4/11/16.
 */
public class CekTagihanFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fr_cek_tagihan, container, false);

        Button btTagihan = (Button) fragmentView.findViewById(R.id.btnTagihan);
        btTagihan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
}

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
public class HasilTagihanFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fr_hasil_tagihan, container, false);

        Button btnBack = (Button) fragmentView.findViewById(R.id.btnBackHasilTagihan);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft =
                        HasilTagihanFragment.this.getActivity()
                                .getSupportFragmentManager()
                                .beginTransaction();

                ft.replace(R.id.fragment_sebelum_login, new CekTagihanFragment());
                ft.commit();
            }
        });

        return fragmentView;
    }
}

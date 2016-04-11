package com.artivisi.app.android.pembayaran.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.artivisi.app.android.pembayaran.R;
import com.artivisi.app.android.pembayaran.activity.SetelahLoginActivity;

/**
 * Created by endymuhardin on 4/11/16.
 */
public class LoginFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View fragmentView = inflater.inflate(R.layout.fr_login, container, false);

        Button btnLogin = (Button) fragmentView.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setelahLoginActivity
                        = new Intent(getContext(), SetelahLoginActivity.class);
                startActivity(setelahLoginActivity);
            }
        });


        return fragmentView;
    }
}

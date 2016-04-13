package com.artivisi.app.android.pembayaran.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.artivisi.app.android.pembayaran.R;
import com.artivisi.app.android.pembayaran.activity.SetelahLoginActivity;
import com.artivisi.app.android.pembayaran.exception.GagalLoginException;
import com.artivisi.app.android.pembayaran.restclient.PembayaranClient;

/**
 * Created by endymuhardin on 4/11/16.
 */
public class LoginFragment extends Fragment {
    private static final String TAG = "Login";
    private PembayaranClient pembayaranClient = new PembayaranClient();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        final View fragmentView = inflater.inflate(R.layout.fr_login, container, false);

        final Button btnLogin = (Button) fragmentView.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = ((EditText)
                        fragmentView.findViewById(R.id.etEmailLogin))
                        .getText().toString();

                String password = ((EditText)
                        fragmentView.findViewById(R.id.etPasswordLogin))
                        .getText().toString();


                new AsyncTask<String, Void, Boolean>(){
                    ProgressDialog progressDialog;
                    String errorMessage;
                    String email;

                    @Override
                    protected void onPreExecute() {
                        progressDialog = ProgressDialog.show(getContext(),
                                "Login", "Logging in", true);
                        btnLogin.setEnabled(false);
                    }

                    @Override
                    protected Boolean doInBackground(String... params) {
                        try {
                            Log.d(TAG, "Login : "+params);
                            email = params[0];
                            pembayaranClient.login(params[0], params[1]);
                            return true;
                        } catch (GagalLoginException err){
                            errorMessage = err.getMessage();
                            Log.d(TAG, "Login error : "+err.getMessage());
                            return false;
                        }
                    }

                    @Override
                    protected void onPostExecute(Boolean sukses) {
                        btnLogin.setEnabled(true);
                        progressDialog.dismiss();
                        Log.d(TAG, "Login sukses : "+sukses);
                        if(sukses) {
                            Intent setelahLoginActivity
                                    = new Intent(getContext(), SetelahLoginActivity.class);
                            setelahLoginActivity.putExtra("email", email);
                            startActivity(setelahLoginActivity);
                        } else {
                            Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG)
                            .show();
                        }
                    }
                }.execute(username, password);
            }
        });


        return fragmentView;
    }
}

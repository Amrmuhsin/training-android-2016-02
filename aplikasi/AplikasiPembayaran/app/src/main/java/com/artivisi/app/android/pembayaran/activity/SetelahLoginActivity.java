package com.artivisi.app.android.pembayaran.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.artivisi.app.android.pembayaran.PembayaranConstants;
import com.artivisi.app.android.pembayaran.R;
import com.artivisi.app.android.pembayaran.dao.TagihanDao;
import com.artivisi.app.android.pembayaran.domain.Tagihan;
import com.artivisi.app.android.pembayaran.fragment.DashboardFragment;
import com.artivisi.app.android.pembayaran.service.RegistrasiGcmService;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class SetelahLoginActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "SetelahLogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setelah_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        insertDummyData();
        loadFragment(new DashboardFragment());

        String email = getIntent().getStringExtra("email");

        // lakukan registrasi ke GCM untuk mendapatkan token
        Log.d(TAG, "Menjalankan service registrasi GCM");
        Intent intent = new Intent(this, RegistrasiGcmService.class);
        intent.putExtra("email", email);
        //startService(intent);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.setelah_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_logout) {
            SharedPreferences sp = this
                    .getSharedPreferences(PembayaranConstants.SHARED_PREF_KEY, Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sp.edit();
            editor.clear();
            editor.commit();
            Intent setelahLogout = new Intent(this,SebelumLoginActivity.class);
            startActivity(setelahLogout);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadFragment(Fragment fr){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.replace(R.id.fragment_setelah_login, fr);
        fragmentTransaction.commit();
    }

    private void insertDummyData(){
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

        TagihanDao td = new TagihanDao(this);
        td.kosongkan();
        td.insertTagihan(t1);
        td.insertTagihan(t2);
        td.insertTagihan(t3);
    }
}

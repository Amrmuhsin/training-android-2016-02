package com.artivisi.app.android.pembayaran.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.artivisi.app.android.pembayaran.R;
import com.artivisi.app.android.pembayaran.domain.Tagihan;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by endymuhardin on 4/12/16.
 */
public class TagihanAdapter extends ArrayAdapter<Tagihan> {

    public TagihanAdapter(Context context, int resource, List<Tagihan> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.lv_daftar_tagihan, parent, false);
        }

        TextView txtNamaProduk = (TextView) convertView.findViewById(R.id.lblNamaProduk);
        TextView txtNomerPelanggan = (TextView) convertView
                .findViewById(R.id.lblNomerPelanggan);
        TextView txtNamaPelanggan = (TextView) convertView
                .findViewById(R.id.lblNamaPelanggan);
        TextView txtBulanTagihan = (TextView) convertView
                .findViewById(R.id.txtBulanTagihan);
        TextView txtJatuhTempo = (TextView) convertView
                .findViewById(R.id.txtJatuhTempo);
        TextView txtNilai = (TextView) convertView
                .findViewById(R.id.txtNilai);

        Tagihan t = getItem(position);
        txtNamaProduk.setText(t.getNamaProduk());
        txtNomerPelanggan.setText(t.getNomerPelanggan());
        txtNamaPelanggan.setText(t.getNamaPelanggan());

        txtBulanTagihan.setText(new SimpleDateFormat("MMMMM yyyy").format(t.getBulanTagihan()));
        txtJatuhTempo.setText(new SimpleDateFormat("dd MMMMM yyyy").format(t.getJatuhTempo()));

        NumberFormat currencyFormatter =
                NumberFormat.getCurrencyInstance(new Locale("id", "id"));
        txtNilai.setText(currencyFormatter.format(t.getNilai()));

        return convertView;
    }
}

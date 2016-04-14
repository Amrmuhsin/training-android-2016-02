package com.artivisi.app.android.pembayaran.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.artivisi.app.android.pembayaran.dto.Produk;

import java.util.List;

/**
 * Created by endymuhardin on 4/14/16.
 */
public class ProdukAdapter extends ArrayAdapter<Produk> {
    public ProdukAdapter (Context context, int resource, List<Produk> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return buatLabel(position);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return buatLabel(position);
    }

    private TextView buatLabel(int position){
        Produk p = getItem(position);
        TextView label = new TextView(getContext());
        label.setText(p.getNama());
        return label;
    }
}

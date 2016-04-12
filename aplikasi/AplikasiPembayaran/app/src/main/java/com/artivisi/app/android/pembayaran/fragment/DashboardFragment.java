package com.artivisi.app.android.pembayaran.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.artivisi.app.android.pembayaran.R;
import com.artivisi.app.android.pembayaran.adapter.TagihanAdapter;
import com.artivisi.app.android.pembayaran.dao.TagihanDao;

/**
 * Created by endymuhardin on 4/12/16.
 */
public class DashboardFragment extends Fragment {

    private TagihanDao tagihanDao = new TagihanDao();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fr_dashboard, container, false);

        ListView lvTagihan = (ListView) fragmentView.findViewById(R.id.lvTagihan);

        lvTagihan.setAdapter(new TagihanAdapter(getContext(),
                R.layout.lv_daftar_tagihan, tagihanDao.semuaTagihan()));

        return fragmentView;
    }
}

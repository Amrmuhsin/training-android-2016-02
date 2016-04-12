package com.artivisi.app.android.pembayaran.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.artivisi.app.android.pembayaran.R;
import com.artivisi.app.android.pembayaran.adapter.TagihanAdapter;
import com.artivisi.app.android.pembayaran.dao.TagihanDao;
import com.artivisi.app.android.pembayaran.domain.Tagihan;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by endymuhardin on 4/12/16.
 */
public class DashboardFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fr_dashboard, container, false);

        TagihanDao tagihanDao = new TagihanDao(getContext());
        List<Tagihan> dataTagihan = tagihanDao.semuaTagihan();
        BigDecimal total = BigDecimal.ZERO;
        for (Tagihan t: dataTagihan) {
            total = total.add(t.getNilai());
        }

        TextView txtTotalTagihan = (TextView) fragmentView.findViewById(R.id.txtTotalTagihan);
        txtTotalTagihan.setText(
                NumberFormat.getCurrencyInstance(new Locale("id", "id"))
                        .format(total));

        ListView lvTagihan = (ListView) fragmentView.findViewById(R.id.lvTagihan);

        lvTagihan.setAdapter(new TagihanAdapter(getContext(),
                R.layout.lv_daftar_tagihan, dataTagihan));

        return fragmentView;
    }
}

package com.artivisi.pembayaran.service;

import com.artivisi.pembayaran.dao.AntrianGcmDao;
import com.artivisi.pembayaran.dao.ProdukDao;
import com.artivisi.pembayaran.entity.Produk;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
public class PembayaranService {
    @Autowired private ProdukDao produkDao;
    @Autowired private AntrianGcmDao antrianGcmDao;
    @Autowired private GcmService gcmService;
    
    public void simpan(Produk p){
        produkDao.save(p);
        Map<String, Object> data = new HashMap<>();
        data.put("action", "update");
        data.put("content", "produk");
        gcmService.kirimGcmMessage("/topics/produk", data);
    }
}

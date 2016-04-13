package com.artivisi.pembayaran.dao;

import com.artivisi.pembayaran.entity.AntrianGcm;
import com.artivisi.pembayaran.entity.StatusAntrian;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AntrianGcmDao extends PagingAndSortingRepository<AntrianGcm, String>{
    public Page<AntrianGcm> findByStatusOrderByWaktuKirimAsc(StatusAntrian statusAntrian, Pageable p);
}

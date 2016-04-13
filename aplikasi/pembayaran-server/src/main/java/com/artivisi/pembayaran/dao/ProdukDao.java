package com.artivisi.pembayaran.dao;

import com.artivisi.pembayaran.entity.Produk;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProdukDao extends PagingAndSortingRepository<Produk, String>{

}

package com.artivisi.pembayaran.controller;

import com.artivisi.pembayaran.entity.Produk;
import com.artivisi.pembayaran.service.PembayaranService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produk")
public class ProdukController {
    
    @Autowired private PembayaranService pembayaranService;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Page<Produk> semuaProduk(Pageable page){
        return pembayaranService.semuaProduk(page);
    }
    
    @RequestMapping(value = "/", method = RequestMethod.POST)    
    public void simpan(@RequestBody @Valid Produk p){
        pembayaranService.simpan(p);
    }
}

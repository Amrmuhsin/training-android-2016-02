package com.artivisi.pembayaran.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@Entity @Table(name = "t_tagihan_favorit")
public class TagihanFavorit extends BaseEntity {
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_produk", nullable = false)
    private Produk produk;
    
    @NotNull @NotEmpty
    @Column(name = "nomer_pelanggan", nullable = false)
    private String nomerPelanggan;
    
    @Column(name = "nama_pelanggan")
    private String namaPelanggan;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Produk getProduk() {
        return produk;
    }

    public void setProduk(Produk produk) {
        this.produk = produk;
    }

    public String getNomerPelanggan() {
        return nomerPelanggan;
    }

    public void setNomerPelanggan(String nomerPelanggan) {
        this.nomerPelanggan = nomerPelanggan;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }
    
    
}

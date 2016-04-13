package com.artivisi.pembayaran.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity @Table(name = "t_tagihan")
public class Tagihan extends BaseEntity {
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_produk", nullable = false)
    private Produk produk;
    
    @NotNull
    @Column(name = "bulan_tagihan", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date bulanTagihan = new Date();
    
    @NotNull
    @Column(name = "jatuh_tempo", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date jatuhTempo = new Date();
    
    @NotNull @Min(0)
    @Column(nullable = false)
    private BigDecimal nilai = BigDecimal.ZERO;
    @NotNull @Min(0)
    @Column(nullable = false)
    private BigDecimal admin = BigDecimal.ZERO;
    @NotNull @Min(0)
    @Column(nullable = false)
    private BigDecimal denda = BigDecimal.ZERO;
    @NotNull @Min(0)
    @Column(nullable = false)
    private BigDecimal pajak = BigDecimal.ZERO;
    @NotNull @Min(0)
    @Column(nullable = false)
    private BigDecimal lainLain = BigDecimal.ZERO;
    
    @NotNull
    @Column(nullable = false)
    private Boolean lunas = Boolean.FALSE;

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

    public Date getBulanTagihan() {
        return bulanTagihan;
    }

    public void setBulanTagihan(Date bulanTagihan) {
        this.bulanTagihan = bulanTagihan;
    }

    public Date getJatuhTempo() {
        return jatuhTempo;
    }

    public void setJatuhTempo(Date jatuhTempo) {
        this.jatuhTempo = jatuhTempo;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public BigDecimal getAdmin() {
        return admin;
    }

    public void setAdmin(BigDecimal admin) {
        this.admin = admin;
    }

    public BigDecimal getDenda() {
        return denda;
    }

    public void setDenda(BigDecimal denda) {
        this.denda = denda;
    }

    public BigDecimal getPajak() {
        return pajak;
    }

    public void setPajak(BigDecimal pajak) {
        this.pajak = pajak;
    }

    public BigDecimal getLainLain() {
        return lainLain;
    }

    public void setLainLain(BigDecimal lainLain) {
        this.lainLain = lainLain;
    }

    public Boolean getLunas() {
        return lunas;
    }

    public void setLunas(Boolean lunas) {
        this.lunas = lunas;
    }
    
    
}

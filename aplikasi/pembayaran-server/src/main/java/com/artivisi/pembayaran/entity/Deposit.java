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

@Entity @Table(name = "t_pembayaran")
public class Deposit extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
    
    @NotNull
    @Column(name = "waktu_transaksi", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuTransaksi = new Date();
    
    @NotNull @Min(0)
    @Column(nullable = false)
    private BigDecimal nilai = BigDecimal.ZERO;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getWaktuTransaksi() {
        return waktuTransaksi;
    }

    public void setWaktuTransaksi(Date waktuTransaksi) {
        this.waktuTransaksi = waktuTransaksi;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }
    
    
}

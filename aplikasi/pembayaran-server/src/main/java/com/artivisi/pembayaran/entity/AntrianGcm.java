package com.artivisi.pembayaran.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;


@Entity @Table(name = "t_antrian_gcm")
public class AntrianGcm extends BaseEntity {
    
    @NotNull
    @Column(nullable = false, name = "waktu_masuk")
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuMasuk = new Date();
    
    @NotNull @NotEmpty
    @Column(nullable = false)
    private String tujuan;
    
    @NotNull @NotEmpty
    @Column(nullable = false)
    private String data;
    
    @Column(name = "waktu_kirim")
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuKirim;
    
    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusAntrian status = StatusAntrian.BARU;
    
    private String keterangan;

    public Date getWaktuMasuk() {
        return waktuMasuk;
    }

    public void setWaktuMasuk(Date waktuMasuk) {
        this.waktuMasuk = waktuMasuk;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getWaktuKirim() {
        return waktuKirim;
    }

    public void setWaktuKirim(Date waktuKirim) {
        this.waktuKirim = waktuKirim;
    }

    public StatusAntrian getStatus() {
        return status;
    }

    public void setStatus(StatusAntrian status) {
        this.status = status;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
    
    
}

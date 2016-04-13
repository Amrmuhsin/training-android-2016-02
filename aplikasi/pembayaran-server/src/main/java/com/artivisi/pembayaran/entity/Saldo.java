package com.artivisi.pembayaran.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity @Table(name = "t_saldo")
public class Saldo extends BaseEntity {
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
    
    @NotNull @Min(0)
    @Column(nullable = false)
    private BigDecimal nilai = BigDecimal.ZERO;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }
    
    
}

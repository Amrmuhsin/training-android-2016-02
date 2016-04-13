package com.artivisi.pembayaran.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@Entity @Table(name = "t_user_gcm_token")
public class UserGcmToken extends BaseEntity {
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
    
    @NotNull @NotEmpty
    @Column(name = "gcm_token", nullable = false)
    private String gcmToken;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGcmToken() {
        return gcmToken;
    }

    public void setGcmToken(String gcmToken) {
        this.gcmToken = gcmToken;
    }
    
    
}

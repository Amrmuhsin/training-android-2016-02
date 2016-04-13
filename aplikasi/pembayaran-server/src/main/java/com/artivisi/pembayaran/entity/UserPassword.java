package com.artivisi.pembayaran.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@Entity @Table(name = "m_user_password")
public class UserPassword extends BaseEntity {
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
    
    @NotNull @NotEmpty
    @Column(name="hashed_password", nullable = false)
    private String hashedPassword;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
    
    
}

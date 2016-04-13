package com.artivisi.pembayaran.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "m_user")
public class User extends BaseEntity {
    
    @NotNull @NotEmpty
    @Column(nullable = false, unique = true)
    private String email;
    
    @NotNull @NotEmpty
    @Column(nullable = false)
    private String fullname;
    
    @NotNull @NotEmpty
    @Column(name = "nomer_handphone", nullable = false)
    private String nomerHandphone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNomerHandphone() {
        return nomerHandphone;
    }

    public void setNomerHandphone(String nomerHandphone) {
        this.nomerHandphone = nomerHandphone;
    }
    
}

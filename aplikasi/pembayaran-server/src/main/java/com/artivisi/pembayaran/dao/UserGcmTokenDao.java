package com.artivisi.pembayaran.dao;

import com.artivisi.pembayaran.entity.User;
import com.artivisi.pembayaran.entity.UserGcmToken;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserGcmTokenDao extends PagingAndSortingRepository<UserGcmToken, String>{

    @Query("delete UserGcmToken ut where ut.user = :user")
    public void deleteAllUserToken(@Param("user") User u);

}

package com.artivisi.pembayaran.dao;

import com.artivisi.pembayaran.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDao extends PagingAndSortingRepository<User, String>{

    public User findByEmail(String email);

}

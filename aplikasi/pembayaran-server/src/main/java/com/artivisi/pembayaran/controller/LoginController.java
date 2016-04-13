package com.artivisi.pembayaran.controller;

import com.artivisi.pembayaran.dto.LoginRequest;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login(@RequestBody @Valid LoginRequest login){
        String validUsername = "endy@muhardin.com";
        String validPassword = "123";

        Map<String, Object> hasil = new HashMap<>();

        if(validUsername.equals(login.getUsername()) && validPassword.equals(login.getPassword())){
            hasil.put("success", true);
        } else {
            hasil.put("success", false);
        }

        return hasil;
    }
}
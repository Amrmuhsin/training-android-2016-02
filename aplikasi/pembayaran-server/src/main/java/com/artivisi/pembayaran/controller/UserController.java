package com.artivisi.pembayaran.controller;

import com.artivisi.pembayaran.entity.User;
import com.artivisi.pembayaran.service.PembayaranService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired private PembayaranService pembayaranService;
    
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public void updateToken(@RequestBody Map<String, String> data){
        pembayaranService.updateToken(data.get("email"), data.get("token"));
    }
}

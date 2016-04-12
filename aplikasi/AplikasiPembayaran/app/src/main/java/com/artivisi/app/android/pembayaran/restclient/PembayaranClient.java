package com.artivisi.app.android.pembayaran.restclient;

import com.artivisi.app.android.pembayaran.exception.GagalLoginException;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by endymuhardin on 4/12/16.
 */
public class PembayaranClient {
    private static final String URL_SERVER = "https://pembayaran.herokuapp.com";

    private RestTemplate restTemplate = new RestTemplate();

    public void login(String username, String password) throws GagalLoginException {
        Map<String, String> loginData = new HashMap<>();
        loginData.put("username", username);
        loginData.put("password", password);

        Map<String, Object> hasil = restTemplate
                .postForObject(URL_SERVER + "/api/login",
                    loginData, Map.class, new Object[]{}
                );

        if(hasil == null){
            throw new GagalLoginException("Response invalid");
        }

        if(!(Boolean)hasil.get("success")){
            throw new GagalLoginException("Username / Password salah");
        }
    }
}

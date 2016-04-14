package com.artivisi.app.android.pembayaran.restclient;

import com.artivisi.app.android.pembayaran.dto.Page;
import com.artivisi.app.android.pembayaran.dto.Produk;
import com.artivisi.app.android.pembayaran.exception.GagalLoginException;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public void registrasiToken(String email, String token) {
        Map<String, String> data = new HashMap<>();
        data.put("email", email);
        data.put("token", token);
        String url = "/api/user/token";
        restTemplate.postForObject(URL_SERVER + url, data, Void.class);
    }

    public List<Produk> ambilDataProduk(){
        String url = URL_SERVER + "/api/produk/";
        Page<Produk> page = restTemplate.getForObject(url, Page.class, new Object[]{});

        List<Map<String, String>> data = page.getContent();
        List<Produk> hasil = new ArrayList<>();
        for (Map<String, String> d : data) {
            Produk p = new Produk();
            p.setId(d.get("id"));
            p.setKode(d.get("kode"));
            p.setNama(d.get("nama"));
            hasil.add(p);
        }

        return hasil;
    }
}

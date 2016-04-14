package com.artivisi.app.android.pembayaran.restclient;

import android.content.SharedPreferences;
import android.util.Log;

import com.artivisi.app.android.pembayaran.dto.Page;
import com.artivisi.app.android.pembayaran.dto.Produk;
import com.artivisi.app.android.pembayaran.exception.GagalLoginException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by endymuhardin on 4/12/16.
 */
public class PembayaranClient {
    private static final String TAG = "REST";
    private static final String URL_SERVER = "https://pembayaran.herokuapp.com";
    private static final String URL_SERVER_PEGADAIAN = "http://sdm.pegadaian.co.id";
    private String cookiePegadaian;

    private RestTemplate restTemplate = new RestTemplate();

    public String login(String username, String password) throws GagalLoginException {
        MultiValueMap<String, String> loginData = new LinkedMultiValueMap<>();
        loginData.add("username", username);
        loginData.add("password", password);

        ResponseEntity<Map> responseEntity = restTemplate
                .postForEntity(URL_SERVER_PEGADAIAN + "/api/userLogin.php",
                    loginData, Map.class, new Object[]{}
                );

        HttpHeaders headers = responseEntity.getHeaders();
        List<String> cookies = headers.get("Set-Cookie");

        for(String cookie : cookies){
            Log.d(TAG, "Cookie Header : "+cookie);
            cookiePegadaian = cookie.substring(0,(cookie.indexOf(";")+1));
            Log.d(TAG, "Cookie setelah substring : "+cookiePegadaian);
        }

        Map<String, Object> hasil = responseEntity.getBody();

        if(hasil.get("username") == null){
            throw new GagalLoginException("Response invalid");
        }

        return cookiePegadaian;
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

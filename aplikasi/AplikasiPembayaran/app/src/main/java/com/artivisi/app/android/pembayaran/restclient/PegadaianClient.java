package com.artivisi.app.android.pembayaran.restclient;

import com.artivisi.app.android.pembayaran.domain.Tagihan;
import com.artivisi.app.android.pembayaran.exception.ResponseGagalException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by endymuhardin on 4/14/16.
 */
public class PegadaianClient {
    private static final String URL_SERVER = "http://sdm.pegadaian.co.id";

    private RestTemplate restTemplate = new RestTemplate();
    private String cookie;
    private String username;

    public PegadaianClient(String cookie, String username) {
        this.cookie = cookie;
        this.username = username;
    }

    public Tagihan inquiry(String produk, String nomer) throws ResponseGagalException {
        String url = URL_SERVER + "/api/tagihan2.php";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", cookie);

        Map<String, String> request = new HashMap<>();
        request.put("action", "INQUIRY");
        request.put("produk", produk);
        request.put("id_pelanggan", nomer);
        request.put("username", username);

        HttpEntity<Map<String, String>> requestBody
                = new HttpEntity<>(request, headers);

        Map<String, String> response = restTemplate.postForObject(url,
                requestBody, Map.class);

        if(!"00".equals(response.get("rc"))){
            throw new ResponseGagalException(response.get("message"));
        }

        Tagihan hasil = new Tagihan();
        hasil.setNomerPelanggan(response.get("id_pelanggan"));
        hasil.setNamaPelanggan(response.get("nm_pelanggan"));
        hasil.setNamaProduk(response.get("produk"));
        hasil.setNilai(new BigDecimal(response.get("tagihan")));

        return hasil;
    }
}

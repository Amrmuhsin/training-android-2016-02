package com.artivisi.android.maps.restservice;

import android.util.Log;

import com.artivisi.android.maps.dto.GenericHttpResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by jimmy on 15/04/16.
 */
public class LocationService {

    public RestTemplate restTemplate = new RestTemplate();
    private static final String URL_SERVER = "http://sdm.pegadaian.co.id";

    public LocationService() {
        restTemplate = new RestTemplate();
    }

    public GenericHttpResponse ambilAllLocation(){
        String url = URL_SERVER + "/api/allLocation.php";
        ResponseEntity hasil = restTemplate.postForEntity(url, null, GenericHttpResponse.class);
        Log.i("hasil", hasil.getStatusCode().getReasonPhrase());

        return (GenericHttpResponse) hasil.getBody();
    }


}

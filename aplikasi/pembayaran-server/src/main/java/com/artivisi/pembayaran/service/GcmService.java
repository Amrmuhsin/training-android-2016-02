package com.artivisi.pembayaran.service;


import com.artivisi.pembayaran.dao.AntrianGcmDao;
import com.artivisi.pembayaran.entity.AntrianGcm;
import com.artivisi.pembayaran.entity.StatusAntrian;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service @Transactional
public class GcmService {
    private static final String API_KEY = "AIzaSyDaaQRk2mhS73MtaXZ9QUf58PYnm2thidw";
    private static final String GCM_SERVER = "https://gcm-http.googleapis.com/gcm/send";
    private static final Logger LOGGER = LoggerFactory.getLogger(GcmService.class);
    
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();
    
    @Autowired private AntrianGcmDao antrianGcmDao;
    
    public GcmService(){
        // inisialisasi rest template supaya setiap kirim request
        // include API Key di header
        restTemplate.setInterceptors(Arrays.asList(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                request.getHeaders().add("Authorization", "key="+API_KEY);
                return execution.execute(request, body);
            }
        }));
    }
    
    public void kirimGcmMessage(String tujuan, Map<String, Object> data){
        try {
            AntrianGcm a = new AntrianGcm();
            a.setTujuan("/topics/produk");
            a.setData(objectMapper.writeValueAsString(data));
            antrianGcmDao.save(a);
        } catch (JsonProcessingException ex) {
            LOGGER.warn("GCM gagal insert ke antrian : {}", ex.getMessage());
        }       
    }
    
    @Scheduled(fixedDelay = 5000)
    public void prosesAntrianGcm(){
        PageRequest pr = new PageRequest(0, 1);
        Page<AntrianGcm> antrianTeratas = antrianGcmDao
                .findByStatusOrderByWaktuKirimAsc(StatusAntrian.BARU, pr);
        
        LOGGER.debug("GCM : Memproses {} antrian dari {}", 
                antrianTeratas.getNumberOfElements(), 
                antrianTeratas.getTotalElements());
        
        if(antrianTeratas.getNumberOfElements() < 1){
            return;
        }
        
        AntrianGcm a = antrianTeratas.getContent().get(0);
        prosesPengiriman(a);
        antrianGcmDao.save(a);
    }
    
    private void prosesPengiriman(AntrianGcm a){
        try {
            Map<String, Object> gcmRequest = new HashMap<>();
            gcmRequest.put("to", a.getTujuan());
            gcmRequest.put("data", objectMapper.readValue(a.getData(), Map.class));
            Map<String, Object> hasil = restTemplate.postForObject(GCM_SERVER, gcmRequest, Map.class);
            LOGGER.debug("GCM sukses : [{}]",hasil.get("success"));
            a.setStatus(StatusAntrian.TERKIRIM);
            a.setWaktuKirim(new Date());
        } catch (Exception ex) {
            LOGGER.warn("GCM gagal : {}", ex.getMessage());
            a.setStatus(StatusAntrian.GAGAL_KIRIM);
            a.setWaktuKirim(new Date());
            a.setKeterangan(ex.getMessage());
        }
    }
}

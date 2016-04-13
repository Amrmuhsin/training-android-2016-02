package com.artivisi.pembayaran.service;

import com.artivisi.pembayaran.PembayaranServerApplication;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(PembayaranServerApplication.class)
public class GcmServiceTests {
    
    @Autowired private GcmService gcm;
    
    //@Test
    public void testKirimGcm() throws Exception {
        
        Map<String, Object> data = new HashMap<>();
        data.put("action", "test");
        gcm.kirimGcmMessage("csppyYR_VVY:APA91bFbVhJm_0Sz-JunnyTypxckHs4BBtF6jSfjOul41jk0B6RJrwjHk2A5J3bFoqwsXCORFNbWXuEOflkF-pWzqyRtWQMFuvymhMH70lD8ENjAs39Yz1ZzImcel7ciLGKI0HPU7ubC", 
                data);
        
        Thread.sleep(10*1000);
    }
}

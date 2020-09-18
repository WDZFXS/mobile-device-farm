package com.wdzfxs.farm.mobiledevicefarmsvc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class MobileDeviceFarmSvcApplicationTests {

    @Test
    void status_200() {
        RestTemplate restTemplate = new RestTemplate();
        var response = restTemplate.getForEntity("http://localhost:8090/devices", String.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
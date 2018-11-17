package com.wk.esdemo;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class TestDemo {
    private static RestTemplate restTemplate = new RestTemplate();
    private static HttpHeaders headers;
    private static Gson gson = new Gson();
    private static String uri = "http://192.168.91.128:9200";

    static {
        headers = new HttpHeaders();
        headers.set("Authorization", "Basic ZWxhc3RpYzoxMjM0NTY=");
        headers.set("Content-Type", "application/json;charset=UTF-8");
        headers.set("Connection", "Keep-Alive");
    }

    @Test
    public void test1() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(uri + "/_cat/nodes", HttpMethod.GET, entity, String.class);
        System.out.println(response.getBody());
    }

    @Test
    public void test2() throws InterruptedException {
        HashMap<String, String> doc = new HashMap<>();
        Random random = new Random();
        AtomicInteger count = new AtomicInteger();
//        count.addAndGet(5000);
        int threadNum = 20;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        Runnable task = () -> {
            RestTemplate restTemplate = new RestTemplate();
//            restTemplate.
            int id;
            while ((id = count.getAndIncrement()) < 1e6) {
                doc.put("name", "name" + random.nextLong());
                doc.put("sex", "sex" + random.nextLong());
                doc.put("description", "des" + random.nextLong());
                doc.put("title", "title" + random.nextLong());
                doc.put("timestamp", "" + System.currentTimeMillis());
                HttpEntity<String> entity = new HttpEntity<>(gson.toJson(doc), headers);
                restTemplate.exchange(uri + "/test1/person/" + id, HttpMethod.PUT, entity, String.class);
            }
            countDownLatch.countDown();
        };
        for (int i = 0; i < threadNum; i++) {
            new Thread(task).start();
        }
        countDownLatch.await();
    }
}

package com.swiftwayz.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by sydney on 2017/05/15.
 */
@Component
public class RESTServer {

    @Autowired
    private RestTemplate restTemplate;

    public <T> T post(String url, Class<T> tClass, Object request, Object... variables) {
        return restTemplate.postForObject(url, request, tClass, variables);
    }

    public <T> T get(String url, Class<T> tClass, Object... variables){
        try {
            return restTemplate.getForObject(url, tClass, variables);
        } catch (RestClientException ex){
            throw new RuntimeException("Error executing :"+ url, ex);
        }
    }
}

package com.searchmetrics.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Component
public class HttpClient {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * This method will use to call external service and return response to caller
     * @param uri
     * @param responseType
     * @return
     * @throws RestClientException
     */
    public Object send(String uri, Class<?> responseType) throws RestClientException {
        return restTemplate.getForObject(uri, responseType);
    }


}

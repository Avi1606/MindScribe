package com.myproject.journalApp.Services;


import com.myproject.journalApp.api.response.WeatherResponce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    public static final String API = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    public  WeatherResponce getWeather (String city) {
        String fianlApi = API.replace("CITY", city).replace("API_KEY", apiKey);
        ResponseEntity<WeatherResponce> response = restTemplate.exchange(fianlApi, HttpMethod.GET, null, WeatherResponce.class);
        WeatherResponce body = response.getBody();
        return body;

    }
}

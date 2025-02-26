package com.myproject.journalApp.Services;


import com.myproject.journalApp.Constants.Placeholders;
import com.myproject.journalApp.api.response.WeatherResponce;
import com.myproject.journalApp.cache.AppCache;
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

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    RedisService redisService;

    public  WeatherResponce getWeather (String city) {
        WeatherResponce weatherResponce = redisService.get("weather_of_" + city, WeatherResponce.class);
        if (weatherResponce != null) {
            return weatherResponce;
        }else{
            String finalApi = appCache.getAppCache().get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.CITY, city).replace(Placeholders.API_KEY, apiKey);
            ResponseEntity<WeatherResponce> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponce.class);
            WeatherResponce body = response.getBody();
            if (body != null) {
                redisService.set("weather_of_"+city,body,300l);
            }
            return body;
        }

    }
}

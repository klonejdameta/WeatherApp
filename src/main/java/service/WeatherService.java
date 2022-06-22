package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.ResponseBody;
import model.WeatherResponse;

import java.io.IOException;

public class WeatherService {
    public static final String HTTPS_API_OPENWEATHERMAP_ORG_DATA_2_5_WEATHER = "https://api.openweathermap.org/data/2.5/weather";
    private static String FIXED_APPIKEY = "7edda0bea071419a1e1da9c91ac2d4c6";

    public WeatherResponse getLiveWeatherValues(String lat, String lon) {
        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        Request request = new Request.Builder()
                .url(HTTPS_API_OPENWEATHERMAP_ORG_DATA_2_5_WEATHER + "?lat=" + lat + "&lon=" + lon + "&APPID=" + FIXED_APPIKEY)
                .method("GET", null)
                .build();
        ResponseBody responseBody = null;
        try {
            responseBody = client.newCall(request).execute().body();
        } catch (IOException e) {
            System.out.println("Url is wrong or no internet connection");
        }
        try {
            return objectMapper.readValue(responseBody.string(), WeatherResponse.class);
        } catch (IOException | NullPointerException e) {
            System.out.println("Response body is null");
            return new WeatherResponse();
        }
    }
}

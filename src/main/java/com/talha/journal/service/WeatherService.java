package com.talha.journal.service;

import com.talha.journal.api.WeatherResponse;
import com.talha.journal.exceptions.WeatherException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    // don't add new lines in this string
    @Autowired
    private RestTemplate restTemplate; // for hitting https requests like hitting for apis


    @Value("${weatherstack.api.url}")
    private  String apiUrl;

    @Value("${weatherstack.api.key}")
    private  String apiKey ;


    public WeatherResponse getWeather(String city) {
//        String finalAPI = API.replace("CITY", city).replace("API_KEY", apiKey);

        String finalAPI = String.format("%s?access_key=%s&query=%s", apiUrl, apiKey, city);
        HttpStatus status;

        try {
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(
                    finalAPI, HttpMethod.GET, null, WeatherResponse.class);

            // Convert HttpStatusCode to HttpStatus
            status = HttpStatus.valueOf(response.getStatusCode().value());

            // Check for non-success status codes
            if (!status.is2xxSuccessful()) {
                throw new WeatherException(
                        "Failed to fetch weather data. Status: " + status,
                        status
                );
            }
            return response.getBody();
        } catch (HttpClientErrorException ex) {

            status = HttpStatus.valueOf(ex.getStatusCode().value());
            // Handle 4xx errors (e.g., 404, 401)
            throw new WeatherException(
                    "Client error: " + ex.getMessage(), status );
        } catch (HttpServerErrorException ex) {
            // Handle 5xx errors (e.g., 500, 503)
            status = HttpStatus.valueOf(ex.getStatusCode().value());
            throw new WeatherException(
                    "Server error: " + ex.getMessage(), status);
        } catch (Exception ex) {
            // Handle unexpected errors
            throw new WeatherException(
                    "Unexpected error occurred while fetching weather data.",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

}

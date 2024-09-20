package com.example.HttpClientDemo.service;

import com.example.HttpClientDemo.model.Post;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@Service
public class PostService {
    HttpClient httpClient;
    ObjectMapper objectMapper;
    @Value("${external.url}")
    String apiUrl;

    public PostService() {
        httpClient = HttpClient.newHttpClient();
        objectMapper = new ObjectMapper();
    }


    public Optional<String> getAllPosts() {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .GET()
                .build();

        try {
            HttpResponse<String> res = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (res.statusCode() == HttpStatus.OK.value()) {
                return Optional.of(res.body());

            } else {
                return Optional.empty();
            }

        } catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<String> addPost(Post post){
        try {
            String postJson = objectMapper.writeValueAsString(post);
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(postJson))
                    .build();
            HttpResponse<String> res = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (res.statusCode()==HttpStatus.CREATED.value()){

                return Optional.of(res.body());
            }else {
                return Optional.empty();
            }

        } catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<String> updatePost(Long id,Post post){

            try {
                String postJson = objectMapper.writeValueAsString(post);
                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .uri(URI.create(apiUrl + "/"+id))
                        .header("Content-Type", "application/json")
                        .PUT(HttpRequest.BodyPublishers.ofString(postJson))
                        .build();
                HttpResponse<String> res = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                if (res.statusCode()>=200 && res.statusCode() <=300){
                    return Optional.of(res.body());
                }else {
                    return Optional.empty();
                }

            } catch (IOException | InterruptedException e) {
                System.err.println(e.getMessage());
                return Optional.empty();
            }


    }

    public Optional<String> deletePost(Long id){
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl+"/"+id))
                .header("Content-Type","application/json")
                .DELETE()
                .build();
        try {
            HttpResponse<String> res = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (res.statusCode() >= HttpStatus.OK.value() ){
                return Optional.of(res.body());
            }else {
                return Optional.empty();
            }
        } catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }

}

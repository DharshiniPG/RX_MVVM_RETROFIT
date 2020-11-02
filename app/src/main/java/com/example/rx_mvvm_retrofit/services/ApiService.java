package com.example.rx_mvvm_retrofit.services;

import com.example.rx_mvvm_retrofit.model.HeroClass;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    String BASE_URL = "https://simplifiedcoding.net/demos/";

    @GET("marvel")
    Observable<List<HeroClass>> getMoviesList();
    //Call<List<HeroClass>> getMoviesList();
}

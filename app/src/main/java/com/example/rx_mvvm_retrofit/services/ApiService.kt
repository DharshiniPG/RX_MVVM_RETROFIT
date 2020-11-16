package com.example.rx_mvvm_retrofit.services

import com.example.rx_mvvm_retrofit.model.HeroClass
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {
    //Call<List<HeroClass>> getMoviesList();
    @get:GET("marvel")
    val moviesList: Observable<List<HeroClass?>?>

    companion object {
        const val BASE_URL = "https://simplifiedcoding.net/demos/"
    }
}
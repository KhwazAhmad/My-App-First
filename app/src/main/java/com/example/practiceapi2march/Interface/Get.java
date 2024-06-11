package com.example.practiceapi2march.Interface;

import com.example.practiceapi2march.Model.Model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Get {
    @GET("everything?q=tesla&sortBy=publishedAt&apiKey=f83f096c0d7843b18a82dcad82851499")
    Call<Model>getModel();
}

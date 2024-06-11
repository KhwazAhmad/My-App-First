package com.example.practiceapi2march.Interface;

import com.example.practiceapi2march.Model.ModelMemes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface meam {
    @GET("gimme")
    Call<ModelMemes> getMeme();
}

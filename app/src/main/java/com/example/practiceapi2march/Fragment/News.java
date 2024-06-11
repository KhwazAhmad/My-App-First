package com.example.practiceapi2march.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practiceapi2march.Adopter.Adopter;
import com.example.practiceapi2march.Interface.Get;
import com.example.practiceapi2march.Model.Model;
import com.example.practiceapi2march.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class News extends Fragment {
    Adopter adopter;
    ArrayList<Model.Articles> arrayList = new ArrayList<>();
    RecyclerView recyclerView;
    ProgressBar progressBar;

    public News() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        adopter = new Adopter(getContext(), arrayList);
        recyclerView = view.findViewById(R.id.recycle);
        progressBar = view.findViewById(R.id.progessBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Retrofit retrofit = new Retrofit.Builder().baseUrl(" https://newsapi.org/v2/").addConverterFactory(GsonConverterFactory.create()).build();
        Get get = retrofit.create(Get.class);
        get.getModel().enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Model newDAta = response.body();
                        adopter.setDAta(newDAta.getArticles());
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setAdapter(adopter);
                    } 
                }
            }
            @Override
            public void onFailure(Call<Model> call, Throwable t) {
            }
        });
        return view;
    }

}
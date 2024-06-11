package com.example.practiceapi2march;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.practiceapi2march.Interface.meam;
import com.example.practiceapi2march.Model.ModelMemes;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Memes extends Fragment {

    ImageView imageView;
    TextView next, share;
    ModelMemes modelMemes;
    ProgressBar progressBar;
    public Memes() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_memes, container, false);
        imageView = view.findViewById(R.id.image);
        next = view.findViewById(R.id.next);
progressBar=view.findViewById(R.id.progessBar);
        share = view.findViewById(R.id.shear);
        getFragmentManager().popBackStack();
        loadMeme();
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "check out this cool app");
//                intent.putExtra(Intent.EXTRA_TEXT,modelMemes.getUrl());
                startActivity(Intent.createChooser(intent, "shear "));
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMeme();
            }
        });

        return view;
    }

    private void loadMeme() {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://meme-api.com/").
                addConverterFactory(GsonConverterFactory.create())
                .build();
        meam meam = retrofit.create(meam.class);
        meam.getMeme().enqueue(new Callback<ModelMemes>() {
            @Override
            public void onResponse(Call<ModelMemes> call, Response<ModelMemes> response) {
                ModelMemes modelMemes = response.body();
                Picasso.get().load(modelMemes.getUrl()).into(imageView);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ModelMemes> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to load meme", Toast.LENGTH_SHORT).show();
            }
        });
    }

    }
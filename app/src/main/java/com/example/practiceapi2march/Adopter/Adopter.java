package com.example.practiceapi2march.Adopter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practiceapi2march.Model.Model;
import com.example.practiceapi2march.News_Detail;
import com.example.practiceapi2march.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adopter extends RecyclerView.Adapter<Adopter.itemHolder> {
    Context context;
    ArrayList<Model.Articles> arrayList;
    private TextToSpeech tts;
    public Adopter(Context context, ArrayList<Model.Articles> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public Adopter.itemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item, parent, false);
        return new itemHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull Adopter.itemHolder holder, int position) {
        Model.Articles articles = this.arrayList.get(position);
        holder.holsitem(articles);
        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(context, News_Detail.class);
                i.putExtra("title",articles.getTitle());
                i.putExtra("content",articles.getContent());
                i.putExtra("des",articles.getDescription());
                i.putExtra("image",articles.getUrlToImage());
                i.putExtra("Url",articles.getUrl());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        });
        setAnimation(holder.itemView);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

//    public void setDAta(ArrayList<Model.Articles> articles) {
//
//    }

    @SuppressLint("NotifyDataSetChanged")
    public void setDAta(ArrayList<Model.Articles> articles) {
        this.arrayList = articles;
        notifyDataSetChanged();
    }

    public static class itemHolder extends RecyclerView.ViewHolder {
        TextView tittle, description;
        ImageView imageView;

        public itemHolder(@NonNull View itemView) {
            super(itemView);
            tittle = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.about);
            imageView = itemView.findViewById(R.id.image);
        }
        public void holsitem(Model.Articles articles) {
            tittle.setText(articles.getTitle());
            description.setText(articles.getDescription());
            Picasso.get().load(articles.getUrlToImage()).into(imageView);

        }
    }
    private void setAnimation(View viewToAnimate)
    {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
    }
}

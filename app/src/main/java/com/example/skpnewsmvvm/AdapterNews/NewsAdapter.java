package com.example.skpnewsmvvm.AdapterNews;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.skpnewsmvvm.MainActivity;
import com.example.skpnewsmvvm.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Collection;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>  {

    private Context context;
    private ArrayList<News> newsList;
    private ArrayList<News> newsListFilter;

    private int layoutId;

    public NewsAdapter(Context context, ArrayList<News> newsList, int layoutId) {
        this.context = context;
        this.layoutId = layoutId;
        this.newsList = newsList;
        this.newsListFilter = new ArrayList<>(newsList);
    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = newsList.get(position);
        Glide.with(context).load(Uri.parse(news.getImage())).into(holder.imageView);
        holder.titleTextView.setText(news.getTitle());
        holder.descriptionTextView.setText(news.getDesc());
        setClickListener(holder.card, news);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    private void setClickListener(View view, News news) {
        view.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("image", news.getImage());
            bundle.putString("title", news.getTitle());
            bundle.putString("desc", news.getDesc());
            bundle.putString("date", news.getTime_news());
            Navigation.findNavController(v).navigate(R.id.detaillFragment, bundle);
        });
    }



    static class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView descriptionTextView;
        MaterialCardView card;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.cardView);
            imageView = itemView.findViewById(R.id.imageView1);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }
    }
}

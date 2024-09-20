package com.example.skpnewsmvvm.ui.search;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.skpnewsmvvm.AdapterNews.News;
import com.example.skpnewsmvvm.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapterSearch extends RecyclerView.Adapter<NewsAdapterSearch.NewsViewHolder> {
    private Context mContext;
    private List<News> mNewsList;

    public NewsAdapterSearch(Context context, List<News> newsList) {
        mContext = context;
        mNewsList = newsList;
    }

    public void filterList(ArrayList<News> filteredList) {
        mNewsList = filteredList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.all_news_item_recycle, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = mNewsList.get(position);
        holder.bind(news);
        setClickListener(holder.view, news);

    }
    @Override
    public int getItemCount() {
        return mNewsList.size();
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

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        private RoundedImageView imageView;
        private TextView titleTextView;
        private TextView descriptionTextView;
        private CardView view;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.cardView);
            imageView = itemView.findViewById(R.id.imageView1);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }

        public void bind(News news) {
            // Load image using Glide library
            Glide.with(itemView)
                    .load(news.getImage())
                    .placeholder(R.drawable.ic_launcher_background) // Placeholder image
                    .error(R.drawable.ic_launcher_background) // Error image
                    .into(imageView);

            titleTextView.setText(news.getTitle());
            descriptionTextView.setText(news.getDesc());
        }
    }
}

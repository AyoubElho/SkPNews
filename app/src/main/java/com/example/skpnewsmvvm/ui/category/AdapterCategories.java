package com.example.skpnewsmvvm.ui.category;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.skpnewsmvvm.R;

import java.util.ArrayList;

public class AdapterCategories extends RecyclerView.Adapter<AdapterCategories.ViewHolder> {

    private ArrayList<Categories> categoriesList;
    private Context context;

    public AdapterCategories(Context context, ArrayList<Categories> categoriesList) {
        this.categoriesList = categoriesList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categorie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Categories category = categoriesList.get(position);
        holder.textView.setText(category.getText());
        holder.imageView.setColorFilter(R.color.blackImage);
        Glide.with(context).load(category.getImage()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("cat", category.getText());
                Navigation.findNavController(v).navigate(R.id.allByCategoryFragment , bundle);

            }
        });
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView2);
            textView = itemView.findViewById(R.id.textView2);
        }
    }
}

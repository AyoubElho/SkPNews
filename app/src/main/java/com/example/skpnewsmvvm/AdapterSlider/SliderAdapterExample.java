package com.example.skpnewsmvvm.AdapterSlider;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.skpnewsmvvm.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

public class SliderAdapterExample extends
        SliderViewAdapter<SliderAdapterExample.SliderAdapterVH> {

    private Context context;
    private ArrayList<SliderItem> mSliderItems ;

    public SliderAdapterExample(Context context , ArrayList<SliderItem> mSliderItems) {
        this.mSliderItems = mSliderItems;
        this.context = context;
    }
    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

        SliderItem sliderItem = mSliderItems.get(position);

        viewHolder.textViewDescription.setText(sliderItem.getTitle());
        viewHolder.textViewDescription.setTextSize(20);
        viewHolder.textViewDescription.setTextColor(Color.WHITE);
        //or to support all versions use
        Typeface typeface = ResourcesCompat.getFont(context, R.font.questv1);
        viewHolder.textViewDescription.setTypeface(typeface);
        Glide.with(viewHolder.itemView)
                .load(Uri.parse(sliderItem.getImage()))
                .centerCrop()
                .into(viewHolder.imageViewBackground);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("image", sliderItem.getImage());
                bundle.putString("title", sliderItem.getTitle());
                bundle.putString("desc", sliderItem.getDesc());
                bundle.putString("date", sliderItem.getTime_news());
                Navigation.findNavController(v).navigate(R.id.detaillFragment, bundle);


        }
        });
    }

    @Override
    public int getCount() {
        return mSliderItems.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        ImageView imageGifContainer;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }
}
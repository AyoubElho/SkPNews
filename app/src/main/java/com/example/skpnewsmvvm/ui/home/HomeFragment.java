package com.example.skpnewsmvvm.ui.home;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.skpnewsmvvm.AdapterNews.News;
import com.example.skpnewsmvvm.AdapterNews.NewsAdapter;
import com.example.skpnewsmvvm.MainActivity;
import com.example.skpnewsmvvm.R;
import com.example.skpnewsmvvm.AdapterSlider.SliderAdapterExample;
import com.example.skpnewsmvvm.AdapterSlider.SliderItem;
import com.example.skpnewsmvvm.databinding.FragmentHomeBinding;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.data.RtlMode;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private NewsAdapter adapter;
    private ArrayList<SliderItem> mSliderItems;
    private Query database;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (binding == null) {
            binding = FragmentHomeBinding.inflate(inflater, container, false);

        }

        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        homeViewModel.fetchData().observe(getActivity(), new Observer<ArrayList<News>>() {
            @Override
            public void onChanged(ArrayList<News> news) {
                    Collections.reverse(news);
                    adapter = new NewsAdapter(getActivity(), news, R.layout.all_news_item_recycle);
                    binding.recycleAllNews.setAdapter(adapter);
                    binding.recycleAllNews.setLayoutManager(new LinearLayoutManager(getContext()));
                    binding.recycleAllNews.setHasFixedSize(true);
                    binding.recycleAllNews.suppressLayout(true);
                    binding.recycleAllNews.setVisibility(View.VISIBLE);
                    binding.shimmer.setVisibility(View.GONE);

            }
        });


        binding.showAll.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_showAllFragment);
        });

        database = FirebaseDatabase.getInstance().getReference("AllNews").limitToFirst(3);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<SliderItem> sliderImages = new ArrayList<>();
                for (DataSnapshot newsSnapshot : dataSnapshot.getChildren()) {
                    String image = newsSnapshot.child("image").getValue(String.class);
                    String desc = newsSnapshot.child("desc").getValue(String.class);
                    String timeNews = newsSnapshot.child("time_news").getValue(String.class);
                    String title = newsSnapshot.child("title").getValue(String.class);
                    sliderImages.add(new SliderItem(image, desc, timeNews, title));
                }

                updateSliderUI(sliderImages);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Handle database error
            }
        });


        return binding.getRoot();
    }

    private void updateSliderUI(ArrayList<SliderItem> sliderImages) {
        if (!sliderImages.isEmpty()) {
            binding.imageSlider.setVisibility(View.VISIBLE);
            binding.shimmerSlider.hideShimmer();
            Collections.reverse(sliderImages);
            SliderAdapterExample adapter = new SliderAdapterExample(getActivity(), sliderImages);
            binding.imageSlider.setSliderAdapter(adapter);
            binding.imageSlider.setScrollTimeInSec(8);
            binding.imageSlider.setAutoCycle(true);
            binding.imageSlider.startAutoCycle();
        }

    }
}
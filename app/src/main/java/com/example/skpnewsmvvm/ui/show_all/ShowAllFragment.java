package com.example.skpnewsmvvm.ui.show_all;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.skpnewsmvvm.AdapterNews.News;
import com.example.skpnewsmvvm.AdapterNews.NewsAdapter;
import com.example.skpnewsmvvm.R;
import com.example.skpnewsmvvm.databinding.FragmentShowAllBinding;

import java.util.ArrayList;

public class ShowAllFragment extends Fragment {
    private FragmentShowAllBinding binding;
    private NewsAdapter adapter;
    ShowAllViewModel showAllViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showAllViewModel = new ViewModelProvider(this).get(ShowAllViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentShowAllBinding.inflate(inflater, container, false);
        binding.recycleAllNews.setLayoutManager(new LinearLayoutManager(getActivity()));

        showAllViewModel.getNewsShow().observe(getViewLifecycleOwner(), new Observer<ArrayList<News>>() {
            @Override
            public void onChanged(ArrayList<News> newsList) {
                if (!newsList.isEmpty()) {
                    adapter = new NewsAdapter(getActivity(), newsList, R.layout.item_show_all);
                    binding.recycleAllNews.setAdapter(adapter);
                    binding.shimmerShowAll.stopShimmer();
                    binding.shimmerShowAll.setVisibility(View.GONE);
                    binding.recycleAllNews.setVisibility(View.VISIBLE);
                }
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

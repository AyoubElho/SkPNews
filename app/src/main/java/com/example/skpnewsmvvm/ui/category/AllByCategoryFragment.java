package com.example.skpnewsmvvm.ui.category;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.skpnewsmvvm.AdapterNews.News;
import com.example.skpnewsmvvm.AdapterNews.NewsAdapter;
import com.example.skpnewsmvvm.MainActivity;
import com.example.skpnewsmvvm.R;
import com.example.skpnewsmvvm.databinding.FragmentAllByCategoryBinding;
import com.example.skpnewsmvvm.databinding.FragmentShowAllBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class AllByCategoryFragment extends Fragment {
    FragmentAllByCategoryBinding binding;
    private Query database;
    private NewsAdapter adapter;
    ArrayList<News> newsList;
    private CategoryViewModel categoryViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAllByCategoryBinding.inflate(inflater, container, false);
        categoryViewModel = new ViewModelProvider(requireActivity()).get(CategoryViewModel.class);

        binding.recycleByCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        newsList = new ArrayList<>();


        Bundle bundle = getArguments();
        if (bundle != null) {

            categoryViewModel.setCategory(bundle.getString("cat"));

            categoryViewModel.getCategory().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String category) {
                    AppCompatActivity activity = (AppCompatActivity) getActivity();
                    if (activity != null && activity.getSupportActionBar() != null) {
                        activity.getSupportActionBar().setTitle(category);
                    }
                }
            });


            database = FirebaseDatabase.getInstance().getReference("AllNews");
            database.orderByChild("category").equalTo(bundle.getString("cat")).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    newsList.clear();
                    for (DataSnapshot newsSnapshot : dataSnapshot.getChildren()) {
                        News news = newsSnapshot.getValue(News.class);
                        if (news != null) {
                            newsList.add(news);
                        }
                    }
                    Collections.reverse(newsList);
                    adapter = new NewsAdapter(getActivity(), newsList, R.layout.item_show_all);
                    binding.recycleByCategory.setAdapter(adapter);

                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Handle database error
                }
            });
        }


        return binding.getRoot();
    }
}
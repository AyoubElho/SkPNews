package com.example.skpnewsmvvm.ui.search;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.skpnewsmvvm.AdapterNews.News;
import com.example.skpnewsmvvm.R;
import com.example.skpnewsmvvm.databinding.FragmentSearchBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    private FragmentSearchBinding binding;
    private List<News> newsList;
    private NewsAdapterSearch adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SearchViewModel dashboardViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        setHasOptionsMenu(true);
        newsList = new ArrayList<>();
        binding.recycleSearch.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recycleSearch.setHasFixedSize(true);


        dashboardViewModel.getNewsList().observe(getViewLifecycleOwner(), new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> news) {
                if (!news.isEmpty()) {
                    newsList = news;
                    adapter = new NewsAdapterSearch(getActivity(), news);
                    binding.recycleSearch.setAdapter(adapter);

                }
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);

        // Get the search item from the menu
        MenuItem item = menu.findItem(R.id.search);
        // Extract the icon of the search item
        Drawable icon = item.getIcon();
        if (isNight()) {
            icon.setTint(ContextCompat.getColor(requireContext(), android.R.color.white));

        }
        item.setIcon(icon);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("بحث");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String userInput = newText.toLowerCase();
                ArrayList<News> filteredList = new ArrayList<>();

                if (!newText.isEmpty()) {
                    for (News news : newsList) {
                        if (news.getTitle().toLowerCase().contains(userInput)) {
                            filteredList.add(news);
                        }
                    }
                }

                updateEmptyStateVisibility(filteredList.size(), newText);

                adapter.filterList(filteredList);
                adapter.notifyDataSetChanged();

                return true;
            }
        });
    }

    private boolean isNight() {
        int config = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        boolean isLight = Configuration.UI_MODE_NIGHT_YES == config;
        return isLight;
    }


    private void updateEmptyStateVisibility(int listSize, String text) {
        if (binding != null && binding.searchForData != null && binding.empty != null) {
            if (listSize == 0 && !text.isEmpty()) {
                if (binding.searchForData.emptyLayout != null) {
                    binding.searchForData.emptyLayout.setVisibility(View.GONE);
                }
                if (binding.empty.emptyLayout != null) {
                    binding.empty.emptyStateTitle.setText("لا توجد نتائج ل \"" + text + "\"");
                    binding.empty.emptyLayout.setVisibility(View.VISIBLE);
                }
            } else if (listSize == 0 && text.isEmpty()) {
                // If there are no search results and the search query is empty
                if (binding.searchForData.emptyLayout != null) {
                    binding.searchForData.emptyLayout.setVisibility(View.VISIBLE);
                }
                if (binding.empty.emptyLayout != null) {
                    binding.empty.emptyLayout.setVisibility(View.GONE);
                }
            } else {
                // If there are search results, hide both empty states
                if (binding.searchForData.emptyLayout != null) {
                    binding.searchForData.emptyLayout.setVisibility(View.GONE);
                }
                if (binding.empty.emptyLayout != null) {
                    binding.empty.emptyLayout.setVisibility(View.GONE);
                }
            }
        }
    }


}

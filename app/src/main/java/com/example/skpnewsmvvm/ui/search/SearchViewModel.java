package com.example.skpnewsmvvm.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.skpnewsmvvm.AdapterNews.News;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchViewModel extends ViewModel {

    private MutableLiveData<List<News>> newsListLiveData;
    private List<News> newsList;
    private DatabaseReference databaseReference;

    public SearchViewModel() {
        newsListLiveData = new MutableLiveData<>();
        newsList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("AllNews");
        fetchDataFromFirebase();
    }

    private void fetchDataFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                newsList.clear();
                for (DataSnapshot newsSnapshot : dataSnapshot.getChildren()) {
                    News news = newsSnapshot.getValue(News.class);
                    if (news != null) {
                        newsList.add(news);
                    }
                }
                newsListLiveData.setValue(newsList);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Handle database error
            }
        });
    }

    public LiveData<List<News>> getNewsList() {
        return newsListLiveData;
    }
}

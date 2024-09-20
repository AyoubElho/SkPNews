package com.example.skpnewsmvvm.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.skpnewsmvvm.AdapterNews.News;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class Repo {
    private Query database;

    public LiveData<ArrayList<News>> getNews() {
        MutableLiveData<ArrayList<News>> mutableLiveData = new MutableLiveData<>();

        database = FirebaseDatabase.getInstance().getReference("AllNews").limitToFirst(4);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<News> newsList = new ArrayList<>();
                for (DataSnapshot newsSnapshot : dataSnapshot.getChildren()) {
                    News news = newsSnapshot.getValue(News.class);
                    if (news != null) {
                        newsList.add(news);
                    }
                }
                mutableLiveData.setValue(newsList);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Handle database error
            }
        });
        return mutableLiveData;
    }
}

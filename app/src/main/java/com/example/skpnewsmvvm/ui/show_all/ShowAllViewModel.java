package com.example.skpnewsmvvm.ui.show_all;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.skpnewsmvvm.AdapterNews.News;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class ShowAllViewModel extends ViewModel {

    private MutableLiveData<ArrayList<News>> mutableLiveData = new MutableLiveData<>();
    private DatabaseReference database;

    public ShowAllViewModel() {
        database = FirebaseDatabase.getInstance().getReference("AllNews");
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
                Collections.reverse(newsList);
                mutableLiveData.setValue(newsList);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Handle database error
            }
        });
    }

    public LiveData<ArrayList<News>> getNewsShow() {
        return mutableLiveData;
    }
}
